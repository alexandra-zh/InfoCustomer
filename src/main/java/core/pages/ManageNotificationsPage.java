package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class ManageNotificationsPage extends PageBase {

    private static final By SAVE_BUTTON = get("manageTab.notifications.saveButton");
    private static final By CANCEL_BUTTON = get("manageTab.notifications.cancelButton");

    public static void saveNotifications() {
        $(SAVE_BUTTON).click();
    }

    public static void cancelNotifications() {
        $(CANCEL_BUTTON).click();
    }
}
