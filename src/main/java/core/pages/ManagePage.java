package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;
import static core.helpers.Waiter.waitForProcessing;

public class ManagePage extends PageBase
{
    private static final String TITLE = "Настройки узлов";

    private static final By SMS_SETTINGS = get("manageTab.smsButton");
    private static final By MAP_BUTTON = get("manageTab.mapButton");
    private static final By SMTP_BUTTON = get("manageTab.smtpButton");
    private static final By USERS_BUTTON = get("manageTab.usersButton");
    private static final By RULES_BUTTON = get("manageTab.rulesButton");
    private static final By NOTIFICATIONS_BUTTON = get("manageTab.notificationsButton");
    private static final By TEMPLATES_BUTTON = get("manageTab.templatesButton");
    private static final By CASCADE_BUTTON = get("manageTab.cascadeButton");
    private static final By PROXY_BUTTON = get("manageTab.proxyButton");

    public static void goToMapTab() {
        $(MAP_BUTTON).click();
    }

    public static void goToCascadeTab() {
        $(CASCADE_BUTTON).click();
    }

    public static void goToProxyTab() {
        $(PROXY_BUTTON).click();
    }

    public static void goToTemplatesTab() {
        $(TEMPLATES_BUTTON).click();
    }

    public static void goToNotificationsTab() {
        $(NOTIFICATIONS_BUTTON).click();
        waitForProcessing();
    }

    public static void goToSMTPTab() {
        $(SMTP_BUTTON).click();
        waitForProcessing();
    }

    public static void goToSMSTab() {
        $(SMS_SETTINGS).click();
        waitForProcessing();
    }

    public static void goToUsersTab() {
        $(USERS_BUTTON).click();
        waitForProcessing();
    }

    public static void goToRulesTab() {
        $(RULES_BUTTON).click();
        waitForProcessing();
    }

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }
}
