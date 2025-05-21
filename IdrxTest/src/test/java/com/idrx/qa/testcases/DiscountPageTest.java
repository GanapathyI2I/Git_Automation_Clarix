package com.idrx.qa.testcases;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.SalesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import com.idrx.qa.pages.DiscountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DiscountPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	SalesPage salesPage;
	TestUtil testUtil;
	DiscountPage discountPage;

	public DiscountPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		salesPage = new SalesPage();
		testUtil = new TestUtil();
		discountPage = new DiscountPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(10000);
		TestUtil.switchToFrame();		
		homePage.clickSalesTab();
		salesPage.clickDiscountTab();
	}	
	
	@Test(priority = 1, enabled=false)
	public void noOfDiscountUnitsTest() throws Exception {
		
		String noOfDiscountUnits = DBUtil.noOfDiscountUnitsGetDBValue();
		System.out.println("--------No. of Discount Units--------");
		int noOfDiscountUnitsExpectedValue = Integer.parseInt(noOfDiscountUnits);
		System.out.println("Expected Value: " + noOfDiscountUnitsExpectedValue);
		String noOfDiscountUnitsVal = discountPage.getNoOfDiscountUnits();
		int noOfDiscountUnitsActualValue = Integer.parseInt(noOfDiscountUnitsVal);
		System.out.println("Actual Value: " + noOfDiscountUnitsActualValue);
		testUtil.assertEquals(noOfDiscountUnitsActualValue, noOfDiscountUnitsExpectedValue, "No. of Discount Units");
	}

	@Test(priority = 2, enabled=false)
	public void totalDiscountValueTest() throws Exception {
		String totalDiscountValue = DBUtil.totalDiscountValueGetDBValue();
		System.out.println("--------Total Discount Value--------");
		String totalDiscountValueExpectedValue = "₹ " + totalDiscountValue;
		System.out.println("Expected Value: " + totalDiscountValueExpectedValue);
		String totalDiscountValueActualValue = discountPage.getTotalDiscountValue();	
		System.out.println("Actual Value: " + totalDiscountValueActualValue);
		testUtil.assertEquals(totalDiscountValueActualValue, totalDiscountValueExpectedValue, "Total Discount Value");
	}

	@Test(priority = 3, enabled=false)
	public void totalDiscountQtyTest() throws Exception {
		
		String totalDiscountQty = DBUtil.totalDiscountQtyGetDBValue();
		System.out.println("--------Total Discount Qty--------");
		int totalDiscountQtyExpectedValue = Integer.parseInt(totalDiscountQty);
		System.out.println("Expected Value: " + totalDiscountQtyExpectedValue);
		String totalDiscountQtyVal = discountPage.getTotalDiscountQty();
		int totalDiscountQtyActualValue = Integer.parseInt(totalDiscountQtyVal);
		System.out.println("Actual Value: " + totalDiscountQtyActualValue);
		testUtil.assertEquals(totalDiscountQtyActualValue, totalDiscountQtyExpectedValue, "Total Discount Qty");
	}

	@Test(priority = 4, enabled=false)
	public void totalDiscountAmountTest() throws Exception {
		String totalDiscountAmount = DBUtil.totalDiscountAmountGetDBValue();
		System.out.println("--------Total Discount Amount--------");
		String totalDiscountAmountExpectedValue = "₹ " + totalDiscountAmount;
		System.out.println("Expected Value: " + totalDiscountAmountExpectedValue);
		String totalDiscountAmountActualValue = discountPage.getTotalDiscountAmount();
		System.out.println("Actual Value: " + totalDiscountAmountActualValue);
		testUtil.assertEquals(totalDiscountAmountActualValue, totalDiscountAmountExpectedValue, "Total Discount Amount");
	}

	@Test(priority = 7, enabled=false)
	public void currentMonthUnitsTest() throws Exception {
		
		String currentMonthUnits = DBUtil.currentMonthUnitsGetDBValue();
		System.out.println("--------Current Month Units--------");
		int currentMonthUnitsExpectedValue = Integer.parseInt(currentMonthUnits);
		System.out.println("Expected Value: " + currentMonthUnitsExpectedValue);
		discountPage.clickUnitsButton();
		Thread.sleep(3000);
		String currentMonthUnitsVal = discountPage.getCurrentMonthDiscount();
		int currentMonthUnitsActualValue = Integer.parseInt(currentMonthUnitsVal);
		System.out.println("Actual Value: " + currentMonthUnitsActualValue);
		testUtil.assertEquals(currentMonthUnitsActualValue, currentMonthUnitsExpectedValue, "Current Month Units");
	}

	@Test(priority = 8, enabled=false)
	public void previousMonthUnitsTest() throws Exception {
		String previousMonthUnits = DBUtil.previousMonthUnitsGetDBValue();
		System.out.println("--------Previous Month Units--------");
		int previousMonthUnitsExpectedValue = Integer.parseInt(previousMonthUnits);
		System.out.println("Expected Value: " + previousMonthUnitsExpectedValue);
		String previousMonthUnitsVal = discountPage.getPreviousMonthDiscount();
		int previousMonthUnitsActualValue = Integer.parseInt(previousMonthUnitsVal);
		System.out.println("Actual Value: " + previousMonthUnitsActualValue);
		testUtil.assertEquals(previousMonthUnitsActualValue, previousMonthUnitsExpectedValue, "Previous Month Units");
	}

	@Test(priority = 5, enabled=false)
	public void currentMonthValueTest() throws Exception {
		String currentMonthValue = DBUtil.currentMonthValueGetDBValue();
		System.out.println("--------Current Month Value--------");
		String currentMonthValueExpectedValue = "₹ " + currentMonthValue;
		System.out.println("Expected Value: " + currentMonthValueExpectedValue);
		String currentMonthValueActualValue = discountPage.getCurrentMonthDiscount();
		System.out.println("Actual Value: " + currentMonthValueActualValue);
		testUtil.assertEquals(currentMonthValueActualValue, currentMonthValueExpectedValue, "Current Month Value");
	}

	@Test(priority = 6, enabled=false)
	public void previousMonthValueTest() throws Exception {
		String previousMonthValue = DBUtil.previousMonthValueGetDBValue();
		System.out.println("--------Previous Month Value--------");
		String previousMonthValueExpectedValue = "₹ " + previousMonthValue;
		System.out.println("Expected Value: " + previousMonthValueExpectedValue);
		String previousMonthValueActualValue = discountPage.getPreviousMonthDiscount();
		System.out.println("Actual Value: " + previousMonthValueActualValue);
		testUtil.assertEquals(previousMonthValueActualValue, previousMonthValueExpectedValue, "Previous Month Value");
	}


	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}