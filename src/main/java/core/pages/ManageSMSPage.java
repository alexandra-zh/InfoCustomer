package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class ManageSMSPage extends PageBase {

    private static final String TITLE = "Настройки SMS";

    private static final By SERVER_ADDRESS = get("manageTab.sms.serverAddress");
    private static final By PORT = get("manageTab.sms.port");
    private static final By USER_FIELD = get("manageTab.sms.userField");
    private static final By PASS_FIELD = get("manageTab.sms.passwordField");
    private static final By SAVE_BUTTON = get("manageTab.sms.saveButton");


    public static void checkExpectedElements () {
        checkExpectedElements(Arrays.asList(SERVER_ADDRESS,PORT,USER_FIELD,PASS_FIELD,SAVE_BUTTON));
    }

    public static void shouldAppear () {
        shouldAppear(TITLE);
    }

    public static void saveSettings() {
        $(SAVE_BUTTON).click();
    }
}
