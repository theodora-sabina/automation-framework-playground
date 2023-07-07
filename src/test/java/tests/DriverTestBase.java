package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import framework.Driver;
import framework.driver_factory.DriverFactory;
import framework.property_manager.PropertyProvider;
import utils.EnvListener;
import utils.TestListener;

import static framework.Driver.quitBrowser;

@Listeners({TestListener.class, EnvListener.class})
public class DriverTestBase {

    private static final Logger LOG = LogManager.getLogger(DriverTestBase.class);

    @BeforeTest(alwaysRun = true)
    public void createBrowser() {
        Driver.setDriver(new DriverFactory()
                .createInstance(PropertyProvider.getBrowserInstanceName()));
    }

    @AfterTest(alwaysRun = true)
    public void destroyBrowserAndSession() {
        LOG.info("Closing browser and Removing AEM session");
        quitBrowser();
    }
}
