package com.idrx.qa.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.nio.file.Files;
import com.idrx.qa.base.TestBase;

public class ExtentReportListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Tester", "Vimal");
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(className + " : " + methodName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = TestBase.driver; // Use static driver from TestBase

        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
        if (screenshotPath != null) {
            test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        WebDriver driver = TestBase.driver;  // Use thread-safe getter
        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
        if (screenshotPath != null) {
            try {
                test.get().skip("Test Skipped: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                test.get().skip("Test Skipped: " + result.getThrowable());
            }
        } else {
            test.get().skip("Test Skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.get().warning("Test failed but within success percentage: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "test-output/screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs(); // Ensure directory exists
            Files.copy(src.toPath(), dest.toPath());
            return dest.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
