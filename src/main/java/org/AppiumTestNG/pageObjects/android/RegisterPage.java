package org.AppiumTestNG.pageObjects.android;

import org.AppiumTestNG.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterPage extends AndroidActions{
	
	AndroidDriver driver;

	
	public RegisterPage(AndroidDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}

	
	@AndroidFindBy(accessibility="English")
	public WebElement language_Eng;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(accessibility="CONTINUE")
	public WebElement continuebtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Login with Google\"]")
	public WebElement loginPagetitle;
	
	
	/*
	 * @AndroidFindBy(xpath="//android.widget.FrameLayout") public WebElement
	 * framepopup;
	 */
	public String framepopup="//android.widget.FrameLayout";
	
	public String backbutton="//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.ImageView[1]";
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Close']")
	public WebElement framepopupclose;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"English\"]")
	public WebElement langEnglish;
	
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]")
	public WebElement Tradelinglogo;
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"Log in to your account\"]")
	public WebElement loginTitle;

	@AndroidFindBy(accessibility="DON'T HAVE AN ACCOUNT? REGISTER")
	public WebElement startregisterClick;
	
	@AndroidFindBy(accessibility="Create your profile")
	public WebElement registertTitle;
	
	@AndroidFindBy(className="android.widget.EditText")
	public WebElement inputEmailPh;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@class, \"android.view.View\"]")
	public WebElement inputEmailPhValerror;
	
	
	@AndroidFindBy(accessibility="Language")
	public WebElement languagetext;
	
	
	
	@AndroidFindBy(accessibility="CONTINUE AS GUEST")
	public WebElement continueasGuestBtn;
	
	@AndroidFindBy(accessibility="CONTINUE")
	public WebElement continueBtn;
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void selectLanguage()
	{
		
		try {
		if(languagetext.isDisplayed()) {
		Assert.assertTrue(languagetext.isDisplayed(), "English language is selected");
		continuebtn.click();
		}
	} catch (Exception e) {
        System.out.println("Failed to click element: " + continuebtn + " due to: " + e.getMessage());
    }
		
	}
	
	public void gotoregister()
	{

		Assert.assertTrue(Tradelinglogo.isDisplayed(), "Login page is displayed");
		
		AndroidActions.clickElement(startregisterClick);
		
		Assert.assertTrue(registertTitle.isDisplayed(), "Register page is displayed");
		
		Assert.assertTrue(inputEmailPh.isDisplayed(), "Email text is displayed");
	}
	public void Register()
	{

		AndroidActions.clickElement(inputEmailPh);
		inputEmailPh.sendKeys("058586281");
		continueBtn.click();
		
		Assert.assertTrue(inputEmailPhValerror.isDisplayed(), "Validationerror is displayed");
		AndroidActions.clickElement(inputEmailPh);
		inputEmailPh.clear();
		//enter the mobile number
		inputEmailPh.sendKeys("585862813");
		
		AndroidActions.clickElement(continueBtn);
		
		
	}

	
	
}
