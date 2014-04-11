package core.pages;

import com.codeborne.selenide.ElementsCollection;
import core.base.PageBase;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class Header extends PageBase{
    private static final By LOGOUT_BUTTON = get("header.logout");
    private static final By EDIT_PROFILE_BUTTON = get("header.editProfile");
    private static final By SUBTABS = get("header.subtabs");
    private static final By SETTINGS = By.cssSelector("#site_header a[title=Настройки]");
    private static final By SETTINGS_OPTIONS = By.cssSelector("#site_header a[title=Настройки] + ul > li");

    public static final By MONITOR_TAB = get("header.mainMenu.monitor");
    public static final By EVENTS_TAB = get("header.mainMenu.eventHistory");
    public static final By MANAGE_TAB = get("header.mainMenu.manage");
    public static final By LOG_TAB = get("header.mainMenu.systemLog");
    public static final By USER = By.cssSelector("#site_header a[title=Пользователь]");

    public static void logout() {
        $(USER).click();
        $(LOGOUT_BUTTON).shouldBe(visible);
        $(LOGOUT_BUTTON).click();
    }

    public static void clickEditProfile() {
        $(USER).click();
        $(EDIT_PROFILE_BUTTON).shouldBe(visible);
        $(EDIT_PROFILE_BUTTON).click();
    }

    public static void clickSettings() {
        $(SETTINGS).click();
        $(SETTINGS_OPTIONS).shouldBe(visible);
    }

    public static void goTo(By tab) {
        $(tab).click();
    }

    public static ElementsCollection getSettingOptions() {
        return $$(SETTINGS_OPTIONS);
    }

    public static void checkExpectedElements () {
        checkExpectedElements(Arrays.asList(MONITOR_TAB, EVENTS_TAB, MANAGE_TAB, LOG_TAB, USER, SETTINGS));
    }

    public static void checkSubtabs (final List<String> subtabs) {
        for (String name : subtabs) {
            $(SUBTABS).$(byText(name)).shouldBe(visible);
        }
    }
}
