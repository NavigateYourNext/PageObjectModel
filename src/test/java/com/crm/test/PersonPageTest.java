package com.crm.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.PersonPage;

public class PersonPageTest extends TestBase 
{
	HomePage homePage = null;
	LoginPage loginPage = null;
	PersonPage personPage = null;
	
	public PersonPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
		personPage = homePage.clickOnPersonImage();
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData()throws Exception
	{
		ArrayList<Object[]> obj = com.crm.util.DataProvider.getTestData();
		return obj.iterator();
	}
	
	@Test(dataProvider="getTestData")
	public void addPersonDetails(String initialTitle, String fname, String lname)
	{
		personPage.addPersonDetails(initialTitle, fname, lname);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
