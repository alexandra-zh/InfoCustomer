package core.dialogs;

import core.base.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static core.helpers.Locators.get;


public class ExportDialog extends PageBase {

    private static final int PAUSE_TIME = 1000;

    //region Elements
    public static final By SELECTED_ELEMENTS_LABEL = get("export.selectedElemLabel");
    private static By window = get("export.dialog");
    private static By cancelButton = get("export.cancelButton");
    private static By searchField = get("export.searchField");
    private static By exportFromDate = get("export.exportFromDate");
    private static By exportToDate = get("export.exportToDate");
    private static By exportTree = get("export.exportTree");
    private static By allNodesLabel = get("export.countItemsLabel");
    private static By exportTreeNodes = get("export.exportTreeNodes");
    private static By allNodesCheckBox = get("export.allNodesCheckBox");
    //endregion

    public static void cancel() {
        $(cancelButton).click();
         actions().pause(PAUSE_TIME).perform();
    }

    public static void setSearchFilter (String nodeName) {
        $(searchField).sendKeys(nodeName);
    }

    public static void selectNodeToExport (String nodeName) {
        $(exportTree).$(byText(nodeName)).click();
    }

    public static String getExportToDate () {
        return $(exportToDate).val();
    }

    public static String getExportFromDate () {
        return $(exportFromDate).val();
    }

    public static int getNumberOfSelectedNodes() {
        return $$(exportTreeNodes).size();
    }

    public static void clickAllNodes () {
        $(allNodesCheckBox).click();
    }

    public static String getNumberOfNodes () {
     return $(allNodesLabel).text();
    }

    public static void shouldAppear() {
        $(window).waitUntil(appears, 30000);
    }
}


