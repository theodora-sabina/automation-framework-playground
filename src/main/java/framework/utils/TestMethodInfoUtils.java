package framework.utils;

import java.util.Arrays;
import java.util.Objects;

import org.testng.ITestResult;

import framework.property_manager.PropertyProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static framework.Constants.CHROME;
import static framework.Constants.FIREFOX;
import static framework.Driver.getDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestMethodInfoUtils {

    public static boolean isApplitoolsTestScope(ITestResult result) {
        return Arrays.stream(getTestMethodGroups(result)).map(String::toUpperCase)
                .anyMatch(it -> it.contains("APPLITOOLS"));
    }

    public static String getTestMethodName(ITestResult result) {
        String testName;
        if (isApplitoolsTestScope(result)) {
            if (result.getParameters().length > 1) {
                testName = result.getParameters()[1].toString() + " > " + result.getParameters()[0].toString();
            } else {
                testName = getSimpleTestMethodName(result);
            }
        } else {
            testName = getSimpleTestMethodName(result);
        }
        return testName;
    }

    public static String getSimpleTestMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

    public static String[] getTestMethodGroups(ITestResult result) {
        return result.getMethod().getGroups();
    }

    public static String getTestMethodClassName(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName();
    }

    public static String getFullTestMethodName(ITestResult result) {
        return getTestMethodName(result) + " > " + getTestMethodDescription(result);
    }

    public static String getTestMethodDescription(ITestResult result) {
        String desc = result.getMethod().getDescription();
        return desc == null || desc.trim().isEmpty() ? "No description available" : desc;
    }

    public static String getBrowserNameIfNeeded(ITestResult result) {
        String browserIdentifier = "";
        String browserTitle = "Browser:  ";
        Object[] params = result.getParameters();

        if (isApplitoolsTestScope(result) && params.length > 0) {
            boolean isChrome = Arrays.stream(params).anyMatch(it -> it.toString().toUpperCase().contains(CHROME));
            if (isChrome) {
                browserIdentifier = browserTitle + CHROME + "<br>";
            } else {
                browserIdentifier = browserTitle + FIREFOX + "<br>";
            }
        } else if (isApplitoolsTestScope(result) && params.length == 0 || !Objects.isNull(getDriver())) {
            browserIdentifier = browserTitle + PropertyProvider.getBrowserInstanceName().toUpperCase() + "<br>";
        }
        return browserIdentifier;
    }
}