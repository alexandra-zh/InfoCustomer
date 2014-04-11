package core.pages;


import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class MonitorPage {
    private static final By LIST_TAB = get("monitorTab.list");
    private static final By MAP_TAB = get("monitorTab.map");
    private static final By CASCADE_TAB = get("monitorTab.cascade");
    private static final List<String> TABS = Arrays.asList("Список","Карта","Каскад");

    public static void checkSubtabs () {
        Header.checkSubtabs(TABS);
    }

    public static void goToListTab () {
        goToAndWait(LIST_TAB);
    }

    public static void goToMapTab () {
        goToAndWait(MAP_TAB);
    }

    public static void goToCascadeTab () {
        goToAndWait(CASCADE_TAB);
    }

    private static void goToAndWait(By tab){
        $(tab).click();
    }
}
