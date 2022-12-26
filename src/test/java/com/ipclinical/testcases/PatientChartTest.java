package com.ipclinical.testcases;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
import com.ipclinical.pageobjects.PatientChartPage;
import com.ipclinical.pageobjects.PatientListPage;
import com.ipclinical.utility.Log;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class PatientChartTest extends BaseClass{

	DashboardPage dashboardPage;
	PatientListPage patientListPage;
	LoginPage loginPage;
	PatientChartPage page;
	PatientCasePage patientCasePage;
	
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
	public void patientCase_TC12_toNavigateto_PatientChart_PatientCase() throws Throwable {
		Log.startTestCase("patientCase_TC12_toNavigateto_PatientChart_PatientCase");
		
		loginPage = new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("code"));
		Log.info("User is logged in successfully");
		
		action.waitPreloader();
		patientListPage = dashboardPage.clickPatientWidget();
		Log.info("Patient Widget is clicked");
		action.waitPreloader();
		
		action.getNgDriver().waitForAngularRequestsToFinish();
		page = patientListPage.goToPatientChart(prop.getProperty("MRN"));
		action.pageLoadTimeOut(getDriver(), 50);
		
		
	    //User should land on Patient Chart Page
	    String currentURL = action.getCurrURL(); 
	    Log.info("Verifying if user explored to the Patient Chart Page");
		if(currentURL.contains(prop.getProperty("url") +"Encounter/PatientChart.aspx")){
			Assert.assertTrue(true);
		    Log.info("User landed on Patient chart page");
		}else {
			
		    Log.info("This is not patient chart page");
		    Assert.assertTrue(false);
		}
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//NgWebDriver ngDriver = new NgWebDriver(js);
		
		action.getNgDriver().waitForAngularRequestsToFinish();
		
		//ngDriver.waitForAngularRequestsToFinish();
		action.waitPreloader();
		action.click(getDriver(), page.tabPatientCase);
		
			    
	    Log.endTestCase("patientCase_TC12_toNavigateto_PatientChart_PatientCase");
	    
    		
	}
	
	
	@Test(priority = 2, enabled=false, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed()
	{
		Log.startTestCase("patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed");
		patientCasePage = new PatientCasePage();
		
		
		action.getNgDriver().waitForAngularRequestsToFinish();

		page.openPatientCaseModel_fromChart();
		action.click(getDriver(), patientCasePage.btnClose);
		
		Log.info("Patient Case Model opened in Patient Chart Page");
		
	    Log.endTestCase("patientCase_fromPatientChart_TC13_clickBtnAdd_PatientCaseModelShouldBeDisplayed");

	}
	
	
	
	
	@Test(priority = 3, enabled=true, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_TC13b_toValidateFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC13b_toValidateFields_PatientCaseModel");
		//page = new PatientCasePage();
		
		page.openPatientCaseModel_fromChart();
		
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
	    action.validateTextArea(page.textAreaDescription, "This is test description for textarea");
	    action.validateTextArea(page.textAreaActionNote, "This is test Action note");
	    
	    //Validating Radio Buttons
	    action.validateRadioButtons(page.radioButtons);
	    
	    action.click(getDriver(),page.btnClose);
	    	    
	    Log.endTestCase("patientCase_TC13b_toValidateFields_PatientCaseModel");
	}
	
	@Test (priority = 4, enabled=true, dependsOnMethods = {"patientCase_TC12_toNavigateto_PatientChart_PatientCase"})
	public void patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel()
	{
		Log.startTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		patientCasePage = new PatientCasePage();
		
		page.openPatientCaseModel_fromChart();
		
		//Filling all all mandatory fields
		patientCasePage.fillMandatoryFields("Subject");
		
		Log.endTestCase("patientCase_TC3a_toValidateMandatoryFields_PatientCaseModel");
		
	}



	
	
}
