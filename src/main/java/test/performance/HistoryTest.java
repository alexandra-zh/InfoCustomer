package test.performance;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.PerformanceTestBase;
import core.pages.EventsHistoryPage;
import core.pages.Header;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Date;

import static core.pages.Header.EVENTS_TAB;

@Test(groups = "performance")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class HistoryTest extends PerformanceTestBase {

    @Test(groups = "performance")
    public void loadHistoryTest() throws Exception {
        startHarRecording("3.3.1 Загрузка страницы: История событий");
        Header.goTo(EVENTS_TAB);
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterOneHistorySearchOneHourTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 1);
        EventsHistoryPage.setAllSeverityLevels();
        startHarRecording("3.3.2 Запрос истории событий за 1 час: фильтр 1 - все уровни критичности");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterTwoHistorySearchOneHourTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 1);
        EventsHistoryPage.setInfoSeverityLevel();
        startHarRecording("3.3.3 Запрос истории событий за 1 час: фильтр 2 - информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterThreeHistorySearchOneHourTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 1);
        EventsHistoryPage.setHighAndInfoSeverityLevel();
        startHarRecording("3.3.4 Запрос истории событий за 1 час: фильтр 3 - высокий уровень критичности и информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterOneHistorySearchFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 4);
        EventsHistoryPage.setAllSeverityLevels();
        startHarRecording("3.3.5 Запрос истории событий за 4 часа: фильтр 1 - все уровни критичности");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterTwoHistorySearchFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 4);
        EventsHistoryPage.setInfoSeverityLevel();
        startHarRecording("3.3.6 Запрос истории событий за 4 часа: фильтр 2 - информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterThreeHistorySearchFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 4);
        EventsHistoryPage.setHighAndInfoSeverityLevel();
        startHarRecording("3.3.7 Запрос истории событий за 4 часа: фильтр 3 - высокий уровень критичности и информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterOneHistorySearchTwentyFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 24);
        EventsHistoryPage.setAllSeverityLevels();
        startHarRecording("3.3.8 Запрос истории событий за 24 часа: фильтр 1 - все уровни критичности");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterTwoHistorySearchTwentyFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 24);
        EventsHistoryPage.setInfoSeverityLevel();
        startHarRecording("3.3.9 Запрос истории событий за 24 часа: фильтр 2 - информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterThreeHistorySearchTwentyFourHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 24);
        EventsHistoryPage.setHighAndInfoSeverityLevel();
        startHarRecording("3.3.10 Запрос истории событий за 24 часа: фильтр 3 - высокий уровень критичности и информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterOneHistorySearchFortyEightHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 48);
        EventsHistoryPage.setAllSeverityLevels();
        startHarRecording("3.3.11 Запрос истории событий за 48 часов: фильтр 1 - все уровни критичности");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterTwoHistorySearchFortyEightHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 48);
        EventsHistoryPage.setInfoSeverityLevel();
        startHarRecording("3.3.12 Запрос истории событий за 48 часов: фильтр 2 - информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }

    @Test(groups = "performance")
    public void filterThreeHistorySearchFortyEightHoursTest() throws Exception {
        Header.goTo(EVENTS_TAB);
        EventsHistoryPage.shouldAppear();
        EventsHistoryPage.setFromTime(new Date(), 48);
        EventsHistoryPage.setHighAndInfoSeverityLevel();
        startHarRecording("3.3.13 Запрос истории событий за 48 часов: фильтр 3 - высокий уровень критичности и информационное сообщение");
        EventsHistoryPage.findEvents();
        finishHarRecording();
    }
}
