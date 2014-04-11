package core.base;

import au.com.bytecode.opencsv.CSVWriter;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import core.helpers.PerformanceTiming;
import core.helpers.TrafficListener;
import core.helpers.Waiter;
import core.pages.LoginPage;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.util.ThreadUtils;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class PerformanceTestBase {
    private static final Date CURRENT_DATE = new Date();
    private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final DateFormat FOLDER_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    private static final String CURRENT_FOLDER_DATE_STRING = FOLDER_DATE_FORMAT.format(CURRENT_DATE);
    private static final String CURRENT_DATE_STRING = SIMPLE_DATE_FORMAT.format(CURRENT_DATE);
    private static final String UPLOAD_URL = "http://192.168.0.205:5000/results/upload";
    private static final String INFOTECS_USER_VARIABLE = "INFOTECS_HOME";
    private String BASE_REPORT_DIR = "./build/reports/performance/";

    private ProxyServer proxy;
    private String testCaseName;
    private PerformanceTiming timing;
    private Har har;
    private TrafficListener trafficListener;

    @BeforeMethod (groups = {"performance"})
    public void setup () throws Exception {
        String home = System.getenv().get(INFOTECS_USER_VARIABLE);
        if(home!= null && Files.exists(Paths.get(home))){
            BASE_REPORT_DIR = Paths.get(home).resolve(BASE_REPORT_DIR.replace("./","")).toString();
        }

        proxy = new ProxyServer(4444);
        proxy.start();
        trafficListener = new TrafficListener();
        proxy.addRequestInterceptor(trafficListener);
        proxy.setRequestTimeout(30000);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, proxy.seleniumProxy());
        WebDriver driver = new ChromeDriver(caps);
        driver.manage().window().maximize();

        Configuration.timeout = 10000;
        Configuration.baseUrl = "http://192.168.5.128";//.128 or .90
        WebDriverRunner.setWebDriver(driver);

        //Starting application
        open(Configuration.baseUrl);
        LoginPage.shouldAppear();
        LoginPage.login();
    }

    @AfterMethod(groups = {"performance"})
    public void teardown (ITestResult result) throws Exception {
        ITestNGMethod method = result.getMethod();
        method.setTimeOut(timing.getFullLoadTime());
        method.setId(testCaseName);

        String methodName = method.getMethodName();
        String name = methodName + "_" + CURRENT_DATE_STRING;
        saveHar(name);

        proxy.stop();
    }

    @AfterTest(groups = {"performance"})
    public void after (ITestContext result) throws Exception {
        Path dirPath = Paths.get(BASE_REPORT_DIR).resolve("reports").toAbsolutePath();
        Files.createDirectories(dirPath);
        ITestNGMethod[] testMethods = result.getAllTestMethods();

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"№", "Наименование теста", "Время загрузки в мс"});
        for(int i = 0; i < testMethods.length; i++){
            data.add(new String[]{i + 1 + ".", testMethods[i].getId(), String.valueOf(testMethods[i].getTimeOut())});
        }

        try(FileWriter writer = new FileWriter(dirPath.resolve("Report_" + CURRENT_FOLDER_DATE_STRING + ".csv").toString());
            CSVWriter CSVwriter = new CSVWriter(writer, '\t', CSVWriter.NO_QUOTE_CHARACTER)) {
            CSVwriter.writeAll(data);
        }
    }


    //This method is called from each test method to start recording statistics
    //the "testName" parameter will be the name of the test in HAR-Storage
    public void startHarRecording(String testName) {
        waitForNetworkTrafficToStop(5000, 60000);

        Waiter.waitForPollingToStart(trafficListener, 1000);

        testCaseName = testName;

        proxy.newHar(testCaseName);
    }

    //This method is called from each test after the actions to be recorded are finished
    public void finishHarRecording() throws Exception {
        waitForNetworkTrafficToStop(5000, 60000);

        har = proxy.getHar();

        timing = new PerformanceTiming(har);

        postToHarStorage(har);
    }

    public void saveHar(String name) throws IOException {

        Path dirPath = Paths.get(BASE_REPORT_DIR).resolve("har/" + CURRENT_FOLDER_DATE_STRING).toAbsolutePath();

        Files.createDirectories(dirPath);

        har.writeTo(dirPath.resolve(name + ".har").toFile());
    }

    public String postToHarStorage(Har har) throws Exception {
        URL url = new URL(UPLOAD_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Automated", "true");

        try(StringWriter dataWriter = new StringWriter();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())){
            har.writeTo(dataWriter);
            wr.write("file=" + URLEncoder.encode(dataWriter.toString(), "utf-8"));
        }

        String response = connection.getResponseMessage();

        connection.disconnect();

        if(!response.contains("OK")){
            throw new Exception("Failed to post to Harstorage: " + response);
        }

        return response;
    }

    public void waitForNetworkTrafficToStop(final long quietPeriodInMs, long timeoutInMs) {
        long start = System.currentTimeMillis();
        boolean result = ThreadUtils.waitFor(new ThreadUtils.WaitCondition() {
            @Override
            public boolean checkCondition(long elapsedTimeInMs) {
                Date lastCompleted = null;
                Har har = proxy.getHar();
                if(har == null || har.getLog() == null) {
                    return true;
                }

                for(HarEntry entry : har.getLog().getEntries()) {
                    if (entry.getRequest().getUrl().contains("ReverseAjax")) {
                        continue;
                    }
                    // if there is an active request, just stop looking
                    if(entry.getResponse().getStatus() < 0) {
                        return false;
                    }

                    Date end = new Date(entry.getStartedDateTime().getTime() + entry.getTime());
                    if(lastCompleted == null) {
                        lastCompleted = end;
                    } else if(end.after(lastCompleted)) {
                        lastCompleted = end;
                    }
                }

                return lastCompleted != null && System.currentTimeMillis() - lastCompleted.getTime() >= quietPeriodInMs;
            }
        }, TimeUnit.MILLISECONDS, timeoutInMs);
        long end = System.currentTimeMillis();
        long time = (end - start);

        if (!result) {
            throw new RuntimeException("Timed out after " + timeoutInMs + " ms while waiting for network traffic to stop");
        }
    }
}
