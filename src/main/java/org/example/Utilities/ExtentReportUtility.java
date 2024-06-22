package org.example.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtility {

    public static ExtentReports getExtentReport() {
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter esr = new ExtentSparkReporter(path);
        esr.config().setReportName("Web automation result");
        esr.config().setDocumentTitle("Extent report");
        esr.config().setTheme(Theme.DARK);

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(esr);
        extentReports.setSystemInfo("Tester","Richa");
        return extentReports;
    }
}
