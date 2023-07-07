package framework.property_manager;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyProvider {

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getGridUrl() {
        return PropertyReader.getProperty("remote.web.driver.url");
    }


    public static String getBrowserInstanceName() {
        return PropertyReader.getProperty("browser.instance");
    }

    public static Boolean getRemoteStatus() {
        return Boolean.parseBoolean(PropertyReader.getProperty("remote.status"));
    }

    public static Boolean isHeadless() {
        return Boolean.parseBoolean(PropertyReader.getProperty("is.headless"));
    }

    public static String getScreenshotPathAsString() {
        return PropertyReader.getProperty("screenshot.folder.path");
    }

    public static Path getScreenshotPathAsPath() {
        return Paths.get(PropertyProvider.getUserDir() + PropertyProvider.getScreenshotPathAsString());
    }

    public static Boolean isEnvTest() {
        return Boolean.parseBoolean(PropertyReader.getProperty("is.env.test"));
    }
}
