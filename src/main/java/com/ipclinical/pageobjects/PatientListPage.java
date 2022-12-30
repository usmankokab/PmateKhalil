package com.ipclinical.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;

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
	
	@FindBy(xpath = "(//*[@id=\"BasicSearch\"]/.//ul/li[2]/a)[1]")
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
		
		action.waitPreloader(30);
		action.click(getDriver(), menuPatient);
		
	}
	
	public String getCurrURL() throws Throwable {
		String pageURL=action.getCurrentURL(getDriver());
		return pageURL;
	}
	
	public PatientChartPage goToPatientChart(String MRN) {
		
		action.type(txtEmrNo, MRN);
		action.JSClick(getDriver(), btnSearchPatient);
		Log.info("Patient Serached");
		action.waitPreloader(30);
		
		//action.click(getDriver(), chkFirstSelectPatient);
		action.explicitWait(getDriver(), btnActionPatientChart, 30);
		action.JSClick(getDriver(), btnActionPatientChart);
		Log.info("Clicked on patient chart icon");
			
		return (new PatientChartPage());
		
	}
	
	

}
