package core.pages;

import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class MonitorMapPage extends MapPage {
    private static final String TITLE = "Пользовательская карта";
    private static final By POLL_GROUP_BUTTON = get("monitorTab.map.pollGroupButton");

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    public static void clickPollGroup() {
        $(POLL_GROUP_BUTTON).click();
    }

    public static void checkExpectedElements() {
        List<By> expectedElements = getExpectedElements();
        expectedElements.add(POLL_GROUP_BUTTON);
        checkExpectedElements(expectedElements);
    }
}
