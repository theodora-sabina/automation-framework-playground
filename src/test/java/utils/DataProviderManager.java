package utils;

import org.testng.annotations.DataProvider;

public class DataProviderManager {

    private DataProviderManager() {
    }

    @DataProvider
    public static Object[][] getData() {
        return new Object[][] {{"First-Value"}, {"Second-Value"}};
    }
}
