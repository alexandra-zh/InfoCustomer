package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

//todo: Possibly develop based class UserDialog for CreateUserDialog and EditUserDialog
public class EditUserDialog extends PageBase {
    private static final By WINDOW = get("editUser.dialog");
    private static final By SAVE_BUTTON = get("editUser.saveButton");
    private static final By DELETE_BUTTON = get("editUser.deleteButton");
    private static final By LAST_NAME = get("editUser.lastName");
    private static final By FIRST_NAME = get("editUser.firstName");
    private static final By MIDDLE_NAME = get("editUser.middleName");
    private static final By USERNAME = get("editUser.username");
    private static final By PASSWORD_HINT = get("editUser.passwordHint");
    private static final By PASSWORD = get("editUser.password");
    private static final By REPEAT_PASSWORD = get("editUser.repeatPassword");
    private static final By  DELETE_CONFIRMATION = get("editUser.deleteConfirmation");
    private static final By  DELETE_YES = get("editUser.delete.Yes");
    private static final By  DELETE_NO = get("editUser.delete.No");
    private static final By IS_ADMIN = get("editUser.isAdmin");

	private static final int WAITTIME = 6000; // todo: change type

    public static void shouldAppear(){
        $(WINDOW).waitUntil(Condition.appears, 60000); //todo: Move to constants [ASEM]
    }

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(SAVE_BUTTON, DELETE_BUTTON, LAST_NAME, FIRST_NAME, MIDDLE_NAME,USERNAME ));
    }

    public static void fillIn(String firstName, String lastName, String middleName) {
        $(LAST_NAME).val(lastName);
        $(FIRST_NAME).val(firstName);
        $(MIDDLE_NAME).val(middleName);
    }

    public static void delete() {
        clickDelete();
	    confirmDeletion();
    }

	public static void clickDelete() {
		$(DELETE_BUTTON).click();
	}

	public static void confirmDeletion() {
		$(DELETE_CONFIRMATION).waitUntil(Condition.appears, 60000); //todo: Move to constants [ASEM]
		$(DELETE_YES).click();
	}



//    public static void setUserRights(boolean isAdmin) {
//        if ($(IS_ADMIN).attr("checked")=="true"){ //&&($(IS_ADMIN).getCssValue("checked")=="checked"))
//            if (!isAdmin){
//                $(IS_ADMIN).click();
//            }
//        }
//        else{
//            if (isAdmin){
//                $(IS_ADMIN).click();
//            }
//        }
//    }

    public static void save(){
        $(SAVE_BUTTON).click();
    }
}
