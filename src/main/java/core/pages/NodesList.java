package core.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class NodesList {
    private static final By NODES = get("manageTab.hosts.nodes");

    public static void expandGroup(int index){
        //todo: add implementation
    }

    public static void expandGroup(String name){
        //todo: add implementation
    }

    public static SelenideElement getGroupNode(int groupIndex, int nodeIndex){
        //todo: add implementation
        return  $$(NODES).findBy(text("name")); //todo: replace with actual code
    }

    public static SelenideElement getGroupNode(String groupName, String nodeName){
        //todo: add implementation
        return  $$(NODES).findBy(text("name")); //todo: replace with actual code
    }

    public static SelenideElement getNode(int index){
        return $$(NODES).get(index);
    }

    public static SelenideElement getNode(String name){
        return $$(NODES).findBy(text(name));
    }
}
