package framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static framework.Driver.getDriver;

/** Utility static class to work with Selenium waits
 *
 * @see JavascriptExecutor */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsExecutorUtils {

    public static void performScriptExecution(String script) {
        ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public static void performFillInEmailField(String email, WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + email + "';", element);
    }

    public static void performClick(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void performScrollIntoView(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

