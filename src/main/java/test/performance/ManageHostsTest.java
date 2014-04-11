package test.performance;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.PerformanceTestBase;
import core.dialogs.AddOpenNodeDialog;
import core.dialogs.DeleteOpenNodeDialog;
import core.pages.Header;
import core.pages.ManageHostsPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static core.pages.Header.MANAGE_TAB;

@Test(groups = "performance")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class ManageHostsTest extends PerformanceTestBase {

    @Test(groups = "performance")
     public void openManageHostsTest() throws Exception {
        startHarRecording("3.4.1 Загрузка страницы: Администрирование-Узлы");
        Header.goTo(MANAGE_TAB);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openCreateGroupTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        startHarRecording("3.4.2 Загрузка диалога: Создание группы");
        ManageHostsPage.clickCreateGroup();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openEditGroupTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        startHarRecording("3.4.3 Загрузка диалога: Редактирование группы");
        ManageHostsPage.getGroup("SpecialGroup").click();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openEditNodeTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        startHarRecording("3.4.4 Загрузка диалога: Редактирование узла");
        ManageHostsPage.clickNode();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openAddNodesDialogTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        startHarRecording("3.4.5 Загрузка диалога: Добавление открытого узла");
        ManageHostsPage.clickAddOpenNodes();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openImportNodesDialogTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        startHarRecording("3.4.6 Загрузка диалога: Импорт открытых узлов");
        ManageHostsPage.clickImportOpenNodes();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void addOpenNodeTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.clickAddOpenNodes();
        AddOpenNodeDialog.shouldAppear();
        AddOpenNodeDialog.setNodeInfo(0, 0, 0, 1, "QAtest");
        startHarRecording("3.4.7 Добавление открытого узла");
        AddOpenNodeDialog.addNode();
        finishHarRecording();
        ManageHostsPage.deleteTestNode();
    }

    @Test(groups = "performance")
    public void openAddNodeToMonitorDialogTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.shouldAppear();
        startHarRecording("3.4.8 Загрузка диалога: Добавление узла в группу мониторинга");
        ManageHostsPage.clickOpenNode();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openDeleteNodesDialogTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.shouldAppear();
        startHarRecording("3.4.9 Загрузка диалога: Удаление открытых узлов");
        ManageHostsPage.clickDeleteOpenNodes();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void deleteOpenNodeTest() throws Exception {
        Header.goTo(MANAGE_TAB);
        ManageHostsPage.shouldAppear();
        ManageHostsPage.createTestNode();
        ManageHostsPage.clickDeleteOpenNodes();
        DeleteOpenNodeDialog.shouldAppear();
        DeleteOpenNodeDialog.selectNode();
        startHarRecording("3.4.10 Удаление открытого узла");
        DeleteOpenNodeDialog.deleteNodes();
        finishHarRecording();
    }
}
