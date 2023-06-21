package com.ipclinical.testcases;



import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.dataprovider.DataProviders;
import com.ipclinical.pageobjects.EditPatient;
import com.ipclinical.pageobjects.EditVisit;
import com.ipclinical.pageobjects.HomePage;
import com.ipclinical.pageobjects.LoginPage;
import com.ipclinical.utility.Log;
import com.ipclinical.utility.NewExcelLibrary;

public class PracticeMate extends BaseClass{
	NewExcelLibrary exlib = new NewExcelLibrary ();
	Action action = new Action();
	
	int i = 2;
	String month = "04";
	String day = "27";
	String year = "2023";

	@Parameters("browser")
	//@BeforeMethod
	@BeforeClass
	public void setup(String browser) {
		launchApp(browser);
		
		Log.startTestCase("loginTest");		
		LoginPage loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	
	
	@Test(dataProvider = "dataset", dataProviderClass = DataProviders.class)
	public void loginTest1(String PatientLastName, String PatientFirstName, String DOB, String Status, String Insurance) throws ParseException {
		//getDriver().navigate().to("https://pm.officeally.com/pm/ManagePatients/ManagePatients.aspx?Tab=P");
		
		
		HomePage homePage = new HomePage();
		
		homePage.searchPatient(PatientLastName.trim() , PatientFirstName.trim());
		
		Log.info("Dob from Excel " + DOB);
		
		// here is a problem when patient is not found it may return empty 
		List <WebElement> rows = getDriver().findElements(By.cssSelector(".ui-row-ltr .cbox"));
		
		if(rows.size() == 0) {
			
			Log.info(PatientLastName + "," + PatientFirstName + " Patient not searched");
			exlib.setCellData("Sheet1", "Status", i, "Patient not searched");
			i++;
			
		}
		
		WebElement insCell = getDriver().findElement(By.xpath("(//td[(((count(preceding-sibling::*) + 1) = 12) and parent::*)])[2]"));
    	String insurance = insCell.getText();
		
		if(rows.size() == 1) {
			if(!insurance.trim().equalsIgnoreCase("Medicare Florida")) {
				
				getDriver().findElement(By.cssSelector(".ui-icon.ui-icon-pencil")).click();
				getDriver().findElement(By.xpath("//a[normalize-space()='Insurance']")).click();
				getDriver().findElement(By.name("ctl00$phFolderContent$ucPatient$btnPopup")).click();
				
				String parentWindowHandler = getDriver().getWindowHandle(); // Store your parent window
				String subWindowHandler = null;

				Set<String> handles = getDriver().getWindowHandles(); // get all window handles
				Iterator<String> iterator = handles.iterator();
				while (iterator.hasNext()){
				    subWindowHandler = iterator.next();
				}
				getDriver().switchTo().window(subWindowHandler); // switch to popup window

				// Now you are in the popup window, perform necessary actions here
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50000));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ctl04$popupBase$txtSearch")));
				getDriver().findElement(By.name("ctl04$popupBase$txtSearch")).sendKeys("Medicare Florida");
				getDriver().findElement(By.id("ctl04_popupBase_btnSearch")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl04_popupBase_grvPopup_ctl02_lnkSelect")));
				getDriver().findElement(By.id("ctl04_popupBase_grvPopup_ctl02_lnkSelect")).click();
				
			
				getDriver().switchTo().window(parentWindowHandler);  // switch back to parent window
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phFolderContent_ucPatient_btnApply")));
				getDriver().findElement(By.id("ctl00_phFolderContent_ucPatient_btnApply")).click();
				
				Log.info(PatientLastName + "," + PatientFirstName+ " Insurance Added");
				exlib.setCellData("Sheet1", "Insurance", i, "Verify Insurancde");
				
				
				
				EditPatient editpatient = new EditPatient();
				Log.info(PatientLastName + ", " + PatientFirstName + " Searched Successfully and Edit button clicked");
				
				editpatient.templateClick();
				
				EditVisit editvisit = new EditVisit();
				editvisit.visitDate(month, day, year);
				editvisit.popSuperBill();
				
				Log.info(PatientLastName + "," + PatientFirstName + " Submitted Sucessfully");
				exlib.setCellData("Sheet1", "Status", i, "submitted after adding Insurance");
				i++;
				
			} else {
				
				getDriver().findElement(By.cssSelector(".ui-icon.ui-icon-pencil")).click();
				
				EditPatient editpatient = new EditPatient();
				Log.info(PatientLastName + ", " + PatientFirstName + " Searched Successfully and Edit button clicked");
				
				editpatient.templateClick();
				
				EditVisit editvisit = new EditVisit();
				editvisit.visitDate(month, day, year);
				editvisit.popSuperBill();
				
				Log.info(PatientLastName + "," + PatientFirstName + " Submitted Sucessfully");
				exlib.setCellData("Sheet1", "Status", i, "submitted");
				i++; 
			}
		
		
		} else if(rows.size() > 1) {
			
			//table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) td:nth-child(12)
			
			//td:nth-child(9)
			
			//td[(((count(preceding-sibling::*) + 1) = 9) and parent::*)]
			//td[(((count(preceding-sibling::*) + 1) = 7) and parent::*)]
			
						
			List <WebElement> allDOBs = getDriver().findElements(By.xpath("//td[(((count(preceding-sibling::*) + 1) = 9) and parent::*)]"));
			
			//List <WebElement> insCols = getDriver().findElements(By.cssSelector("td:nth-child(12)"));
			
			
	        for (WebElement webElement : allDOBs) {
	            String dob = webElement.getText();
	            if(allDOBs.indexOf(webElement) == 0) {
	            	continue;
	            }
	            
	            Log.info("Dob from excel " + DOB);
	            
	            int indexplus = allDOBs.indexOf(webElement)+1;
            	insCell = getDriver().findElement(By.xpath("(//td[(((count(preceding-sibling::*) + 1) = 12) and parent::*)])["+indexplus+"]"));
            	insurance = insCell.getText();
            	Log.info("insurance is "+insurance);
	            
	            if(dob.compareTo(DOB) == 0 && insurance.trim().equalsIgnoreCase("Medicare Florida")) {
	            	
	            	Log.info("DOB and Insurance are matched on Index " + allDOBs.indexOf(webElement));
	            	
	            	WebElement firstLink = getDriver().findElement(By.xpath("(//td[(((count(preceding-sibling::*) + 1) = 7) and parent::*)]/a)["+allDOBs.indexOf(webElement)+"]"));
		            firstLink.click();
		            Log.info("FirstLink clicked");
		            
		            EditPatient editpatient = new EditPatient();
		    		Log.info(PatientLastName + ", " + PatientFirstName + " Searched Successfully and Edit button clicked");
		    		
		    		editpatient.templateClick();
		    		
		    		EditVisit editvisit = new EditVisit();
		    		editvisit.visitDate(month, day, year);
		    		editvisit.popSuperBill();
		    		
		    		Log.info(PatientLastName + "," + PatientFirstName + " Submitted Sucessfully, multiple patients");
		    		//exlib.setCellData("Sheet1", "Status", i, "submitted, multiple patients");
		    		break;	
	            	
	            } else {
	            	
	            	Log.info("DOB is not matched at index " + allDOBs.indexOf(webElement) + " or Insurance is not Medicare Florida");
	            	continue;
	            }
	            
	        }
			
	        exlib.setCellData("Sheet1", "Status", i, "Somtthing Wrong/Visit Added");
	        i++;
			//exlib.setCellData("Sheet1", "Status", i, "Not Found/Multiple Patients, verify if added");
			//i++; 
			//Log.info(PatientLastName + ", " + PatientFirstName + " Multiple Patients");
			
		}
		
		
	}
	
	
	
	
//	@DataProvider(name = "data-set")
//    public static Object[][] DataSet(){
//        //read the jason or excel data
//        Object[][] obj = {
//                {"Ramsey", "WILLIAM"},
//                {"Peacock", "WILLIAM"},
//                {"Passmore", "WILLIAM"},
//                {"Partin", "WILLIAM"},
//                {"Mixon", "WILLIAM"},
//                {"McDonald", "WILLIAM"}
//                };
//        return obj;
//    }
}
