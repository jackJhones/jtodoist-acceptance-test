package com.todoist.developer.rest.acceptance.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("===========");
        System.out.println("Test Started: " + result.getName());
        System.out.println();
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Successfully Finished: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    public void onStart(ITestContext context) {
        System.out.println("Output directory: " + context.getOutputDirectory());
    }

    public void onFinish(ITestContext context) {
        System.out.println("PASSED TEST CASES: " + context.getPassedTests());
        System.out.println("FAILED TEST CASES:" + context.getFailedTests());
        System.out.println("Test completed on: " + context.getEndDate().toString());
    }
}
