package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import utils.DataProviderManager;

public class DataProviderTest extends DriverTestBase {

    private static final Logger LOG = LogManager.getLogger(BeforeTestBase.class);

    @Test(dataProvider = "getData", dataProviderClass = DataProviderManager.class)
    public static void testDateProvider(String entry){
        System.out.println(
                "Current Thread ID: "
                        + Thread.currentThread().getId() + " ENTRY: " + entry);
    }
}
