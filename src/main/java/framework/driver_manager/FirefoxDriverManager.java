package framework.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import static framework.property_manager.PropertyProvider.isHeadless;

public class FirefoxDriverManager implements IDriverManager<FirefoxOptions>{

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(
                Boolean.TRUE.equals(isHeadless())
                        ? getOptions().setHeadless(true)
                        : getOptions()
        );
    }

    @Override
    public FirefoxOptions getOptions() {
        return new FirefoxOptions();
    }
}
