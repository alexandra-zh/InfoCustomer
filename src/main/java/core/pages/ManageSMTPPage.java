package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.helpers.Actions;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class ManageSMTPPage extends PageBase {

    private static final String TITLE = "Настройки SMTP";

    private static final By SERVER_ADDRESS = get("manageTab.smtp.serverAddress");
    private static final By PORT = get("manageTab.smtp.port");
    private static final By EMAIL = get("manageTab.smtp.email");
    private static final By SEND_BUTTON = get("manageTab.smtp.testButton");
    private static final By SAVE_BUTTON = get("manageTab.smtp.saveButton");
    private static final By CANCEL_BUTTON = get("manageTab.smtp.cancelButton");

    private static final By ACCOUNT_CHECKBOX = get("manageTab.smtp.accountCheckBox");
    private static final By LOGIN = get("manageTab.smtp.inputLogin");
    private static final By PASSWORD = get("manageTab.smtp.inputPassword");

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(SERVER_ADDRESS, PORT, EMAIL, SEND_BUTTON, SAVE_BUTTON, CANCEL_BUTTON, ACCOUNT_CHECKBOX, LOGIN, PASSWORD));
    }

    public static SelenideElement getCheckBox() {
        return $(ACCOUNT_CHECKBOX);
    }

    public static void checkLogin() {
        $(LOGIN).shouldBe(disabled);
    }

    public static void checkPassword() {
        $(PASSWORD).shouldBe(disabled);
    }

    public static void setLogin(String newLogin) {
        $(LOGIN).val(newLogin);
        $(LOGIN).shouldHave(value(newLogin));
    }

    public static void setPassword(String newPassword) {
        $(PASSWORD).val(newPassword);
        $(PASSWORD).shouldHave(value(newPassword));
    }

    public static void shouldAppear () {
        shouldAppear(TITLE);
    }

    public static void selectAccount(boolean b) {
        if (b)
            Actions.check($(ACCOUNT_CHECKBOX));
        else
            Actions.uncheck($(ACCOUNT_CHECKBOX));

    }

    public static void saveSettings() {
        $(SAVE_BUTTON).click();
    }
}
