package framework.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static framework.Driver.getDriver;
import static framework.utils.waits.WaitUtils.waitForPageToLoad;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActionsUtils {

    public static void dragAndDrop(WebElement from, WebElement to) {
        new Actions(getDriver())
                .dragAndDrop(from, to)
                .build()
                .perform();
        waitForPageToLoad();
    }
}
