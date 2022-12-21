package com.ipclinical.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.base.BaseClass;

public class ClinicalInboxPage extends BaseClass{

	public ClinicalInboxPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath="(//td[@ng-click = 'ReadPatientRefundList(item.PatientRefund,222)'])[5]/a")
	public WebElement refundAgainstSaim;
	
	@FindBy(xpath="//th[@class='SetHeadingCell'][normalize-space()='Patient Refund']")
	public WebElement refundHeading;
	
	
	
	
	
	
	
}
