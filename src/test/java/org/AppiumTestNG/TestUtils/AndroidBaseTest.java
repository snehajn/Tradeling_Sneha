package org.AppiumTestNG.TestUtils;

import org.testng.annotations.AfterMethod;
import org.AppiumTestNG.utils.AndroidActions;
import org.AppiumTestNG.utils.AppiumUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.AppiumTestNG.pageObjects.android.HomePage;
import org.AppiumTestNG.pageObjects.android.RegisterPage;
import org.AppiumTestNG.utils.AppiumUtils;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public RegisterPage registerPage;
	public HomePage homePage;
	public AndroidActions androidactions;
	private ExecutorService executorService;
    public CountDownLatch latch;
    
	
	

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException {
		
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "Google Nexus 4");
			capabilities.setCapability("app",
					"C:\\Users\\Sneha\\Desktop\\New_workspace\\AppiumFrameworkCloud\\1719410446.apk");
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("appPackage", "com.tradeling.mobilebuyerapp");
			// capabilities.setCapability("appActivity",
			// "com.tradeling.mobilebuyerapp.LoginActivity");
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("autoGrantPermissions", true);
			

			driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			executorService = Executors.newSingleThreadExecutor();
			executorService.submit(this::handleMarketingPopup);
			latch = new CountDownLatch(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		registerPage = new RegisterPage(driver);
		homePage = new HomePage(driver);
		androidactions= new AndroidActions(driver);
		
		
		
		//POPUP handler running for every test
		
		  		
		
		
	

	}
	 

	    private void handleMarketingPopup() {
	        try {
	            while (true) {
	                // Check for the marketing popup
	            	 if (driver.findElements(By.xpath("//android.widget.FrameLayout[@displayed='false']")).size() > 0) {
		                	WebElement popup = driver.findElement(By.xpath("//android.widget.FrameLayout[@displayed='false']"));
		                    popup.click();
		                    System.out.println("Popup detected and closed");
		                    WebElement backbuttonele = driver.findElement(By.className("android.widget.ImageView"));
		                    System.out.println(backbuttonele);
		                    AndroidActions.clickElement(backbuttonele);  
		                    System.out.println("back button clicked");
	                    
	                    // Pause the main thread for a moment
	                    //TimeUnit.SECONDS.sleep(2);
	                    
	                    // Count down the latch to let the main thread proceed
	                    latch.countDown();
	                    break;
	                }
	                // Check for popup every second
	                TimeUnit.SECONDS.sleep(1);
	            }
	            
	        }catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	    


	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		
		
		
		if (executorService != null) {
            executorService.shutdownNow();
        }
		driver.quit();
		// service.stop();
	}

}
