package core.dialogs;

import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static core.helpers.Locators.get;

public class PollGroupDialog extends PageBase {

    //region Elements
    public static final By RIGHT_LIST_COUNT_LABEL = get("pollGroup.rightListCount");
    private static By window = get("pollGroup.dialog");
    private static By closeButton = get("pollGroup.closeButton");
    private static By leftNodeFilter = get("pollGroup.leftFilter");
    private static By clearLeftFilterButton = get("poll.Group.clearLeftFilter");
    private static By leftTree = get("pollGroup.leftTree");
    private static By rightTree = get("pollGroup.rightTree");
    private static By rightTreeExpander = get("pollGroup.rightTreeExpander");
    private static By moveToRightButton = get("pollGroup.moveToRight");
    private static By moveToLeftButton = get("pollGroup.moveToLeft");
    private static By firstGroup = get("pollGroup.firstGroup");
    private static By firstRightGroup = get("pollGroup.firstRightGroup");
    private static By rightTreeNodes = get("pollGroup.rightTreeNodes");
    //endregion

    public static void close() {
        $(closeButton).click();
        actions().pause(1000).perform();
    }

    public static void clickMoveToRight () {
        $$(moveToRightButton).get(0).click();
    }

    public static void clickMoveToLeft () {
        $(moveToLeftButton).click();
    }

    public static void selectLeftTreeNode (String nodeName) {
        $(leftTree).$(byText(nodeName)).click();
    }

    public static void checkRightTreeNode (String nodeName) {
        $(rightTree).$(byText(nodeName)).shouldBe(visible);
    }

    public static void setSearchFilter (String nodeName) {
//        $(leftNodeFilter).sendKeys(nodeName);
        $(leftNodeFilter).val(nodeName);
    }

    public static void clearSearchFilter () {
        $(clearLeftFilterButton).click();
    }

    public static void expandRightTree () {
        $(rightTreeExpander).click();
    }

    public static void selectFirstGroup () {
        $(firstGroup).click();
    }

    public static String getNameOfFirstGroupNode () {
        return $(firstGroup).text();
    }

    public static String getNameOfRightFirstGroupNode () {
        return $(firstRightGroup).text();
    }

    public static int getNumberOfNodes() {
        return $$(rightTreeNodes).size();
    }

    public static SelenideElement getGroupByName(String groupName){
        return $$(".NodeLabel").findBy(text(groupName));
    }

    public static void shouldAppear() {
        $(window).waitUntil(appears, 30000);
    }

}


