package com.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class CasePage extends TestBase
{
	@FindBy(xpath="//a[text()='Add Case']")
	public WebElement addCaseButton;
	
	@FindBy(xpath="//input[@role='searchbox' and @placeholder='Find a person or organisation']")
	public WebElement enterPersonName;
	
	@FindBy(xpath="//input[@class='form-input-text']")
	public WebElement caseName;
	
	@FindBy(xpath="//div[@role='listbox']/div[1]/div[2]/span/span")
	public WebElement personName;
	
	@FindBy(xpath="//tbody/tr[2]/td[2]")
	public WebElement personNameinCaseTable;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveButton;
	
	
	
	
	public CasePage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public void addCase(String initialTitle, String fname, String lname)
	{
		String person = fname+" "+lname;
		addCaseButton.click();
		enterPersonName.sendKeys(person);
		personName.click();
		caseName.sendKeys(person+" Case Created");
		
		saveButton.click();
	}
	
	public boolean verifyCaseStatus(String initialTitle, String fname, String lname)
	{
		
		if(personNameinCaseTable.getText().contains(fname+" "+lname))
		{
			String caseStatus  = driver.findElement(By.xpath("//tbody/tr[2]/td[3]")).getText();
			if(caseStatus.equals("Open"))
				return true;
		}
		return false;
	}
}
