package com.ipclinical.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.actiondriver.Action;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;

public class PatientChartPage extends BaseClass {
	
	@FindBy(id = "tabPatientCase")
	public WebElement tabPatientCase;
	
	@FindBy(id = "ReferralSearch")
	public WebElement referralSearchModel;
	
	@FindBy(id = "myModalLabel")
	public WebElement referralModalLabel;
	
	
	
	//@ByAngularModel.FindBy(model = "")
	@FindBy(xpath = "//*[@id=\"PatientCase\"]/div/div/div/div/div[2]/a")
	WebElement btnAdd;
	
	@FindBy(xpath = "//a[@ng-click='RefreshPage()']")
	public WebElement btnRefresh;
	
	@FindBy(id= "PatientCaseModel")
	public WebElement PatientCaseModel;
	
	@FindBy(xpath= "//input[@id='chkoutboundonly']")
	public WebElement chkOutBoundOnly;
	

		
	@FindBy(xpath= "//div[@id='ReferralSearch']/.//input[@type='button' and @value='Close']")
	public WebElement btnCloseReferralModel;
	
	@FindBy(xpath= "//div[@id='PatientCaseModel']/.//input[@type='button' and @value='Save & Add Orders']")
	public WebElement btnSave$AddOrders;
	
	@FindBy(xpath = "//div[@id='PatientCaseModel']/.//label/input[@type='radio']")
	public List<WebElement> radioButtons;
	
	@FindBy(xpath = "(//div[@id='PatientCaseModel']/.//textarea)[1]")
	public WebElement textAreaDescription;
	
	@FindBy(xpath = "(//div[@id='PatientCaseModel']/.//textarea)[2]")
	public WebElement textAreaActionNote;
	
	@FindBy(xpath="(//img[@class='btn-22'])[position()>=1 and position() <= (last())][3]")
	public WebElement firstPatientCaseInListChart;
		
		
	
	public PatientChartPage ( ) {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	Action action = new Action();
	
	
	
	public boolean openPatientCaseModel_fromChart() {
		
		action.click(getDriver(), tabPatientCase);
		action.waitPreloader(30);
		action.click(getDriver(), btnAdd);
		action.waitForModelDisplayed(PatientCaseModel, chkOutBoundOnly);
		
		return true;
	}

	
	public void clickOnFirstResultOfPtCaseList() {
		
		Log.info("Going to click on first result edit button of the first page");
		action.JSClick(getDriver(), firstPatientCaseInListChart);
		Log.info("clicked on first result edit button");
		
	}


}
