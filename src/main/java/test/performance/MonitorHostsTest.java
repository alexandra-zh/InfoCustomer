package test.performance;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.PerformanceTestBase;
import core.helpers.Actions;
import core.pages.MonitorHostsPage;
import core.pages.MonitorPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test(groups = "performance")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class MonitorHostsTest extends PerformanceTestBase {
    @Test(groups = "performance")
    public void loadListTest() throws Exception {
        MonitorPage.goToMapTab();
        Actions.clearCookies();
        startHarRecording("3.1.1 Загрузка страницы: Мониторинг-Список");
        MonitorPage.goToListTab();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void hostDetailInfoTest() throws Exception {
        startHarRecording("3.1.2 Загрузки детальной информации об узле: Мониторинг-Список");
        MonitorHostsPage.clickNode(0);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void pollGroupDialogTest() throws Exception {
        startHarRecording("3.1.3 Открытие диалога 'Опросить группу': Мониторинг-Список");
        MonitorHostsPage.openPollGroup();
        finishHarRecording();
    }
}
