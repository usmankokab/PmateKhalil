package com.ipclinical.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class LoginPage extends BaseClass {

	public LoginPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
		
	@FindBy(id="txtUserName")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id="txtPassword")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id="txtCode")
	@CacheLookup
	WebElement txtCode;
	
	@FindBy(id="btnsubmit")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(name="Location")
	WebElement drpLocation;
	
	@FindBy(id="btnsubmitLocation")
	WebElement btnProceedNow;
	
	Action action = new Action();
	
	public DashboardPage login(String uname, String pwd, String code) {
		
		action.type(txtUserName, uname);
		action.type(txtPassword, pwd);
		action.type(txtCode, code);
		action.click(getDriver(), btnLogin);
		action.waitPreloader();
		WebElement webElement = getDriver().findElement(By.xpath("//select[@name='Location']/option[@value='1']"));
		action.explicitWait(getDriver(), webElement, 30);
		action.selectByIndex(drpLocation, 1);
		action.click(getDriver(), btnProceedNow);
		
		return new DashboardPage();
		
	}
	
	
	
}









