package com.idrx.qa.testcases;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.SalesPage;
import com.idrx.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	SalesPage salesPage;
	TestUtil testUtil;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeTest
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
	
	@Test(priority = 1)
	public void vehicleSoldTest() throws Exception {
		
		Object vechicalSold = TestUtil.getValue("Vehicle Sold", "Sheet1");
		System.out.println("--------Vehicle Sold--------");
		double vechicalSoldValue = (double) vechicalSold;
		int vechicalSoldExpectedValue = (int) vechicalSoldValue;
		String vechicalSoldVal = salesPage.getVechicalSold();
		int vechicalSoldActualValue = Integer.parseInt(vechicalSoldVal);
		testUtil.assertEquals(vechicalSoldActualValue, vechicalSoldExpectedValue, "Vehicle Sold");
	}
	
	@Test(priority = 2)
	public void vehicleSalesTest() throws Exception {	
		
		Object vechicalRevenue = TestUtil.getValue("Vehicle Sales", "Sheet1");
		System.out.println("--------Vehicle Sales--------");
		String vechicalRevenueValue = (String) vechicalRevenue;
		String vechicalRevenueExpectedValue = "₹ " + vechicalRevenueValue;
		String vechicalRevenueActualValue = salesPage.getSalesRevenue();	
		testUtil.assertEquals(vechicalRevenueActualValue, vechicalRevenueExpectedValue, "Vehicle Sales");
	}
	
	@Test(priority = 3)
	public void vehicleBookedYesterdayTest() throws Exception {
		
		Object vehicleBookedYesterday = TestUtil.getValue("Vehicle Booked Yesterday", "Sheet1");
		System.out.println("--------Vehicle Booked Yesterday--------");
		double vehicleBookedYesterdayValue = (double) vehicleBookedYesterday;
		int vechicalBookedYesterdayExpectedValue = (int) vehicleBookedYesterdayValue;
		String vechicalBookedYesterdayVal = salesPage.getVechicalBookedYesterday();
		int vechicalBookedYesterdayActualValue = Integer.parseInt(vechicalBookedYesterdayVal);		
		testUtil.assertEquals(vechicalBookedYesterdayActualValue, vechicalBookedYesterdayExpectedValue, "Vehicle Booked Yesterday");
	}

	@Test(priority = 4)
	public void vehicleInvoicedYesterdayTest() throws Exception {
		
		Object vehicleInvoicedYesterday = TestUtil.getValue("vehicle Invoiced Yesterday", "Sheet1");
		System.out.println("--------Vehicle Invoiced Yesterday--------");
		double vehicleInvoicedYesterdayValue = (double) vehicleInvoicedYesterday;
		int vehicleInvoicedYesterdayExpectedValue = (int) vehicleInvoicedYesterdayValue;
		String vehicleInvoicedYesterdayVal = salesPage.getVehicleInvoicedYesterday();
		int vehicleInvoicedYesterdayActualValue = Integer.parseInt(vehicleInvoicedYesterdayVal);
		testUtil.assertEquals(vehicleInvoicedYesterdayActualValue, vehicleInvoicedYesterdayExpectedValue, "vehicle Invoiced Yesterday");
	}
	
	@Test(priority = 5)
	public void vehicleInvoicedThisMonthTest() throws Exception {
		
		Object vehicleInvoicedThisMonth = TestUtil.getValue("vehicle Invoiced this month", "Sheet1");
		System.out.println("--------Vehicle Invoiced this month--------");
		double vehicleInvoicedThisMonthValue = (double) vehicleInvoicedThisMonth;
		int vehicleInvoicedThisMonthExpectedValue = (int) vehicleInvoicedThisMonthValue;
		String vehicleInvoicedThisMonthVal = salesPage.getVehicleInvoicedThisMonth();
		int vehicleInvoicedThisMonthActualValue = Integer.parseInt(vehicleInvoicedThisMonthVal);		
		testUtil.assertEquals(vehicleInvoicedThisMonthActualValue, vehicleInvoicedThisMonthExpectedValue, "vehicle Invoiced this month");
	}
	
	@Test(priority = 6)
	public void vehicleInvoicedButNotDeliveredTest() throws Exception {
	
		Object vehicleInvoicedNotDelivered = TestUtil.getValue("Vehicle Invoiced but not delivered", "Sheet1");
		System.out.println("--------Vehicle Invoiced but not delivered--------");
		double vehicleInvoicedNotDeliveredValue = (double) vehicleInvoicedNotDelivered;
		int vehicleInvoicedNotDeliveredExpectedValue = (int) vehicleInvoicedNotDeliveredValue;
		String vehicleInvoicedNotDeliveredVal = salesPage.getVehicleInvoicedNotDelivered();
		int vehicleInvoicedNotDeliveredActualValue = Integer.parseInt(vehicleInvoicedNotDeliveredVal);
		testUtil.assertEquals(vehicleInvoicedNotDeliveredActualValue, vehicleInvoicedNotDeliveredExpectedValue, "Vehicle Invoiced but not delivered");
	}
	
	@Test(priority = 7)
	public void aprilMonthSalesTrendTest() throws Exception {
	
		Object aprilMonthSalesTrend = TestUtil.getValue("Qty Trend - Apr'2025", "Sheet1");
		System.out.println("--------Qty Trend - Apr'2025--------");
		double aprilMonthSalesTrendValue = (double) aprilMonthSalesTrend;
		int aprilMonthSalesTrendExpectedValue = (int) aprilMonthSalesTrendValue;
		String aprilMonthSalesTrendVal = salesPage.getAprilMonthWiseSalesTrend();
		int aprilMonthSalesTrendValActualValue = Integer.parseInt(aprilMonthSalesTrendVal);	
		testUtil.assertEquals(aprilMonthSalesTrendValActualValue, aprilMonthSalesTrendExpectedValue, "Qty Trend - Apr'2025");
	}
	
	@Test(priority = 8)
	public void marchMonthSalesTrendTest() throws Exception {
		
		Object marchMonthSalesTrend = TestUtil.getValue("Qty Trend - Mar'2025", "Sheet1");
		System.out.println("--------Qty Trend - Mar'2025--------");
		double marchMonthSalesTrendValue = (double) marchMonthSalesTrend;
		int marchMonthSalesTrendExpectedValue = (int) marchMonthSalesTrendValue;
		String marchMonthSalesTrendVal = salesPage.getMarchMonthWiseSalesTrend();
		int marchMonthSalesTrendValActualValue = Integer.parseInt(marchMonthSalesTrendVal);		
		testUtil.assertEquals(marchMonthSalesTrendValActualValue, marchMonthSalesTrendExpectedValue, "Qty Trend - Mar'2025");
	}
	
	@Test(priority = 9)
	public void salesTargetAchievedTest() throws Exception {
		
		Object salesTarget = TestUtil.getValue("Sales Target achieved %", "Sheet1");
		System.out.println("--------Sales Target achieved %--------");
		double salesTargetExpectedVal = Double.parseDouble(salesTarget.toString());
		String salesTargetExpectedValue = String.format("%.0f%%", salesTargetExpectedVal * 100);
		TestUtil.switchToFrame();	
		String salesTargetActualValue = salesPage.getSalesTargetAchieved();		
		testUtil.assertEquals(salesTargetActualValue, salesTargetExpectedValue, "Sales Target achieved %");	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
