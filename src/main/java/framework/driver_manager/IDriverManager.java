package framework.driver_manager;

import org.openqa.selenium.WebDriver;

public interface IDriverManager<T> {

    WebDriver createDriver();

    T getOptions();
}
