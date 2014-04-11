package core.pages;

import core.base.PageBase;
import core.helpers.Creds;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;
import static core.helpers.Waiter.waitForProcessing;

public class LoginPage extends PageBase {
    private static final String TITLE = "Вход в систему";

    private static final By FORM = get("loginPage.form");
    private static final By NAME_FIELD = get("loginPage.userNameInput");
    private static final By PASS_FIELD = get("loginPage.userPassInput");
    private static final By LOGIN = get("loginPage.loginButton");

    public static final By ERROR_MESSAGE = get("loginPage.errorMessage");

    public static void login(String name, String pass){
        $(NAME_FIELD).val(name);
        $(PASS_FIELD).val(pass);
        $(LOGIN).click();
    }

    public static void login(){
        String [] creds = Creds.get("admin");
        login(creds[0], creds[1]);
        waitForProcessing();
    }

    public static void login(String credType){
        String [] creds = Creds.get(credType);
        login(creds[0],creds[1]);
        waitForProcessing();
    }

    public static void checkExpectedElements () {
        checkExpectedElements(Arrays.asList(FORM, NAME_FIELD, PASS_FIELD, LOGIN));
    }

    public static void shouldAppear(){
        shouldAppear(TITLE);
    }
}
