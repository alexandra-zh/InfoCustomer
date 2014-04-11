package core.pages;

import core.base.PageBase;
import core.helpers.Actions;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class Footer extends PageBase {
    private static final String REPORT_PARAMS_FILE_LOCATION = "/reportParams";
    private static final String HELP = "Справка";
    private static final String ABOUT = "О программе";

    private static final By CONTAINER = get("footer");
    private static final By REPORT = get("footer.report");
    private static final By BLUE_STRIP = get("footer.blueStrip");
    private static final By ALARM = get("footer.alarm");
    private static final By RAW_DATA_EXPORT = get("footer.rawdata");
    private static final By MOVEMENTS_EXPORT = get("footer.movements");
    private static final By REPORT_PARAMS_BUTTON = By.cssSelector("a[title='Показать']");
    public static final By REPORT_PARAMS_CONTAINER = get("footer.params.conainer");

    public static final By MUTE = get("footer.mute");

    public static void checkExpectedElements () {
        checkExpectedElements(Arrays.asList(REPORT,MUTE,ALARM,RAW_DATA_EXPORT,MOVEMENTS_EXPORT));
        $(BLUE_STRIP).$(byText(HELP)).shouldBe(visible);
        $(BLUE_STRIP).$(byText(ABOUT)).shouldBe(visible);
    }

    public static void help() {
        $(BLUE_STRIP).$(byText(HELP)).click();
    }

    public static void about() {
        $(BLUE_STRIP).$(byText(ABOUT)).click();
    }

    public static void mute() {
        $(MUTE).click();
    }

    public static void openNotifications() {
        $(ALARM).click();
    }

    public static void showReportParams() {
        $(CONTAINER).$(REPORT_PARAMS_BUTTON).click();
        $(REPORT_PARAMS_CONTAINER).shouldBe(visible);
    }

    public static void checkReportParams() {
        List<String> headerNames = Actions.getStringsFromFile(REPORT_PARAMS_FILE_LOCATION);
        List<String> headerValues = Arrays.asList($(REPORT_PARAMS_CONTAINER).$$(("label")).getTexts());
        Assert.assertEquals(headerNames, headerValues);
    }
}
