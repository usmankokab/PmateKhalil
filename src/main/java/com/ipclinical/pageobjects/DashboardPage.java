package com.ipclinical.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;

public class DashboardPage extends BaseClass{

	//Page Elements
	
	@FindBy(css = "a[href='./Patient/PatientList.aspx']")
	WebElement linkPatients;
	
	public DashboardPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	//Action Methods
	public PatientListPage clickPatientWidget() {
		
		action.click(getDriver(), linkPatients);
		return new PatientListPage();
	}
	
	
	
}
