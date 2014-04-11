package test.setup;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.data.CommonDataProvider;
import core.helpers.Actions;
import core.pages.Header;
import core.pages.ManageHostsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static core.pages.Header.MANAGE_TAB;

@Test(groups = "setup")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class SetupGroupsTest extends TestBase {
	@BeforeMethod
	@Override
	public void setup() throws MalformedURLException {
		super.setup();
		Actions.login();
		Header.goTo(MANAGE_TAB);
		ManageHostsPage.shouldAppear();
	}

	@Test(groups = "fast", dataProvider = "performanceGroupsData", dataProviderClass = CommonDataProvider.class)
	public void CreateGroupsTest(String groupName, String nodesCount) {
		ManageHostsPage.createGroup(groupName, Integer.parseInt(nodesCount));
	}

	@Test(groups = "setup")
	public void CreateGroup6000Test() {
		ManageHostsPage.createGroup("Group6000", 6000);
	}

	@Test(groups = "setup")
	public void CreateGroup5000Test() {
		ManageHostsPage.createGroup("Group5000", 5000);
	}

	@Test(groups = "setup")
	public void CreateGroup3000Test() {
		ManageHostsPage.createGroup("Group3000", 3000);
	}

	@Test(groups = "setup")
	public void CreateGroup3000x2Test() {
		ManageHostsPage.createGroup("Group3000x2", 3000);
	}

	@Test(groups = "setup")
	public void CreateGroup2980Test() {
		ManageHostsPage.createGroup("Group2980", 2980);
	}

	@Test(groups = "setup")
	public void CreateGroup20Test() {
		ManageHostsPage.createGroup("Group20", 20);
	}
}
