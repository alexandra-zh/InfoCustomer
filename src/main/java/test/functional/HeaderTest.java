package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.helpers.Actions;
import core.helpers.Creds;
import core.helpers.Waiter;
import core.base.TestBase;
import core.dialogs.EditProfileDialog;
import core.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static core.pages.Header.EVENTS_TAB;
import static core.pages.Header.MANAGE_TAB;
import static core.pages.Header.LOG_TAB;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class HeaderTest extends TestBase{

    @Override
    @BeforeMethod
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void logoutTest() {
        $(Header.USER).shouldHave(text(Creds.get("admin.Andrew")[0]));
        Header.logout();
        LoginPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void editProfileTest() {
        Header.clickEditProfile();
        EditProfileDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void settingsTest() {
        Header.clickSettings();
        Header.getSettingOptions().shouldHaveSize(3);
    }

    @Test(groups = "fast")
    public void UiElementsTest() {
        Header.checkExpectedElements();
    }

    @Test(groups = "fast")
    public void mainMenuTabsTest() {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.shouldAppear();
        Header.goTo(LOG_TAB);
        Waiter.waitForPageTitle("Журнал"); //todo: change according pattern above
    }
}
