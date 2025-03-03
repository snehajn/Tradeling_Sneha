package org.AppiumTestNG.TestUtils;

import org.testng.annotations.AfterMethod;
import org.AppiumTestNG.utils.PopupHandler;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.AppiumTestNG.pageObjects.android.HomePage;
import org.AppiumTestNG.pageObjects.android.RegisterPage;
import org.AppiumTestNG.utils.AppiumUtils;
import org.aspectj.lang.annotation.Before;
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

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public RegisterPage registerPage;
	public HomePage homePage;
	private static PopupHandler popupHandler;

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//org//rahulshettyacademy//resources//data.properties");
		prop.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: prop.getProperty("ipAddress");
		System.out.println(ipAddress);

		// String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		// service = startAppiumServer(ipAddress,Integer.parseInt(port));
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		registerPage = new RegisterPage(driver);
		homePage = new HomePage(driver);
		//POPUP handler running for every test
		ExecutorService executor = Executors.newSingleThreadExecutor();
		PopupHandler popupHandler = new PopupHandler(driver, registerPage.framepopup, registerPage.backbutton);
		executor.submit(popupHandler);

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		
		popupHandler.stopPopupHandler();
		driver.quit();
		// service.stop();
	}

}
