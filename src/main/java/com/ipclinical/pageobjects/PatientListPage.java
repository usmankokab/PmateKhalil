package com.ipclinical.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class PatientListPage extends BaseClass{
	
	@FindBy(xpath = "input[ng-model='EmrNo']")
	WebElement menuPatient;
	
	@FindBy(css = "a[href='/Patient/Patientcase.aspx']")
	WebElement linkPatientCase;
	
	
	
	
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
	
	public void goToPatientChart() {
		
		
	}

}
