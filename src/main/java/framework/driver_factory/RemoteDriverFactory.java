package framework.driver_factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import framework.driver_manager.ChromeDriverManager;
import framework.driver_manager.FirefoxDriverManager;

import static framework.Constants.CHROME;
import static framework.Constants.FIREFOX;
import static framework.property_manager.PropertyProvider.getGridUrl;

public class RemoteDriverFactory implements IDriverFactory{

    private static final Logger LOG = LogManager.getLogger(RemoteDriverFactory.class);

    @Override
    public WebDriver createInstance(String browser) {
            MutableCapabilities capability = switch (browser.toUpperCase()) {
                case CHROME -> new ChromeDriverManager().getOptions();
                case FIREFOX -> new FirefoxDriverManager().getOptions();
                default -> throw new IllegalStateException(browser + " is not supported!");
            };

        return createRemoteInstance(capability);
        }

        private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
            RemoteWebDriver remoteWebDriver = null;
            try {
                remoteWebDriver = new RemoteWebDriver(new URL(getGridUrl()), capability);
                remoteWebDriver.setFileDetector(new LocalFileDetector());
            } catch (MalformedURLException e) {
                LOG.error("URL is invalid or Grid is not available at the moment");
                LOG.error("Browser: {}", capability.getBrowserName(), e);
            } catch (IllegalArgumentException e) {
                LOG.error("Browser {} is not valid or recognized", capability.getBrowserName(), e);
            }
            return remoteWebDriver;
        }
}
