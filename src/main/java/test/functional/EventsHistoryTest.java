package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.helpers.Actions;
import core.base.TestBase;
import core.pages.EventsHistoryPage;
import core.pages.Header;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static core.pages.Header.EVENTS_TAB;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class EventsHistoryTest extends TestBase {

    @BeforeMethod
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void searchFiltersUITest (){
        EventsHistoryPage.checkExpectedElements();
        EventsHistoryPage.checkHideAndUnWrap();
    }

    @Test(groups = "fast")
    public void rulesFilterUITest ()  {
        EventsHistoryPage.checkPresenceOfRules();
    }

    @Test(groups = "fast")
    public void severityLevelsUITest() {
        EventsHistoryPage.checkPresenceOfSeverity();
    }
}
