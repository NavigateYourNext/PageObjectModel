package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class HomePage extends TestBase
{
	@FindBy(xpath="//span[text()='Akshay Shete']")
	public WebElement userNameDisplayed;
	
	@FindBy(xpath="//span[text()='People & Organisations']//parent::a/parent::div")
	public WebElement personImage;
	
	@FindBy(xpath="//span[text()='Cases']//parent::a/parent::div")
	public WebElement caseImage;
	

	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	public boolean validateHomePage()
	{
		if(userNameDisplayed.isDisplayed())
			return true;
		else
			return false;
	}
	
	public PersonPage clickOnPersonImage()
	{
		personImage.click();
		return new PersonPage();
	}
	
	public CasePage clickOnCaseImage()
	{
		caseImage.click();
		return new CasePage();
	}
	
	
}
