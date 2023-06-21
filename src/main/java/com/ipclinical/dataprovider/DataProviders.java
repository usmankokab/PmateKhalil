/**
 * 
 */
package com.ipclinical.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.ipclinical.utility.NewExcelLibrary;

/**
 * @author Hitendra
 *
 */
public class DataProviders {

	NewExcelLibrary obj = new NewExcelLibrary();

//Class --> LoginPageTest,HomePageTest Test Case--> loginTest, wishListTest, orderHistoryandDetailsTest

	@DataProvider(name = "dataset")
	public Object[][] getPatient() {
		// Totals rows count
		int rows = obj.getRowCount("Sheet1");
		// Total Columns
		int column = obj.getColumnCount("Sheet1");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Sheet1", j, i + 2);
			}
		}
		return data;
	}
	
	@DataProvider(name = "dataset2")
	public Object[][] getPatient2() {
		// Totals rows count
		int rows = obj.getRowCount("Sheet2");
		// Total Columns
		int column = obj.getColumnCount("Sheet2");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Sheet2", j, i + 2);
			}
		}
		return data;
	}
	
	
	@DataProvider(name = "dataset3")
	public Object[][] getPatient3() {
		// Totals rows count
		int rows = obj.getRowCount("Sheet3");
		// Total Columns
		int column = obj.getColumnCount("Sheet3");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Sheet3", j, i + 2);
			}
		}
		return data;
	}


	@DataProvider(name = "dataset4")
	public Object[][] getPatient4() {
		// Totals rows count
		int rows = obj.getRowCount("Sheet4");
		// Total Columns
		int column = obj.getColumnCount("Sheet4");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Sheet4", j, i + 2);
			}
		}
		return data;
	}
	
	@DataProvider(name = "dataset5")
	public Object[][] getPatient5() {
		// Totals rows count
		int rows = obj.getRowCount("Sheet5");
		// Total Columns
		int column = obj.getColumnCount("Sheet5");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Sheet5", j, i + 2);
			}
		}
		return data;
	}

	
	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		// Totals rows count
		int rows = obj.getRowCount("Credentials");
		// Total Columns
		int column = obj.getColumnCount("Credentials");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Credentials", j, i + 2);
			}
		}
		return data;
	}

//Class --> AccountCreationPage  Test Case--> verifyCreateAccountPageTest	
	@DataProvider(name = "email")
	public Object[][] getEmail() {
		// Totals rows count
		int rows = obj.getRowCount("Email");
		// Total Columns
		int column = obj.getColumnCount("Email");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Email", j, i + 2);
			}
		}
		return data;
	}

//Class --> AddToCartPageTest, EndToEndTest,  Test Case--> addToCartTest, endToEndTest	
	@DataProvider(name = "getProduct")
	public Object[][] getProduct() {
		// Totals rows count
		int rows = obj.getRowCount("ProductDetails");
		// Total Columns
		int column = obj.getColumnCount("ProductDetails");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("ProductDetails", j, i + 2);
			}
		}
		return data;
	}

	// Class --> SearchResultPageTest, Test Case--> productAvailabilityTest
	@DataProvider(name = "searchProduct")
	public Object[][] getProductPrice() {
		// Totals rows count
		int rows = obj.getRowCount("SearchProduct");
		// Total Columns
		int column = obj.getColumnCount("SearchProduct");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("SearchProduct", j, i + 2);
			}
		}
		return data;
	}
	
	@DataProvider(name = "newAcountDetailsData")
	public Object[][] accountCreation() {

		// Totals rows count
		int rows = obj.getRowCount("AccountCreationData");
		// Total Columns
		int column = obj.getColumnCount("AccountCreationData");
		int actRows = rows - 1;
		//Created an object of array to store data
		Object[][] data = new Object[actRows][1];
		
		for (int i = 0; i < actRows; i++) {
			Map<String, String> hashMap = new HashMap<>();
			for (int j = 0; j < column; j++) {
				hashMap.put(obj.getCellData("AccountCreationData", j, 1),
						obj.getCellData("AccountCreationData", j, i + 2));
			}
			data[i][0]=hashMap;
		}
		return data;
	}

}
