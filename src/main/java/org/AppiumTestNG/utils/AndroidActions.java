package org.AppiumTestNG.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.util.function.Function;

public class AndroidActions extends AppiumUtils{
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver)
	{
	
		this.driver = driver;
	}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	/*
	 * public void button_click(WebElement button) { //List<?> !button =
	 * !driver.findElements(By.id("!buttonID"));
	 * 
	 * if(button.isEmpty()){
	 * !driver.findElement(By.id("!ButtonBehindPopup")).click(); Thread.sleep(1000);
	 * } else{ !driver.findElement(By.id("!buttonID")).click(); Thread.sleep(1000);
	 * }
	 */
	
	

	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
		}while(canScrollMore);
	}
	
	public void scrollToText(String text)
	{
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));
		
		
	}
	
	public void popupWait (WebElement ele) {
		 FluentWait<AndroidDriver> wait =  new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(30))   // Maximum wait time
	                .pollingEvery(Duration.ofSeconds(5))   // Polling interval
	                .ignoring(NoSuchElementException.class);  // Ignore specific exceptions (optional)
	              
	                wait.until(new Function<WebDriver, WebElement>() {
	                    public WebElement apply(WebDriver driver) {
	                        return driver.findElement((By) ele);
	                    }
	                });

	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated((By) ele));
	        
	        // Perform actions on the popup if necessary
	        popup.click();
	    }
	
	public static boolean isDisplayed(WebElement element) {
        try {
            if(element.isDisplayed())
                return element.isDisplayed();
            }catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }
	
	public void waitf(WebElement ele) {
	    Wait<AndroidDriver> wait = new FluentWait<>(driver)
	        .withTimeout(Duration.ofSeconds(15))  // Max wait time
	        .pollingEvery(Duration.ofSeconds(1))  // Retry every second
	        .ignoring(NoSuchElementException.class);

	    try {
	        WebElement element = wait.until(driver -> {
	            if (ele.isDisplayed()) {  // Check if the element is visible
	                return ele;
	            }
	            return null;  // Continue waiting if element is not visible
	        });

	        if (element != null) {
	            element.click();
	        }
	    } catch (TimeoutException e) {
	        System.out.println("Element not found after multiple retries.");
	    }
	}

	private void waitForElementToBeVisible(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    try {
	        wait.until(ExpectedConditions.visibilityOf(element));
	    } catch (TimeoutException e) {
	        Assert.fail("Element " + element + " was not found within the timeout.");
	    }
	}
	

}
