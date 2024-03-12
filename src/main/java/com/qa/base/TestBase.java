package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.*;
import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.android.AndroidDriver;

public class TestBase {
	
	public AndroidDriver mobileDriver;
	
	public String appiumServer = "127.0.0.1";
	
	public int appiumPort = 4723;
	
	URL appiumURL = null;
	
	public static Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/OR.properties");
			
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	public AndroidDriver intializeDriver() {
		try {
			appiumURL = new URL("http://" + appiumServer + ":" + appiumPort + "/wd/hub");
			this.mobileDriver = new AndroidDriver(appiumURL, setAppCapabilitiesAndroid());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mobileDriver;
	}
	
	@SuppressWarnings("deprecation")
	public DesiredCapabilities setAppCapabilitiesAndroid() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/applications/"+"com.llamalab.");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		
		
		
		return cap;
		
	}
	
	public String takeScreenshotPath(String testCaseName, AndroidDriver mobileDriver) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) mobileDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		
	}
}
