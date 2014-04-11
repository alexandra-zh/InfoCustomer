package core.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.helpers.Locators.get;

public class Waiter {
    private static final int DEFAULT_TIME_OUT = 10;

    public static void waitFor(ExpectedCondition condition){
        getWaiter().until(condition);
    }

    public static ExpectedCondition handlesCountMoreThan(final int count){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return getWebDriver().getWindowHandles().size()> count;
            }
        };
    }

    public static void waitForPageTitle(String title){
        getWaiter().until(ExpectedConditions.titleIs(title));
    }

    public static void waitForProcessing(){
        try {
            getWaiter().until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return $(get("processingDialog")).isDisplayed();
                }
            });
            $(get("processingDialog")).waitUntil(disappear, 120000);
        }
        catch (TimeoutException ex){
            //do nothing
        }
    }

    public static void waitForPollingToStart(final TrafficListener poller, final long millisec) {
        getWaiter(60000).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return poller.isPolling(millisec);
            }
        });
    }

    public static void waitForQuitePeriodOf(final TrafficListener poller, final long millisec) {
        getWaiter(60000).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return poller.isQuiteFor(millisec);
            }
        });
    }

    public static void waitForJquery(){
        getWaiter().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }

    public static void waitForPageToLoad(){
        getWaiter().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return ((String) js.executeScript("return document.readyState")).equalsIgnoreCase("complete");
            }
        });
    }

    private static WebDriverWait getWaiter(){
        return new WebDriverWait(getWebDriver(), DEFAULT_TIME_OUT);
    }

    private static WebDriverWait getWaiter(final int timeout){
        return new WebDriverWait(getWebDriver(), timeout);
    }
}