package test.setup;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.helpers.Actions;
import core.pages.Header;
import core.pages.ManagePage;
import core.pages.ManageSMTPPage;
import core.pages.MonitorHostsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static core.pages.Header.MANAGE_TAB;

@Test(groups = "setup")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class SetupSMTPTest extends TestBase {
	@BeforeMethod
	@Override
	public void setup() throws MalformedURLException {
		super.setup();
		Actions.login();
		MonitorHostsPage.shouldAppear();
		Header.goTo(MANAGE_TAB);
		ManagePage.shouldAppear();
		ManagePage.goToSMTPTab();
		ManageSMTPPage.shouldAppear();
	}
	@Test(groups = "setup")
	public void SetupSMTPTest() {
	/*	host 192.168.0.140  (Infotex-MSERVER.BY-MINSK.CSI.local)
		port 2222
		stresstest1@infotex.test
		пароль: 12345678

		stresstest2@infotex.test
		пароль: 12345678

		емэйл для настроек smtp
		второй у пользователя прописывается
*/

	}


}
