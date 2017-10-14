package com.epam.lab;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {
    final static Logger logger = Logger.getLogger(CustomTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test has started running:" + result.getMethod().getMethodName()
                + " at:" + result.getStartMillis());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Result was: success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Result was: failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test was skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Passed tests: " + context.getPassedTests());
        logger.info("Failed tests:" + context.getFailedTests());
    }
}
