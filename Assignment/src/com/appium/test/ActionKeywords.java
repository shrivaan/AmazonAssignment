package com.appium.test;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class ActionKeywords extends ExcelUtils {
	int iRow;

	public static AndroidDriver driver;




	public static void SwipeAction(int startX, int startY, int endX, int endY) throws Exception{

		@SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(startX,startY))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(endX, endY))
		.release()
		.perform();
	}

	public static void scrollToElementByTextContains(String text) {
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
	            ".scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0));");
	}
	
	public static void swipeScreen (String direction) throws Exception {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();

		int top = (int) Math.round(y*0.27);
		int bottom = (int) Math.round(y*0.75);

		try {
			switch(direction.toUpperCase()) {
			case "UP":
				SwipeAction(50, bottom, 50, top);
				break;
			case "DOWN":
				SwipeAction(50, bottom, 50, top);
				break;
			}
		} catch (IllegalArgumentException IAE) {
			System.out.println("Invalid direction");
		}
	}
}