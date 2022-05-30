package com.vedban.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Amount Balance")
	private WebElement amountBalanceLink;
	
	@FindBy(xpath = "//a[.='Log out']" )
	private WebElement logoutLink;
	
	@FindBy(xpath = "//a[@class='active']")
	private WebElement headerTxt;
	
	public void logout()
	{
		logoutLink.click();
	}
	
	public void clickOnAmmountBalance()
	{
		amountBalanceLink.click();
	}
	
	public String conformationText()
	{
		return headerTxt.getText();
	}
}
