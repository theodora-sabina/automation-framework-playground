package framework.driver_factory;

import org.openqa.selenium.WebDriver;

import framework.driver_manager.ChromeDriverManager;
import framework.driver_manager.FirefoxDriverManager;

import static framework.Constants.CHROME;
import static framework.Constants.FIREFOX;

public class LocalDriverFactory implements IDriverFactory {


    @Override
    public WebDriver createInstance(String browser) {
       return
               switch (browser.toUpperCase()) {

            case CHROME -> new ChromeDriverManager().createDriver();
            case FIREFOX -> new FirefoxDriverManager().createDriver();
            default -> throw new IllegalStateException(browser + " is not supported!");

        };

    }
}
