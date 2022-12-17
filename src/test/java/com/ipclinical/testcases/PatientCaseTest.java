package com.ipclinical.testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.pageobjects.DashboardPage;
import com.ipclinical.pageobjects.LoginPage;
import com.ipclinical.pageobjects.PatientCasePage;
import com.ipclinical.pageobjects.PatientListPage;
import com.ipclinical.utility.Log;

public class PatientCaseTest extends BaseClass{
	DashboardPage dashboardPage;
	PatientListPage patientListPage;
	LoginPage loginPage;
	PatientCasePage patientCasePage;
	
	WebElement element;
	List<WebElement> elements;
	
	SoftAssert softAssert = new SoftAssert();
	Action action = new Action();
	
	
	//@Parameters("browser")
	@BeforeClass
	public void setup() {
		launchApp(prop.getProperty("browser")); 
	}
	
	@AfterClass
	public void tearDown() {
		//getDriver().quit();
	}

	@Test(priority = 1)
	public void patientCase_TC1_toNavigatePatientCase() throws Throwable {
		Log.startTestCase("patientCase_TC1_toNavigatePatientCase");
		//Verifying user is logged in
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("code"), dashboardPage);
		Log.info("User is logged in");
		
		dashboardPage = new DashboardPage();
		patientListPage = dashboardPage.clickPatientWidget();
		Log.info("Patient Widget is clicked");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		
		String actualURL = patientListPage.getCurrURL();
		String expectedURL = "https://staging.pemr.com/Patient/PatientList.aspx";
		Log.info("Verifying if user explored to the Patient List Page");
	    Assert.assertEquals(actualURL, expectedURL);
	    Log.info("User is on Patient List Page");
	    
	    patientListPage.clickPatientsMenu();
	    Log.info("Patients Menu is clicked");
	    patientListPage.clickPatientCaseLink();
	    Log.info("Patient Case in Patients menu is clicked");
	    action.waitPreloader();
	    action.pageLoadTimeOut(getDriver(), 20);
	    
	    //User should land on Patient case page
	    patientCasePage = new PatientCasePage();
	    actualURL = patientCasePage.getCurrURL();
	    expectedURL = "https://staging.pemr.com/Patient/Patientcase.aspx";
		Log.info("Verifying if user explored to the Patient Case Page");
	    Assert.assertEquals(actualURL, expectedURL);
	    Log.info("User landed on Patient case page");
	    
	    //Verifying Patient Name label and TextBox
	    element = patientCasePage.lblPatientName;
	    patientCasePage.validateLabel(element, "Patient Name");
	    element = patientCasePage.txtPatientName;
	    patientCasePage.validateTextBox(element, "Test Patient");
	    
	    
	    //Validating multiple Locations checkboxes
	    elements= patientCasePage.chkboxesLocations;
	    patientCasePage.validateCheckboxes(elements);
	    
	   //Validating multiple Providers checkboxes
	    elements = patientCasePage.chkboxesProviders;
	    patientCasePage.validateCheckboxes(elements);

	    //Validating Status dropdown and its option values
	    String[] expected = {"Please Select","Open","Close"};	
		element = patientCasePage.ddStatus;
	    patientCasePage.validateDropdown(expected, element);
	    Log.info("Expected values matched with Actual values, and user can select any value");
	    
	    
	    //Validating Search Button
	    element = patientCasePage.btnSearch;
	    patientCasePage.validateButton(element);
	    
	    //Validating AddPatientCase Button
	    element = patientCasePage.btnAddPatientCase;
	    patientCasePage.validateButton(element);
	    
	    //Validating FromDate Textbox
	    element = patientCasePage.txtFromDate;
	    patientCasePage.validateTxtDate(element);
	    
	    //Validating ToDate Textbox
	    element = patientCasePage.txtToDate;
	    patientCasePage.validateTxtDate(element);
	    
