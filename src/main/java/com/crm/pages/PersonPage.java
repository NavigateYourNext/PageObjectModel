package com.crm.pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.crm.base.TestBase;

public class PersonPage extends TestBase
{
	@FindBy(xpath="//a[text()='Add Person']")
	public WebElement addPersonButton;
	
	@FindBy(xpath="//select[@name='party:j_id108:j_id116']")
	public WebElement title;
	
	@FindBy(xpath="//input[@name='party:fnDecorate:fn']")
	public WebElement firstName;
	
	@FindBy(xpath="//input[@name='party:lnDecorate:ln']")
	public WebElement lastName;
	
	@FindBy(xpath="//input[@name='party:save']")
	public WebElement saveButton;
	
	public PersonPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void addPersonDetails(String initialTitle, String fname, String lname)
	{
		addPersonButton.click();
		title.sendKeys(initialTitle);
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		
		saveButton.click();
	}
	
	
}
