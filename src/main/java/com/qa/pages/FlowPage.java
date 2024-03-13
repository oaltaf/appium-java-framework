package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

import io.appium.java_client.android.AndroidDriver;

public class FlowPage extends TestBase{
	
	public AndroidDriver mobileDriver;
	
	@FindBy(id="android:id/button1")
	WebElement acceptBtn;
	
	@FindBy(xpath="//android.widget.ImageButton[@content-desc='Open drawer']")
	WebElement btnSideMenu;

	public FlowPage(AndroidDriver mobileDriver) {
		// TODO Auto-generated constructor stub
		this.mobileDriver = mobileDriver;
		PageFactory.initElements(mobileDriver, this);
	}

	public void accept() {
		// TODO Auto-generated method stub
		try {
			acceptBtn.click();
		}
		catch(Exception e) {
			throw new RuntimeException("Unable to click on accept");
		}
		
	}

	public SideMenuPage clickOnSideMenu() {
		// TODO Auto-generated method stub
		try {
			btnSideMenu.click();
			return new SideMenuPage(mobileDriver);
		}catch(Exception e) {
			throw new RuntimeException("Unable to click on side menu");
		}
//		return null;
	}

}
