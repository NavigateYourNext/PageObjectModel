package com.crm.test;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class LoginPageTest extends TestBase
{
	
	LoginPage loginPage = null;
	HomePage homePage = null;
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialisation();
		log.info("LoginPageTest started succesfully");
	}
	
	@Test()
	public void loginUser()
	{
		loginPage = new LoginPage();
		homePage = loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		log.info("LoginPageTest ended succesfully");
	}
}
