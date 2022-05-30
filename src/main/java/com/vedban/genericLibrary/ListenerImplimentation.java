package com.vedban.genericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListenerImplimentation implements ITestListener {
	ExtentReports report;
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+" is Pass");
		}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL, result.getThrowable());
		WebDriverLibrary weblDriverLibrary=new WebDriverLibrary();
		JavaLibrary javaLibrary=new JavaLibrary();
		test.addScreenCaptureFromPath(weblDriverLibrary.screenShot(BaseClass.staticdriver, javaLibrary, result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName()+" is sikpped");
		test.log(Status.SKIP, result.getThrowable());		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		report=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReport/extentReport.html");
		spark.config().setDocumentTitle("Vedban Report");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Vedban Report");
		report.attachReporter(spark);
		report.setSystemInfo("Environment", "TestNG Environment");
		report.setSystemInfo("Platform", "Windows");
		report.setSystemInfo("Version", "8.1");
		report.setSystemInfo("Build management tool", "Maven");
		report.setSystemInfo("Automation", "Selenium");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		
	}

}
