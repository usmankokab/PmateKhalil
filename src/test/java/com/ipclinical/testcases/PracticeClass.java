package com.ipclinical.testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.pageobjects.DashboardPage;
import com.ipclinical.pageobjects.LoginPage;
import com.ipclinical.pageobjects.PatientCasePage;
import com.ipclinical.pageobjects.PatientListPage;
import com.ipclinical.utility.Log;

public class PracticeClass extends BaseClass{

	DashboardPage dashboardPage;
	PatientListPage patientListPage;
	LoginPage loginPage;
	PatientCasePage patientCasePage;
	
	WebElement element;
	List<WebElement> elements;
	
	SoftAssert softAssert = new SoftAssert();
	PatientCaseTest patientCase = new PatientCaseTest();
	
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
		action.pageLoadTimeOut(getDriver(), 40);
		
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
	    
	    
	    Log.endTestCase("patientCase_TC1_toNavigatePatientCase");
	    
	    	
	}
	
	@Test(priority = 2)
	public void patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase()
	{
		
		Log.startTestCase("patientCase_TC7_clickSave_withSelectingPatient_patientCaseMainWindowShouldBeDisplayed_verifyAddedPatientCase");
		
		
		
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.waitPreloader();
		//Scheduler model wait can be used later.
		getDriver().navigate().to("https://staging.pemr.com/Patient/Patientcase.aspx");
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		element = patientCasePage.btnAddPatientCase;
		action.JSClick(getDriver(), element);
		
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
		
		
		patientCase.patientCase_TC4c_SelectingPatient_SearchResultShouldAppear_patientShouldBeSelected();
		
		Log.info("Clicking Save button to add the automated test case that will close this patient case model");
		element = patientCasePage.btnSave;
		action.click(getDriver(), element);
		Log.info("Save button is clicked");
		
		action.waitPreloader();
		action.pageLoadTimeOut(getDriver(), 50);
		action.click(getDriver(), patientCasePage.btnSearch);
		action.waitPreloader();
		element = patientCasePage.lastPatientCaseInList;
		
		
		if(!element.isDisplayed()) {
			
			patientCasePage.chkboxesProviders.get(0).click();
			action.click(getDriver(), patientCasePage.btnSearch);
			action.JSClick(getDriver(), element);
		}else {
			action.JSClick(getDriver(), element);
			
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
