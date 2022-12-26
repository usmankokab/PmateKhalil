package com.ipclinical.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class PatientListPage extends BaseClass{
	
	@FindBy(xpath = "//a[@title='Patients']")
	WebElement menuPatient;
	
	@FindBy(css = "a[href='/Patient/Patientcase.aspx']")
	WebElement linkPatientCase;
	
	@FindBy(xpath = "//input[@ng-model='EmrNo']")
	WebElement txtEmrNo;
	
	@FindBy(xpath = "//button[normalize-space()='Search Patient']")
	WebElement btnSearchPatient;
	
	@FindBy(xpath = "(//input[@ng-model='Patient.IsCCCDChecked'])[1]")
	WebElement chkFirstSelectPatient;
	
	@FindBy(xpath = "(//img[@src='../App_Themes/AvicenTheme-test/img/svg/icons/patient-chart.svg'])[1]")
	WebElement btnActionPatientChart;
	
	
	
	
	
	
	
	public PatientListPage ( ) {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	public PatientCasePage clickPatientCaseLink() {
		
			action.click(getDriver(), linkPatientCase);
			return new PatientCasePage();
		
	}
	
	public void clickPatientsMenu() {
		
		action.click(getDriver(), menuPatient);
		
	}
	
	public String getCurrURL() throws Throwable {
		String pageURL=action.getCurrentURL(getDriver());
		return pageURL;
	}
	
	public PatientChartPage goToPatientChart(String MRN) {
		
		action.type(txtEmrNo, MRN);
		action.click(getDriver(), btnSearchPatient);
		Log.info("Patient Serached");
		action.waitPreloader();
		
		action.click(getDriver(), chkFirstSelectPatient);
		action.click(getDriver(), btnActionPatientChart);
		Log.info("Clicked on patient chart icon");
		action.waitPreloader();
		
		return (new PatientChartPage());
		
	}
	
	

}
