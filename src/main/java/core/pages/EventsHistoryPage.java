package core.pages;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.helpers.Actions;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class EventsHistoryPage extends PageBase {

    private static final String TITLE = "История событий";
    private static final String RULES_FILE = "/rules.txt";
    private static final String SEVERITY_FILE = "/severity.txt";
    private static final int BAD_ENTRY = -1;

    //region Elements
    private static final By HIDE_BUTTON = get("eventsHistoryTab.hideButton");
    private static final By NODES_PANEL = get("eventsHistoryTab.nodes");
    private static final By RULES_PANEL = get("eventsHistoryTab.rules");
    private static final By CRITICALITY_PANEL = get("eventsHistoryTab.criticalityLevel");
    private static final By FIND_BUTTON = get("eventsHistoryTab.findButton");
    private static final By RULE = get("eventsHistoryTab.rule");
    private static final By FROM_DATE = get("eventsHistoryTab.fromDate");
    private static final By FROM_TIME = get("eventsHistoryTab.fromTime");
    private static final By CALENDAR = get("calendar.container");
    private static final By SEVERITY_LEVELS = get("eventsHistoryTab.severityLevels");
    //endregion

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(HIDE_BUTTON, NODES_PANEL, RULES_PANEL, CRITICALITY_PANEL,FIND_BUTTON));
    }

    public static void checkPresenceOfRules () {
        List<String> namesOfRules = getAllRuleNames();
        List<String> namesFromFile = Actions.getStringsFromFile(RULES_FILE);
        Assert.assertTrue(namesFromFile.containsAll(namesOfRules));
    }

    public static void checkPresenceOfSeverity () {
        List<String> namesOfSeverity = getNamesOfSeverity();
        List<String> namesOfSeverityFromFile = Actions.getStringsFromFile(SEVERITY_FILE);
        Assert.assertTrue(namesOfSeverityFromFile.containsAll(namesOfSeverity));
    }

    public static void checkHideAndUnWrap() {
        $(HIDE_BUTTON).click();
        $(NODES_PANEL).should(disappear);
        $(HIDE_BUTTON).click();
        $(NODES_PANEL).should(appear);
    }

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    private static List<String> getNamesOfSeverity () {
        List<String> listOfCriticalityLevels = Arrays.asList($(CRITICALITY_PANEL).$$(RULE).getTexts());
        for(String rule : listOfCriticalityLevels) {
            if ((rule.indexOf("\n")) != BAD_ENTRY ) {
                int index = rule.indexOf("\n");
                String correctName = rule.substring(0,index);
                listOfCriticalityLevels.set(listOfCriticalityLevels.indexOf(rule),correctName);
            }
        }
        return listOfCriticalityLevels;
    }

    private static List<String> getAllRuleNames() {
        List<String> listOfRules = Arrays.asList($(RULES_PANEL).$$(RULE).getTexts());
        for(String rule : listOfRules) {
            if ((rule.indexOf("\n")) != BAD_ENTRY ) {
                int index = rule.indexOf("\n");
                String correctName = rule.substring(0,index);
                listOfRules.set(listOfRules.indexOf(rule),correctName);
            }
        }
        return listOfRules;
    }

    private static void setFromDay(int day){
        $(FROM_DATE).click();
        $(CALENDAR).should(appear);
        $(CALENDAR).$(byText(String.valueOf(day))).click();
    }

    public static void setFromTime(Date time, int delta) {
        String hours = setCorrectFormat(time, delta).get(0);
        String minutes = setCorrectFormat(time, delta).get(1);
        $(FROM_TIME).click();
        if (delta <= time.getHours()) {
            setFromDay(time.getDate());
            String setTime = hours + ":" + minutes;
            $(FROM_TIME).setValue(setTime);
        }
        if (delta > time.getHours()) {
            if (delta == 24)
                setFromDay(time.getDate() - 1);
            if (delta == 48)
                setFromDay(time.getDate() - 2);
        }
    }

    private static List<String> setCorrectFormat(Date time, int delta) {
        String hours;
        String minutes;
        if ((time.getHours() - delta) < 10) {
            hours = "0" + String.valueOf(time.getHours() - delta);
        }
        else {
            hours = String.valueOf(time.getHours() - delta);
        }
        if (time.getMinutes() < 10) {
            minutes = "0" + String.valueOf(time.getMinutes());
        }
        else {
            minutes = String.valueOf(time.getMinutes());
        }
        return Arrays.asList(hours, minutes);
    }

    public static void findEvents(){
        $(FIND_BUTTON).click();
    }

    public static void setAllSeverityLevels(){
        for(SelenideElement elem: $$(SEVERITY_LEVELS)){
            Actions.check(elem);
        }
    }

    public static void setInfoSeverityLevel() {
        for (SelenideElement se : $$(SEVERITY_LEVELS))
            Actions.uncheck(se);
        Actions.check($(SEVERITY_LEVELS, 3));
    }

    public static void setHighAndInfoSeverityLevel() {
        for (SelenideElement se : $$(SEVERITY_LEVELS)){
            Actions.uncheck(se);
        }
        Actions.check($(SEVERITY_LEVELS, 0));
        Actions.check($(SEVERITY_LEVELS, 3));
    }
}
