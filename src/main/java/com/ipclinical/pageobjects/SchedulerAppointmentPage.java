package com.ipclinical.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ipclinical.base.BaseClass;

public class SchedulerAppointmentPage extends BaseClass{

	public SchedulerAppointmentPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath= "(//img[@src='/App_Themes/AvicenTheme-test/img/Icons/logo.png'])[2]")
	public WebElement logoElement;
	
	@FindBy(css= "div.pull-left.pd-5 > a:nth-child(2)")
	public WebElement btnTodayShedule;
	
	
}
