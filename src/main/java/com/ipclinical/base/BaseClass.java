package com.ipclinical.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.ietf.jgss.Oid;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.ipclinical.actiondriver.Action;
import com.ipclinical.utility.ExtentManager;
import com.ipclinical.utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * @author Hitendra: BaseClass is used to load the config file and Initialize 
 * WebDriver
 *  
 */
public class BaseClass {
	public static Properties prop;
	
	// Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	
	//loadConfig method is to load the configuration
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		
		return driver.get();
		
	}

	public void launchApp(String browserName) {
		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			//mention the below chrome option to solve timeout exception issue
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}
		//Maximize the screen
		getDriver().manage().window().maximize();
		//Delete all the cookies
		getDriver().manage().deleteAllCookies();
		//Implicit TimeOuts
		//getDriver().manage().timeouts().implicitlyWait
		//(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		//PageLoad TimeOuts
		//getDriver().manage().timeouts().pageLoadTimeout
		//(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
		//Launching the URL
		getDriver().get(prop.getProperty("url"));
	}

	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
	
}
