package test.setup;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Test(groups = "setup")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class SetupSMSTest extends TestBase {
}
