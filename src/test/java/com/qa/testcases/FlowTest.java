package com.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.FlowPage;
import com.qa.pages.SideMenuPage;

import io.appium.java_client.android.AndroidDriver;

public class FlowTest extends TestBase{

	public AndroidDriver mobileDriver;
	
	@BeforeMethod
	public void initialize() {
		mobileDriver = intializeDriver();
	}
	
	@Test(description = "Verify context under Help!")
	public void verifyHelpTest() {
		FlowPage fp = new FlowPage(mobileDriver);
		fp.accept();
		SideMenuPage sp = fp.clickOnSideMenu();
		sp.selectHelpAndFeedback();
		Assert.assertTrue(sp.verifyHelpOnDocumentationIsDisplayed(), "Help context - help on documentations is not displayed");
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {
		this.mobileDriver.quit();
	}
}
