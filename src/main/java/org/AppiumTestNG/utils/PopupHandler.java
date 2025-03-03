package org.AppiumTestNG.utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.util.function.Function;

public class PopupHandler implements Runnable{
	
	AndroidDriver driver;
	
    //private AppiumDriver<MobileElement> driver;
    private String popupLocator;
    private String backbutton;
    private volatile boolean stopThread = false;

    public PopupHandler (AndroidDriver driver, String popupLocator, String backbutton) {
        this.driver = driver;
        this.popupLocator = popupLocator;
        this.backbutton =backbutton;
       
    }
    
    @Override
    public void run() {
        while (!stopThread) {
            try {
                //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Short wait to check frequently
                //WebElement popupElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(popupLocator)));
            	WebElement popupElement = driver.findElement(By.xpath("//android.widget.FrameLayout"));
            	WebElement backbuttonele = driver.findElement(By.xpath(backbutton));
            	
                if (popupElement.isDisplayed()) {
                	  // Explicit cast
                    System.out.println("Popup detected. Closing it.");
                    popupElement.click();
                    backbuttonele.click();
                }
            } catch ( NoSuchElementException e) {
                // No popup found, continue execution
            }

            try {
                Thread.sleep(3000); // Check for popups every 3 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    public void stopPopupHandler() {
        stopThread = true;
    }
}
