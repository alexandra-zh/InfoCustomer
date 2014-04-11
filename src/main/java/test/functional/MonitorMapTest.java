package test.functional;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import core.base.TestBase;
import core.dialogs.PollGroupDialog;
import core.helpers.Actions;
import core.pages.MonitorMapPage;
import core.pages.MonitorPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Test(groups = "fast")
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class MonitorMapTest extends TestBase {
    private static final String MAP_OPTION_LOCAL = "Локальная карта";
    private static final String MAP_OPTION_STREET = "OpenStreetMap";
    private static final List<String> MAP_OPTIONS = Arrays.asList(MAP_OPTION_LOCAL, MAP_OPTION_STREET);

    @BeforeMethod
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        Actions.login();
        MonitorPage.goToMapTab();
        MonitorMapPage.shouldAppear();
    }

    @Test(groups = "fast")
    public void UIElementsTest ()  {
        MonitorMapPage.checkExpectedElements();
    }

    @Test(groups = "fast")
    public void mapSettingsTest ()  {
        MonitorMapPage.openSettings();
        $(MonitorMapPage.SETTINGS).should(appear);
        MonitorMapPage.openNodeTab();
        $(MonitorMapPage.SETTINGS_NODETAB).should(appear);
        MonitorMapPage.openGeoTab();
        $(MonitorMapPage.SETTINGS_GEOTAB).should(appear);
        MonitorMapPage.closeSettings();
        $(MonitorMapPage.SETTINGS).should(disappear);
    }

    @Test(groups = "fast")
    public void hostsListPollGroupWindowTest () {
        MonitorMapPage.clickPollGroup();
        PollGroupDialog.shouldAppear();
    }

    @Test(groups = "fast")
    public void mapOptionsTest () {
        Assert.assertTrue(MonitorMapPage.getMapOptions().containsAll(MAP_OPTIONS));
        $(MonitorMapPage.MAP_SELECT).getSelectedOption().shouldHave(text(MAP_OPTION_LOCAL));
    }

    @Test(groups = "fast")
    public void hideMinimapTest () {
        $(MonitorMapPage.MINIMAP).shouldBe(visible);
        MonitorMapPage.clickHideMinimap();
        $(MonitorMapPage.MINIMAP).shouldBe(disappear);
        MonitorMapPage.clickHideMinimap();
        $(MonitorMapPage.MINIMAP).shouldBe(visible);
    }

    @Test(groups = "slow")
    public void localMapZoomTest () {
        for(MonitorMapPage.MapScale scale : MonitorMapPage.MapScale.values()){
            MonitorMapPage.chooseMapScale(scale);
            MonitorMapPage.assertMapScale(scale);
        }
    }
}
