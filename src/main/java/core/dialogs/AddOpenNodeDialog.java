package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class AddOpenNodeDialog extends PageBase {

    private static final By WINDOW = get("addOpenNodeDialog.window");
    private static final By IP_SLOTS = get("addOpenNodeDialog.ipContainer");
    private static final By NODE_NAME = get("addOpenNodeDialog.nodeName");
    private static final By ADD_BUTTON = get("addOpenNodeDialog.addButton");

    public static void setNodeInfo(Integer ip1, Integer ip2, Integer ip3, Integer ip4, String name) {
        $$(IP_SLOTS).get(0).setValue(ip1.toString());
        $$(IP_SLOTS).get(1).setValue(ip2.toString());
        $$(IP_SLOTS).get(2).setValue(ip3.toString());
        $$(IP_SLOTS).get(3).setValue(ip4.toString());
        $(NODE_NAME).setValue(name);
    }

    public static void addNode() {
        $(ADD_BUTTON).click();
    }

    public static void shouldAppear() {
        $(WINDOW).waitUntil(Condition.appears, 30000);
    }
}
