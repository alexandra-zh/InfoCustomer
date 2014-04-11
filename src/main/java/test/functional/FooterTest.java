package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.dialogs.AboutDialog;
import core.dialogs.NotificationListDialog;
import core.helpers.Actions;
import core.pages.Footer;
import core.pages.HelpPage;
import core.pages.MonitorHostsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class FooterTest extends TestBase {
    private static final String MUTE_OFF_TITLE = "Звук отключен";
    private static final String MUTE_ON_TITLE = "Звук включен";

    @Override
    @BeforeMethod()
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void UiElementsTest() {
        Footer.checkExpectedElements();
    }

    @Test(groups = "fast")
    public void helpTest() {
        Footer.help();
        Actions.switchToLatestTab();
        HelpPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void aboutTest() {
        Footer.about();
        AboutDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void muteTest() {
        $(Footer.MUTE).shouldHave(attribute("data-status", "on"))
                .shouldHave(attribute("title", MUTE_ON_TITLE));
        Footer.mute();
        $(Footer.MUTE).shouldHave(attribute("data-status", "off"))
                .shouldHave(attribute("title", MUTE_OFF_TITLE));
    }

    @Test(groups = "fast")
    public void notificationsTest() {
        Footer.openNotifications();
        NotificationListDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void reportParamsTest() {
        Footer.showReportParams();
        Footer.checkReportParams();
    }
}
