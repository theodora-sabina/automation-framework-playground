package framework.utils.waits;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static framework.Driver.getCurrentUrl;
import static framework.Driver.getDriver;
import static framework.report.ExtentTestManager.makeCompleteErrorLog;

/**
 * Utility static class to work with Selenium waits
 *
 * @see WebDriverWait
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE) public class WaitUtils {

    private static final Logger LOG = LogManager.getLogger(WaitUtils.class);

    private static final int WAIT_60_S = 60;
    private static final int WAIT_30_S = 30;

    public static void waitUntil(ExpectedCondition<?> condition) {
        waitUntil(condition, WAIT_60_S);
    }

    public static void waitUntil(ExpectedCondition<?> condition, int timeout) {
        waitUntil(condition, timeout, 500);
    }

    public static void waitUntil(ExpectedCondition<?> condition, int timeout, int poll) {
        new WebDriverWait(getDriver(), timeout, poll).until(condition);
    }

    public static void waitUntilTextPresented(By by, String text) {
        waitUntil(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public static <T> void waitUntilDisplayed(T element) {
        if (element.getClass().getName().contains("By")) {
            waitUntil(ExpectedConditions.visibilityOfElementLocated((By) element), WAIT_30_S);
        } else {
            waitUntil(ExpectedConditions.visibilityOf((WebElement) element), WAIT_30_S);
        }
    }

    public static <T> WebElement waitUntilDisplayed(T element, String info) {
        try {
            waitUntilDisplayed(element);
            return element instanceof WebElement ? (WebElement) element : getDriver().findElement((By) element);
        } catch (TimeoutException e) {
            makeCompleteErrorLog(info);
            throw new TimeoutException(e);
        }
    }

    public static <T> void waitUntilClickable(T element) {
        if (element.getClass().getName().contains("By")) {
            waitUntil(ExpectedConditions.elementToBeClickable((By) element), WAIT_30_S);
        } else {
            waitUntil(ExpectedConditions.elementToBeClickable((WebElement) element), WAIT_30_S);
        }
    }

    public static <T> WebElement waitUntilClickable(T element, String info) {
        try {
            waitUntilClickable(element);
            return element instanceof WebElement ? (WebElement) element : getDriver().findElement((By) element);
        } catch (TimeoutException e) {
            makeCompleteErrorLog(info);
            throw new TimeoutException(e);
        }
    }

    public static void waitUntilAnyDisplayed(List<WebElement> elements) {
        waitUntil(ExpectedConditions.visibilityOf(elements.get(0)));
    }

    public static <T> void waitUntilAllDisplayed(List<T> elements) {
        elements.forEach(WaitUtils::waitUntilDisplayed);
    }

    public static void waitUntilUrlContain(String text) {
        try {
            waitUntil(ExpectedConditions.urlContains(text), WAIT_30_S);
        } catch (TimeoutException e) {
            makeCompleteErrorLog("Current url does not contain: " + text + "\nUrl: " + getCurrentUrl());
            throw new TimeoutException(e);
        }
    }

    public static void waitForPageToLoad() {
        try {
            waitUntil(new PageLoadComplete());
        } catch (TimeoutException e) {
            LOG.error("Timed out after {} seconds waiting for page load complete", WAIT_60_S);
            throw e;
        }
    }

    public static void waitForAemSpinnerToLoad(int seconds) {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".foundation-ui-mask>.coral3-Wait")),
                seconds);
    }

    public static void waitForAemSpinnerToLoad() {
        waitForAemSpinnerToLoad(WAIT_60_S);
    }
}

