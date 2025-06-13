package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.ServicePage;
import com.idrx.qa.pages.ServiceRevenuePage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ServiceRevenueTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ServiceRevenuePage serviceRevenuePage;
    ServicePage servicePage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        servicePage = new ServicePage();
        serviceRevenuePage = new ServiceRevenuePage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickServiceTab();
        servicePage.clickServiceRevenueTab();
        Thread.sleep(5000);
    }

    @Test(priority = 1, enabled = true)
    public void serviceRevenueTest() throws Exception {
        String serviceRevenue = DBUtil.serviceRevenueGetDBValue();
        System.out.println("--------Service Revenue--------");
        String serviceRevenueExpectedValue = "₹ " + serviceRevenue;
        System.out.println("Expected Value: " + serviceRevenueExpectedValue);
        String serviceRevenueActualValue = serviceRevenuePage.getServiceRevenue();
        System.out.println("Actual Value: " + serviceRevenueActualValue);
        testUtil.assertEquals(serviceRevenueActualValue, serviceRevenueExpectedValue, "Service Revenue");
    }

    @Test(priority = 2, enabled = true)
    public void avgRevPerVehicleTest() throws Exception {
        String avgRevPerVehicle = DBUtil.avgRevPerVehicleGetDBValue();
        System.out.println("--------Average Revenue Per Vehicle--------");
        String avgRevPerVehicleExpectedValue = "₹ " + avgRevPerVehicle;
        System.out.println("Expected Value: " + avgRevPerVehicleExpectedValue);
        String avgRevPerVehicleActualValue = serviceRevenuePage.getAvgRevPerVehicle();
        System.out.println("Actual Value: " + avgRevPerVehicleActualValue);
        testUtil.assertEquals(avgRevPerVehicleActualValue, avgRevPerVehicleExpectedValue,
                "Average Revenue Per Vehicle");
    }

    @Test(priority = 3, enabled = true)
    public void currentMonthRevenueTest() throws Exception {
        String currentMonthRevenue = DBUtil.currentMonthRevenueGetDBValue();
        System.out.println("--------Current Month Revenue--------");
        String currentMonthRevenueExpectedValue = "₹ " + currentMonthRevenue;
        System.out.println("Expected Value: " + currentMonthRevenueExpectedValue);
        String currentMonthRevenueActualValue = serviceRevenuePage.getCurrentMonthRevenue();
        System.out.println("Actual Value: " + currentMonthRevenueActualValue);
        testUtil.assertEquals(currentMonthRevenueActualValue, currentMonthRevenueExpectedValue,
                "Current Month Revenue");
    }

    @Test(priority = 4, enabled = true)
    public void previousMonthRevenueTest() throws Exception {
        String previousMonthRevenue = DBUtil.previousMonthRevenueGetDBValue();
        System.out.println("--------Previous Month Revenue--------");
        String previousMonthRevenueExpectedValue = "₹ " + previousMonthRevenue;
        System.out.println("Expected Value: " + previousMonthRevenueExpectedValue);
        String previousMonthRevenueActualValue = serviceRevenuePage.getPreviousMonthRevenue();
        System.out.println("Actual Value: " + previousMonthRevenueActualValue);
        testUtil.assertEquals(previousMonthRevenueActualValue, previousMonthRevenueExpectedValue,
                "Previous Month Revenue");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
