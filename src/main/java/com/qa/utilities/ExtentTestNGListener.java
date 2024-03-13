package com.qa.utilities;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestNGListener implements IReporter {
	
		private static final String OUTPUT_FOLDER = "test-output/";
		private static final String FILE_NAME = "Extent.html";
		
	
	    private ExtentReports extent;
	 
	    
	    
	    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//	        extent = new ExtentReports(outputDirectory + File.separator + "ExtentReportTestNG.html", true);
//	    	init();
	 
	    	
	        for (ISuite suite : suites) {
	            Map<String, ISuiteResult> result = suite.getResults();
	 
	            for (ISuiteResult r : result.values()) {
	                ITestContext context = r.getTestContext();
	 
	                buildTestNodes(context.getPassedTests(), Status.PASS);
	                buildTestNodes(context.getFailedTests(), Status.FAIL);
	                buildTestNodes(context.getSkippedTests(), Status.SKIP);
	            }
	        }
	 
	        extent.flush();
//	        extent.close();
	    }
	 
	    private void buildTestNodes(IResultMap tests, Status status) {
	        ExtentTest test;
	 
	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                test = extent.startTest(result.getMethod().getMethodName());
	 
	                test.getTest().startedTime = getTime(result.getStartMillis());
	                test.getTest().endedTime = getTime(result.getEndMillis());
	 
	                for (String group : result.getMethod().getGroups())
	                    test.assignCategory(group);
	 
	                String message = "Test " + status.toString().toLowerCase() + "ed";
	 
	                if (result.getThrowable() != null)
	                    message = result.getThrowable().getMessage();
	 
	                test.log(status, message);
	 
	                extent.endTest(test);
	            }
	        }
	    }
	 
	    private Date getTime(long millis) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(millis);
	        return calendar.getTime();        
	    }
	}

