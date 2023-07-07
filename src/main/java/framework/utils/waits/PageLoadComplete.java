package framework.utils.waits;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoadComplete implements ExpectedCondition<Boolean> {

    @Override
    public Boolean apply(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isJQueryComplete = true;
        boolean domReady = js.executeScript("return document.readyState").equals("complete");
        boolean isJQuery = Boolean.TRUE.equals(js.executeScript("return window.jQuery != undefined"));
        if (isJQuery) {
            isJQueryComplete = Boolean.TRUE.equals(js.executeScript("return jQuery.isReady"));
        }
        return domReady && isJQueryComplete;
    }
}
