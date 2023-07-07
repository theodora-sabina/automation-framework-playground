package framework.utils;

import java.io.File;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import static framework.Driver.getDriver;
import static framework.property_manager.PropertyProvider.getScreenshotPathAsPath;
import static framework.report.ExtentTestManager.getTest;

/** Utility static class to work with Selenium screenshot feature*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenshotUtil {

    @SneakyThrows
    public static void captureScreenshot(String name) {
        String screenshotName = name + ".png";
        File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, getScreenshotPathAsPath().resolve(screenshotName).toFile());
        getTest().addScreenCaptureFromPath(screenshotName);
    }

    public static void captureScreenshotAndAddToExtent(String name) {
        if (!Objects.isNull(getDriver())) {
            captureScreenshot(name);
        }
    }
}