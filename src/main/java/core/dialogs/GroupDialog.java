package core.dialogs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static core.helpers.Locators.get;
import static core.helpers.Waiter.waitForProcessing;

public class GroupDialog extends PageBase {
    private static final By WINDOW = get("createGroup.window");
    private static final By GROUP_NAME_INPUT = get("createGroup.name");
    private static final By ALL_HOSTS_TAB = get("createGroup.viewport");
    private static final By TO_RIGHT = get("createGroup.rightButton");
    private static final By TO_LEFT = get("createGroup.leftButton");
    private static final By SAVE = get("createGroup.save");
    private static final By CANCEL = get("createGroup.cancel");
    private static final By DELETE = get("createGroup.delete");
    private static final By NODES = get("createGroup.nodes");
    private static final By GROUPS = get("createGroup.nodeGroups");
    private static final By EXPANDER = get("expander");

    public static void save() {
        $(SAVE).click();
        waitForProcessing();
    }

    public static void cancel() {
        $(CANCEL).click();
    }

    public static void delete(){
        actions().pause(2000).click($(DELETE).toWebElement()).perform();
        $(get("popup")).$(byText("Да")).click();
        waitForProcessing();
        $(WINDOW).waitUntil(Condition.disappears, 30000);
    }

    public static void setName(String groupName) {
        $(GROUP_NAME_INPUT).val(groupName);
    }

    public static void addNodes(int count) {
        $(ALL_HOSTS_TAB).$(EXPANDER).click();
        for(int i = 0; i < count; i++) {
            $(ALL_HOSTS_TAB).$(NODES).click();
            $(TO_RIGHT).click();
        }
    }

    public static ElementsCollection getGroupNodes() {
        return $$(ALL_HOSTS_TAB).get(1).$$(NODES);
    }

    public static void shouldAppear() {
        $(WINDOW).waitUntil(Condition.appears, 30000);
    }
}
