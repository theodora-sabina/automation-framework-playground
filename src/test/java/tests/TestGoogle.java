package tests;

import org.testng.annotations.Test;

import framework.annotations.SmokeTest;
import framework.pages.GoogleStartPage;

import static framework.Driver.openBrowser;

public class TestGoogle extends BeforeTestBase {

    public static String url = "http://www.google.com";

    @SmokeTest
    @Test
    public static void test() {
       // throw new IllegalStateException("Error!");
        openBrowser(url);
        GoogleStartPage googlePage = new GoogleStartPage();
        googlePage.printElement();
    }
}
