package test.performance;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.PerformanceTestBase;
import core.pages.MonitorMapPage;
import core.pages.MonitorPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static core.pages.MapPage.MapScale.*;

@Test(groups = "performance")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class MonitoringMapTest extends PerformanceTestBase {
    private static final String MAP_OPTION_LOCAL = "Локальная карта";
    private static final String MAP_OPTION_STREET = "OpenStreetMap";

    @Test(groups = "performance")
    public void loadMonitoringMapTest() throws Exception {
        startHarRecording("3.2.1 Загрузка страницы: Мониторинг-Карта");
        MonitorPage.goToMapTab();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void loadOpenStreetMapTest() throws Exception {
        MonitorPage.goToMapTab();
        startHarRecording("3.2.8 Загрузка openStreetMap");
        MonitorMapPage.setMapType(MAP_OPTION_STREET);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void loadLocalMapTest() throws Exception {
        MonitorPage.goToMapTab();
        startHarRecording("3.2.2 Загрузка Локальной карты");
        MonitorMapPage.setMapType(MAP_OPTION_LOCAL);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void localMapZoomToCityTest() throws Exception {
        MonitorPage.goToMapTab();
        startHarRecording("3.2.2 Масштабирование локальной карты: Город");
        MonitorMapPage.chooseMapScale(Город);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void localMapZoomToAreaTest() throws Exception {
        MonitorPage.goToMapTab();

        startHarRecording("3.2.2 Масштабирование локальной карты: Район");
        MonitorMapPage.chooseMapScale(Район);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void localMapZoomToRegionTest() throws Exception {
        MonitorPage.goToMapTab();

        startHarRecording("3.2.2 Масштабирование локальной карты: Область");
        MonitorMapPage.chooseMapScale(Область);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void localMapZoomToDistrictTest() throws Exception {
        MonitorPage.goToMapTab();

        startHarRecording("3.2.2 Масштабирование локальной карты: Округ");
        MonitorMapPage.chooseMapScale(Округ);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void localMapZoomToCountryTest() throws Exception {
        MonitorPage.goToMapTab();

        startHarRecording("3.2.2 Масштабирование локальной карты: Страна");
        MonitorMapPage.chooseMapScale(Страна);
        finishHarRecording();
    }


    @Test(groups = "performance")
    public void openPollingDialogTest() throws Exception {
        MonitorPage.goToMapTab();

        startHarRecording("3.2.9 Открыть диалог \"Опросить узлы\"");
        MonitorMapPage.clickPollGroup();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public  void dragMapTest() throws Exception {
        MonitorPage.goToMapTab();
        startHarRecording("3.2.5 Перетаскивание карты мышью");
        MonitorMapPage.dragMapBy(500, 0);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public  void zoomInOpenStreetMapTest() throws Exception {
        MonitorPage.goToMapTab();
        MonitorMapPage.setMapType(MAP_OPTION_STREET);
        startHarRecording("3.2.8 Изменение масштаба OpenStreetMap карты");
        MonitorMapPage.zoomIn();

        finishHarRecording();
    }

    @Test(groups = "performance")
    public void openPollGroupDialogTest() throws Exception {
        MonitorPage.goToMapTab();
        startHarRecording("3.2.12 Открытие диалога 'Опросить группу': Мониторинг-Карта");
        MonitorMapPage.clickPollGroup();
        finishHarRecording();
    }
}
