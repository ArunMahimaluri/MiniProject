package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	  ReadConfig readconfig = new ReadConfig();
	  public String baseURL = readconfig.getApplicationURL(); 
	  public String username = readconfig.getUserName(); 
	  public String password = readconfig.getPassword();
	  public static WebDriver driver;
	 
	/*
	 * public String baseURL = "https://demo.guru99.com/v4/"; public String username
	 * = "mngr296810"; public String password = "dadEbep"; public static WebDriver
	 * driver;
	 */
	 
	@Parameters("browser")  
	@BeforeClass
	public void setup(String br)
	{
		if(br.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			foptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			foptions.addArguments("--dsiable-notificaitons");
			foptions.addArguments("--disable-infobars");
			driver = new FirefoxDriver(foptions);
		}
		else if(br.equals("ie"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions eoptions = new EdgeOptions();
			eoptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			eoptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new EdgeDriver(eoptions);
		}
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+"tname"+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}
}
