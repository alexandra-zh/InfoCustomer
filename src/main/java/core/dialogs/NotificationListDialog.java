package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class NotificationListDialog  extends PageBase{
    private static final By WINDOW = get("notificationListDialog.window");

    public static void shouldAppear(){
        $(WINDOW).waitUntil(Condition.appears, 30000);
    }
}
