package com.ipclinical.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class PatientChartPage extends BaseClass {
	
	@FindBy(id = "tabPatientCase")
	public WebElement tabPatientCase;
	
	//@ByAngularModel.FindBy(model = "")
	@FindBy(xpath = "//a[@ng-click='OpenPatientCaseModel()']")
	WebElement btnAdd;
	
	@FindBy(id= "PatientCaseModel")
	public WebElement PatientCaseModel;
	
	@FindBy(xpath= "//input[@id='chkoutboundonly']")
	public WebElement chkOutBoundOnly;
	
	@FindBy(xpath= "//a[normalize-space()='Select Patient']")
	public WebElement btnSelectPatient;
	
	
	//check for duplicates and extras
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
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateFrom']")
	public WebElement txtFromDate;
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateTo']")
	public WebElement txtToDate;
	
	@FindBy(xpath= "//div[@name='SearchResult']//div[@class='SetHeadingCell']")
	public List<WebElement> tableHeadings;//input[@id='chkoutboundonly']
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Close']")
	public WebElement btnClose;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Print']")
	public WebElement btnPrint;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save']")
	public WebElement btnSave;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save & Add Orders']")
	public WebElement btnSave$AddOrders;
	
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
	
	@FindBy(css = "textarea[ng-model='PatientCase.Description']")
	public WebElement textAreaDescription;
	
	@FindBy(css = "textarea[ng-model='PatientCase.Activity.ActionNote']")
	public WebElement textAreaActionNote;
	
	
	@FindBy(xpath= "//input[@id='chkoplp']")
	public WebElement chkOutSideProvider;
	
	
	
	
	
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
		
	
	public PatientChartPage ( ) {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	
	public void openPatientCaseModel_fromChart() {
		
		action.waitForAngularRequestsToFinish();
		action.click(getDriver(), tabPatientCase);
		action.waitPreloader();
		action.click(getDriver(), btnAdd);
		action.waitForModelDisplayed(PatientCaseModel, chkOutBoundOnly);
		action.waitPreloader();
	}

	


}
