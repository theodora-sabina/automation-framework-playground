package framework.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtentTestManager {

    private static final Logger LOG = LogManager.getLogger(ExtentTestManager.class);

    static ExtentReports extent = ExtentManager.setUpExtentReports();

    private static final ThreadLocal<ExtentTest> thread = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return thread.get();
    }

    public static void startTest(String testName) {
        LOG.info("\nCreating extent test {}", testName);
        thread.set(extent.createTest(testName));
    }

    public static void flushAndRemove() {
        extent.flush();
        thread.remove();
    }

    public static void logErrorToConsole(String info) {
        LOG.error("\n{}", info);
    }

    public static void makeCompleteInfoLog(String info) {
        LOG.info("\n{}", info);
        getTest().info(info);
    }

    public static void makeCompleteErrorLog(String info) {
        logErrorToConsole(info);
        getTest().fail(info.replace("\n", "<br>"));
    }

    public static void makeHighlightedCompleteErrorLog(String info) {
        logErrorToConsole(info);
        getTest().log(
                Status.FAIL,
                MarkupHelper.createLabel("<b>" + info.replace("\n", "<br>") + "</b>",
                        ExtentColor.RED));
    }
}


