package com.ipclinical.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class PatientListPage extends BaseClass{
	
	@FindBy(id = "MainContentArea_TM9920000")
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

}
