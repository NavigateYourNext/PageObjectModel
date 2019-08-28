package com.crm.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.util.TimeOut;

public class TestBase 
{
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log = Logger.getLogger(TestBase.class);
	
	public TestBase()
	{
		try
		{
			
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config/config.properties");
			prop.load(fis);
			log.info("Properties File Load Succesfully");
		}
		catch(Exception e)
		{
			log.error("Error Caught In TestBase Constructor : "+e.getMessage());
		}
	}
	
	public static void initialisation()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/resources/IEDriverServer.exe");
			driver = new ChromeDriver();
			log.info("Internet explorer has been initiated");
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/resources/geckodriver.exe");
			driver = new ChromeDriver();
			log.info("Firefox has been initiated");
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Chrome has been initiated");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TimeOut.implicit_Wait, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TimeOut.page_Load_TimeOut, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
}
