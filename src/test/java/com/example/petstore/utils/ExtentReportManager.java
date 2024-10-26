package com.example.petstore.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extReports;
    private static ExtentTest test;
    private String reportTime;

    @Override
    public void onStart(ITestContext context) {
        reportTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss a"));
        extReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                "./src/test/resources/reports/Test-Reports_" + reportTime + ".html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("RestAssured+Java Petstore");
        sparkReporter.config().setReportName("Pet Store API Test Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");
        extReports.attachReporter(sparkReporter);
        setSystemInfo();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getName() + " started executing.");
        test = extReports.createTest(result.getTestClass().getName() + " - " +
                result.getMethod().getPriority() + ". " + result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.log(Status.INFO, result.getName() + " started executing.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logTestResult(result, Status.PASS, "successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.INFO, result.getThrowable());
        logTestResult(result, Status.FAIL, "failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.INFO, result.getThrowable());
        logTestResult(result, Status.SKIP, "skipped");
    }

    private void logTestResult(ITestResult result, Status status, String message) {
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(status, result.getName() + " got " + message + ".");
        System.out.println(result.getName() + " got " + message + ".");
    }

    @Override
    public void onFinish(ITestContext context) {
        extReports.flush();
        openReport();
    }

    private void setSystemInfo() {
        extReports.setSystemInfo("Application", "Pet Store Api");
        extReports.setSystemInfo("User", "Charles Lana");
        extReports.setSystemInfo("Environment", "QA");
    }

    private void openReport() {
        if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            return;
        }
        String extentReportPath = "./src/test/resources/reports/Test-Reports_" + reportTime + ".html";
        File extentReportFile = new File(extentReportPath);
        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