	    //Validating Search Table Headings
	    String[] expectedHeadings = {"MR Number", "Patient Name", "Location Name", "Provider Name", "Source", "Recipient", "Edit"};
	    elements = patientCasePage.tableHeadings;
	    patientCasePage.validateTableHeadings(expectedHeadings, elements);
	    Log.info("All expected headings are present in search table");
	    
	    Log.endTestCase("patientCase_TC1_toNavigatePatientCase");
	    
	    
	    	    
    		
	}
	
	@Test (priority=1, enabled=true, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC1a_toValidateFields_PatientCase() throws Throwable {
		Log.startTestCase("patientCase_TC1a_toValidateFields_PatientCase");
		
		//Verifying Patient Name label and TextBox
	    element = patientCasePage.lblPatientName;
	    patientCasePage.validateLabel(element, "Patient Name");
	    element = patientCasePage.txtPatientName;
	    patientCasePage.validateTextBox(element, "Test Patient");
	    
	    
	    //Validating multiple Locations checkboxes
	    elements= patientCasePage.chkboxesLocations;
	    patientCasePage.validateCheckboxes(elements);
	    
	   //Validating multiple Providers checkboxes
	    elements = patientCasePage.chkboxesProviders;
	    patientCasePage.validateCheckboxes(elements);

	    //Validating Status dropdown and its option values
	    String[] expected = {"Please Select","Open","Close"};	
		element = patientCasePage.ddStatus;
	    patientCasePage.validateDropdown(expected, element);
	    Log.info("Expected values matched with Actual values, and user can select any value");
	    
	    
	    //Validating Search Button
	    element = patientCasePage.btnSearch;
	    patientCasePage.validateButton(element);
	    
	    //Validating AddPatientCase Button
	    element = patientCasePage.btnAddPatientCase;
	    patientCasePage.validateButton(element);
	    
	    //Validating FromDate Textbox
	    element = patientCasePage.txtFromDate;
	    patientCasePage.validateTxtDate(element);
	    
	    //Validating ToDate Textbox
	    element = patientCasePage.txtToDate;
	    patientCasePage.validateTxtDate(element);
	    
	    //Validating Search Table Headings
	    String[] expectedHeadings = {"MR Number", "Patient Name", "Location Name", "Provider Name", "Source", "Recipient", "Edit"};
	    elements = patientCasePage.tableHeadings;
	    patientCasePage.validateTableHeadings(expectedHeadings, elements);
	    Log.info("All expected headings are present in search table");
	    
	    Log.endTestCase("patientCase_TC1a_toValidateFields_PatientCase");
	    
			
	}

	
	@Test(priority = 2, enabled=true)
	public void patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed()
	{
		Log.startTestCase("patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed");
		//patientCasePage = new PatientCasePage();
		WebElement waitElement = patientCasePage.PatientCaseModel;
		WebElement verifyElement = patientCasePage.chkOutBoundOnly;
		element = patientCasePage.btnAddPatientCase;
		element.click();
		patientCasePage.waitForModelDisplayed(element, waitElement, verifyElement);
	    
	    
	    Log.endTestCase("patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed");

	}
	
	
	@Test(priority = 3, enabled=true)
	public void patientCase_TC3_toValidateFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC3_toValidateFields_PatientCaseModel");
		//patientCasePage = new PatientCasePage();
		
		//Validating buttons
		element = patientCasePage.btnClose;
		patientCasePage.validateButton(element);
		
		element = patientCasePage.btnPrint;
		patientCasePage.validateButtonDisabled(element);
		
		element = patientCasePage.btnSave;
		patientCasePage.validateButtonDisabled(element);
	    
		element = patientCasePage.btnSave$Schedule;
		patientCasePage.validateButtonDisabled(element);
		
		//Validating dropdowns and their values
		String[] expected = {"Please Select","Patient","Parent/Care Giver", "Spouse/Partner", "Pharmacy", "Lab", "PcP", "Specialist", "Clinical Staff", "Hospital", "Other"};	
		element = patientCasePage.dropdownSource;
	    patientCasePage.validateDropdown(expected, element);
	    
	    String[] expected1 = {"Please Select","Administrative","Clinical Question", "Medical Record- Electronic", "Medical Record - Paper", "Medication", "Referral", "Refill", "Patient No Show", "Lab", "Imaging", "Procedure", "DME", "Refund", "Other"};	
		element = patientCasePage.dropdownType;
	    patientCasePage.validateDropdown(expected1, element);

	    String[] expected2 = {"Please Select", "Ahmar, Wasim","Rosselot, Eric", "TEHRANI, ALI REZAZADEH"};	
		element = patientCasePage.dropdownProvider;
	    patientCasePage.validateDropdown(expected2, element);
		
	    String[] expected3 = {"Please Select","High","Medium", "Low"};	
		element = patientCasePage.ddPriority;
	    patientCasePage.validateDropdown(expected3, element);
	    
	    String[] expected4 = {"Please Select","Facility","North Bethesda Maryland"};	
		element = patientCasePage.dropdownLocation;
	    patientCasePage.validateDropdown(expected4, element);
		
	    
	    //Validating checkboxes
	    WebElement element1 = patientCasePage.chkOutBoundOnly;
	    WebElement element2 = patientCasePage.chkOutSideProvider;
	    if(patientCasePage.validateCheckbox(element1) && patientCasePage.validateCheckbox(element2)) 
	    {
	    	Log.info("All checkboxes are displayed and checkable");
	    }else
	    {
	    	Log.error("All or any of the checkbox in the Model window does not appear");
	    	
	    }
	    
	    
	    //Validating TextBoxes
	    element = patientCasePage.txtPersonToCall;
	    patientCasePage.validateTextBox(element, "Person to Call");
	    
	    element = patientCasePage.txtSubject;
	    patientCasePage.validateTextBox(element, "Test Subject");
	    
	    element = patientCasePage.txtOther;
	    patientCasePage.validateTextBox(element, "");
	    
	    //Validating Text Areas
	    elements = patientCasePage.textAreas;
	    patientCasePage.validateTextAreas(elements, "This is test description for textarea field/n" + " This is test description for textarea field");
	    
	    //Validating Radio Buttons
	    elements = patientCasePage.radioButtons;
	    patientCasePage.validateRadioButtons(elements);
	    
	    //Validating buttons
	    
	    
	    
	    	    
	    Log.endTestCase("patientCase_TC3_toValidateFields_PatientCaseModel");
	    
	    
	    
	}
	
	@Test (priority = 4, enabled=true)
	public void patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		element = patientCasePage.dropdownSource;
		patientCasePage.selectDropdown(element);
		
		element = patientCasePage.dropdownProvider;
		patientCasePage.selectDropdown(element);
		
		element = patientCasePage.txtSubject;
		element.clear();
		element.sendKeys("Subject");
		softAssert.assertTrue(true);
		Log.info("All mandatory fields are filled");
		
		element = patientCasePage.btnPrint;
		boolean flag1 = action.isEnabled(getDriver(), element);
		
		element = patientCasePage.btnSave;
		boolean flag2 = action.isEnabled(getDriver(), element);
		
		element = patientCasePage.btnSave$Schedule;
		boolean flag3 = action.isEnabled(getDriver(), element);
		
		if(flag1 && flag2 &flag3) {
			
			Assert.assertTrue(true);
			Log.info("mandatory fields are verified");
		}else {
			
			Assert.assertTrue(false);
			Log.info("mandatory fields are not verified, something went wrong");
		}
		
		Log.endTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		
	}
	
	@Test(priority = 4, enabled=true, dependsOnMethods = { "patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel" })
	public void patientCase_TC5_clickBtnSaveSchudular_withoutPatienSelecting_AlertShouldBeDisplayed() throws InterruptedException
	{
		
		Log.startTestCase("patientCase_TC5_clickBtnSaveSchudular_withoutPatienSelecting_AlertShouldBeDisplayed");
		//patientCasePage = new PatientCasePage();
		
		element = patientCasePage.btnSave$Schedule;
		element.click();
		if(action.isAlertPresent(getDriver())) {
			Log.info("Alert Present"+ "(Please Select Patient)");
			Alert alert = getDriver().switchTo().alert();
			String alertMessage = alert.getText();
			Assert.assertEquals(alertMessage, "Please Select Patient");
			alert.accept();
			//Thread.sleep(5000);
		}else {
			Log.warn("Alert does not present, test failed");
			Assert.assertTrue(false);
		}
		
	    
	    Log.endTestCase("patientCase_TC5_clickBtnSaveSchudular_withoutPatienSelecting_AlertShouldBeDisplayed");

	}

	
	@Test(priority = 5, enabled=true)
	public void patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed()
	{
		
		Log.startTestCase("patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed");
		//patientCasePage = new PatientCasePage();
		WebElement waitElement = patientCasePage.modelPatientSearch;
		WebElement verifyElement = patientCasePage.btnSearchPatientModel;
		element = patientCasePage.btnSelectPatient;
		
		element.click();
		patientCasePage.waitForModelDisplayed(element, waitElement, verifyElement);
	    
	    
	    Log.endTestCase("patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed");

	}
	
	@Test(priority = 5, enabled=true, dependsOnMethods = { "patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed" })
	public void patientCase_TC4b_clickBtnSelect_withoutPatienSelecting_AlertShouldBeDisplayed() throws InterruptedException
	{
		
		Log.startTestCase("patientCase_TC4b_clickBtnSelect_withoutPatienSelecting_AlertShouldBeDisplayed");
		//patientCasePage = new PatientCasePage();
		
		element = patientCasePage.btnSelectPatientModel;
		element.click();
		if(action.isAlertPresent(getDriver())) {
			Log.info("Alert Present"+ "(Please select patient first)");
			Alert alert = getDriver().switchTo().alert();
			String alertMessage = alert.getText();
			Assert.assertEquals(alertMessage, "Please select patient first");
			alert.accept();
			//Thread.sleep(5000);
		}else {
			Log.warn("Alert does not present, test failed");
			Assert.assertTrue(false);
		}
		
	    
	    Log.endTestCase("patientCase_TC4b_clickBtnSelect_withoutPatienSelecting_AlertShouldBeDisplayed");

	}


	
	@Test(priority = 6, enabled=true)
	public void patientCase_TC4a_toValidateFields_SelectPatientModel() 
	{
		Log.startTestCase("patientCase_TC4_toValidateFields_SelectPatientModel");
		//Pending - Validate desired call back dropdown with patient selected
		
		//Validating textboxes of Select Patient Model
		element = patientCasePage.txtPatientPortalName;
		patientCasePage.validateTextBox(element, "Patient Name");
		
		element = patientCasePage.txtPatientPortalPhoneNumber;
		patientCasePage.validateTextBox(element, "786-868-0792");
		
		element = patientCasePage.txtPatientPortalSSN;
		patientCasePage.validateTextBox(element, "8680792");
		
		element = patientCasePage.txtPatientPortalEMRNo;
		patientCasePage.validateTextBox(element, "0792");
		
		element = patientCasePage.txtPatientPortalDOB;
		patientCasePage.validateTextBox(element, "01/01/1981"); //Verifying data types of each fields still pending
		
		//Validating Radio Buttons
		elements = patientCasePage.radiosPatientPortalActiveInActive;
		patientCasePage.validateRadioButtons(elements);
		
		//Validating Buttons
		element = patientCasePage.btnSearchPatientModel;
		patientCasePage.validateButton(element);
		
		element = patientCasePage.btnClosePatientModel;
		patientCasePage.validateButton(element);
		
		element = patientCasePage.btnSelectPatientModel;
		patientCasePage.validateButton(element);
		
		Log.endTestCase("patientCase_TC4_toValidateFields_SelectPatientModel");
		
	}
	
	@Test(priority = 7, enabled=true)
	public void patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected()
	{
		
		Log.startTestCase("patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected");
		
		//clear all fields
		getDriver().navigate().refresh();
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.click(getDriver(), patientCasePage.btnAddPatientCase);
		patientCasePage.waitForModelDisplayed(patientCasePage.btnAddPatientCase, patientCasePage.PatientCaseModel, patientCasePage.chkOutBoundOnly);
		
		action.JSClick(getDriver(), patientCasePage.btnSelectPatient);
		patientCasePage.searchAndSelectPatient();
		
		
	    Log.endTestCase("patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected");

	}
	
	@Test(priority = 8, enabled=false)
	public void patientCase_TC6_clickSaveScheduler_withSelectingPatient_SchulderPageShouldBeDisplayed()
	{
		
		Log.startTestCase("patientCase_TC6_clickSaveScheduler_withSelectingPatient_SchulderPageShouldBeDisplayed");
		
		//ReOpening the Patient Case Page and exploring to the patient case model
		getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.JSClick(getDriver(), patientCasePage.btnAddPatientCase);
		patientCasePage.waitForModelDisplayed(patientCasePage.btnAddPatientCase, patientCasePage.PatientCaseModel, patientCasePage.chkOutBoundOnly);
		
		action.JSClick(getDriver(), patientCasePage.btnSelectPatient);
		patientCasePage.searchAndSelectPatient();
		
		Log.info("Selecting last value of the Source Dropdown");
		patientCasePage.selectDropdown(patientCasePage.dropdownSource);
		
		Log.info("Selecting last value of the Provider Dropdown");
		patientCasePage.selectDropdown(patientCasePage.dropdownProvider);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		Log.info("Typing " +currentDateTime+ " currentDateTime into the Subject textBox");
		action.type(patientCasePage.txtSubject, currentDateTime);
		
		Log.info("Typing text into the Description TextArea");
		action.type(patientCasePage.textAreas.get(0), "This is an automated test case saving after clicking the Save&Schedule button");
		
		Log.info("Clicking Save&Schedule button to add the automated test case and navigate to the appointment screen");
		action.click(getDriver(), patientCasePage.btnSave$Schedule);
		
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		String currentURL = action.getCurrentURL(getDriver());
		
		if(currentURL.contains("https://staging.pemr.com/Scheduler/SchedulerAppointment.aspx")) {
			Log.info("User is going to be redirected to Expected URL of ShedulerAppointment");
			Assert.assertTrue(true);
			Log.info("User is redirected");
		}else {
			Log.info("User is not redirected to Expected URL of ShedulerAppointment");
			Assert.assertTrue(false);
		}
		
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.waitPreloader();
		//Scheduler model wait can be used later.
		getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.click(getDriver(), patientCasePage.btnSearch);
		
		//if paging happens
		try {
			patientCasePage.btnLastPage.isDisplayed();
			Log.info("There is a paging in Patient Case List");
			patientCasePage.btnLastPage.click();
			Log.info("Got to the last page of Patient Case List");
			action.waitPreloader();
			
			//Click edit button of last result of the last page
			element = patientCasePage.lastPatientCaseInList;
			if(!action.isDisplayed(getDriver(), element)) {
								
			patientCasePage.chkboxesProviders.get(0).click();
			Log.info("Checked Select All provider checkbox");
			action.click(getDriver(), patientCasePage.btnSearch);
			Log.info("Clicked search button");
			action.JSClick(getDriver(), element);
			}else {
					Log.info("Going to click on last result edit button");
					action.JSClick(getDriver(), element);
					Log.info("clicked on last result edit button");
					}
		//if paging not happens	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			//Click edit button of last result of the first page
			element = patientCasePage.lastPatientCaseInList;
			if(!action.isDisplayed(getDriver(), element)) {
				
				patientCasePage.chkboxesProviders.get(0).click();
				Log.info("Checked Select All provider checkbox");
				action.click(getDriver(), patientCasePage.btnSearch);
				Log.info("Clicked search button");
				action.JSClick(getDriver(), element);
			}else {
				Log.info("Going to click on last result edit button");
				action.JSClick(getDriver(), element);
				Log.info("clicked on last result edit button");
			}
		}

		
		patientCasePage.waitForModelDisplayed(patientCasePage.btnAddPatientCase, patientCasePage.PatientCaseModel, patientCasePage.chkOutBoundOnly);
		String actualDateTime = patientCasePage.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully before or after redirecting to the Schedular screen");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved before or after redirecting to the Schedular screen");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
	    Log.endTestCase("patientCase_TC6_clickSaveScheduler_withSelectingPatient_SchulderPageShouldBeDisplayed");

	}


	
	
	@Test(priority = 9, enabled=false)
	public void patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase()
	{
		
		Log.startTestCase("patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");
		
		getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.JSClick(getDriver(), patientCasePage.btnAddPatientCase);
		patientCasePage.waitForModelDisplayed(patientCasePage.btnAddPatientCase, patientCasePage.PatientCaseModel, patientCasePage.chkOutBoundOnly);
		
		Log.info("Selecting last value of the Source Dropdown");
		element = patientCasePage.dropdownSource;
		patientCasePage.selectDropdown(element);
		
		Log.info("Selecting last value of the Provider Dropdown");
		element = patientCasePage.dropdownProvider;
		patientCasePage.selectDropdown(element);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		Log.info("Typing " +currentDateTime+ " currentDateTime into the Subject textBox");
		element = patientCasePage.txtSubject;
		action.type(element, currentDateTime);
		
		Log.info("Typing text into the Description TextArea");
		elements = patientCasePage.textAreas;
		action.type(elements.get(0), "This is an automated test case saving after clicking the Save button");
		
		//Selecting the patient
		action.JSClick(getDriver(), patientCasePage.btnSelectPatient);
		patientCasePage.searchAndSelectPatient();
			
		Log.info("Clicking Save button to add the automated test case that will close this patient case model");
		element = patientCasePage.btnSave;
		action.click(getDriver(), element);
		Log.info("Save button is clicked");
		action.waitPreloader();
		
		//exploring to the patient case page again
		getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.click(getDriver(), patientCasePage.btnSearch);
		action.waitPreloader();
		
		//power of isDisplayed from our action class
		//if paging happens
		if(action.isDisplayed(getDriver(), patientCasePage.btnLastPage)){ 
			Log.info("There is a paging in Patient Case List");
			patientCasePage.btnLastPage.click();
			Log.info("Got to the last page of Patient Case List");
			action.waitPreloader();
			
			//Click edit button of last result of the last page
			element = patientCasePage.lastPatientCaseInList;
			if(!action.isDisplayed(getDriver(), element)) {
				
				patientCasePage.chkboxesProviders.get(0).click();
				Log.info("Checked Select All provider checkbox");
				action.click(getDriver(), patientCasePage.btnSearch);
				Log.info("Clicked search button");
				action.JSClick(getDriver(), element);
			}else {
				Log.info("Going to click on last result edit button");
				action.JSClick(getDriver(), element);
				Log.info("clicked on last result edit button");
			}
		//if paging does not happens
		}else {
			
			//Click edit button of last result of the first page
			//Never use direct IsDisplayed, can give NoSuchElementException and stop the test case
			element = patientCasePage.lastPatientCaseInList;
			if(!action.isDisplayed(getDriver(), element)) {
				patientCasePage.chkboxesProviders.get(0).click();
				Log.info("Checked Select All provider checkbox");
				action.click(getDriver(), patientCasePage.btnSearch);
				Log.info("Clicked search button");
				action.JSClick(getDriver(), element);
			}else {
				Log.info("Going to click on last result edit button");
				action.JSClick(getDriver(), element);
				Log.info("clicked on last result edit button");
				
			}
		}
		
		patientCasePage.waitForModelDisplayed(patientCasePage.btnAddPatientCase, patientCasePage.PatientCaseModel, patientCasePage.chkOutBoundOnly);
		String actualDateTime = patientCasePage.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
		
	    Log.endTestCase("patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");

	}
	
	
}

	

	
	