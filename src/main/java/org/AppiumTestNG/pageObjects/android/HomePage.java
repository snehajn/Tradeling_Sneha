package org.AppiumTestNG.pageObjects.android;

import org.AppiumTestNG.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends AndroidActions{
	
	AndroidDriver driver;

	
	public HomePage(AndroidDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}
	
	
	@AndroidFindBy(id="com.android.permissioncontroller:id/grant_dialog")
	public WebElement locationDialog;
	

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	public WebElement allowlocationPermission;

	@AndroidFindBy(accessibility="Deliver to")
	public WebElement delivertotext;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView")
	public WebElement scrollableContent;
	
	
	@AndroidFindBy(accessibility="Categories\\nTab 2 of 5")
	public WebElement catgegoriesOption;	
	

	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"Express\"")
	public WebElement ExpressOption;	
	

	@AndroidFindBy(accessibility="Water")
	public WebElement waterOption;	


	
	///ennd
	
	
			
	public void gotoCatgories()
	{

		Assert.assertTrue(catgegoriesOption.isDisplayed(), "Catgories Option is displayed on home page");
		
		catgegoriesOption.click();
		
		
	}
	public void clickExpress()
	{

		Assert.assertTrue(catgegoriesOption.isDisplayed(), "Catgories Option is displayed on home page");
		
		ExpressOption.click();
		
		
	}
	public void Wateroption()
	{

		Assert.assertTrue(catgegoriesOption.isDisplayed(), "Catgories Option is displayed on home page");
		
		waterOption.click();
		
		
	}
	public void locationpermission()
	{

		//Location permission
		if(locationDialog.isDisplayed()) {
			
			allowlocationPermission.click();
			
		}
				
		Assert.assertTrue(delivertotext.isDisplayed(), "Location permissiion granted Home page is displayed");
		
		
	}
	
}
