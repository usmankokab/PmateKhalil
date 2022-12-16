package com.ipclinical.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ipclinical.base.BaseClass;
import com.ipclinical.pageobjects.DashboardPage;
import com.ipclinical.pageobjects.LoginPage;
import com.ipclinical.pageobjects.PatientListPage;
import com.ipclinical.utility.Log;

public class LoginPageTest extends BaseClass{
	DashboardPage dashboardPage;
	PatientListPage patientListPage;
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod
	public void tearDown() {
		//getDriver().quit();
	}
	
	@Test
	public void loginTest() {
		Log.startTestCase("loginTest");		
		LoginPage loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("code"), dashboardPage);
				
		Log.info("Verifying if user is able to login");
		WebElement element = getDriver().findElement(By.id("MainContentArea_hSignOut"));
		
		if(element != null) {
		
			Log.info("User is successfully Logged in");
			Log.info("LoginTest passed");
			
		}else {
			
			Assert.assertTrue(false);
			Log.info("LoginTest failed");
			
		}
		
				
				
		
	}
	


}
