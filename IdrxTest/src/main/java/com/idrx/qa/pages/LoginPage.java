package com.idrx.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	TestUtil testUtil = new TestUtil();
	
	@FindBy(id = "username")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;
	
	// Initializing the page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String userName, String password) {		
		testUtil.sendKeys(this.userName, userName, "Username Field");
		testUtil.sendKeys(this.password, password, "Password Field");
		testUtil.clickElement(this.submitButton, "Submit Button");	
	}


}
