package com.crm.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CasePage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.PersonPage;

import junit.framework.Assert;

public class HomePageTest extends TestBase 
{	
	LoginPage loginPage = null;
	HomePage homePage = null;
	PersonPage personPage = null;
	CasePage casePage = null;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void validateHomePage()
	{
		boolean result = homePage.validateHomePage();
		Assert.assertTrue(result);
	}
	
	@Test
	public void clickOnPersonPage()
	{
		personPage = homePage.clickOnPersonImage();
	}
	
	@Test
	public void clickOnCaseImage()
	{
		casePage = homePage.clickOnCaseImage();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
