package com.idrx.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
	}
	
	@Test
	public void loginTest() throws Exception {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(10000);
		TestUtil.switchToFrame();
		
		homePage.clickSalesTab();
		Object vechicalSold = TestUtil.getValue("Vehicle Sold", "Sheet1");
		double vechicalSoldValue = (double) vechicalSold;
		int vechicalSoldExpectedValue = (int) vechicalSoldValue;
		String vechicalSoldVal = homePage.getVechicalSold();
		int vechicalSoldActualValue = Integer.parseInt(vechicalSoldVal);
		Assert.assertEquals(vechicalSoldActualValue, vechicalSoldExpectedValue);		
		
		Object vechicalRevenue = TestUtil.getValue("Vehicle Sales", "Sheet1");
		String vechicalRevenueValue = (String) vechicalRevenue;
		String vechicalRevenueExpectedValue = "₹ " + vechicalRevenueValue;
		String vechicalRevenueActualValue = homePage.getSalesRevenue();
		Assert.assertEquals(vechicalRevenueActualValue, vechicalRevenueExpectedValue);
		
		Object vehicleBookedYesterday = TestUtil.getValue("Vehicle Booked Yesterday", "Sheet1");
		double vehicleBookedYesterdayValue = (double) vehicleBookedYesterday;
		int vechicalBookedYesterdayExpectedValue = (int) vehicleBookedYesterdayValue;
		String vechicalBookedYesterdayVal = homePage.getVechicalBookedYesterday();
		int vechicalBookedYesterdayActualValue = Integer.parseInt(vechicalBookedYesterdayVal);
		Assert.assertEquals(vechicalBookedYesterdayActualValue, vechicalBookedYesterdayExpectedValue);
		
		Object vehicleInvoicedNotDelivered = TestUtil.getValue("Vehicle Invoiced but not delivered", "Sheet1");
		double vehicleInvoicedNotDeliveredValue = (double) vehicleInvoicedNotDelivered;
		int vehicleInvoicedNotDeliveredExpectedValue = (int) vehicleInvoicedNotDeliveredValue;
		String vehicleInvoicedNotDeliveredVal = homePage.getVehicleInvoicedNotDelivered();
		int vehicleInvoicedNotDeliveredActualValue = Integer.parseInt(vehicleInvoicedNotDeliveredVal);
		Assert.assertEquals(vehicleInvoicedNotDeliveredActualValue, vehicleInvoicedNotDeliveredExpectedValue);
		
		Object salesTarget = TestUtil.getValue("Sales Target achieved %", "Sheet1");
		double salesTargetExpectedVal = Double.parseDouble(salesTarget.toString());
		String salesTargetExpectedValue = String.format("%.0f%%", salesTargetExpectedVal * 100);
		TestUtil.switchToFrame();		
		String salesTargetActualValue = homePage.getSalesTargetAchieved();
		Assert.assertEquals(salesTargetActualValue, salesTargetExpectedValue);		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
