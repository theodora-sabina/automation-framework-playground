package framework.utils;

import org.openqa.selenium.WebElement;

import static framework.utils.waits.WaitUtils.waitUntilClickable;
import static framework.utils.waits.WaitUtils.waitUntilDisplayed;

public class ElementActions {

    private static final String ELEMENT_NOT_FOUND = "Element was not loaded";

    private ElementActions() {
    }

    public static <T> WebElement findElement(T ele) {
        return waitUntilDisplayed(ele, ELEMENT_NOT_FOUND + " on findElement : " + ele);
    }

    public static <T> void click(T ele) {
        waitUntilClickable(ele, ELEMENT_NOT_FOUND + " before click : " + ele).click();
    }

    public static <T> void sendKeys(T ele, String keys) {
        waitUntilDisplayed(ele, ELEMENT_NOT_FOUND + " before sending keys '" + keys + "' : " + ele).sendKeys(keys);
    }

    public static <T> String getText(T ele) {
        return waitUntilDisplayed(ele, ELEMENT_NOT_FOUND + " before getting text : " + ele).getText();
    }
}
