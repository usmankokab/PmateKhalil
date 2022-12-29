package com.ipclinical.testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import com.ipclinical.pageobjects.ClinicalInboxPage;
import com.ipclinical.pageobjects.DashboardPage;
import com.ipclinical.pageobjects.LoginPage;
import com.ipclinical.pageobjects.PatientCasePage;
import com.ipclinical.pageobjects.PatientChartPage;
import com.ipclinical.pageobjects.PatientListPage;
import com.ipclinical.pageobjects.SchedulerAppointmentPage;
import com.ipclinical.utility.Log;

public class PatientCaseTest extends BaseClass{
	DashboardPage dashboardPage;
	PatientListPage patientListPage;
	LoginPage loginPage;
	PatientChartPage chartPage;
	
	PatientCasePage page;
	
	
	WebElement element;
	List<WebElement> elements;
	
	SoftAssert softAssert = new SoftAssert();
	Action action = new Action();
	
	
	//@Parameters("browser")
	@BeforeClass
	public void setup() {
		
		launchApp(prop.getProperty("browser")); 
		//Verifying user is logged in
				
	}
	
	@AfterClass
	public void tearDown() {
		//getDriver().quit();
	}

	@Test(priority = 1)
	public void patientCase_TC1_toNavigatePatientCase() throws Throwable {
		Log.startTestCase("patientCase_TC1_toNavigatePatientCase");
		
		loginPage = new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("code"));
		Log.info("User is logged in successfully");
		
		action.waitPreloader(30);
		patientListPage = dashboardPage.clickPatientWidget();
		Log.info("Patient Widget is clicked");
		action.waitPreloader(30);
		action.pageLoadTimeOut(getDriver(), 50);
		Log.info("User is on Patient List Page");
	    
	    patientListPage.clickPatientsMenu();
	    Log.info("Patients Menu is clicked");
	    page = patientListPage.clickPatientCaseLink();
	    Log.info("Patient Case in Patients menu is clicked");
	    action.waitPreloader(30);
	    action.pageLoadTimeOut(getDriver(), 20);
	    
	    //User should land on Patient case page
	    //page = new page();
	    String actualURL = action.getCurrURL();
	    String expectedURL = prop.getProperty("url") +"Patient/Patientcase.aspx";
		Log.info("Verifying if user explored to the Patient Case Page");
	    Assert.assertEquals(actualURL, expectedURL);
	    Log.info("User landed on Patient case page");
	    
	    
	    	    
