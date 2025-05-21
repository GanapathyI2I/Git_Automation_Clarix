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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class RetailPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	SalesPage salesPage;
	TestUtil testUtil;
	
	public RetailPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		salesPage = new SalesPage();
		testUtil = new TestUtil();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(10000);
		TestUtil.switchToFrame();		
		homePage.clickSalesTab();
	}	
	
	@Test(priority = 1, enabled=true)
	public void vehicleSoldTest() throws Exception {
		
		String vechicalSold = DBUtil.vehicleSoldGetDBValue();
		System.out.println("--------Vehicle Sold--------");
		//double vechicalSoldValue = (double) vechicalSold;
		int vechicalSoldExpectedValue = Integer.parseInt(vechicalSold);
		String vechicalSoldVal = salesPage.getVechicalSold();
		int vechicalSoldActualValue = Integer.parseInt(vechicalSoldVal);
		testUtil.assertEquals(vechicalSoldActualValue, vechicalSoldExpectedValue, "Vehicle Sold");
	}
	
	@Test(priority = 2, enabled=true)
	public void vehicleSalesTest() throws Exception {	
		
	//	Object vechicalRevenue = TestUtil.getValue("Vehicle Sales", "Sheet1");
		String vechicalRevenue = DBUtil.vehicleSalesTestDBValue();
		System.out.println("--------Vehicle Sales--------");
		String vechicalRevenueExpectedValue = "₹ " + vechicalRevenue;
		System.out.println("Expected Value: " + vechicalRevenueExpectedValue);
		String vechicalRevenueActualValue = salesPage.getSalesRevenue();	
		System.out.println("Actual Value: " + vechicalRevenueActualValue);
		testUtil.assertEquals(vechicalRevenueActualValue, vechicalRevenueExpectedValue, "Vehicle Sales");
	}
	
	@Test(priority = 3, enabled=true)
	public void vehicleBookedYesterdayTest() throws Exception {
		
		String vehicleBookedYesterday = DBUtil.vehicleBookedYesterdayTestDBValue();
		System.out.println("--------Vehicle Booked Yesterday--------");
		int vechicalBookedYesterdayExpectedValue = Integer.parseInt(vehicleBookedYesterday);
		String vechicalBookedYesterdayVal = salesPage.getVechicalBookedYesterday();
		int vechicalBookedYesterdayActualValue = Integer.parseInt(vechicalBookedYesterdayVal);		
		testUtil.assertEquals(vechicalBookedYesterdayActualValue, vechicalBookedYesterdayExpectedValue, "Vehicle Booked Yesterday");
	}

	@Test(priority = 4, enabled=true)
	public void vehicleInvoicedYesterdayTest() throws Exception {
		
		String vehicleInvoicedYesterday = DBUtil.vehicleInvoicedYesterdayTestDBValue();
		System.out.println("--------Vehicle Invoiced Yesterday--------");
		int vehicleInvoicedYesterdayExpectedValue = Integer.parseInt(vehicleInvoicedYesterday);
		String vehicleInvoicedYesterdayVal = salesPage.getVehicleInvoicedYesterday();
		int vehicleInvoicedYesterdayActualValue = Integer.parseInt(vehicleInvoicedYesterdayVal);
		testUtil.assertEquals(vehicleInvoicedYesterdayActualValue, vehicleInvoicedYesterdayExpectedValue, "vehicle Invoiced Yesterday");
	}
	
	@Test(priority = 5, enabled=true)
	public void vehicleInvoicedThisMonthTest() throws Exception {
		
		String vehicleInvoicedThisMonth = DBUtil.vehicleInvoicedThisMonthTestDBValue();
		System.out.println("--------Vehicle Invoiced this month--------");
		int vehicleInvoicedThisMonthExpectedValue = Integer.parseInt(vehicleInvoicedThisMonth);
		String vehicleInvoicedThisMonthVal = salesPage.getVehicleInvoicedThisMonth();
		int vehicleInvoicedThisMonthActualValue = Integer.parseInt(vehicleInvoicedThisMonthVal);		
		testUtil.assertEquals(vehicleInvoicedThisMonthActualValue, vehicleInvoicedThisMonthExpectedValue, "vehicle Invoiced this month");
	}
	
	@Test(priority = 6, enabled=true)
	public void vehicleInvoicedButNotDeliveredTest() throws Exception {
	
		String vehicleInvoicedNotDelivered = DBUtil.vehicleInvoicedButNotDeliveredTestDBValue();
		System.out.println("--------Vehicle Invoiced but not delivered--------");
		int vehicleInvoicedNotDeliveredExpectedValue = Integer.parseInt(vehicleInvoicedNotDelivered);
		String vehicleInvoicedNotDeliveredVal = salesPage.getVehicleInvoicedNotDelivered();
		int vehicleInvoicedNotDeliveredActualValue = Integer.parseInt(vehicleInvoicedNotDeliveredVal);
		testUtil.assertEquals(vehicleInvoicedNotDeliveredActualValue, vehicleInvoicedNotDeliveredExpectedValue, "Vehicle Invoiced but not delivered");
	}
	
	@Test(priority = 7, enabled=true)
	public void currentMonthTest() throws Exception {
	
        String currentMonth =DBUtil.currentMonthTestDBValue();
		System.out.println("--------Qty Trend - Apr'2025--------");
		int currentMonthExpectedValue = Integer.parseInt(currentMonth);	
		String aprilMonthSalesTrendVal = salesPage.getAprilMonthWiseSalesTrend();
		int aprilMonthSalesTrendValActualValue = Integer.parseInt(aprilMonthSalesTrendVal);	
		testUtil.assertEquals(aprilMonthSalesTrendValActualValue, currentMonthExpectedValue, "Qty Trend - Apr'2025");
	}
	
	@Test(priority = 8, enabled=true)
	public void marchMonthSalesTrendTest() throws Exception {
		
		String lastMonthSalesTrend = DBUtil.lastMonthTestDBValue();
		System.out.println("--------Qty Trend - Mar'2025--------");
		int lastMonthExpectedValue = Integer.parseInt(lastMonthSalesTrend);
		String marchMonthSalesTrendVal = salesPage.getMarchMonthWiseSalesTrend();
		int marchMonthSalesTrendValActualValue = Integer.parseInt(marchMonthSalesTrendVal);		
		testUtil.assertEquals(marchMonthSalesTrendValActualValue, lastMonthExpectedValue, "Qty Trend - Mar'2025");
	}
	
	// @Test(priority = 9, enabled=false)
	// public void salesTargetAchievedTest() throws Exception {
		
	// 	String salesTarget = DBUtil.salesTargetTestDBValue();
	// 	System.out.println("--------Sales Target achieved %--------");
	// 	double salesTargetExpectedVal = Double.parseDouble(salesTarget.toString());
	// 	String salesTargetExpectedValue = String.format("%.0f%%", salesTargetExpectedVal * 100);
	// 	TestUtil.switchToFrame();	
	// 	String salesTargetActualValue = salesPage.getSalesTargetAchieved();		
	// 	testUtil.assertEquals(salesTargetActualValue, salesTargetExpectedValue, "Sales Target achieved %");	
	// }
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
