package test.setup;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.helpers.Actions;
import core.pages.ManageMapPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.open;


@Test(groups = "setup")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class SetupMapTest extends TestBase {

	@BeforeMethod
	@Override
	public void setup() throws MalformedURLException {
		super.setup();
		Actions.login();
		open("/mapAdmin.action"); //todo: replace to user action
		ManageMapPage.shouldAppear();
	}

	@Test(groups = "setup")
	public void SetupGroup6000MapTest() {
		ManageMapPage.putNodesOnMap(ManageMapPage.getGroup("Group6000"));
    }

	@Test(groups = "setup")
	public void SetupGroup3000MapTest() {
		ManageMapPage.zoomIn();
		ManageMapPage.putNodesOnMap(ManageMapPage.getGroup("Group3000"));
	}

	@Test(groups = "setup")
	public void SetupGroup3000x2MapTest() {
		ManageMapPage.zoomIn();
		ManageMapPage.putNodesOnMap(ManageMapPage.getGroup("Group3000x2"));
	}

	@Test(groups = "setup")
	public void SetupGroup2980MapTest() {
		ManageMapPage.zoomIn();
		ManageMapPage.putNodesOnMap(ManageMapPage.getGroup("Group2980"));
	}

	@Test(groups = "setup")
	public void SetupGroup20MapTest() {
		for (int i = 1; i <= 20; i = i + 1) {
			ManageMapPage.putNodesOnMap(ManageMapPage.getNode(i));
		}
	}
}

