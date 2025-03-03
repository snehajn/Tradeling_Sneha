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

	public void ValidateWaterOption() throws InterruptedException

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {

			AndroidActions w = new AndroidActions(driver);

			registerPage.selectLanguage();

			try {
				wait.until(ExpectedConditions.visibilityOf(registerPage.loginPagetitle));
				AssertJUnit.assertTrue(registerPage.loginPagetitle.isDisplayed());
				System.out.println(registerPage.loginPagetitle + "Assertion passed: Element is visible.");
			} catch (TimeoutException e) {
				Assert.fail("Element did not appear within the timeout.");
			}

			AssertJUnit.assertTrue(registerPage.loginPagetitle.isDisplayed());
			
			//register the user 
			registerPage.gotoregister();
			registerPage.Register();

			registerPage.continueasGuestBtn.click();

			// home page
			Thread.sleep(3000);

			/*
			 * while (!w.isDisplayed(homePage.delivertotext)) { Thread.sleep(3000);
			 * System.out.println("Element is not visible yet"); }
			 */
			AssertJUnit.assertTrue(homePage.delivertotext.isDisplayed());
			
			//Grant location access
			homePage.locationpermission();
			
			homePage.clickExpress();
			
			homePage.Wateroption();
			

		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted: " + e.getMessage());
			/*
			 * catch (Exception e) { System.out.println("Unexpected error: " +
			 * e.getMessage());
			 */
		}
	}
}