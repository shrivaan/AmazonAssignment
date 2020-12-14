package com.appium.test;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class Amazon extends ActionKeywords {




	@BeforeClass
	public void beforeClass() {
//		Please change the app path 
		File app = new File("Add your path");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Samsung Device");
		capabilities.setCapability("platformVersion", "7.0.0");
		capabilities.setCapability("app", app.getAbsolutePath());
//		Please change the device id accordingly
		capabilities.setCapability("udid", "ZY223J6CS4");
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		//		com.amazon.mShop.splashscreen.StartupActivity
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@Test (priority=1)
	public void validateLogin() {
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/sign_in_button")).click();
//		Please change the username and password
		driver.findElement(By.id("ap_email_login")).sendKeys("xyz@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("password");
		driver.findElement(By.id("signInSubmit")).click();
		Assert.assertTrue(driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).isDisplayed());
	}

	@Test (priority=2)
	public void searchProduct() {
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("65-inch TV");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		String sResult = driver.findElementById("com.amazon.mShop.android.shopping:id/rs_results_count_text").getText();
		Assert.assertTrue(sResult.contains("Results"));
	}

	@Test (priority=3)
	public void selectProduct() throws Exception {
		Thread.sleep(5000);
		scrollToElementByTextContains("448");
		//		swipeScreen("UP");
		@SuppressWarnings("unchecked")
		List<WebElement> sPriceElements = driver.findElementsByXPath("//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView");
		for (WebElement element : sPriceElements){
			String myText = element.getText();
//			System.out.println(myText);
			if (myText.contains("448")) {
				element.click();
				swipeScreen("UP");
				driver.findElement(By.id("add-to-cart-button")).click();
			}
		}
	}
}
