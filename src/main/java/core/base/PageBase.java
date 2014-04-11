package core.base;

import core.helpers.Waiter;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PageBase {

    protected static void shouldAppear(String title){
        Waiter.waitForPageTitle(title);
    }

    protected static void checkExpectedElements(List<By> expectedElements){
        for (By element : expectedElements) {
            $(element).shouldBe(visible);
        }
    }
}
