package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class EditProfileDialog extends PageBase {
    private static final By WINDOW = get("editUserDialog.window");

    public static void shouldAppear(){
        $(WINDOW).waitUntil(Condition.appears, 30000);
    }
}
