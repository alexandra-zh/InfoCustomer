package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

//todo: Possibly develop based class UserDialog for CreateUserDialog and EditUserDialog
public class CreateUserDialog extends PageBase {
    private static By WINDOW = get("createUser.dialog");
    private static By SAVE_BUTTON=By.cssSelector("#userEntryDialog.popupWindow div.buttons_navigation button.button");
    private static By CANCEL_BUTTON = get("createUser.cancelButton");
    private static By LAST_NAME_FIELD = get("createUser.lastNameField");
    private static By FIRST_NAME_FIELD = get("createUser.firstNameField");
    private static By MIDDLE_NAME_FIELD = get("createUser.middleNameField");
    private static By USERNAME_FIELD = get("createUser.usernameField");
    private static By REPEAT_PASSWORD_FIELD = get("createUser.repeatPasswordField");
    private static By PASSWORD_FIELD_HINT = get("createUser.passwordFieldHint");
    private static By PASSWORD_FIELD = get("createUser.passwordField");
    private static By DESCRIPTION_FIELD = get("createUser.descriptionField");
 //   private static By PHONE_NUMBER_TABLE = get("createUser.phoneNumberField");
 //   private static By EMAILS_LIST_TABLE = get("createUser.emailsListField");
    private static By ADMIN_CHECK_BOX = get("createUser.adminCheckBox");
    public static By DATA_ERROR=By.cssSelector("#userEntryForm.user_entry  div.error");

    public static void shouldAppear() {
        $(WINDOW).waitUntil(Condition.appears, 60000); //todo: Move to constants [ASEM]
    }

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(SAVE_BUTTON));
        checkExpectedElements(Arrays.asList(LAST_NAME_FIELD, FIRST_NAME_FIELD));
        checkExpectedElements(Arrays.asList(MIDDLE_NAME_FIELD, USERNAME_FIELD,REPEAT_PASSWORD_FIELD, DESCRIPTION_FIELD, PASSWORD_FIELD_HINT, ADMIN_CHECK_BOX ));
        // checkExpectedElements(Arrays.asList(PHONE_NUMBER_TABLE, EMAILS_LIST_TABLE));
    }

    public static void fillIn(String firstName, String lastName, String middleName, String username, String password) {
        $(FIRST_NAME_FIELD).val(firstName);
        $(LAST_NAME_FIELD).val(lastName);
        $(MIDDLE_NAME_FIELD).val(middleName);
        $(USERNAME_FIELD).val(username);
        $(PASSWORD_FIELD_HINT).click();
        $(PASSWORD_FIELD).shouldBe(Condition.visible);
        $(PASSWORD_FIELD).val(password);
        $(REPEAT_PASSWORD_FIELD).val(password);
    }

    public static void checkWrongDataError() {
        checkExpectedElements(Arrays.asList(DATA_ERROR));
    }

    public static void submit() {
        $(SAVE_BUTTON).click();
    }

    public static void cancel() {
        $(CANCEL_BUTTON).click();
    }

    public static void createUser(String firstName, String lastName, String middleName, String username, String password) {
        fillIn(firstName, lastName, middleName, username, password);
        submit();
    }

    public static void createAdmin(String firstName, String lastName, String middleName, String username, String password) {
        fillIn(firstName, lastName, middleName, username, password);
        $(ADMIN_CHECK_BOX).click();
        submit();
    }

    public static void setName(String name) {
        $(FIRST_NAME_FIELD).val(name);
    }

    public static void setUsername(String surname) {
        $(LAST_NAME_FIELD).val(surname);
    }

    public static void setMiddleName(String middleName) {
        $(MIDDLE_NAME_FIELD).val(middleName);
    }

    public static void setUserName(String userName) {
        $(USERNAME_FIELD).val(userName);
    }

    public static void setPass(String pass) {
        $(PASSWORD_FIELD).val(pass);
    }
}