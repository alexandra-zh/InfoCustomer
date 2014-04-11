package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.helpers.Actions;
import core.base.TestBase;
import core.dialogs.GroupDialog;
import core.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.exist;
import static core.pages.Header.MANAGE_TAB;

@Test
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class ManagementTest extends TestBase {

    private static final String GROUP_NAME = "MyNewGroup";
    private static final int GROUP_SIZE = 5;

    @BeforeMethod
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.shouldAppear();
    }

    @Test(groups = "slow")
    public void createNodeGroupTest() {
        ManageHostsPage.createGroup(GROUP_NAME, GROUP_SIZE);
        ManageHostsPage.getGroup(GROUP_NAME).should(exist);
        ManageHostsPage.getGroup(GROUP_NAME).click();
        GroupDialog.shouldAppear();
        GroupDialog.getGroupNodes().shouldHaveSize(GROUP_SIZE);
        GroupDialog.delete();
        ManageHostsPage.getGroup(GROUP_NAME).shouldNot(exist);
    }

    @Test(groups = "fast")
    public void hostsUIElementsTest () {
        ManageHostsPage.checkExpectedElements();
    }

    @Test(groups = "fast")
    public void smsUIElementsTest(){
        ManagePage.goToSMSTab();
        ManageSMSPage.shouldAppear();
        ManageSMSPage.checkExpectedElements();
    }
}
