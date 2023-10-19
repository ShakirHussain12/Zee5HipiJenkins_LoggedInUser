package com.zee5.Android_HipiScripts;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;


public class LocationChange {

	
	
	private static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability("deviceName", "Android Emulator");

		dc.setCapability("platformName", "Android");

		dc.setCapability("appPackage", "com.google.android.apps.maps");

		dc.setCapability("appActivity", "com.google.android.maps.MapsActivity");

		dc.setCapability("noReset", true);

		dc.setCapability("automationName", "UiAutomator2");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Location location = new Location(11.1271, 78.6569, 1);
		driver.setLocation(location);
		System.out.println(location);

	}
	
	
	
	
	
	
}
