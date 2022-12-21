/**
 * 
 */
package com.ipclinical.actiondriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ipclinical.actioninterface.ActionInterface;
import com.ipclinical.base.BaseClass;
import com.ipclinical.utility.Log;


/**
 * @author Hitendra Verma added on 13th March2019
 *
 */
public class Action extends BaseClass implements ActionInterface {

	@Override
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	@Override
	public void click(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();

	}

	@Override
	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}

	@Override
	public boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	@Override
	public boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}

	@Override
	public boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}

	/**
	 * Type text at location
	 * 
	 * @param locatorName
	 * @param text
	 * @return - true/false
	 */
	@Override
	public boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}

	@Override
	public boolean selectBySendkeys(String value,WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");		
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param index       : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 * 
	 */
	@Override
	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param value       : Value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	@Override
	public boolean selectByValue(WebElement element,String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param visibletext : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	@Override
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	@Override
	public boolean mouseHoverByJavaScript(WebElement ele) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	@Override
	public boolean JSClick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}

	@Override
	public boolean switchToFrameByIndex(WebDriver driver,int index) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameById(WebDriver driver,String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameByName(WebDriver driver,String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	@Override
	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}

	@Override
	public void mouseOverElement(WebDriver driver,WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	@Override
	public boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean mouseover(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}
	@Override
	public boolean draggable(WebDriver driver,WebElement source, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {
		
			return false;
			
		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source+"\"");
			}
		}
	}
	@Override
	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}
	
	@Override
	public boolean slider(WebDriver driver,WebElement ele, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}
	
	@Override
	public boolean rightclick(WebDriver driver,WebElement ele) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}
	
	@Override
	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}
	@Override
	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");				
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	
	@Override
	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	
	
	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	@Override
	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");		
			} else{
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}
	@Override
	public boolean launchUrl(WebDriver driver,String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \""+url+"\"");				
			} else {
				System.out.println("Failed to launch \""+url+"\"");
			}
		}
	}
	
	@Override
	public boolean isAlertPresent(WebDriver driver) 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}
	
	@Override
	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \""+text+"\"");
		}
		return text;
	}
	
	@Override
	public String getCurrentURL(WebDriver driver)  {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \""+text+"\"");
		}
		return text;
	}
	
	@Override
	public boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on \""+locatorName+"\"");
			} else {
				System.out.println("Click Unable to click on \""+locatorName+"\"");
			}
		}

	}
	
	@Override
	public void fluentWait(WebDriver driver,WebElement element, int timeOut) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
	    }catch(Exception e) {
	    }
	}
	@Override
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Override
	public void explicitWait(WebDriver driver, WebElement element, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	@Override
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}
	
	//Custom for PEMR
	public void waitPreloader() {
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Preloader")));
	}
	
	
	@Override
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
	}
	@Override
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}
	
	//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	//LocalDateTime now = LocalDateTime.now();  
	//String currentDateTime = dtf.format(now);
	
	
	public String getCurrURL() throws Throwable {
		String pageURL= getCurrentURL(getDriver());
		return pageURL;
	}
	
	
	//================================CUSTOM FUNCTIONS FOR IPICLINICAL BY USMAN KOKAB===================================
	
	
		//Validating any label
		public void validateLabel(WebElement element, String fieldText) {
		String labelText = element.getText();
		Assert.assertTrue(isDisplayed(getDriver(), element));
		Log.info("Label" +element.getText()+  "is present");		
				
		Assert.assertEquals(labelText, fieldText);
		Log.info(labelText +  "is matched with "+fieldText+ "(Expected Text)");
								
		}
		
		
		//Validating any textBox
		public void validateTextBox(WebElement element, String sendText) {
				
		type(element, sendText);
		String attributeV= element.getAttribute("value");
		Log.info("values entered in " + element.getText()+ "/" +element.getAttribute("value"));
		Assert.assertTrue(isDisplayed(getDriver(), element));
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

		//Validating TextAreas
		public void validateTextAreas(List <WebElement> elements, String sentDescription) 
		{
			List <WebElement> txtAreas =  elements;
			System.out.print(txtAreas.size());
			for(int i=0; i<txtAreas.size(); i++)
			{
				Assert.assertTrue(isDisplayed(getDriver(), txtAreas.get(i)));
				type(txtAreas.get(i), sentDescription);
				Log.info(i + " textarea is present");
				Assert.assertEquals(txtAreas.get(i).getAttribute("value"), sentDescription);
				Log.info(i + " Textarea's description verified");
				
				
			}
			
		}
		
		
		//Validating all checkboxes
		public void validateCheckboxes(List<WebElement> elements) {
			
			List <WebElement> checkBoxes =  elements; 
			//System.out.println(checkBoxes.size());
			
			for(int i = 0; i<checkBoxes.size(); i++) {
				
				if(isSelected(getDriver(), checkBoxes.get(i))) {
					Assert.assertTrue(isSelected(getDriver(), checkBoxes.get(i)));
					((WebElement) checkBoxes.get(i)).click(); //to unselect and then select
					click(getDriver(), (WebElement) checkBoxes.get(i));
					Assert.assertTrue(true);
					Log.info(i + " checkBox is selected");
					//System.out.println(i + " checkBox is selected");
				//if not selected, then do select
				}else if(!isSelected(getDriver(), (WebElement) checkBoxes.get(i))) {
					Assert.assertTrue(!isSelected(getDriver(), (WebElement) checkBoxes.get(i)));
					click(getDriver(), (WebElement) checkBoxes.get(i));
					Assert.assertTrue(true);
					Log.info(i + " checkBox is selected");
				}
			 
			}
		}
		
		
		public boolean validateCheckbox(WebElement element) 
		{
			if(isSelected(getDriver(), element))
		    {
		    	click(getDriver(), element);
		    	click(getDriver(), element);
		    	Assert.assertTrue(true);
		    	return true;
		    }
	   		click(getDriver(), element);
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
					Log.info(element.getText()+"/"+element.getAttribute("value")+ ": One of values from the dropdown field is selected");
				}

		public void validateButton(WebElement element) 
		{
				if(isDisplayed(getDriver(), element))
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
			
				if(isDisplayed(getDriver(), element))
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
				if(isDisplayed(getDriver(), elements.get(i)))
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
				
				if(isDisplayed(getDriver(), elements.get(i)))
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
		 
			
		public void validateTxtDateCurrent(WebElement element) {
			
			if(isDisplayed(getDriver(), element))
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

		public boolean waitForModelDisplayed(WebElement waitElement, WebElement verifyElement) {
			waitPreloader();
			explicitWait(getDriver(), waitElement, 50);
			if(isDisplayed(getDriver(), verifyElement)) 
		    {
		    	Log.info(waitElement.getText()+ "Model/Popup appears");
		    	Assert.assertTrue(true);
		    	return true;
		    }else
		    {
		    	Log.error(waitElement.getText()+ "Model does not appear");
		    	return false;
		    }
		}

	
	
	

}
