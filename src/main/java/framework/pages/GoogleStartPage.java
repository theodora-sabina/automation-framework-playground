package framework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static framework.Driver.getDriver;
import static framework.Driver.quitBrowser;

public class GoogleStartPage {

    private static final Logger LOG = LogManager.getLogger(GoogleStartPage.class);

    @FindBy(css="input[name='q']")
    WebElement searchInput;

    public GoogleStartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void printElement()
    {
        LOG.info("Element is: {} ", searchInput);
        LOG.info("cevaaaa");
    }
}
