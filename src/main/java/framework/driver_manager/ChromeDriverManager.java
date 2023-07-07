package framework.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import static framework.property_manager.PropertyProvider.isHeadless;

public class ChromeDriverManager implements IDriverManager<ChromeOptions>{


    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(
                Boolean.TRUE.equals(isHeadless())
                        ? getOptions().setHeadless(true)
                        : getOptions()
        );
    }

    @Override
    public ChromeOptions getOptions() {
        return new ChromeOptions();
    }
}
