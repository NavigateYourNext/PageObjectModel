package com.crm.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CasePage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.PersonPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CasePageTest extends TestBase 
{
	
	HomePage homePage = null;
	CasePage casePage = null;
	LoginPage loginPage = null;
	PersonPage personPage = null;
	
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	public CasePageTest()
	{
		super();
	}
	
	@BeforeTest
	public void setExtent()
	{
		extentReports = new ExtentReports(System.getProperty("user.dir")+"/extentReports/extent_report.html",true);
		extentReports.addSystemInfo("Admin", "Akshay Shete");
		extentReports.addSystemInfo("Host", "HP Windows 10");
		extentReports.addSystemInfo("Date", LocalDateTime.now().toString());
		
	}
	
	
	
	@BeforeMethod
	public void setUp()
	{
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
		personPage = homePage.clickOnPersonImage();
		
	}
	
	public static String getScreenshot(WebDriver driver,String screenshotName)
	{
		String destination = null;
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			destination = System.getProperty("user.dir")+"/screenshots/"+screenshotName+dateName;
			FileUtils.copyFile(src, new File(destination));
		} catch(Exception e)
		{
			
			System.out.println("Error Caught : "+e.getMessage());
		}
		
		return destination;
	}
	
	
	@DataProvider
	public Iterator<Object[]> getTestData()throws Exception
	{
		ArrayList<Object[]> obj = com.crm.util.DataProvider.getTestData();
		return obj.iterator();
	}
	
	@Test(dataProvider = "getTestData",description="Add Case")
	public void addCaseDetails(String initialTitle, String fname, String lname)
	{
		extentTest = extentReports.startTest("addCaseDetails");
		
		personPage.addPersonDetails(initialTitle, fname, lname);
		
		String getScreenshot = getScreenshot(driver, "Person Details");
		
		extentTest.log(LogStatus.PASS, "Person Details Added Succesfully");
		extentTest.log(LogStatus.PASS, "Trying to add person details", "Succesfully added");
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(getScreenshot));
		
		casePage = homePage.clickOnCaseImage();
		casePage.addCase(initialTitle, fname, lname);
	}
	
	@Test(dataProvider = "getTestData",description="Verify Case Status Of Added Case")
	public void verifyCaseStatus(String initialTitle, String fname, String lname)
	{
		extentTest = extentReports.startTest("verifyCaseStatus");
		casePage = homePage.clickOnCaseImage();
		boolean result = casePage.verifyCaseStatus(initialTitle, fname, lname);
		Assert.assertTrue(result);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, result.getName());
			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath)); // to add screenshot
		}
		
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, result.getName());
			extentTest.log(LogStatus.FAIL, result.getThrowable());
			
			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot
			
		}
		
		else if(result.getStatus() == ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, result.getName()+" Test Skipped");
		}
		
		extentReports.endTest(extentTest); // ending test and prepare to create HTML report
		
		driver.quit();
	}
	
	@AfterTest
	public void endReport()
	{
		extentReports.flush();
		extentReports.close();
	}
	
}
