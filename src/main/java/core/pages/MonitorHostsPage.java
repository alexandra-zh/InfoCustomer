package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.helpers.Actions;
import core.helpers.Waiter;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class MonitorHostsPage extends PageBase {

    private static final String TITLE = "Список узлов";
    private static final String GRID_HEADER_FILE_LOCATION = "/monitoringGridHeader";

    //region EventFilter
    private static final String ALL_EVENTS = "Все события";
    private static final String HIGH_LEVEL_EVENTS = "Высокий уровень";
    private static final String AVERAGE_LEVEL_EVENTS = "Средний уровень";
    private static final String lOW_LEVEL_EVENTS = "Низкий уровень";
    private static final String INFO_MESSAGE_LEVEL = "Информационное сообщение";
    //endregion

    //region GroupFilter
    private static final By APPLY_GROUP_FILTER = get("groupViewingMode.applyFilter");
    //endregion


    //region core.helpers.Locators
    private static final By POLL_GROUP_BUTTON = get("monitorTab.hosts.pollGroupButton");
    private static final By EXPORT_BUTTON = get("monitorTab.hosts.exportButton");
    private static final By CLEAR_BUTTON = get("monitorTab.hosts.clearButton");
    private static final By GROUP_VIEWING_MODE = get("monitorTab.hosts.groupViewingMode");
    private static final By EVENT_VIEWING_MODE = get("monitorTab.hosts.eventViewingMode");
    private static final By SEARCH_FIELD = get("monitorTab.hosts.searchField");
    private static final By NODES_COUNTER = get("monitorTab.hosts.nodesCounter");
    private static final By GRID = get("monitorTab.hosts.grid");
   //endregion

    //region Grid
    private static By nodeName = get("grid.nodeName");
    private static By groupName = get("grid.groupName");
    //endregion

    private static final By NODE = get("monitorTab.hosts.node");

    public static void openPollGroup() {
        $(POLL_GROUP_BUTTON).click();
        Waiter.waitForProcessing();
    }

    public static void openExportNodes() {
        $(EXPORT_BUTTON).click();
    }

    public static void deselectGroup (String groupName) {
        $(GROUP_VIEWING_MODE).click();
        $(GROUP_VIEWING_MODE).$(byText(groupName)).click();
        $(APPLY_GROUP_FILTER).click();
    }

    public static void setFilter(String filter) {
        $(EVENT_VIEWING_MODE).selectOption(filter);
    }

    public static void resetFilters() {
        $(CLEAR_BUTTON).click();
    }

    public static String getNodeName(int nodeNum) {
        return $(nodeName, nodeNum).text();
    }

    public static String getNameOfFirstGroup () {
         return $(groupName).text();
    }

    public static void searchNode() {
        $(SEARCH_FIELD).sendKeys($(nodeName).text());
        $(nodeName).should(appear);
    }

    public static void verifyAbsenceGroup (String group){
        for (SelenideElement element : $$(groupName)) {
            element.shouldNotHave(text(group));
        }
    }

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(GRID, NODES_COUNTER, POLL_GROUP_BUTTON, EXPORT_BUTTON, CLEAR_BUTTON,
                GROUP_VIEWING_MODE, EVENT_VIEWING_MODE, SEARCH_FIELD));
    }

    public static void checkFilterOptions() {
        $(EVENT_VIEWING_MODE).click();
        for (String element : Arrays.asList(
                ALL_EVENTS,HIGH_LEVEL_EVENTS,AVERAGE_LEVEL_EVENTS,lOW_LEVEL_EVENTS,INFO_MESSAGE_LEVEL)) {
            $(EVENT_VIEWING_MODE).$(byText(element)).shouldBe(visible);
        }
    }

    public static void checkGridHeader() {
        List<String> headerNames = Actions.getStringsFromFile(GRID_HEADER_FILE_LOCATION);
        List<String> headerValues = Arrays.asList($$(get("grid.columnName")).getTexts());
        Assert.assertEquals(headerNames, headerValues);
    }

    public static void clickNode(int nodeNum) {
        $(NODE, nodeNum).click();
    }
}
