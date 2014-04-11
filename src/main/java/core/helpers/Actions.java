package core.helpers;

import com.codeborne.selenide.SelenideElement;
import core.pages.LoginPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.helpers.Waiter.handlesCountMoreThan;
import static core.helpers.Waiter.waitFor;

public class Actions {

    public static void login(){
        String userType = System.getProperty("creds");
        if(userType == null) {
            LoginPage.login();
        }
        else {
            LoginPage.login(userType);
        }
    }

    public static List<String> getStringsFromFile(String file) {
        List<String> info = new ArrayList<>();
        try(InputStream configStream = Actions.class.getResourceAsStream(file);
            InputStreamReader isr = new InputStreamReader(configStream, "UTF-8");
            BufferedReader configReader = new BufferedReader(isr)){
            for (String line; (line = configReader.readLine()) != null; ) {
                info.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return info;
    }

    public static void switchToLatestTab(){
        for(String winHandle : getWebDriver().getWindowHandles()){
            switchTo().window(winHandle);
        }
    }

    public static void check(SelenideElement elem){
        if(!$(elem).isSelected()){
            $(elem).click();
        }
        $(elem).shouldBe(selected);
    }

    public static void uncheck(SelenideElement elem){
        if($(elem).isSelected()){
            $(elem).click();
        }
        $(elem).shouldNotBe(selected);
    }

    public static void switchToTab(final int tabNum){
        waitFor(handlesCountMoreThan(tabNum));
        Set<String> winHandles = getWebDriver().getWindowHandles();
        switchTo().window((String)winHandles.toArray()[tabNum]);
    }

    public static void clearCookies(){
        getWebDriver().manage().deleteAllCookies();
    }
}
