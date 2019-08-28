package com.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class LoginPage extends TestBase 
{
	
	@FindBy(xpath="//input[@name='login:usernameDecorate:username']")
	@CacheLookup
	public WebElement username;
	
	@FindBy(xpath="//input[@name='login:passwordDecorate:password']")
	@CacheLookup
	public WebElement password;
	
	@FindBy(xpath="//input[@name='login:login']")
	@CacheLookup
	public WebElement loginButton;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public HomePage loginUser(String uname, String pass)
	{
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginButton.click();
		
		log.info("Login Succesful");
		
		
		return new HomePage();
	}
}
