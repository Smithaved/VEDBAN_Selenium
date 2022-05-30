package com.vedban.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "edit-name")
	private WebElement userNameTxt;
	
	@FindBy(id = "edit-pass")
	private WebElement passwordTxt;
	
	@FindBy(id = "edit-submit")
	private WebElement submitBtn;
	
	@FindBy(id = "page-title")
	private WebElement headerTxt;
	
	public void login(String username, String password)
	{
		userNameTxt.sendKeys(username);
		passwordTxt.sendKeys(password);
		submitBtn.click();
	}
	
	public String conformationText()
	{
		return headerTxt.getText();		
	}
}
