package com.employee.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		htmlReporter= new ExtentHtmlReporter("./reports/myReport.html");//Specify Location
		htmlReporter.config().setDocumentTitle("Automation Report");//Tile of REPORT
		htmlReporter.config().setReportName("RestAssured API Testing");//name of the report
		htmlReporter.config().setTheme(Theme.DARK);


		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost ");
		extent.setSystemInfo("Environment","QA" );
		extent.setSystemInfo("User","Lokesh" );
	}

	public void onTestSuccess(ITestResult result) {
		//test=extent.createTest(result.getClass().getName());
		//test.createNode(result.getName());
		test=extent.createTest(result.getName());//Create New Entry in Report
		test.log(Status.PASS, "Test Case Passed Is "+ result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());//Create New Entry in Report

		test.log(Status.FAIL, "Test Case Failed is "+ result.getName());//to add name in extent Report
		test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());//to add error/exception in extent Report
	}
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());//Create New Entry in Report
		test.log(Status.SKIP, "Test Case Skipped is "+ result.getName());
	}
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
