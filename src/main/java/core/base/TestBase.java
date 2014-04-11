package core.base;

import com.codeborne.selenide.Configuration;
import core.pages.LoginPage;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeMethod(alwaysRun = true)
    public void setup() throws MalformedURLException {
        Configuration.timeout = 10000;
        Configuration.baseUrl = "http://192.168.5.128";
        //Configuration.browser = "chrome";
        open(Configuration.baseUrl);
        LoginPage.shouldAppear();
    }
}
