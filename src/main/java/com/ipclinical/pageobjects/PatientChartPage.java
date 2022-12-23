package com.ipclinical.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class PatientChartPage extends BaseClass {
	
	@FindBy(id = "MainContentArea_TM9920000")
	WebElement menuPatient;
	
	@FindBy(css = "a[href='/Patient/Patientcase.aspx']")
	WebElement linkPatientCase;
	
	public PatientChartPage ( ) {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	


}
