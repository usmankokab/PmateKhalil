package com.ipclinical.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class EditPatient extends BaseClass{
	
	 
	
	public EditPatient()
		{
			PageFactory.initElements(getDriver(), this);
		}
			
		@FindBy(id="Login1_UserName")
		@CacheLookup
		WebElement txtUserName;
		
		@FindBy(id="Login1_Password")
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
		
		@FindBy(css=".ui-icon.ui-icon-pencil")
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
		
		@FindBy(className="error-code")
		@CacheLookup
		WebElement error;
		
		@FindBy(xpath="//a[normalize-space()='Insurance']")
		@CacheLookup
		public WebElement tabInsurance;
		
		@FindBy(name="ctl00$phFolderContent$ucPatient$btnPopup")
		@CacheLookup
		public WebElement insuranceDots;

		@FindBy(name="ctl04$popupBase$txtSearch")
		@CacheLookup
		public WebElement txtInsPop;
		
		@FindBy(id="ctl04_popupBase_btnSearch")
		@CacheLookup
		public WebElement btnSearchPop;
		
		@FindBy(id="ctl04_popupBase_grvPopup_ctl02_lnkSelect")
		@CacheLookup
		public WebElement lnkSelect;
		
		@FindBy(id="ctl00_phFolderContent_ucPatient_btnApply")
		@CacheLookup
		public WebElement btnApply;
		
		
		
		
		
		Action action = new Action();
		
		public void netCheck() {
			
			if(action.isDisplayed(getDriver(), error)) {
				//if(error.getText().equals("ERR_INTERNET_DISCONNECTED") || error.getText().equals("DNS_PROBE_FINISHED_NO_INTERNET") || error.getText().equals("ERR_NAME_NOT_RESOLVED")) {
				System.out.println("No Internet Connection");
			         while(!action.isDisplayed(getDriver(), error)) {
			           		
			           		getDriver().navigate().refresh();
			           		
			           }
					
			      }
		}
		
		
		public EditVisit templateClick() {
			

			netCheck();
			action.explicitWait(getDriver(), tabTemplate, 50000);
			action.JSClick(getDriver(), tabTemplate);
			
			netCheck();
			action.explicitWait(getDriver(), linkNewVisit, 50000);
			action.click(getDriver(), linkNewVisit);
			netCheck();
			
			
			
			return new EditVisit();
		}


}
