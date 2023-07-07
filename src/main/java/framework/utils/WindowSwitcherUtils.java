package framework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static framework.Driver.getDriver;

/** Utility static class to work with Selenium window handlers*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WindowSwitcherUtils {
    /**
     * Switch focus from current window to next open window
     *
     */
    public static void switchFocusFromCurrentWindow() {
        for (String browserWindow : getDriver().getWindowHandles()) {
            String mainWindow = getDriver().getWindowHandle();
            if (!browserWindow.equals(mainWindow)) {
                getDriver().switchTo().window(browserWindow);
                break;
            }
        }
    }

    /**
     * Switch focus from current window to another window
     *
     * @param windowHandle The handle of the window as {@link String}
     */
    public static void switchFocusToWindow(String windowHandle){
        getDriver().switchTo().window(windowHandle);
        getDriver().switchTo().defaultContent();
    }

    /**
     * Close window
     *
     * @param windowHandle The handle of the window as {@link String}
     */
    public static void closeWindow(String windowHandle){
        getDriver().switchTo().window(windowHandle).close();
    }
}
