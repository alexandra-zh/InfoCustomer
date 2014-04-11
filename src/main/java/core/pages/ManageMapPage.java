package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.helpers.Actions;
import core.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static core.helpers.Locators.get;

public class ManageMapPage extends MapPage {
    private static final String TITLE = "Настройки карты";
    private static final String MAP_GEO_ELEMENTS_PARAMS_FILE = "/mapGeoElements";

    private static final By ARROWS_EXPAND = get("manageTab.map.arrowsExpand");
    private static final By NODES = get("manageTab.map.nodes");
    private static final By GROUPS = get("manageTab.map.groups");
    private static final By DELETE_ALL_HOSTS_BUTTON = get("manageTab.map.deleteAllHostsButton");
    private static final By ALLOCATE_NODES_BUTTON = get("manageTab.map.allocateNodesButton");
    private static final By CLOSE_DIALOG_BUTTON = get("manageTab.map.closeDialogButton");
    private static final By DELETE_DIALOG_BUTTON_YES = get("manageTab.map.deleteDialogButtonYes");
    private static final By SETTINGS_MAP_SCALES = get("manageTab.map.settings.mapScales");
    private static final By SETTINGS_ELEMENTS = get("manageTab.map.settings.controls");

    public static void clearMap() {
        $(DELETE_ALL_HOSTS_BUTTON).click();
        $(DELETE_DIALOG_BUTTON_YES).click();
    }

    public static void closeEditDialog() {
        $(CLOSE_DIALOG_BUTTON).click();
    }

    public static void expandFirstGroup(){
        $(ARROWS_EXPAND).click();
        Waiter.waitForJquery();
    }

    public static SelenideElement getNode(int index){
        return $$(NODES).get(index);
    }

    public static SelenideElement getNode(String name){
        return $$(NODES).findBy(text(name));
    }

    public static SelenideElement getGroup(String name){
        return $$(GROUPS).findBy(text(name));
    }

    public static SelenideElement getGroup(int index){
        return $$(GROUPS).get(index);
    }

    public static void putNodesOnMap(SelenideElement elementFrom) {
        Action drag = actions().dragAndDrop($(elementFrom), $(MAP)).build();
        drag.perform();
    }

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    public static void checkSettingsElements() {
        $(SETTINGS_RESTORE_BUTTON).isDisplayed();
        $(SETTINGS_CLOSE_BUTTON).isDisplayed();
        $(SETTINGS_MAP_SCALES).isDisplayed();
        List<String> expectedGeoElements = Actions.getStringsFromFile(MAP_GEO_ELEMENTS_PARAMS_FILE);
        List<String> actualGeoElements = getSettingsElements();
        Assert.assertEquals(actualGeoElements, expectedGeoElements);
    }

    public static List<String> getSettingsElements() {
        return Arrays.asList($(SETTINGS_ELEMENTS).$$("label").getTexts());
    }

    public static void checkExpectedElements() {
        List<By> expectedElements = getExpectedElements();
        expectedElements.add(DELETE_ALL_HOSTS_BUTTON);
        expectedElements.add(ALLOCATE_NODES_BUTTON);
        checkExpectedElements(expectedElements);
    }
}
