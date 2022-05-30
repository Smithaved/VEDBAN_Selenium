package com.vedban.genericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vedban.elementRepository.HomePage;
import com.vedban.elementRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver staticdriver;
	public WebDriver driver;
	public FileLibrary fileLibrary;
	WebDriverLibrary webDriverLibrary;
	long longTimeout;
	JavaLibrary javaLibrary;
	//String username;
	//String password;
	LoginPage loginPage;
	public HomePage homePage;
	@BeforeSuite
	public void beforeSuite()
	{
		fileLibrary=new FileLibrary();
		fileLibrary.openCommanDataFile(ExternalFilePath.COMMONDATA);
		fileLibrary.openTestDataFile(ExternalFilePath.TESTDATA);
	}
	@AfterSuite
	public void afterSuite()
	{
		fileLibrary.closeTestDataFile();
	}
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browser,String url)
	{
		webDriverLibrary=new WebDriverLibrary();
		javaLibrary=new JavaLibrary();
		String timeout=fileLibrary.getCommonData("timeout");
		longTimeout=javaLibrary.stringToLong(timeout);
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;	
		default:
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			break;
		}
		staticdriver=driver;
		webDriverLibrary.launchApplication(driver, url);
		webDriverLibrary.implicitWait(driver,longTimeout);
		webDriverLibrary.maximizeBrowser(driver);
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
		if(loginPage.conformationText().contains("Welcome to Bank Management System"))
		{
			fileLibrary.setTestCaseData("customer", 2, 4, "Login page is displayed");
			fileLibrary.setTestCaseData("customer", 2, 5, "Pass");
		}
	}
	@AfterClass
	public void afterClass()
	{
		fileLibrary.openTestDataFileToWrite(ExternalFilePath.TESTDATA);
		webDriverLibrary.closeBrowse(driver);
		
	}
	@Parameters({"username","password"})
	@BeforeMethod
	public void beforeMethod(String username,String password)
	{
	    
		loginPage.login(username, password);
		if(homePage.conformationText().equals("Home"))
		{
			fileLibrary.setTestCaseData("customer", 3, 4, "Home page is displayed");
			fileLibrary.setTestCaseData("customer", 3, 5, "Pass");
		}
	}
	@AfterMethod
	public void afterMethod()
	{
		homePage.logout();
		if(loginPage.conformationText().contains("Welcome to Bank Management System"))
		{
			fileLibrary.setTestCaseData("customer", 6, 4, "Login page is displayed");
			fileLibrary.setTestCaseData("customer", 6, 5, "Pass");
		}
		
	}
}
