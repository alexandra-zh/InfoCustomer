package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.dialogs.ExportDialog;
import core.dialogs.PollGroupDialog;
import core.helpers.Actions;
import core.helpers.Waiter;
import core.pages.MonitorHostsPage;
import core.pages.MonitorPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class MonitorTest extends TestBase {
    private static final String FILTER = "Высокий уровень";
    private static final String DEFAULT_GROUP = "Группа по умолчанию";

    @BeforeMethod
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListUIElementsTest ()  {
        MonitorHostsPage.checkExpectedElements();
    }

    @Test(groups = "fast")
    public void hostsListPollGroupWindowTest () {
        MonitorHostsPage.openPollGroup();
        PollGroupDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListExportWindowTest () {
        MonitorHostsPage.openExportNodes();
        ExportDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListFiltersUITest () {
        MonitorHostsPage.checkFilterOptions();
        MonitorHostsPage.setFilter(FILTER);
        MonitorHostsPage.resetFilters();
    }

    @Test(groups = "fast")
    public void hostsListPopUpUITest () {
        MonitorHostsPage.openExportNodes();
        ExportDialog.shouldAppear();
        ExportDialog.cancel();
        MonitorHostsPage.openPollGroup();
        PollGroupDialog.shouldAppear();
        PollGroupDialog.close();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListPollGroupUITest (){
        MonitorHostsPage.openPollGroup();
        PollGroupDialog.shouldAppear();
        PollGroupDialog.setSearchFilter(MonitorHostsPage.getNodeName(0));
        PollGroupDialog.selectLeftTreeNode(MonitorHostsPage.getNodeName(0));
        PollGroupDialog.clickMoveToRight();
        PollGroupDialog.expandRightTree();
        PollGroupDialog.checkRightTreeNode(MonitorHostsPage.getNodeName(0));
        PollGroupDialog.clearSearchFilter();
        PollGroupDialog.selectFirstGroup();
        PollGroupDialog.clickMoveToRight();
        PollGroupDialog.checkRightTreeNode(PollGroupDialog.getNameOfFirstGroupNode());
        PollGroupDialog.expandRightTree();
        $(PollGroupDialog.RIGHT_LIST_COUNT_LABEL).shouldHave(text(String.valueOf(PollGroupDialog.getNumberOfNodes())));
        PollGroupDialog.close();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListExportUITest () {
        MonitorHostsPage.openExportNodes();
        ExportDialog.shouldAppear();
        Assert.assertNotNull(ExportDialog.getExportFromDate());
        Assert.assertNotNull(ExportDialog.getExportToDate());
        ExportDialog.setSearchFilter(MonitorHostsPage.getNodeName(0));
        ExportDialog.selectNodeToExport(MonitorHostsPage.getNodeName(0));
        $(ExportDialog.SELECTED_ELEMENTS_LABEL).shouldHave(text(String.valueOf(ExportDialog.getNumberOfSelectedNodes())));
        ExportDialog.clickAllNodes();
        $(ExportDialog.SELECTED_ELEMENTS_LABEL).shouldHave(text(ExportDialog.getNumberOfNodes()));
        ExportDialog.cancel();
        MonitorHostsPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void hostsListFiltersTest () {
        MonitorHostsPage.deselectGroup(DEFAULT_GROUP);
        MonitorHostsPage.verifyAbsenceGroup(DEFAULT_GROUP);
        MonitorHostsPage.setFilter(FILTER);
        MonitorHostsPage.resetFilters();
        MonitorHostsPage.searchNode();
    }

    @Test(groups = "fast")
     public void hostsListSubtabListTest () {
        MonitorPage.checkSubtabs();
    }

    @Test(groups = "fast")
    public void hostsListSubtabsTest () {
        MonitorPage.goToMapTab();
        Waiter.waitForPageTitle("Пользовательская карта");
        MonitorPage.goToCascadeTab();
        Waiter.waitForPageTitle("Каскад"); //todo: change to page objects should appear
    }

    @Test(groups = "fast")
    public void hostsListGridHeaderTest () {
        MonitorHostsPage.checkGridHeader();
    }
}
