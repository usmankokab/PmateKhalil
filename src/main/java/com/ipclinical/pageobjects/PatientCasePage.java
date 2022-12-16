package com.ipclinical.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;

public class PatientCasePage extends BaseClass{
	
	@FindBy(xpath = "(//div[@class='TableDataCell'][normalize-space()='Patient Name'])[1]")
	public WebElement lblPatientName;
	
	@FindBy(xpath = "//div[@class='col-sm-4']//div[@class='TableDataCell'][normalize-space()='Patient Name']//following::input[1]")
	public WebElement txtPatientName;
	
	@FindBy(css = "input[id^='LocationsCheckbox']")
	public List<WebElement> chkboxesLocations;
	
	@FindBy(css = "input[id^='ProvidersCheckbox']")
	public List<WebElement> chkboxesProviders;
	
	@FindBy(xpath = "//*[@id=\"SearchForm\"]//select")
	public WebElement ddStatus;
	
	@FindBy(xpath = "//a[normalize-space()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath = "//button[normalize-space()='Add Patient Case']")
	public WebElement btnAddPatientCase;
	
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateFrom']")
	public WebElement txtFromDate;
	
	@FindBy(xpath = "//input[@ng-model='PatientCaseSearch.DateTo']")
	public WebElement txtToDate;
	
	@FindBy(xpath= "//div[@name='SearchResult']//div[@class='SetHeadingCell']")
	public List<WebElement> tableHeadings;//input[@id='chkoutboundonly']
	
	@FindBy(xpath= "//input[@id='chkoutboundonly']")
	public WebElement chkOutBoundOnly;
	
	@FindBy(xpath= "//input[@id='chkoplp']")
	public WebElement chkOutSideProvider;
	
	@FindBy(xpath= "//a[normalize-space()='Select Patient']")
	public WebElement btnSelectPatient;
	
	@FindBy(id= "PatientCaseModel")
	public WebElement PatientCaseModel;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[1]")
	public WebElement btnClose;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[2]")
	public WebElement btnPrint;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[3]")
	public WebElement btnSave;
	
	@FindBy(xpath= "//*[@id='PatientCaseModel']/div[1]/div/div[4]/input[4]")
	public WebElement btnSave$Schedule;
	
	@FindBy(id= "DropdownSource")
	public WebElement dropdownSource;
	
	@FindBy(id= "DropdownType")
	public WebElement dropdownType;
	
	@FindBy(id= "DropdownProvider")
	public WebElement dropdownProvider;
	
	@FindBy(id= "DropdownLocation")
	public WebElement dropdownLocation;

	@FindBy(id= "CallBackNumber")
	public WebElement ddCallBackNumber;
	
	@FindBy(xpath= "//*[@id=\"PatientCaseModel\"]//select[@ng-model='PatientCase.Priority']")
	public WebElement ddPriority;
	
	@FindBy(css = "input[ng-model='PatientCase.PersonToCall']")
	public WebElement txtPersonToCall;
	
	@FindBy(id = "txtSubject")
	public WebElement txtSubject;
	
	@FindBy(id = "otherfield")
	public WebElement txtOther;
	
	@FindBy(xpath = "//label/input[@type='radio']")
	public List<WebElement> radioButtons;
	
	@FindBy(xpath = "//textarea")
	public List<WebElement> textAreas;
	
	
	//Elements on Patient Search Model
	@FindBy(id="idOpenPatientSearch")
	public WebElement modelPatientSearch;
	
	@FindBy(xpath="(//button[@type='button' and text()='Search'])[1]")
	public WebElement btnSearchPatientModel;
	
	@FindBy(xpath = "(//button[normalize-space()='Close'])[1]")
	public WebElement btnClosePatientModel;
	
	@FindBy(xpath = "(//button[normalize-space()='Select'])[1]")
	public WebElement btnSelectPatientModel;
	
	
	@FindBy(xpath="(//input[@id='txtPatientPortalName'])[1]")
	public WebElement txtPatientPortalName;
	
	@FindBy(id="txtPatientPortalPhoneNumber")
	public WebElement txtPatientPortalPhoneNumber;
	
	@FindBy(id="txtPatientPortalSSN")
	public WebElement txtPatientPortalSSN;
	
	@FindBy(id="txtPatientPortalEMRNo")
	public WebElement txtPatientPortalEMRNo;
	
	@FindBy(id="dtPatientPortalDOB")
	public WebElement txtPatientPortalDOB;
	
	
	@FindBy(xpath="(//div[@class='TableDataControl']/input[@type='radio'])[1 <= position() and position() < 3]")
	public List <WebElement> radiosPatientPortalActiveInActive;
			
	@FindBy(id="dtPatientPortalDOB")
	public WebElement rdbPatientPortalActive;
	
	@FindBy(id="dtPatientPortalDOB")
	public WebElement rdbPatientPortalInActive;
	
	@FindBy(xpath="(//input[@id='chSelect'])[1]")
	public WebElement resultSearchPatient;
	
	@FindBy(xpath="(//div[@ng-show='Patient != null'])[1]")
	public WebElement selectedPatientShowed;
	
	@FindBy(xpath="(//img[@class='btn-22'])[position()>=1 and position() <= (last() - 1)][last()]")
	public WebElement lastPatientCaseInList;
	
	@FindBy(xpath="(//a[@class='Style5'])[1]")
	public WebElement btnLastPage;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PatientCasePage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	//Validating any label and its text box
	public void validateLabel(WebElement element, String fieldText) {
		String labelText = element.getText();
		Assert.assertTrue(action.isDisplayed(getDriver(), element));
		Log.info("Label" +element.getText()+  "is present");		
		
		Assert.assertEquals(labelText, fieldText);
		Log.info(labelText +  "is matched with "+fieldText+ "(Expected Text)");
						
	}
	
	public void validateTextBox(WebElement element, String sendText) {
		
		action.type(element, sendText);
		String attributeV= element.getAttribute("value");
		Log.info("values entered in " + element.getText()+ "/" +element.getAttribute("value"));
		Assert.assertTrue(action.isDisplayed(getDriver(), element));
		Log.info(element.getText()+ "/" +element.getAttribute("value") +  " TextBox is present");
		
		if(element.isEnabled()) {
			Assert.assertEquals(attributeV, sendText);
			Log.info(element.getText()+ "/" +element.getAttribute("value") +  " TextBox is editable");
			}
		if(!element.isEnabled())
		{
			Assert.assertTrue(true);
			Log.info(element.getText()+"/"+element.getAttribute("value")+ " TextBox is disabled to type");
			
		} 
		
	}
	
	
	public void validateTextAreas(List <WebElement> elements, String sentDescription) 
	{
		List <WebElement> txtAreas =  elements;
		System.out.print(txtAreas.size());
		for(int i=0; i<txtAreas.size(); i++)
		{
			Assert.assertTrue(action.isDisplayed(getDriver(), txtAreas.get(i)));
			action.type(txtAreas.get(i), sentDescription);
			Log.info(i + " textarea is present");
			Assert.assertEquals(txtAreas.get(i).getAttribute("value"), sentDescription);
			Log.info(i + " Textarea's description verified");
			
			
		}
		
	}
	
	
	public void validateCheckboxes(List<WebElement> elements) {
		
		List <WebElement> checkBoxes =  elements; 
		//System.out.println(checkBoxes.size());
		
		for(int i = 0; i<checkBoxes.size(); i++) {
			
			if(action.isSelected(getDriver(), checkBoxes.get(i))) {
				Assert.assertTrue(action.isSelected(getDriver(), checkBoxes.get(i)));
				((WebElement) checkBoxes.get(i)).click(); //to unselect and then select
				action.click(getDriver(), (WebElement) checkBoxes.get(i));
				Assert.assertTrue(true);
				Log.info(i + " checkBox is selected");
				//System.out.println(i + " checkBox is selected");
			//if not selected, then do select
			}else if(!action.isSelected(getDriver(), (WebElement) checkBoxes.get(i))) {
				Assert.assertTrue(!action.isSelected(getDriver(), (WebElement) checkBoxes.get(i)));
				action.click(getDriver(), (WebElement) checkBoxes.get(i));
				Assert.assertTrue(true);
				Log.info(i + " checkBox is selected");
			}
		 
		}
	}
	
	
	public boolean validateCheckbox(WebElement element) 
	{
		if(action.isSelected(getDriver(), element))
	    {
	    	action.click(getDriver(), element);
	    	action.click(getDriver(), element);
	    	Assert.assertTrue(true);
	    	return true;
	    }
   		action.click(getDriver(), element);
   		Assert.assertTrue(true);
   		return true;
	}
	
	public void validateRadioButtons(List <WebElement> elements) 
	{
		
		List<WebElement> radio = elements;
		for(int i=0; i<radio.size(); i++)
		{
			radio.get(i).click();
			Assert.assertTrue(true, radio.get(i).getAttribute("checked"));
			Log.info(radio.get(i).getAttribute("value")+ " option is checked"); 
			//Log.info("");
			
			for(int j=0; j<radio.size(); j++) {
				
				String checkStatus = radio.get(j).getAttribute("checked");
				//System.out.println(checkStatus);
				
				if(i==j) 
				{
					if(checkStatus!=null)
					{
						Assert.assertTrue(true);
						Log.info(radio.get(j).getAttribute("value")+ " only option is checked");
					} else {
						Assert.assertTrue(false);
						Log.info("Something went wrong in veryfing only one radoi selection");
					}
				}else if(i!=j)
				{
					if(checkStatus==null)
					{
						Assert.assertTrue(true);
						Log.info(radio.get(j).getAttribute("value")+ " other option is unchecked");
					} else {
						Assert.assertTrue(false);
						Log.info("Something went wrong in veryfing all options are unselected except of one radoi selection");
					}
					
				}
				
			}
			
		}
	}
	
	
	public void validateDropdown(String[] exp, WebElement element) 
	{
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		
			for(int i=0; i<options.size(); i++) 
			{
				Assert.assertEquals(options.get(i).getText(), exp[i]);
				Log.info(options.get(i).getText() + " = "+ exp[i] +"(Actual Matched with Expected");
				
				//selecting all values
				select.selectByIndex(i);
				Assert.assertTrue(true);
				Log.info("User can select "+options.get(i).getText()+ " value from the dropdown field");
				
			}
	}

	public void selectDropdown(WebElement element) 
	{
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
				
				select.selectByIndex(options.size()-1);
				Assert.assertTrue(true);
				Log.info("One of values from the dropdown field is selected");
			}

	public void validateButton(WebElement element) 
	{
			if(element.isDisplayed())
			{
				Assert.assertTrue(true);
				Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is displayed");
				if(element.isEnabled())
				{
					Assert.assertTrue(true);
					Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is enabled to click");
				}else {
					Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button should not be disabled to click, but disabled");
					Assert.assertTrue(false);
				}
			}else {
				Assert.assertTrue(false);
				Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is not displayed" );
			}
	 }
	
	public void validateButtonDisabled(WebElement element) 
	{
		
			if(element.isDisplayed())
			{
				Assert.assertTrue(true);
				Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is displayed" );
				if(!element.isEnabled())
				{
					Assert.assertTrue(true);
					Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is disabled to click");
				}else {
					Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button should not be enabled to click, but enabled");
					Assert.assertTrue(false);
					
				}
			}else {
				Log.info(element.getText()+"/"+element.getAttribute("value")+ " Button is not displayed, but should be" );
				Assert.assertTrue(false);
				
			}
	}
	
	
	
	public void validateButtons(List <WebElement> elements) 
	{
		
		for(int i=0; i<elements.size(); i++)
		{
			if(elements.get(i).isDisplayed())
			{
				Assert.assertTrue(true);
				Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is displayed" );
				if(elements.get(i).isEnabled())
				{
					Assert.assertTrue(true);
					Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is enabled to click");
				}else {
					Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button should not be disabled to click, but disabled");
					Assert.assertTrue(false);
				}
			}else {
				Assert.assertTrue(false);
				Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is not displayed" );
			}
		}
	}
	
	public void validateButtonsDisabled(List <WebElement> elements) 
	{
		
		for(int i=0; i<elements.size(); i++) 
		{
			
			if(elements.get(i).isDisplayed())
			{
				Assert.assertTrue(true);
				Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is displayed" );
				if(!elements.get(i).isEnabled())
				{
					Assert.assertTrue(true);
					Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is disabled to click");
				}else {
					Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button should not be enabled to click, but enabled");
					Assert.assertTrue(false);
					
				}
			}else {
				Log.info(elements.get(i).getText()+"/"+elements.get(i).getAttribute("value")+ " Button is not displayed, but should be" );
				Assert.assertTrue(false);
				
			}
		}
		
	}
	 
		
	public void validateTxtDate(WebElement element) {
		
		if(txtFromDate.isDisplayed())
		{
			Assert.assertTrue(true);
			Log.info("Date Textbox is displayed with current date of " +element.getAttribute("value"));
			
			// Create object of SimpleDateFormat class and decide the format
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			//get current date time with Date()
			 Date date = new Date();
			// Now format the date
			 String date1= dateFormat.format(date);
			// Print the Date
			 Log.info(date1);
			
			 Assert.assertEquals(element.getAttribute("value"), date1);
			 Log.info("Textbox date is compared with current date sucessfully");
			
		}
		
	}
	
public void validateTableHeadings(String[] exp, List<WebElement> elements) {
		
		List <WebElement> headings =   elements; 
		//System.out.println(headings.size());
		
		for(int i = 0; i<headings.size(); i++) {
			Assert.assertEquals((headings.get(i)).getText(), exp[i]);
			Log.info((headings.get(i)).getText()+ " = " + exp[i]);
		}
	}

	public boolean waitForModelDisplayed(WebElement element, WebElement waitElement, WebElement verifyElement) {
		action.waitPreloader();
		action.explicitWait(getDriver(), waitElement, 50);
		if(verifyElement.isDisplayed()) 
	    {
	    	Log.info(element.getText()+ "Model/Popup appears");
	    	Assert.assertTrue(true);
	    	return true;
	    }else
	    {
	    	Log.error(element.getText()+ "Model does not appear");
	    	return false;
	    }
	}
	
	public void searchAndSelectPatient()
	{
		
		
		Log.info("Typing the MR No. to search patient");
		action.type(txtPatientPortalEMRNo, "13");
		
		Log.info("Clicking search button to search patient");
		action.click(getDriver(), btnSearchPatientModel);
		
		Log.info("Selecting required patient from the resulted table");
		action.click(getDriver(), resultSearchPatient);
		
		Log.info("Clicking Select button to Select patient");
		action.click(getDriver(), btnSelectPatientModel);
		waitForModelDisplayed(btnAddPatientCase, PatientCaseModel, chkOutBoundOnly);
		if(selectedPatientShowed.isDisplayed()) {
			
			Assert.assertTrue(true);
			Log.info("Required patient is selected sucessfully");
		}else {
			Log.info("Required patient is not selected");
			Assert.assertTrue(false);
		}
		
	}
	
	public String getCurrURL() throws Throwable {
		String pageURL=action.getCurrentURL(getDriver());
		return pageURL;
	}
	


}
