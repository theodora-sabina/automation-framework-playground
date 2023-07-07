package framework.driver_factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import static framework.property_manager.PropertyProvider.getRemoteStatus;

public class DriverFactory {

    private static final Logger LOG = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createInstance(String browser){
        WebDriver webdriver;
        if (!getRemoteStatus()) {
            LOG.info("Starting '{}' locally", browser);
            webdriver = new LocalDriverFactory().createInstance(browser);
        } else if (getRemoteStatus()) {
            LOG.info("Starting '{}' remotely", browser);
            webdriver = new RemoteDriverFactory().createInstance(browser);
        } else {
            throw new IllegalStateException("Use Local or remote to run tests");
        }
        return webdriver;
    }
}
