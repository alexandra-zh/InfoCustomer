package core.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.base.PageBase;
import core.helpers.Actions;
import core.helpers.Waiter;
import junit.framework.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static core.helpers.Locators.get;

public class ManageRulesPage extends PageBase {
    private static final String TITLE = "Настройки правил анализа";
    //region rule type dialog
    private static final By RULE_TYPE_DIALOG = get("manageTab.rules.ruleTypeDialog");
    private static final By SIMPLE_RULE_RADIO = get("manageTab.rules.simpleRuleRadio");
    private static final By COMPLEX_RULE_RADIO = get("manageTab.rules.complexRuleRadio");
    private static final By DIALOG_OK_BUTTON = get("manageTab.rules.ruleTypeDialog.OK");
    private static final By DIALOG_CANCEL_BUTTON = get("manageTab.rules.ruleTypeDialog.CANCEL");
    //endregion

    //region Locators
    private static final By RULES_CHECKBOXES = get("manageTab.rules.checkboxesRules");
    private static final By TREE_RULES = get("manageTab.rules.treeRules");
    private static final By INNER_RULES = get("manageTab.rules.innerRules");
    private static final By RULE_NAME = get("manageTab.rules.ruleName");
    private static final By ADD_RULE_BUTTON = get("manageTab.rules.addButton");
    private static final By COPY_BUTTON = get("manageTab.rules.copyButton");
    private static final By DELETE_BUTTON = get("manageTab.rules.deleteButton");
    private static final By SAVE_BUTTON = get("manageTab.rules.saveButton");
    private static final By CANCEL_BUTTON = get("manageTab.rules.cancelButton");
    private static final By EDIT_MODE = get("manageTab.rules.editMode");
    private static final String RULE_REDACTOR = "#ruleExpression";
    private static final String RULES_FILE = "/rules.txt";
    private static String RULE = "//li[@class='ruleItem']//span[contains(text(),'%s')]";
    //endregion

    static List<String> RULES_FOLDERS = Arrays.asList("Встроенные правила", "Пользовательские правила", "Удалённые правила");

    public static void checkExpectedElements() {
        checkExpectedElements(Arrays.asList(ADD_RULE_BUTTON, COPY_BUTTON, DELETE_BUTTON, SAVE_BUTTON, CANCEL_BUTTON));
    }

    public static ElementsCollection getInnerRules() {
        return $$(INNER_RULES);
    }

    public static ElementsCollection getTreeRules() {
        return $$(TREE_RULES);
    }

    public static SelenideElement getRule(String rule){
        return  $(By.xpath(String.format(RULE, rule)));
    }

    public static void clickRule(int ruleNum) {
        $(INNER_RULES, ruleNum).click();
    }

    public static void clickCreateRule() {
        $(ADD_RULE_BUTTON).click();
    }

    public static void clickCreateSimpleRule() {
        clickCreateRule();
        $(RULE_TYPE_DIALOG).$(SIMPLE_RULE_RADIO).click();
        $(RULE_TYPE_DIALOG).$(DIALOG_OK_BUTTON).click();
    }

    public static void clickCreateComplexRule() {
        clickCreateRule();
        $(RULE_TYPE_DIALOG).$(COMPLEX_RULE_RADIO).click();
        $(RULE_TYPE_DIALOG).$(DIALOG_OK_BUTTON).click();
    }

    public static ElementsCollection getCheckBoxes(){
        return $$(RULES_CHECKBOXES);
    }

    public static SelenideElement getRuleRedactor(){
        return $(RULE_REDACTOR);
    }

    public static void checkRuleName(String rule){
        String EXCEPTION_MSG = "Rule name not match!";
        Assert.assertEquals(EXCEPTION_MSG, rule, $(RULE_NAME).val());
    }

    public static void verifyRuleFolders(){
        for (SelenideElement ruleFolder : ManageRulesPage.getTreeRules()){
            RULES_FOLDERS.contains(ruleFolder.text());
            ruleFolder.shouldBe(Condition.exist);
        }
    }

    public static void verifyInnerRules(){
        String EXCEPTION_MSG = "Not found: ";
        List<String> INNER_RULES_VALUES = Actions.getStringsFromFile(RULES_FILE);
        for(SelenideElement rule : ManageRulesPage.getTreeRules()){
            EXCEPTION_MSG += rule.text();
            Assert.assertTrue(EXCEPTION_MSG, INNER_RULES_VALUES.contains(rule.text()));
        }
    }

    public static void shouldAppear() {
        shouldAppear(TITLE);
    }

    public static void saveSettings() {
        $(SAVE_BUTTON).click();
        Waiter.waitForProcessing();
    }

    public static void cancelSettings() {
        $(CANCEL_BUTTON).click();
        Waiter.waitForProcessing();
    }

    public static void copyRule() {
        $(COPY_BUTTON).click();
        Waiter.waitForProcessing();
    }

    public static void deleteRule() {
        $(DELETE_BUTTON).click();
        Waiter.waitForProcessing();
    }

    public static void toEditMode() {
        $(EDIT_MODE).click();
        Waiter.waitForProcessing();
    }
}
