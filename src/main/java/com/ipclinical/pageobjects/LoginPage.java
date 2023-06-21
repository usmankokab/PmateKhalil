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
		
	@FindBy(id="username")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id="password")
	@CacheLookup
	WebElement txtPassword;
	
	
	@FindBy(id="Login1_LoginButton")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(id="manage-patients_tab")
	@CacheLookup
	WebElement managePatients;
	
	@FindBy(id="ctl00_phFolderContent_ucSearch_txtSearch")
	@CacheLookup
	WebElement txtSearch;
	
	@FindBy(id="ctl00_phFolderContent_ucSearch_ctlArrow")
	@CacheLookup
	WebElement searchArrow;
	
	@FindBy(id="ctl00_phFolderContent_ucSearch_txtSearchAdvanced")
	@CacheLookup
	WebElement txtSearchAdvanced;
	
	@FindBy(id="ctl00_phFolderContent_ucSearch_btnSearch")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(css="td:nth-child(6) a:nth-child(1)")
	@CacheLookup
	WebElement linkLast;
	
	@FindBy(xpath="//a[normalize-space()='Template']")
	@CacheLookup
	WebElement tabTemplate;
	
	@FindBy(xpath="//a[normalize-space()='Create New Visit']")
	@CacheLookup
	WebElement linkNewVisit;
	
	@FindBy(id="ctl00_phFolderContent_DateVisited_Month")
	@CacheLookup
	WebElement monthVisited;
	
	@FindBy(id="ctl00_phFolderContent_DateVisited_Day")
	@CacheLookup
	WebElement dayVisited;
	
	@FindBy(id="ctl00_phFolderContent_DateVisited_Year")
	@CacheLookup
	WebElement yearVisited;
	
	
	@FindBy(xpath="//a[normalize-space()='Billing Info']")
	@CacheLookup
	WebElement tabBillingInfo;
	
	@FindBy(xpath="//input[@value='Superbill']")
	@CacheLookup
	WebElement btnSuperbill;
	
	
	@FindBy(id="lstSuperbill")
	@CacheLookup
	WebElement lstSuperbill;
	
	
	@FindBy(id="chkCPT0")
	@CacheLookup
	WebElement chkCollect;
	
	@FindBy(id="chkDiagnosisCode0")
	@CacheLookup
	WebElement chkDx;
	
	@FindBy(id="Button1")
	@CacheLookup
	WebElement btnClose;
	
	
	@FindBy(id="ctl00_phFolderContent_ucVisitLineItem_ucBillingCPT_DiagnosisCode0")
	@CacheLookup
	WebElement txtPointer;
	
	
	@FindBy(xpath="//a[normalize-space()='Billing Options']")
	@CacheLookup
	WebElement tabBilligOption;
	
	
	@FindBy(id="ctl00_phFolderContent_CLIA")
	@CacheLookup
	WebElement txtCLIA;
	
	
	
	
	
	
	
	Action action = new Action();
	
	public HomePage login(String uname, String pwd) {
		
		action.type(txtUserName, uname);
		action.type(txtPassword, pwd);
		txtPassword.submit();
		
		//action.click(getDriver(), btnLogin);
		return new HomePage();
	}
	
}









