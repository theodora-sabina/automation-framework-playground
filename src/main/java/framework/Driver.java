package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import framework.report.ExtentTestManager;

public class Driver {

    private Driver() {
    }

    private static final Logger LOG = LogManager.getLogger(ExtentTestManager.class);

    public static ThreadLocal<WebDriver> thread = new ThreadLocal<>();


    public static void setDriver(WebDriver driverInstance){
        thread.set(driverInstance);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            quitBrowser();
        }));
    }

    public static WebDriver getDriver(){
       return thread.get();
    }

    public static void quitBrowser(){
        if (getDriver() != null) {
            getDriver().quit();
        }
        thread.remove();
    }

    public static void openBrowser(String url) {
        LOG.info("Open browser");
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }

    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
