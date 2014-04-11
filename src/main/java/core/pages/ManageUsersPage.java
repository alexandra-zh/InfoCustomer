package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.dialogs.CreateUserDialog;
import core.dialogs.EditUserDialog;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class ManageUsersPage extends PageBase {

    private static final String TITLE = "Настройки пользователей";
    public static final By SEARCH_FIELD = get("manageTab.users.searchField");
    public static final By CREATE_BUTTON = get ("manageTab.users.createButton");
    public static final By ERROR_MSG = get("manageTab.users.errorMsg");
    public static final By ERROR_MSG_OK = get("manageTab.users.errorMsgOk");
    public static final By CLOSE_SEARCH = get("manageTab.users.closeSearchButton");
    public static final By USERS_TABLE = get("manageTab.users.usersTable");


    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(CREATE_BUTTON, SEARCH_FIELD));
    }

    public static void clickCreateUser() {
        $(CREATE_BUTTON).click();
        CreateUserDialog.shouldAppear();
      }

    //todo: Obsolete
    public static void checkUserCreationResult(boolean isValid) {
        if(isValid) {
            ManageUsersPage.shouldAppear();
        }
        else {
            if ($(CreateUserDialog.DATA_ERROR).isDisplayed()) {
                CreateUserDialog.cancel();
            }
            else {
                $(ManageUsersPage.ERROR_MSG).shouldBe(visible);
//                $(ERROR_MSG_OK).click();
                CreateUserDialog.shouldAppear();
                CreateUserDialog.cancel();
            }
        }
    }

    public static void setSearchField(String userLastName){
        $(SEARCH_FIELD).val(userLastName);
    }

    public static void searchUser(String userLastName) {
        setSearchField(userLastName);
    }

    public static SelenideElement searchUser(String userLastName, String userName) {
        setSearchField(userName);
        return getUser(userLastName);
    }

    // todo: Possibly change to one method with additional boolean parameter isAdmin
    public static void createUser(String firstName, String lastName, String middleName, String username,
                                  String password) {
        ManageUsersPage.clickCreateUser();
        CreateUserDialog.shouldAppear();
        CreateUserDialog.checkExpectedElements();
        CreateUserDialog.createUser(firstName, lastName, middleName, username, password);
        ManageUsersPage.shouldAppear();
    }

    public static void createAdmin(String firstName, String lastName, String middleName, String username,
                                   String password) {
        ManageUsersPage.clickCreateUser();
        CreateUserDialog.shouldAppear();
        CreateUserDialog.createAdmin(firstName, lastName, middleName, username, password);
        ManageUsersPage.shouldAppear();
    }

    public static void deleteUser(String userLName) {
        ManageUsersPage.openUser(userLName);
        EditUserDialog.shouldAppear();
        EditUserDialog.delete();
        ManageUsersPage.shouldAppear();
    }

    public static SelenideElement getUser(String lastName) {
        return $(USERS_TABLE).$(byText(lastName));
    }

    public static void openUser(String lastName) {
        getUser(lastName).click();
    }

    public static void clearSearchFilter () {
        $(CLOSE_SEARCH).click();
    }
}
