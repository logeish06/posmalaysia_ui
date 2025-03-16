package com.posmalaysia.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {
    private static ExtentReports extent;
    public static ExtentTest test;

    public static void setupReport() {
        if (extent == null) {  // Initialize only once
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Pos Malaysia Automation Report");
            htmlReporter.config().setReportName("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
    }

    public static void startTest(String testName) {
        if (extent == null) {
            setupReport();
        }
        test = extent.createTest(testName);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
