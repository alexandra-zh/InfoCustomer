package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.dialogs.AddOpenNodeDialog;
import core.dialogs.DeleteOpenNodeDialog;
import core.dialogs.GroupDialog;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class ManageHostsPage extends PageBase{
    private static final String TITLE = "Настройки узлов";

    //region Elements
    private static final By MONITORING_GRID = get("manageTab.hosts.monitoringGrid");
    private static final By RIGHT_GRID = get("manageTab.hosts.rightGrid");
//    private static final By GROUPS = get("manageTab.hosts.nodeGroups");
    private static final By NODES = get("manageTab.hosts.nodes");
    private static final By ARROW_COLLAPSED = get("arrow.collpsedState");
    private static final By ARROW_EXPANDED = get("arrow.expandedState");
    private static final By CREATE_GROUP_BUTTON = get("manageTab.hosts.createGroupButton");
    private static final By ADD_NODES_MONITORING_BUTTON = get("manageTab.hosts.addNodesMonitoring");
    private static final By SEARCH_FILTER = get("manageTab.hosts.searchField");
    private static final By ADD_OPEN_NODES = By.cssSelector("input[value='Добавить']");
    private static final By IMPORT_OPEN_NODES = By.cssSelector("input[value='Импортировать']");
    private static final By DELETE_OPEN_NODES = By.cssSelector("input[value='Удалить']");
    private static final By GROUPS = get("manageTab.hosts.Groups");
    private static final By NODE = get("monitorTab.hosts.node");
    private static final By OPEN_NODE = get("monitorTab.hosts.openNode");
    //endregion

    public static void clickCreateGroup(){
        $(CREATE_GROUP_BUTTON).click();
        GroupDialog.shouldAppear();
    }

    public static void clickNode() {
        $(NODE).click();
    }

    public static void checkExpectedElements () {
        checkExpectedElements(Arrays.asList(
                CREATE_GROUP_BUTTON, ADD_NODES_MONITORING_BUTTON, SEARCH_FILTER, ADD_OPEN_NODES, IMPORT_OPEN_NODES));
    }

    public static void deleteGroup(String groupName){
        collapseAll();
        $(MONITORING_GRID).$(withText(groupName)).click();
        GroupDialog.shouldAppear();
        GroupDialog.delete();
    }

    public static void createGroup(String group, int nodesCount){
        clickCreateGroup();
        GroupDialog.setName(group);
        GroupDialog.addNodes(nodesCount);
        GroupDialog.save();
    }

    public static SelenideElement getGroup(String groupName){
        collapseAll();
        return $(MONITORING_GRID).$(withText(groupName));
    }

    public static SelenideElement getGroup(int num){
        collapseAll();
        return $$(GROUPS).get(num);
    }

    private static void collapseAll(){
        while($(MONITORING_GRID).$$(ARROW_EXPANDED).size() > 0)
        {
            for(SelenideElement elem : $(MONITORING_GRID).$$(ARROW_EXPANDED)){
                if(elem.isDisplayed()) elem.click();
            }
        }
    }

	public static void shouldAppear(){
        shouldAppear(TITLE);
    }

    public static void clickAddOpenNodes() {
        $(ADD_OPEN_NODES).click();
    }

    public static void clickImportOpenNodes() {
        $(IMPORT_OPEN_NODES).click();
    }

    public static void clickDeleteOpenNodes() {
        $(DELETE_OPEN_NODES).click();
    }

    public static void clickOpenNode() {
        $(OPEN_NODE).click();
    }

    public static void deleteTestNode() {
        clickDeleteOpenNodes();
        DeleteOpenNodeDialog.shouldAppear();
        DeleteOpenNodeDialog.selectNode();
        DeleteOpenNodeDialog.deleteNodes();
    }

    public static void createTestNode() {
        ManageHostsPage.clickAddOpenNodes();
        AddOpenNodeDialog.shouldAppear();
        AddOpenNodeDialog.setNodeInfo(0, 0, 0, 1, "QAtest");
        AddOpenNodeDialog.addNode();
    }
}