	    Log.endTestCase("patientCase_TC1_toNavigatePatientCase");
	    
    		
	}
	
	
	@Test (priority=1, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC1a_toValidateFields_page() throws Throwable {
		Log.startTestCase("patientCase_TC1a_toValidateFields_page");
		
		
		//Verifying Patient Name label and TextBox
		//page = new PatientCasePage();
	    action.validateLabel(page.lblPatientName, "Patient Name");
	    action.validateTextBox(page.txtPatientName, "Test Patient");
	    
		//Validating multiple Locations checkboxes
	    action.validateCheckboxes(page.chkboxesLocations);
	    
	   //Validating multiple Providers checkboxes
	    action.validateCheckboxes(page.chkboxesProviders);

	    //Validating Status dropdown and its option values
	    //String[] expected = {"Please Select","Open","Close"};	
	    action.validateDropdown(page.ddStatus);
	    Log.info("Expected values matched with Actual values, and user can select any value");
	    
	    
	    //Validating Search Button
	    action.validateButton(page.btnSearch);
	    
	    //Validating AddPatientCase Button
	    action.validateButton(page.btnAddPatientCase);
	    
	    //Validating FromDate Textbox
	    action.validateTxtDateCurrent(page.txtFromDate);
	    
	    //Validating ToDate Textbox
	    action.validateTxtDateCurrent(page.txtToDate);
	    
	    //Validating Search Table Headings
	    String[] expectedHeadings = {"MR Number", "Patient Name", "Location Name", "Provider Name", "Source", "Recipient", "Edit"};
	    elements = page.tableHeadings;
	    action.validateTableHeadings(expectedHeadings, elements);
	    Log.info("All expected headings are present in search table");

	    
	    Log.endTestCase("patientCase_TC1a_toValidateFields_page");
	    
			
	}

	
	@Test(priority = 2, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed()
	{
		Log.startTestCase("patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed");
		//page = new PatientCasePage();
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		page.openPatientCaseModel();
		action.waitPreloader(30);

		action.click(getDriver(), page.btnAddPatientCase);
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
	    Log.endTestCase("patientCase_TC2_clickBtnAddPatientCase_PatientCaseModelShouldBeDisplayed");

	}
	
	
	@Test(priority = 3, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC3_8_9_toValidateFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC3_8_9_toValidateFields_PatientCaseModel");
		//page = new PatientCasePage();
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		page.openPatientCaseModel();
		action.waitPreloader(30);
		
		//Validating buttons
		action.validateButton(page.btnClose);
		action.validateButtonDisabled(page.btnPrint);
		action.validateButtonDisabled(page.btnSave);
		action.validateButtonDisabled(page.btnSave$Schedule);
		
		//Validating dropdowns and their values
		//String[] expected = {"Please Select","Patient","Parent/Care Giver", "Spouse/Partner", "Pharmacy", "Lab", "PcP", "Specialist", "Clinical Staff", "Hospital", "Other"};	
		action.validateDropdown(page.dropdownSource);
	    
	    //String[] expected1 = {"Please Select","Administrative","Clinical Question", "Medical Record- Electronic", "Medical Record - Paper", "Medication", "Referral", "Refill", "Patient No Show", "Lab", "Imaging", "Procedure", "DME", "Refund", "Other"};	
	    action.validateDropdown(page.dropdownType);

	    //String[] expected2 = {"Please Select", "Ahmar, Wasim","Rosselot, Eric", "TEHRANI, ALI REZAZADEH"};	
	    action.validateDropdown(page.dropdownProvider);
		
	    //String[] expected3 = {"Please Select","High","Medium", "Low"};	
	    action.validateDropdown(page.ddPriority);
	    
	    //String[] expected4 = {"Please Select","Facility","North Bethesda Maryland"};	
	    action.validateDropdown(page.dropdownLocation);
		
	    
	    //Validating checkboxes
	    if(action.validateCheckbox(page.chkOutBoundOnly) && action.validateCheckbox(page.chkOutSideProvider)) 
	    {
	    	Log.info("All checkboxes are displayed and checkable");
	    }else
	    {
	    	Log.error("All or any of the checkbox in the Model window does not appear");
	    	
	    }
	    
	    //Validating TextBoxes
	    action.validateTextBox(page.txtPersonToCall, "Person to Call");
	    action.validateTextBox(page.txtSubject, "Test Subject");
	    action.validateTextBox(page.txtOther, "Other");
	    
	    //Validating Text Areas
	    action.validateTextAreas(page.textAreas, "This is test description for textarea field/n" + " This is test description for textarea field");
	    
	    //Validating Radio Buttons
	    action.validateRadioButtons(page.radioButtons);
	    
	    	    
	    Log.endTestCase("patientCase_TC3_8_9_toValidateFields_PatientCaseModel");
	    
	    
	    
	}
	
	@Test (priority = 4, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		//page = new PatientCasePage();
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		page.openPatientCaseModel();
		action.waitPreloader(30);
		
		//Filling all all mandatory fields
		page.fillMandatoryFields("Subject");
		
		Log.endTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		
	}
	
	@Test(priority = 4, enabled=false, dependsOnMethods = { "patientCase_TC1_toNavigatePatientCase" })
	public void patientCase_TC5_clickBtnSaveSchudular_BtnSave_withoutPatienSelecting_AlertShouldBeDisplayed() throws InterruptedException
	{
		
		Log.startTestCase("patientCase_TC5_clickBtnSaveSchudular_withoutPatienSelecting_AlertShouldBeDisplayed");
		//page = new page();
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		page.openPatientCaseModel();
		action.waitPreloader(30);
		
		page.fillMandatoryFields("Subject");
		
		Log.info("Validating Alert message once clicked on Save&Schedule button if patient is not selected");
		action.click(getDriver(), page.btnSave$Schedule);
		if(action.isAlertPresent(getDriver())) {
			Log.info("Alert Present"+ "(Please Select Patient)");
			Alert alert = getDriver().switchTo().alert();
			String alertMessage = alert.getText();
			Assert.assertEquals(alertMessage, "Please Select Patient");
			alert.accept();
			Log.info("Alert message validated once clicked on Save&Schedule button if patient is not selected");
			//Thread.sleep(5000);
		}else {
			Log.warn("Alert does not present, test failed");
			Assert.assertTrue(false);
		}
		
		Log.info("Validating Alert message once clicked on Save button if patient is not selected");
		action.click(getDriver(), page.btnSave);
		if(action.isAlertPresent(getDriver())) {
			Log.info("Alert Present"+ "(Please Select Patient)");
			Alert alert = getDriver().switchTo().alert();
			String alertMessage = alert.getText();
			Assert.assertEquals(alertMessage, "Please Select Patient");
			alert.accept();
			Log.info("Alert message validated once clicked on Save button if patient is not selected");
			//Thread.sleep(5000);
		}else {
			Log.warn("Alert does not present, test failed");
			Assert.assertTrue(false);
		}
		
	    
	    Log.endTestCase("patientCase_TC5_clickBtnSaveSchudular_withoutPatienSelecting_AlertShouldBeDisplayed");

	}

	
	@Test(priority = 5, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed()
	{
		
		Log.startTestCase("patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed");
		//page = new PatientCasePage();
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		
		page.openPatientCaseModel();
		action.waitPreloader(30);
		
		if(page.openPatientSearchModel()) {
			Assert.assertTrue(true);
			Log.info("User clicked on Select Patient button");
			Log.info("Patient Search Model displayed");
		}else {
			Log.info("Patient Search Model does not appear, somthing went wrong");
			Assert.assertTrue(false);
		}
		
		
	    Log.endTestCase("patientCase_TC4_clickBtnSelectPatient_PatientSearchModelShouldBeDisplayed");

	}
	
	@Test(priority = 5, enabled=false, dependsOnMethods = { "patientCase_TC1_toNavigatePatientCase" })
	public void patientCase_TC4b_clickBtnSave_withoutPatienSelecting_AlertShouldBeDisplayed() throws InterruptedException
	{
		
		Log.startTestCase("patientCase_TC4b_clickBtnSelect_withoutPatienSelecting_AlertShouldBeDisplayed");
		//page = new PatientCasePage();
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		action.click(getDriver(), page.btnAddPatientCase);
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		action.click(getDriver(), page.btnSelectPatient);
		action.waitForModelDisplayed(page.modelPatientSearch, page.txtPatientName);
		
		action.click(getDriver(), page.btnSelectPatientModel);
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


	
	@Test(priority = 6, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC4a_toValidateFields_SelectPatientModel() 
	{
		Log.startTestCase("patientCase_TC4_toValidateFields_SelectPatientModel");
		//page = new PatientCasePage();	
		
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		action.click(getDriver(), page.btnAddPatientCase);
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		//Validating textboxes of Select Patient Model
		action.click(getDriver(), page.btnSelectPatient);
		action.validateTextBox(page.txtPatientPortalName, "Patient Name");
		action.validateTextBox(page.txtPatientPortalPhoneNumber, "(786) 868-0792");
		action.validateTextBox(page.txtPatientPortalSSN, "8680792");
		action.validateTextBox(page.txtPatientPortalEMRNo, "0792");
		action.validateTextBox(page.txtPatientPortalDOB, "01/01/1981"); //Verifying data types of each fields still pending
		
		//verifying radio buttons
		action.validateRadioButtons(page.radiosPatientPortalActiveInActive);
		
		//Validating Buttons
		action.validateButton(page.btnSearchPatientModel);
		action.validateButton(page.btnClosePatientModel);
		action.validateButton(page.btnSelectPatientModel);
		
		Log.endTestCase("patientCase_TC4_toValidateFields_SelectPatientModel");
		
	}
	
	@Test(priority = 7, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected()
	{
		
		Log.startTestCase("patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected");
		//page = new PatientCasePage();
		
		//clear all fields
		getDriver().navigate().refresh();
		action.waitPreloader(30);
		action.pageLoadTimeOut(getDriver(), 50);
		
		page.openPatientCaseModel();
		page.openPatientSearchModel();
		page.searchAndSelectPatient(prop.getProperty("MRN"));
		action.waitPreloader(30);
		
		Log.info("Patient Selected, Search Result Appeared and Patient selected succesfully");
		
		
	    Log.endTestCase("patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected");

	}
	
	@Test(priority = 8, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC6_clickSaveScheduler_withSelectingPatient_schedulerPageShouldBeDisplayed() throws Throwable
	{
		
		Log.startTestCase("patientCase_TC6_clickSaveScheduler_withSelectingPatient_schedulerPageShouldBeDisplayed");
		//page = new PatientCasePage();
		
		//ReOpening the Patient Case Page and exploring to the patient case model
		getDriver().navigate().to(prop.getProperty("url") +"Patient/Patientcase.aspx");
		action.pageLoadTimeOut(getDriver(), 50);
		action.JSClick(getDriver(), page.btnAddPatientCase);
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		//Filling mandatory fields 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		page.fillMandatoryFields(currentDateTime);
		
		
		Log.info("Typing text into the Description TextArea");
		action.type(page.textAreas.get(0), "This is an automated test case saving after clicking the Save&Schedule button");
		
		//Selecting the patient
		action.JSClick(getDriver(), page.btnSelectPatient);
		page.searchAndSelectPatient((prop.getProperty("MRN")));
		
		Log.info("Going to click Save&Schedule button to add the automated test case and navigate to the appointment screen");
		action.click(getDriver(), page.btnSave$Schedule);
		Log.info("Save&Schedule button is clicked");
		
		SchedulerAppointmentPage schedulerAppointmentPage = new SchedulerAppointmentPage();
		action.explicitWait(getDriver(), schedulerAppointmentPage.btnTodayShedule, 50); 
		Log.info("Button TodaySchedule displayed on SchedularAppointmentPage");
		
		
		
		//Verification of redirection
		String currentURL = action.getCurrURL();
		if(currentURL.contains(prop.getProperty("url")+ "Scheduler/SchedulerAppointment.aspx")) {
			Log.info("User is going to be redirected to Expected URL of ShedulerAppointment");
			Assert.assertTrue(true);
			Log.info("User is redirected");
		}else {
			Log.info("User is not redirected to Expected URL of ShedulerAppointment");
			Assert.assertTrue(false);
		}
		
		
		//To verify all added cases of relevant patients are shown
		//to verify user can be able to click on edit of a specific patient case
		//To verify whether or not patient case is saved once clicked on Save and Schedule
		action.implicitWait(getDriver(), 20);		
		getDriver().navigate().to(prop.getProperty("url")+ "Patient/Patientcase.aspx");
		
		action.waitPreloader(30);
		action.click(getDriver(), page.chkboxesProviders.get(0));
		action.click(getDriver(), page.btnSearch);
		action.waitPreloader(30);
		
		//clicking on last result of the patient case list
		page.clickOnLastResultOfPtCaseList();

		
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		String actualDateTime = page.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully before or after redirecting to the Schedular screen");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved before or after redirecting to the Schedular screen");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
	    Log.endTestCase("patientCase_TC6_clickSaveScheduler_withSelectingPatient_schedulerPageShouldBeDisplayed");

	}



	@Test(priority = 9, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC7_TC10_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase()

	{
		
		Log.startTestCase("patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");
		//page = new PatientCasePage();
		
		getDriver().navigate().to(prop.getProperty("url")+ "Patient/Patientcase.aspx");
		action.pageLoadTimeOut(getDriver(), 50);
		action.JSClick(getDriver(), page.btnAddPatientCase);
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		//Filling mandatory fields 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		page.fillMandatoryFields(currentDateTime);
				
		
		//Selecting the patient
		action.JSClick(getDriver(), page.btnSelectPatient);
		page.searchAndSelectPatient((prop.getProperty("MRN")));
			
		Log.info("Clicking Save button to add the automated test case that will close this patient case model");
		action.click(getDriver(), page.btnSave);
		Log.info("Save button is clicked");
		action.waitPreloader(30);
		
		//exploring to the patient case page again
		//getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		
		//To verify all added cases of relevant patients are shown
		//to verify user can edit the patient case
		//To verify whether or not patient case is saved
		//action.waitPreloader();
		
		action.click(getDriver(), page.chkboxesProviders.get(0));
		action.click(getDriver(), page.btnSearch);
		action.waitPreloader(30);
		
		//clicking on last result of the patient case list
		page.clickOnLastResultOfPtCaseList();
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		String actualDateTime = page.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
		//Editing textarea and saving
		page.textAreas.get(0).sendKeys(" (Edited)");
		page.btnSave.click();
		Log.info("Patient Case Edited successfully");
		
	    Log.endTestCase("patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");

	}
	
	@Test (priority = 10, enabled=false, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC11_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstSelectedProvider() {
	
		Log.startTestCase("patientCase_TC11_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstAssignedBucket");
		
		//Navigate to patient case
		getDriver().navigate().to(prop.getProperty("url")+ "Patient/Patientcase.aspx");
		action.waitPreloader(30);
		action.click(getDriver(), page.btnAddPatientCase);
		action.waitPreloader(30);
		
		//filling mandatory fields
		String currentTime = action.getCurrentTime();
		page.fillMandatoryFields(currentTime);
		action.selectBySendkeys("Refund", page.dropdownType);
		
		//Search and Select patient
		page.btnSelectPatient.click();
		page.searchAndSelectPatient((prop.getProperty("MRN")));
		
		//Assigning the patient case
		page.assignPatientCase("saim", page.AssigneeElementaliSaim);
		
		
		getDriver().navigate().to(prop.getProperty("url")+ "ClinicalInbox.aspx");
		action.waitPreloader(30);
		
		ClinicalInboxPage clinicalInboxPage = new ClinicalInboxPage();
		action.explicitWait(getDriver(), clinicalInboxPage.refundAgainstSaim, 50);
		action.click(getDriver(), clinicalInboxPage.refundAgainstSaim);
		
		action.fluentWait(getDriver(), clinicalInboxPage.refundHeading, 50);
		action.click(getDriver(), getDriver().findElement(By.linkText(currentTime)));
		
		//Verifying added and assigned Patient Case in Clinical Inbox
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		String subjectText = page.txtSubject.getAttribute("value");
		Assert.assertEquals(currentTime, subjectText);
		Log.info("Patient case is saved successfully and shown in the Clinical Inbox under Patient Refund against the assigned bucket");
		
		
		Log.endTestCase("patientCase_TC11_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstSelectedProvider");
		
	}
	
	
	
	///////////////////////////////////////////////Paient Chart Patient Case Tests////////////////////////
	
	
	
	@Test(priority = 15, dependsOnMethods = {"patientCase_TC1_toNavigatePatientCase"})
	public void patientCase_TC12_toNavigateto_PatientChart_PatientCase() throws Throwable {
		Log.startTestCase("patientCase_TC12_toNavigateto_PatientChart_PatientCase");
		
		//loginPage = new LoginPage();
		//dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("code"));
		//Log.info("User is logged in successfully");
		getDriver().navigate().to("https://staging.pemr.com/Patient/PatientList.aspx");
		action.waitPreloader(30);
		
		chartPage = patientListPage.goToPatientChart(prop.getProperty("MRN"));
		Log.info("User Landed on Patient Chart page");
		
		action.pageLoadTimeOut(getDriver(), 100);
		action.waitForClickablility(chartPage.tabPatientCase, 100);
		action.JSClick(getDriver(), chartPage.tabPatientCase);
		Log.info("User clicked on Patient Case Tab");
		
	    Log.endTestCase("patientCase_TC12_toNavigateto_PatientChart_PatientCase");
	    
    		
	}
	
	
	@Test(priority = 16, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed()
	{
		Log.startTestCase("patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed");
		
		
		action.getNgDriver().waitForAngularRequestsToFinish();

		chartPage.openPatientCaseModel_fromChart();
		Log.info("Patient Case Model opened in Patient Chart Page");
		
		page.btnClose.click();
		
	    Log.endTestCase("patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed");

	}
	
	
	
	
	@Test(priority = 17, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_TC14_toValidateFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC13b_toValidateFields_PatientCaseModel");
		//page = new PatientCasePage();
		
		chartPage.openPatientCaseModel_fromChart();
		
		//Validating buttons
		action.validateButton(page.btnClose);
		action.validateButtonDisabled(page.btnPrint);
		action.validateButtonDisabled(page.btnSave);
		action.validateButtonDisabled(page.btnSave$AddOrders);
		
		//Validating dropdowns and their values
		action.validateDropdown(page.dropdownSource);
	    action.validateDropdown(page.dropdownType);
	    action.validateDropdown(page.dropdownProvider);
	    action.validateDropdown(page.ddCallBackNumber);
	    action.validateDropdown(page.ddPriority);
	    action.validateDropdown(page.dropdownLocation);
	
	    //Validating checkboxes
	    if(action.validateCheckbox(page.chkOutBoundOnly) && action.validateCheckbox(page.chkOutSideProvider)) 
	    {
	    	Log.info("All checkboxes are displayed and checkable");
	    }else
	    {
	    	Log.error("All or any of the checkbox in the Model window does not appear");
	    	
	    }
	    
	    //Validating TextBoxes
	    action.validateTextBox(page.txtPersonToCall, "Person to Call");
	    action.validateTextBox(page.txtSubject, "Test Subject");
	    action.validateTextBox(page.txtOther, "Other");
	    
	    //Validating Text Areas
	    action.validateTextArea(chartPage.textAreaDescription, "This is test description for textarea");
	    action.validateTextArea(chartPage.textAreaActionNote, "This is test Action note");
	    
	    //Validating Radio Buttons
	    action.validateRadioButtons(page.radioButtons);
	    
	    action.click(getDriver(),page.btnClose);
	    	    
	    Log.endTestCase("patientCase_TC13b_toValidateFields_PatientCaseModel");
	}
	
	@Test (priority = 18, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_TC15_toValidateMandatoryFields_PatientCaseModel_VerifyingDisabledButtons()
	{
		Log.startTestCase("patientCase_TC15_toValidateMandatoryFields_PatientCaseModel_VerifyingDisabledButtons");
				
		chartPage.openPatientCaseModel_fromChart();
		
		//Filling all all mandatory fields
		page.fillMandatoryFields("Subject");
		page.btnClose.click();
		
		Log.endTestCase("patientCase_TC15_toValidateMandatoryFields_PatientCaseModel_VerifyingDisabledButtons");
		
	}

	
	@Test(priority = 20, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_patientChart_TC17_BtnSelectPatient_ShouldNotBeDisplayed()
	{
		
		Log.startTestCase("patientCase_patientChart_TC17_BtnSelectPatient_ShouldNotBeDisplayed");
		//page = new PatientCasePage();
		chartPage.openPatientCaseModel_fromChart();
		
		
		if(!action.isDisplayed(getDriver(), page.btnSelectPatient)) {
			Assert.assertTrue(true);
			Log.info("Select Patient button is not available as expected");
		}else {
			Log.info("Patient Search Model appear, but should not be present as expected, test faled.");
			Assert.assertTrue(false);
		}
		action.click(getDriver(), page.btnClose);
		
	    Log.endTestCase("patientCase_patientChart_TC17_BtnSelectPatient_ShouldNotBeDisplayed");

	}
	
		
	@Test(priority = 24, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_patientChart_TC21_clickSaveAddOrders_referralModelShouldBeDisplayed() throws Throwable
	{
		
		Log.startTestCase("patientCase_patientChart_TC21_clickSaveAddOrders_referralModelShouldBeDisplayed");
		//page = new PatientCasePage();
		
		chartPage.openPatientCaseModel_fromChart();
		
		//Filling mandatory fields 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		
		//Select select = new Select(page.dropdownType);
		
		if(action.selectByVisibleText("Referral", page.dropdownType)) {
			Log.info("Type as Referral selected");
			Assert.assertTrue(true);
		}else {
			Log.info("Type as Referral not selected");
			Assert.assertTrue(false);
		}
		page.fillMandatoryFields(currentDateTime);
		
		
		Log.info("Typing text into the Description TextArea");
		action.type(chartPage.textAreaDescription, "This is an automated test case saving after clicking the Save&Schedule button");
		
		//Selecting referral from Type dropdown
		
		Log.info("Going to click Save&AddOrder button to add the automated test case and navigate to the referral model");
		action.JSClick(getDriver(), chartPage.btnSave$AddOrders);
		Log.info("Save&AddOrder button clicked");
		
		//Verification of redirection
		action.waitForModelDisplayed(chartPage.referralSearchModel, chartPage.referralModalLabel);
		Log.info("User is redirected to Referral Model");
		
		//To verify all added cases of relevant patients are shown
		//to verify user can be able to click on edit of a specific patient case
		//To verify whether or not patient case is saved once clicked on Save and AddOrder button
		
		chartPage.btnCloseReferralModel.click();
		action.waitPreloader(30);
	
		chartPage.btnRefresh.click();
		action.waitPreloader(30);
		//clicking on first result of the patient case list
		chartPage.clickOnFirstResultOfPtCaseList();
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		String actualDateTime = page.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully before or after redirecting to the referral model");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved before or after redirecting to the referral model");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
		page.btnClose.click();
		
	    Log.endTestCase("patientCase_patientChart_TC21_clickSaveAddOrders_referralModelShouldBeDisplayed");

	}


	@Test(priority = 25, enabled=true, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_patientChart_TC22_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase()

	{
		
		Log.startTestCase("patientCase_patientChart_TC22_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");
		//page = new PatientCasePage();
		
		chartPage.openPatientCaseModel_fromChart();
		
		//Filling mandatory fields 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		
		page.fillMandatoryFields(currentDateTime);
				
			
		Log.info("Clicking Save button to add the automated test case that will close this patient case model");
		action.click(getDriver(), page.btnSave);
		Log.info("Save button is clicked");
		action.waitPreloader(30);
		
		//exploring to the patient case page again
		//getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		
		//To verify all added cases of relevant patients are shown
		//to verify user can edit the patient case
		//To verify whether or not patient case is saved
		//action.waitPreloader();
		
		//clicking on last result of the patient case list
		chartPage.clickOnFirstResultOfPtCaseList();
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		
		String actualDateTime = page.txtSubject.getAttribute("value");
		Log.info("Comparing Actual DateTime-" +actualDateTime+ " with CurrentDateTime-" +currentDateTime);
		
		if(actualDateTime.equals(currentDateTime)) {
			Log.info("Patient case is saved successfully");
			Assert.assertEquals(actualDateTime, currentDateTime);
		} else {
			Log.info("Patient case is not saved");
			Assert.assertEquals(actualDateTime, currentDateTime);
		}
		
		//Editing textarea and saving
		page.textAreas.get(0).sendKeys(" (Edited)");
		page.btnSave.click();
		Log.info("Patient Case Edited successfully");
		
	    Log.endTestCase("patientCase_patientChart_TC22_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");

	}
	
	@Test (priority = 26, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_patientChart_TC23_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstSelectedProvider() {
	
		Log.startTestCase("patientCase_patientChart_TC23_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstSelectedProvider");
		
		//Navigate to patient case
		getDriver().navigate().to(prop.getProperty("url")+ "Patient/Patientcase.aspx");
		action.waitPreloader(30);
		action.click(getDriver(), page.btnAddPatientCase);
		action.waitPreloader(30);
		
		//filling mandatory fields
		String currentTime = action.getCurrentTime();
		page.fillMandatoryFields(currentTime);
		action.selectBySendkeys("Refund", page.dropdownType);
		
		//Search and Select patient
		page.btnSelectPatient.click();
		page.searchAndSelectPatient((prop.getProperty("MRN")));
		
		//Assigning the patient case
		page.assignPatientCase("saim", page.AssigneeElementaliSaim);
		
		
		getDriver().navigate().to(prop.getProperty("url")+ "ClinicalInbox.aspx");
		action.waitPreloader(30);
		
		ClinicalInboxPage clinicalInboxPage = new ClinicalInboxPage();
		action.explicitWait(getDriver(), clinicalInboxPage.refundAgainstSaim, 50);
		action.click(getDriver(), clinicalInboxPage.refundAgainstSaim);
		
		action.fluentWait(getDriver(), clinicalInboxPage.refundHeading, 50);
		action.click(getDriver(), getDriver().findElement(By.linkText(currentTime)));
		
		//Verifying added and assigned Patient Case in Clinical Inbox
		action.waitForModelDisplayed(page.PatientCaseModel, page.chkOutBoundOnly);
		String subjectText = page.txtSubject.getAttribute("value");
		Assert.assertEquals(currentTime, subjectText);
		Log.info("Patient case is saved successfully and shown in the Clinical Inbox under Patient Refund against the assigned bucket");
		
		
		Log.endTestCase("patientCase_patientChart_TC23_selectTypeAsRefund_patientCaseShouldbeDisplayedIn_refundColumnInClinicalInbox_againstSelectedProvider");
		
	}

	
	
}

	

	
	