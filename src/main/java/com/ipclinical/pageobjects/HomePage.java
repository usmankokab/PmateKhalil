package com.ipclinical.pageobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;

public class HomePage extends BaseClass{

		public HomePage()
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
		
		@FindBy(css="#manage-patients_tab>span")
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
		public WebElement iconEdit;
		
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
		
		@FindBy(xpath="//select[@id='ctl00_phFolderContent_ucSearch_lstSearchByAdvanced']")
		@CacheLookup
		WebElement andDrop;
		
		@FindBy(id="srcFaM_0")
		@CacheLookup
		WebElement Mtext;
		
		@FindBy(id="srcFaD_0")
		@CacheLookup
		WebElement Dtext;
		
		@FindBy(id="srcFaY_0")
		@CacheLookup
		WebElement Ytext;
		
		@FindBy(className="ui-icon-seek-next")
		@CacheLookup
		WebElement btnNext;
		
		@FindBy(className=".ui-row-ltr.cbox")
		@CacheLookup
		public List <WebElement> records;

		@FindBy(xpath="(//td[(((count(preceding-sibling::*) + 1) = 12) and parent::*)])[2]")
		@CacheLookup
		public List <WebElement> insCell;
		
		@FindBy(xpath="//td[(((count(preceding-sibling::*) + 1) = 9) and parent::*)]")
		@CacheLookup
		public List <WebElement> allDOBs;
		
		Action action = new Action();
		
		
		public void netCheck() {
			
			if(action.isDisplayed(getDriver(), error)) {
				if(error.getText().equals("ERR_INTERNET_DISCONNECTED") || error.getText().equals("DNS_PROBE_FINISHED_NO_INTERNET") || error.getText().equals("ERR_NAME_NOT_RESOLVED")) {
			           System.out.println("No Internet Connection");
			           try {
			   			Thread.sleep(30000);
			   		} catch (InterruptedException e) {
			   			// TODO Auto-generated catch block
			   			e.printStackTrace();
			   		}
			           
			           getDriver().navigate().refresh();
			      }
			}
		}
		
		public EditPatient searchPatient(String secndname, String firstname) {
		
		netCheck();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		action.explicitWait(getDriver(), managePatients, 50000);
		//Log.info("mange patients tab found");
		action.JSClick(getDriver(), managePatients);
		//Log.info("Manage Patient Tab Clicked");
		netCheck();
		// typing  second name
		action.explicitWait(getDriver(), txtSearch, 50000);
		action.type(txtSearch, secndname.trim());
		//Log.info("Second name typed successfully");
		
		action.explicitWait(getDriver(), searchArrow, 50000);
		action.JSClick(getDriver(), searchArrow);
		//Log.info("Arrow button search clicked successfully");
		
		//action.explicitWait(getDriver(), andDrop, 50000);
		//action.selectByIndex(andDrop, 2);
		
		//PUTTING dob in their specific fields
		
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//		Date dobDate = null;
//		try {
//			dobDate = formatter.parse(dob);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		cal.setTime(dobDate);
//		
//		String year = (String.valueOf(cal.get(Calendar.YEAR)));
//		String month = (String.valueOf(cal.get(Calendar.MONTH)+1));
//		String day = (String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
//
//		action.type(Mtext, month);
//		action.type(Dtext, day);
//		action.type(Ytext, year);
		
		
		//typing first name
		action.explicitWait(getDriver(), txtSearchAdvanced, 50000);
		action.type(txtSearchAdvanced, firstname.trim());
		//Log.info("firstname typed successfully");
		
		//Search Button click
		action.explicitWait(getDriver(), btnSearch, 50000);
		action.JSClick(getDriver(), btnSearch);
		//Log.info("Search button clicked");
		netCheck();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//action.click(getDriver(), iconEdit);
		//Log.info("Edit Button Clicked");
		
		return new EditPatient();
		
	}
		
		
}
