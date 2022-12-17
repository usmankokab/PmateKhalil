package com.ipclinical.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;

public class PatientCasePage extends BaseClass{
	
	@FindBy(xpath = "(//div[@class='TableDataCell'][normalize-space()='Patient Name'])[1]")
	public WebElement lblPatientName;
	
	@FindBy(xpath = "//div[@class='col-sm-4']//div[@class='TableDataCell'][normalize-space()='Patient Name']//following::input[1]")
	public WebElement txtPatientName;
	
	@FindBy(css = "input[id^='LocationsCheckbox']")
	public List<WebElement> chkboxesLocations;
	
	@FindBy(css = "input[id^='ProvidersCheckbox']")
	public List<WebElement> chkboxesProviders;
	
	@FindBy(xpath = "//*[@id=\"SearchForm\"]//select")
	public WebElement ddStatus;
	
	@FindBy(xpath = "//a[normalize-space()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath = "//button[normalize-space()='Add Patient Case']")
	public WebElement btnAddPatientCase;
	
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateFrom']")
	public WebElement txtFromDate;
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateTo']")
	public WebElement txtToDate;
	
	@FindBy(xpath= "//div[@name='SearchResult']//div[@class='SetHeadingCell']")
	public List<WebElement> tableHeadings;//input[@id='chkoutboundonly']
	
	@FindBy(xpath= "//input[@id='chkoutboundonly']")
	public WebElement chkOutBoundOnly;
	
	@FindBy(xpath= "//input[@id='chkoplp']")
	public WebElement chkOutSideProvider;
	
	@FindBy(xpath= "//a[normalize-space()='Select Patient']")
	public WebElement btnSelectPatient;
	
	@FindBy(id= "PatientCaseModel")
	public WebElement PatientCaseModel;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[1]")
	public WebElement btnClose;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[2]")
	public WebElement btnPrint;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[3]")
	public WebElement btnSave;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[4]")
	public WebElement btnSave$Schedule;
	
	@FindBy(id= "DropdownSource")
	public WebElement dropdownSource;
	
	@FindBy(id= "DropdownType")
	public WebElement dropdownType;
	
	@FindBy(id= "DropdownProvider")
	public WebElement dropdownProvider;
	
	@FindBy(id= "DropdownLocation")
	public WebElement dropdownLocation;

	@FindBy(id= "CallBackNumber")
	public WebElement ddCallBackNumber;
	
	@FindBy(xpath= "//*[@id=\"PatientCaseModel\"]//select[@ng-model='PatientCase.Priority']")
	public WebElement ddPriority;
	
	@FindBy(css = "input[ng-model='PatientCase.PersonToCall']")
	public WebElement txtPersonToCall;
	
	@FindBy(id = "txtSubject")
	public WebElement txtSubject;
	
	@FindBy(id = "otherfield")
	public WebElement txtOther;
	
	@FindBy(xpath = "//label/input[@type='radio']")
	public List<WebElement> radioButtons;
	
	@FindBy(xpath = "//textarea")
	public List<WebElement> textAreas;
	
	
	//Elements on Patient Search Model
	@FindBy(id="idOpenPatientSearch")
	public WebElement modelPatientSearch;
	
	@FindBy(xpath="(//button[@type='button' and text()='Search'])[1]")
	public WebElement btnSearchPatientModel;
	
	@FindBy(xpath = "(//button[normalize-space()='Close'])[1]")
	public WebElement btnClosePatientModel;
	
	@FindBy(xpath = "(//button[normalize-space()='Select'])[1]")
	public WebElement btnSelectPatientModel;
	
	
	@FindBy(xpath="(//input[@id='txtPatientPortalName'])[1]")
	public WebElement txtPatientPortalName;
	
	@FindBy(id="txtPatientPortalPhoneNumber")
	public WebElement txtPatientPortalPhoneNumber;
	
	@FindBy(id="txtPatientPortalSSN")
	public WebElement txtPatientPortalSSN;
	
	@FindBy(id="txtPatientPortalEMRNo")
	public WebElement txtPatientPortalEMRNo;
	
	@FindBy(id="dtPatientPortalDOB")
	public WebElement txtPatientPortalDOB;
	
	
	@FindBy(xpath="(//div[@class='TableDataControl']/input[@type='radio'])[1 <= position() and position() < 3]")
	public List <WebElement> radiosPatientPortalActiveInActive;
			
	@FindBy(id="dtPatientPortalDOB")
	public WebElement rdbPatientPortalActive;
	
	@FindBy(id="dtPatientPortalDOB")
	public WebElement rdbPatientPortalInActive;
	
	@FindBy(xpath="(//input[@id='chSelect'])[1]")
	public WebElement resultSearchPatient;
	
	@FindBy(xpath="(//div[@ng-show='Patient != null'])[1]")
	public WebElement selectedPatientShowed;
	
	@FindBy(xpath="(//img[@class='btn-22'])[position()>=1 and position() <= (last() - 1)][last()]")
	public WebElement lastPatientCaseInList;
	
	@FindBy(xpath="(//a[@class='Style5'])[1]")
	public WebElement btnLastPage;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PatientCasePage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	public boolean openPatientCaseModel_btnAddPatientCase()
	{
		
		action.click(getDriver(), btnAddPatientCase);
		action.waitForModelDisplayed(btnAddPatientCase, PatientCaseModel, chkOutBoundOnly);
		return true;
	}

	
	public boolean openPatientSearchModel_btnSelectPatient()
	{
		
		action.click(getDriver(), btnSelectPatient);
		action.waitForModelDisplayed(btnSelectPatient, modelPatientSearch, btnSearchPatientModel);
		return true;
	}
	
	public void searchAndSelectPatient(String MR)
	{
	
		action.type(txtPatientPortalEMRNo, MR);
		action.click(getDriver(), btnSearchPatientModel);
		action.click(getDriver(), resultSearchPatient);
		action.click(getDriver(), btnSelectPatientModel);
		action.waitForModelDisplayed(btnAddPatientCase, PatientCaseModel, chkOutBoundOnly);
		
		if(action.isDisplayed(getDriver(), selectedPatientShowed)) {
			
			Assert.assertTrue(true);
			Log.info("Required patient is selected sucessfully");
		}else {
			Log.info("Required patient is not selected");
			Assert.assertTrue(false);
		}
		
	}
	
	


}
