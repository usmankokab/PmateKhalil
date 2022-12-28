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

}