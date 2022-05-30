package com.vedban.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerAmountBalance {
	public CustomerAmountBalance(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[@class='views-field views-field-field-balance-left-prof']")
	private WebElement amountBalanceLable;
	
	@FindBy(xpath = "//a[@class='active']")
	private WebElement headerTxt;
	
	public String getBalance()
	{
		return amountBalanceLable.getText();
	}
	
	public String conformationText()
	{
		return headerTxt.getText();
	}
}
