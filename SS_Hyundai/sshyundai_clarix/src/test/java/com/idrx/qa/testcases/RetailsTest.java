package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.ETBRPages;
import com.idrx.qa.pages.RetailsPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class RetailsTest extends TestBase{

    LoginPage loginPage;
    HomePage homePage;
    ETBRPages etbrPages;
    RetailsPage retailsPage;
    TestUtil testUtil;
    
    public RetailsTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        homePage = new HomePage();
        etbrPages = new ETBRPages();
        retailsPage = new RetailsPage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickSalesTab();
        Thread.sleep(5000);
        etbrPages.clickRetailsTile();
    }

    @Test(priority = 1, enabled = false)
    public void vehiclesSoldTest() throws Exception {
        String vehiclesSold = DBUtil.vehiclesSoldGetDBValue();
        System.out.println("--------Vehicles Sold--------");
        int vehiclesSoldExpectedValue = Integer.parseInt(vehiclesSold);
        String vehiclesSoldVal = retailsPage.getVehiclesSold();
        int vehiclesSoldActualValue = Integer.parseInt(vehiclesSoldVal);
        testUtil.assertEquals(vehiclesSoldActualValue, vehiclesSoldExpectedValue, "Vehicles Sold");
    }

    @Test(priority = 2, enabled = false)
    public void avgSalesTest() throws Exception {
        String avgSales = DBUtil.avgSalesGetDBValue();
        System.out.println("--------Avg Sales--------");
        int avgSalesExpectedValue = Integer.parseInt(avgSales);
        String avgSalesVal = retailsPage.getAvgSales();
        int avgSalesActualValue = Integer.parseInt(avgSalesVal);
        testUtil.assertEquals(avgSalesActualValue, avgSalesExpectedValue, "Avg Sales");
    }

    @Test(priority = 3, enabled = false)
    public void enquiriesYdayTest() throws Exception {
        String enquiriesYday = DBUtil.enquiriesYdayGetDBValue();
        System.out.println("--------Enquiries Y'day--------");
        int enquiriesYdayExpectedValue = Integer.parseInt(enquiriesYday);
        String enquiriesYdayVal = retailsPage.getYesterdayEnquries();
        int enquiriesYdayActualValue = Integer.parseInt(enquiriesYdayVal);
        testUtil.assertEquals(enquiriesYdayActualValue, enquiriesYdayExpectedValue, "Enquiries Y'day");
    }

    @Test(priority = 4, enabled = true)
    public void vehiclesBookedYdayTest() throws Exception {
        String vehiclesBookedYday = DBUtil.vehiclesBookedYdayGetDBValue();
        System.out.println("--------Vehicles Booked Y'day--------");
        int vehiclesBookedYdayExpectedValue = Integer.parseInt(vehiclesBookedYday);
        String vehiclesBookedYdayVal = retailsPage.getYesterdayBooked();
        int vehiclesBookedYdayActualValue = Integer.parseInt(vehiclesBookedYdayVal);
        testUtil.assertEquals(vehiclesBookedYdayActualValue, vehiclesBookedYdayExpectedValue, "Vehicles Booked Y'day");
    }

    @Test(priority = 5, enabled = true)
    public void vehiclesInvoicedYdayTest() throws Exception {
        String vehiclesInvoicedYday = DBUtil.vehiclesInvoicedYdayGetDBValue();
        System.out.println("--------Vehicles Invoiced Y'day--------");
        int vehiclesInvoicedYdayExpectedValue = Integer.parseInt(vehiclesInvoicedYday);
        String vehiclesInvoicedYdayVal = retailsPage.getYesterdayInvoiced();
        int vehiclesInvoicedYdayActualValue = Integer.parseInt(vehiclesInvoicedYdayVal);
        testUtil.assertEquals(vehiclesInvoicedYdayActualValue, vehiclesInvoicedYdayExpectedValue, "Vehicles Invoiced Y'day");
    }

    @Test(priority = 6, enabled = false)
    public void vehiclesInvoicedThisMonthTest() throws Exception {
        String vehiclesInvoicedThisMonth = DBUtil.vehiclesInvoicedThisMonthGetDBValue();
        System.out.println("--------Vehicles Invoiced This Month--------");
        int vehiclesInvoicedThisMonthExpectedValue = Integer.parseInt(vehiclesInvoicedThisMonth);
        String vehiclesInvoicedThisMonthVal = retailsPage.getInvoicedThisMonth();
        int vehiclesInvoicedThisMonthActualValue = Integer.parseInt(vehiclesInvoicedThisMonthVal);
        testUtil.assertEquals(vehiclesInvoicedThisMonthActualValue, vehiclesInvoicedThisMonthExpectedValue, "Vehicles Invoiced This Month");
    }

    @Test(priority = 7, enabled = false)
    public void qtyTrendCurrentMonthTest() throws Exception {
        String qtyTrendCurrentMonth = DBUtil.qtyTrendCurrentMonthGetDBValue();
        System.out.println("--------Qty Trend Current Month--------");
        int qtyTrendCurrentMonthExpectedValue = Integer.parseInt(qtyTrendCurrentMonth);
        String qtyTrendCurrentMonthVal = retailsPage.getCurrentMonth();
        int qtyTrendCurrentMonthActualValue = Integer.parseInt(qtyTrendCurrentMonthVal);
        testUtil.assertEquals(qtyTrendCurrentMonthActualValue, qtyTrendCurrentMonthExpectedValue, "Qty Trend Current Month");
    }

    @Test(priority = 8, enabled = true)
    public void qtyTrendPreviousMonthTest() throws Exception {
        String qtyTrendPreviousMonth = DBUtil.qtyTrendPreviousMonthGetDBValue();
        System.out.println("--------Qty Trend Previous Month--------");
        int qtyTrendPreviousMonthExpectedValue = Integer.parseInt(qtyTrendPreviousMonth);
        String qtyTrendPreviousMonthVal = retailsPage.getPreviousMonth();
        int qtyTrendPreviousMonthActualValue = Integer.parseInt(qtyTrendPreviousMonthVal);
        testUtil.assertEquals(qtyTrendPreviousMonthActualValue, qtyTrendPreviousMonthExpectedValue, "Qty Trend Previous Month");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
