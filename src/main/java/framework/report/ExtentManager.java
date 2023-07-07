package framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import framework.property_manager.PropertyProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtentManager {

    public static final ExtentReports extentReport = new ExtentReports();

    public static ExtentReports setUpExtentReports() {
        ExtentSparkReporter spark = new ExtentSparkReporter(PropertyProvider.getUserDir() +
                PropertyProvider.getScreenshotPathAsString() + "/extent-report.html")
                .config(ExtentSparkReporterConfig
                        .builder()
                        .theme(Theme.DARK)
                        .documentTitle("Automation Report")
                        .reportName("Automation Report")
                        .timeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a")
                        .offlineMode(true)
                        .build())
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY})
                .apply();
        extentReport.attachReporter(spark);
        return extentReport;
    }
}

