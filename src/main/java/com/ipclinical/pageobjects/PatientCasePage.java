package com.ipclinical.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
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
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Close']")
	public WebElement btnClose;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Print']")
	public WebElement btnPrint;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save']")
	public WebElement btnSave;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save & Schedule']")
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
	
	@FindBy(xpath = "//div[@id='PatientCaseModel']/.//label/input[@type='radio']")
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
	
	@FindBy(id="assignTo")
	public WebElement txtAssignTo;
	
	@FindBy(xpath="//a[text() = 'Ali,Saim (ClinicalStaff)']")
	public WebElement AssigneeElementaliSaim;
	
	//Patient Chart Page
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save & Add Orders']")
	public WebElement btnSave$AddOrders;
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PatientCasePage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	public boolean openPatientCaseModel()
	{
		
		if(action.isDisplayed(getDriver(), btnAddPatientCase)) {
			
			action.click(getDriver(), btnAddPatientCase);
			action.waitForModelDisplayed(PatientCaseModel, chkOutBoundOnly);
			return true;
		} else {
			
			PatientChartPage patientChartPage = new PatientChartPage();
			patientChartPage.openPatientCaseModel_fromChart();
			return true;
		}
		
	}

	
	public boolean openPatientSearchModel()
	{
		action.click(getDriver(), btnAddPatientCase);
		action.click(getDriver(), btnSelectPatient);
		action.waitForModelDisplayed(modelPatientSearch, this.txtPatientPortalEMRNo);
		return true;
	}
	
	public void searchAndSelectPatient(String MR)
	{
	
		action.type(txtPatientPortalEMRNo, MR);
		action.click(getDriver(), btnSearchPatientModel);
		action.click(getDriver(), resultSearchPatient);
		action.click(getDriver(), btnSelectPatientModel);
		action.waitForModelDisplayed(PatientCaseModel, chkOutBoundOnly);
		
		if(action.isDisplayed(getDriver(), selectedPatientShowed)) {
			
			Assert.assertTrue(true);
			Log.info("Required patient is selected sucessfully");
		}else {
			Log.info("Required patient is not selected");
			Assert.assertTrue(false);
		}
		
	}
	
	public void fillMandatoryFields(String Subject) {
		
		action.selectLastValueOfDropdown(dropdownSource);
		action.selectLastValueOfDropdown(dropdownProvider);
		action.type(txtSubject, Subject);
		Log.info("All mandatory fields are filled");
		
		boolean flag1 = action.isEnabled(getDriver(), btnPrint);
		boolean flag2 = action.isEnabled(getDriver(), btnSave);
		boolean flag3 = action.isEnabled(getDriver(), btnSave$Schedule);
		boolean flag4 = action.isEnabled(getDriver(), btnSave$AddOrders);
		
		if(flag1 && flag2 && (flag3 || flag4)) {
			
			Assert.assertTrue(true);
			Log.info("mandatory fields, enabled and disabled buttons are verified");
		}else {
			
			Log.info("mandatory fields are not verified, something went wrong");
			Assert.assertTrue(false);
			
		}
		
		
	}
	
	public void assignPatientCase(String assignee, WebElement assigneeElement) {
		
		action.click(getDriver(), radioButtons.get(3));
		action.type(txtAssignTo, "saim");
		action.explicitWait(getDriver(), AssigneeElementaliSaim, 10);
		
		//action.click(getDriver(), page.aliSaim);
		txtAssignTo.sendKeys(Keys.DOWN);
		txtAssignTo.sendKeys(Keys.ENTER);
		action.click(getDriver(), btnSave);

	}
	
	
	public void clickOnLastResultOfPtCaseList() {
		
		//power of isDisplayed from our action class
		//if paging happens
		if(action.isDisplayed(getDriver(), btnLastPage)){ 
			Log.info("There is a paging in Patient Case List");
			btnLastPage.click();
			Log.info("Got to the last page of Patient Case List");
			action.waitPreloader(30);
			Log.info("Going to click on last result edit button of the last page");
			action.JSClick(getDriver(), lastPatientCaseInList);
			Log.info("clicked on last result edit button");
		//if paging does not happens
		}else {
			
			//Click edit button of last result of the first page
			//Never use direct IsDisplayed, can give NoSuchElementException and stop the test case
			Log.info("Going to click on last result edit button");
			action.JSClick(getDriver(), lastPatientCaseInList);
			Log.info("clicked on last result edit button");
			
		}

	}
	
	
	
}
