package org.AppiumTestNG;

import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.google.common.collect.ImmutableMap;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.AppiumTestNG.TestUtils.AndroidBaseTest;
import org.AppiumTestNG.pageObjects.android.HomePage;
import org.AppiumTestNG.utils.AndroidActions;
import org.AppiumTestNG.utils.PopupHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class Tradeling_Waterbottle extends AndroidBaseTest {

	

	@Test
	public void ValidateWaterOption() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	   


	  //  try {
	        AndroidActions w = new AndroidActions(driver);
	        wait.until(ExpectedConditions.visibilityOf(registerPage.languagetext));
	        registerPage.selectLanguage();

	       // wait.until(ExpectedConditions.visibilityOf(registerPage.loginPagetitle));
	        Assert.assertTrue(registerPage.loginPagetitle.isDisplayed());
	        System.out.println("Assertion passed: Element is visible.");
	        
	        registerPage.gotoregister();
	        registerPage.Register();
	        AndroidActions.clickElement(registerPage.continueasGuestBtn);
	        Thread.sleep(3000);

	        Assert.assertTrue(homePage.delivertotext.isDisplayed());
	        homePage.locationpermission();
	        homePage.clickExpress();
	        homePage.Wateroption();
	        
	        System.out.println("Waiting for popup to be handled...");
	        latch.await();
	        System.out.println("Popup handled. Continuing with the test...");
			/*
			 * } catch (TimeoutException e) {
			 * Assert.fail("Element did not appear within the timeout."); return; } catch
			 * (NoSuchElementException e) { System.out.println("Element not found: " +
			 * e.getMessage()); } catch (InterruptedException e) {
			 * System.out.println("Thread was interrupted: " + e.getMessage()); }
			 */	}

}