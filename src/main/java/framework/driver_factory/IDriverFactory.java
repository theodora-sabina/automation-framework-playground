package framework.driver_factory;

import org.openqa.selenium.WebDriver;

public interface IDriverFactory {

    WebDriver createInstance(String browser);
}
