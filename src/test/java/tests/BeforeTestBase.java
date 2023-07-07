package tests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import framework.annotations.SmokeTest;

public class BeforeTestBase extends DriverTestBase {

    private static final Logger LOG = LogManager.getLogger(BeforeTestBase.class);

    @BeforeMethod
    public static void checkSmoke(Method m){
        if (m.isAnnotationPresent(SmokeTest.class)){
            LOG.info("This is a smoke test!");
        }
    }
}
