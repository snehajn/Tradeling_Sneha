package org.AppiumTestNG.utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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
    private volatile boolean isHandlingPopup = false; 

    public PopupHandler (AndroidDriver driver) {
        this.driver = driver;
      
    }
    
    @Override
    public void run() {
    	while (!stopThread) {
            try {
                if (true) {
                    isHandlingPopup = true; // Notify main thread
                    System.out.println("Popup detected. Closing it...");

                   	WebElement popupElement = driver.findElement(By.xpath("//android.widget.FrameLayout"));
            	WebElement backbuttonele = driver.findElement(By.xpath(backbutton));

                    popupElement.click();
                    backbuttonele.click();
                    
                    System.out.println("Popup closed.");
                    isHandlingPopup = false; // Reset flag
                }
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                isHandlingPopup = false; // No popup found
            }

            try {
                Thread.sleep(3000); // Check for popups every 3 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        stopThread = true;
    }

    public boolean isPopupBeingHandled() {
        return isHandlingPopup;
    }
}
