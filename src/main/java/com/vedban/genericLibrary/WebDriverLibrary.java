package com.vedban.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to store all the common methods of the selenium webdriver
 * @author USER
 *
 */
public class WebDriverLibrary {
	/**
	 * This method is used to launch the Application
	 * @param url
	 */
	public void launchApplication(WebDriver driver,String url)
	{
		driver.get(url);
	}
	/**
	 * This method is used for implicit wait
	 * @param driver
	 * @param longtimeout
	 */
	public void implicitWait(WebDriver driver, long longtimeout)
	{
		driver.manage().timeouts().implicitlyWait(longtimeout, TimeUnit.SECONDS);
	}
	/**
	 * This method is used to close the browser
	 * @param driver
	 */
	public void closeBrowse(WebDriver driver)
	{
		driver.quit();
	}
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	public String screenShot(WebDriver driver,JavaLibrary javaLibrary, String fileName)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String date=javaLibrary.dateFormat();
		File dst=new File("./ScreenShots/"+fileName+date+".png");
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem during copying the files");
		}
		return dst.getAbsolutePath();
	}
}
