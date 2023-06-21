package com.ipclinical.pageobjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class EditVisit extends BaseClass{

		public EditVisit()
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
			
			@FindBy(id="ctl00_phFolderContent_btnUpdate")
			@CacheLookup
			WebElement btnUpdate;
			
			@FindBy(className="error-code")
			@CacheLookup
			WebElement error;
			
			

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
			
			public void visitDate(String month, String day, String year) {
				
				netCheck();
				
				action.explicitWait(getDriver(), monthVisited, 50000);
				action.type(monthVisited, month);
				action.type(dayVisited, day);
				action.type(yearVisited, year);
				
				action.explicitWait(getDriver(), tabBillingInfo, 50000);
				action.click(getDriver(), tabBillingInfo);

				action.explicitWait(getDriver(), btnSuperbill, 50000);
				action.click(getDriver(), this.btnSuperbill);
				netCheck();
				
			}
			
			
			public void popSuperBill() {
				
				String parentWindowHandler = getDriver().getWindowHandle(); // Store your parent window
				String subWindowHandler = null;

				Set<String> handles = getDriver().getWindowHandles(); // get all window handles
				Iterator<String> iterator = handles.iterator();
				while (iterator.hasNext()){
				    subWindowHandler = iterator.next();
				}
				getDriver().switchTo().window(subWindowHandler); // switch to popup window

				// Now you are in the popup window, perform necessary actions here
				
				netCheck();
				
				
				action.explicitWait(getDriver(), lstSuperbill, 50000);
				action.selectByIndex(lstSuperbill, 2);
				
				action.explicitWait(getDriver(), chkCollect, 50000);
				action.click(getDriver(), chkCollect);
				
				action.explicitWait(getDriver(), chkDx, 50000);
				action.click(getDriver(), chkDx);
				
				action.explicitWait(getDriver(), btnClose, 50000);
				action.click(getDriver(), btnClose);

				getDriver().switchTo().window(parentWindowHandler);  // switch back to parent window
				
				action.explicitWait(getDriver(), txtPointer, 50000);
				action.type(txtPointer, "1");
				
				action.explicitWait(getDriver(), tabBilligOption, 50000);
				action.click(getDriver(), tabBilligOption);
				
				action.explicitWait(getDriver(), txtCLIA, 50000);
				action.type(txtCLIA, "10D2243591");
				
				
				action.explicitWait(getDriver(), btnUpdate, 50000);
				action.click(getDriver(), btnUpdate);
				netCheck();
			}
}
