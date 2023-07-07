package utils;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import static framework.LogConstants.*;
import static framework.report.ExtentTestManager.*;
import static framework.utils.ScreenshotUtil.captureScreenshotAndAddToExtent;
import static framework.utils.TestMethodInfoUtils.*;

public class TestListener implements ITestListener, IExecutionListener {

        private static final Logger LOG = LogManager.getLogger(TestListener.class.getName());

        @Override
        public void onExecutionStart() {
           // ZephyrManager.startZephyr();
        }

        @Override
        public synchronized void onTestStart(ITestResult result) {
            LOG.info(logOnTestResult(result, "STARTED"));

            startTest(getFullTestMethodName(result));
            String methodInfo =
                    "<b>" + getBrowserNameIfNeeded(result) +
                            "Class name:  " + getTestMethodClassName(result) + "<br>" +
                            "Test method name:  " + getTestMethodName(result) + "<br>" +
                            "Test method ID:  " + getTestMethodDescription(result) + "</b>";

            getTest()
                    .assignCategory(result.getMethod().getGroups())
                    .info(methodInfo);
        }

        @Override
        public synchronized void onTestSuccess(ITestResult result) {
            LOG.info(logOnTestResult(result, PASSED_TEST));

            String logText = "<b>TEST PASSED : " + getFullTestMethodName(result) + "</b>";

            getTest().pass(MarkupHelper.createLabel(logText, ExtentColor.GREEN))
                    .assignCategory(PASSED_TEST);
        }

        @Override
        public synchronized void onTestFailure(ITestResult result) {
            LOG.error(logOnTestResult(result, FAILED_TEST) + "\nReason: " + result.getThrowable());

            String fullMethodName = getFullTestMethodName(result);
            String logText = "<b>TEST FAILED : " + fullMethodName + "</b>";

            getTest().log(Status.FAIL, MarkupHelper.createLabel(logText, ExtentColor.RED))
                    .assignCategory(FAILED_TEST);
            expandStackTrace(result);

            captureScreenshotAndAddToExtent(fullMethodName);
        }

        @Override
        public synchronized void onTestSkipped(ITestResult result) {
            LOG.error(logOnTestResult(result, SKIPPED_TEST));

            String logText = "<b>TEST SKIPPED : " + getFullTestMethodName(result) + "</b>";

            getTest().log(Status.SKIP, MarkupHelper.createLabel(logText, ExtentColor.ORANGE))
                    .assignCategory(SKIPPED_TEST);
            expandStackTrace(result);
        }

        @Override
        public void onExecutionFinish() {
            flushAndRemove();
        }

        private String logOnTestResult(ITestResult result, String status) {
            return "\n\nTest: " + getTestMethodClassName(result) + " # " + getTestMethodName(result) + " "
                    + status + "!" + "\n";
        }

        private void expandStackTrace(ITestResult result) {
            String message = result.getThrowable().getMessage();
            String stackTrace = Arrays.toString(result.getThrowable().getStackTrace())
                    .replaceAll(", ", "<br><span class=\"tabbed\"></span>");

            String infoBlock = "<details>"
                    + "<summary>Exception Occured : Click To View</summary>"
                    + "<p><br>"
                    + message
                    + "<br><br>"
                    + "<span class=\"tabbed\"></span>" + stackTrace
                    + "</p></details>";

            switch (result.getStatus()) {
            case ITestResult.FAILURE:
                getTest().fail(infoBlock);
                break;

            case ITestResult.SKIP:

            case ITestResult.CREATED:
                getTest().skip(infoBlock);
                break;
            }
        }

}
