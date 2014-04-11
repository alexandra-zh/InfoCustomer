package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import test.functional.MonitorMapTest;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static core.helpers.Locators.get;

public class MapPage extends PageBase {
    protected static final String MAP_LOCAL_COUNTRY_IMG = "/MAP_LOCAL_COUNTRY.PNG";
    protected static final String MAP_LOCAL_COUNTY_IMG = "/MAP_LOCAL_COUNTY.PNG";
    protected static final String MAP_LOCAL_DISTRICT_IMG = "/MAP_LOCAL_DISTRICT.PNG";
    protected static final String MAP_LOCAL_REGION_IMG = "/MAP_LOCAL_REGION.PNG";
    protected static final String MAP_LOCAL_CITY_IMG = "/MAP_LOCAL_CITY.PNG";

    protected static final By MAP = get("map.mapControl");
    public static final By SETTINGS = get("map.settingsContainer");
    public static final By SETTINGS_CLOSE_BUTTON = get("map.settings.close");
    public static final By MINIMAP = get("map.minimap");
    public static final By MAP_SELECT = get("map.selectMap");
    public static final By SETTINGS_GEOTAB = get("map.settings.geoTab");
    public static final By SETTINGS_NODETAB = get("map.settings.nodesTab");
    protected static final By NODE_SELECT = get("map.nodeCombo");
    protected static final By GEO_SELECT = get("map.geoCombo");
    protected static final By SETTINGS_BUTTON = get("map.settingButton");
    protected static final By HIDE_MINIMAP_BUTTON = get("map.hideMinimap");
    protected static final By SHOW_MINIMAP_BUTTON = get("map.showMinimap");
    protected static final By MAP_CONTROL_PANEL = get("map.controlPanel");
    protected static final By SETTINGS_RESTORE_BUTTON = get("map.settings.restoreButton");// todo: add
    protected static final By SETTINGS_GEOTAB_REF = get("map.settings.geoTabref");
    protected static final By SETTING_NODETAB_REF = get("map.settings.nodeTabref");

    public enum MapScale{
        Город, Район, Область, Округ, Страна
    }

    public static void openSettings() {
        $(SETTINGS_BUTTON).click();
    }

    public static void clickHideMinimap() {
        if($(HIDE_MINIMAP_BUTTON).isDisplayed()) $(HIDE_MINIMAP_BUTTON).click();
        else $(SHOW_MINIMAP_BUTTON).click();
    }

    public static void closeSettings() {
        $(SETTINGS_CLOSE_BUTTON).click();
    }

    public static void openGeoTab() {
        $(SETTINGS_GEOTAB_REF).click();
    }

    public static void openNodeTab() {
        $(SETTING_NODETAB_REF).click();
    }

    public static void restoreSettings() {
        $(SETTINGS_RESTORE_BUTTON).click();
    }

    public static List<String> getMapOptions() {
        return Arrays.asList($(MAP_SELECT).$$("option").getTexts());
    }

    public static void setMapType(String option) {
        if (!$(MAP_SELECT).getSelectedValue().equals(option)) {
            $(MAP_SELECT).selectOption(option);
        }
        sleep(2000); //wait map loaded
    }

    public static void chooseMapScale(MapScale scale) {
        $(MAP_CONTROL_PANEL).$(byText(scale.toString())).click();
        sleep(3000);
    }

    public static void dragMapBy(int xOffset, int yOffset) {
        actions().dragAndDropBy($(MAP), xOffset, yOffset).perform();
    }

    public static void zoomIn() {
        actions().doubleClick($(MAP)).perform();
    }

    public static void clickOnMap(){
        $(MAP).click();
    }

    public static void assertMapScale(MapScale scale){
        String imagePath;
        switch(scale) {

            case Страна:{
                imagePath = MAP_LOCAL_COUNTRY_IMG;
                break;
            }

            case Район:{
                imagePath = MAP_LOCAL_REGION_IMG;
                break;
            }

            case Область:{
                imagePath = MAP_LOCAL_DISTRICT_IMG;
                break;
            }

            case Округ:{
                imagePath = MAP_LOCAL_COUNTY_IMG;
                break;
            }

            case Город:{
                imagePath = MAP_LOCAL_CITY_IMG;
                break;
            }

            default:{
                throw new NotImplementedException();
            }
        }

        ScreenRegion region = new DesktopScreenRegion();
        Target target = new ImageTarget(new File(MonitorMapTest.class.getResource(imagePath).getPath()));
        target.setMinScore(0.7);
        Assert.assertTrue(region.wait(target, 5000) != null);
    }

    protected static List<By> getExpectedElements() {
        return Arrays.asList(MAP, MINIMAP, MAP_SELECT, NODE_SELECT, GEO_SELECT, SETTINGS_BUTTON);
    }
}
