package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.data.CommonDataProvider;
import core.pages.LoginPage;
import core.pages.MonitorHostsPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class LoginTest extends TestBase {
    private static final String ERROR_MSG = "Неверный пароль или имя пользователя";

    @Test(groups = "fast", dataProvider = "loginData", dataProviderClass = CommonDataProvider.class)
    public void loginTest(String user, String pass, String isValid) {
        LoginPage.login(user, pass);
        checkLoginResult(isValid);
    }

    @Test(groups = "fast")
    public void UiElementsTest() {
        LoginPage.checkExpectedElements();
    }

    private void checkLoginResult(String isValid){
        if(Boolean.parseBoolean(isValid)){
            MonitorHostsPage.shouldAppear();
        }
        else {
            $(LoginPage.ERROR_MESSAGE).shouldBe(visible).shouldHave(text(ERROR_MSG));
        }
    }
}
