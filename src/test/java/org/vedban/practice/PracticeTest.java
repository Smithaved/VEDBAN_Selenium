package org.vedban.practice;

import org.testng.annotations.Test;

import com.vedban.elementRepository.CustomerAmountBalance;
import com.vedban.genericLibrary.BaseClass;

public class PracticeTest extends BaseClass{
	@Test
	public void sample(){
		CustomerAmountBalance customerAmountBalance=new CustomerAmountBalance(driver);
		homePage.clickOnAmmountBalance();
		if(customerAmountBalance.conformationText().equals("Customer Account Balance"))
		{
			fileLibrary.setTestCaseData("customer", 4, 4, "Customer Account Balance Page is displayed");
			fileLibrary.setTestCaseData("customer", 4, 5, "Pass");
		}
		String balance = customerAmountBalance.getBalance();
		if(balance.isEmpty()== false)
		{
			fileLibrary.setTestCaseData("customer", 5, 4, "amount is featched");
			fileLibrary.setTestCaseData("customer", 5, 5, "pass");
		}
		System.out.println(balance);
	}
}
