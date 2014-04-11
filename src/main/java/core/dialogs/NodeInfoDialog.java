package core.dialogs;

import com.codeborne.selenide.Condition;
import core.base.PageBase;
import junit.framework.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static core.helpers.Locators.get;

public class NodeInfoDialog extends PageBase {

    //region nodeInfo
    private static final By WINDOW = get("nodeInfo.dialog");
    private static final By NODE_NAME = get("nodeInfo.name");
    private static final By NODE_IP = get("nodeInfo.IP");
    private static final By NODE_ID = get("nodeInfo.node_id");
    private static final By NODE_TIME = get("nodeInfo.node_time");
    private static final By WEB_LINK = get("nodeInfo.web_link");
    private static final By NODE_STATE = get("nodeInfo.node_state");
    private static final By NODE_POLL_TIME = get("nodeInfo.poll_time");
    private static final By SHOW_ON_MAP_BUTTON = get("nodeInfo.show_on_map");
    private static final By POLL_NODE_BUTTON = get("nodeInfo.poll");
    private static final By CLOSE_BUTTON = get("nodeInfo.close");
    //endregion


    //region nodeInfo.detailedInfoTab
    private static final By DETAILED_INFO_TAB = get("nodeInfo.detailedInfoTab");
    private static final By COMMON_PARAMS_TAB = get("nodeInfo.detailedInfoTab.commonParamsTab");
    private static final By INFO_COMMON_PARAMS_TAB = get("nodeInfo.detailedInfoTab.commonParamsTab.information");
    private static final By COMMON_PARAMS_TAB_OPEN = get("nodeInfo.detailedInfoTab.commonParamsTabOpen");
    private static final By COMMON_PARAMS_TAB_CLOSE = get("nodeInfo.detailedInfoTab.commonParamsTabClose");
    private static final By VIPNET_PARAMS_TAB = get("nodeInfo.detailedInfoTab.vipnetParamsTab");
    private static final By VIPNET_PARAMS_TAB_OPEN = get("nodeInfo.detailedInfoTab.vipnetParamsTabOpen");
    private static final By VIPNET_PARAMS_TAB_CLOSE = get("nodeInfo.detailedInfoTab.vipnetParamsTabClose");
    private static final By NETWORK_INTERFACES_TAB = get("nodeInfo.detailedInfoTab.networkInterfacesTab");
    private static final By NETWORK_INTERFACES_TAB_OPEN = get("nodeInfo.detailedInfoTab.networkInterfacesTabOpen");
    private static final By NETWORK_INTERFACES_TAB_CLOSE = get("nodeInfo.detailedInfoTab.networkInterfacesTabClose");
    private static final By MFTP_PARAMS_TAB = get("nodeInfo.detailedInfoTab.mftpParamsTab");
    private static final By MFTP_PARAMS_TAB_OPEN = get("nodeInfo.detailedInfoTab.mftpParamsTabOpen");
    private static final By MFTP_PARAMS_TAB_CLOSE = get("nodeInfo.detailedInfoTab.mftpParamsTabClose");
    private static final By PASSIVE_ELEMENT_TAB = get("nodeInfo.detailedInfoTab.passiveElementParamsTab");
    private static final By PASSIVE_ELEMENT_TAB_OPEN = get("nodeInfo.detailedInfoTab.passiveElementParamsTabOpen");
    private static final By PASSIVE_ELEMENT_TAB_CLOSE = get("nodeInfo.detailedInfoTab.passiveElementParamsTabClose");
    //endregion

    private static final By CURRENT_EVENTS_TAB = get("nodeInfo.currentEventsTab");
    private static final By STOP_BUTTON = get("nodeInfo.currentEventsTab.stopButton");

    private static final By EVENTS_HISTORY_TAB = get("nodeInfo.eventsHistoryTab");

    private  static List<String> COMMON_TAB_DATA;
    private  static List<String> NODE_INFO_DATA = Arrays.asList($(NODE_NAME).text(), $(NODE_ID).text(), $(NODE_STATE).text());

    private static void extractCommonTabData() {
        String s_NODE_NAME = $(INFO_COMMON_PARAMS_TAB, 1).text();
        String s_NODE_ID = $(INFO_COMMON_PARAMS_TAB, 0).text();
        String s_NODE_STATE = $(INFO_COMMON_PARAMS_TAB, 2).text();
        COMMON_TAB_DATA = Arrays.asList(s_NODE_NAME, s_NODE_ID, s_NODE_STATE);
    }

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(NODE_NAME, NODE_IP, NODE_ID, NODE_TIME, WEB_LINK, NODE_STATE, NODE_POLL_TIME,
                SHOW_ON_MAP_BUTTON, POLL_NODE_BUTTON, CLOSE_BUTTON, DETAILED_INFO_TAB, CURRENT_EVENTS_TAB, EVENTS_HISTORY_TAB));
    }

    public static void checkExpectedTabs() {
        checkExpectedElements(Arrays.asList(COMMON_PARAMS_TAB, VIPNET_PARAMS_TAB, NETWORK_INTERFACES_TAB, MFTP_PARAMS_TAB, PASSIVE_ELEMENT_TAB));
    }

    public static void clickTabs() {
        List<By> clicker = Arrays.asList(COMMON_PARAMS_TAB_OPEN, COMMON_PARAMS_TAB_CLOSE, VIPNET_PARAMS_TAB_OPEN, VIPNET_PARAMS_TAB_CLOSE, NETWORK_INTERFACES_TAB_OPEN, NETWORK_INTERFACES_TAB_CLOSE, MFTP_PARAMS_TAB_OPEN, MFTP_PARAMS_TAB_CLOSE, PASSIVE_ELEMENT_TAB_OPEN, PASSIVE_ELEMENT_TAB_CLOSE);
        for (By se : clicker)
        {
            $(se).click();
            if (se == COMMON_PARAMS_TAB_OPEN)
                extractCommonTabData();
        }
    }

    public static void shouldAppear(){
        $(WINDOW).waitUntil(Condition.appears, 30000);
    }

    public static void closeDialog() {
        $(CLOSE_BUTTON).click();
    }

    public static void compareInfo() {
        Assert.assertEquals("Don't match!", COMMON_TAB_DATA, NODE_INFO_DATA);
    }

    public static void checkStopButton() {
        $(CURRENT_EVENTS_TAB).click(); //click currentEventsTab
        checkExpectedElements(Arrays.asList(STOP_BUTTON));
    }

    public static void clickDetailedInfoTab() {
        $(DETAILED_INFO_TAB).click();
    }

    public static void clickCurrentEventsTab() {
        $(CURRENT_EVENTS_TAB).click();
    }

    public static void clickEventsHistoryTab() {
        $(EVENTS_HISTORY_TAB).click();
    }

    public static void clickPollNode() {
        $(POLL_NODE_BUTTON).click();
    }
}
