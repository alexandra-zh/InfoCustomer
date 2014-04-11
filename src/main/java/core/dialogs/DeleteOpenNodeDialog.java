package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class DeleteOpenNodeDialog extends PageBase {

    private static final By WINDOW = get("deleteOpenNodeDialog.window");
    private static final By TEST_NODE = By.cssSelector("a[title='QAtest']");
    private static final By DELETE_BUTTON = get("deleteOpenNodeDialog.deleteButton");

    public static void selectNode() {
        $(TEST_NODE).click();
    }

    public static void deleteNodes() {
        $(DELETE_BUTTON).click();
    }

    public static void shouldAppear() {
        $(WINDOW).waitUntil(Condition.appear, 3000);
    }
}
