package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.PayablesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class PayablesTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    PayablesPage payablesPage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        payablesPage = new PayablesPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        TestUtil.scrollDownBy(driver, 1000);
        homePage.clickPayablesTab();
    }

    @Test(priority = 1, enabled = true)
    public void getTotalPayablesTest() throws Exception {
        String totalPayablesExpectedValue = DBUtil.totalPayablesGetDBValue();
        System.out.println("--------Total Payables--------");
        System.out.println("Expected Value: " + totalPayablesExpectedValue);
        String totalPayablesActualValue = payablesPage.getTotalPayables();
        System.out.println("Actual Value: " + totalPayablesActualValue);
        testUtil.assertEquals(totalPayablesActualValue, totalPayablesExpectedValue, "Total Payables");
    }
//----------------------------------On hold for now----------------------------------
    // @Test(priority = 2, enabled = true)
    // public void getMahindraPayablesTest() throws Exception {
    //     String mahindraPayablesExpectedValue = DBUtil.mahindraPayablesGetDBValue();
    //     System.out.println("--------Mahindra Payables--------");
    //     System.out.println("Expected Value: " + mahindraPayablesExpectedValue);
    //     String mahindraPayablesActualValue = payablesPage.getMahindra();
    //     System.out.println("Actual Value: " + mahindraPayablesActualValue);
    //     testUtil.assertEquals(mahindraPayablesActualValue, mahindraPayablesExpectedValue, "Total Payables");
    // }

    // @Test(priority = 3, enabled = true)
    // public void getSalesPayablesTest() throws Exception {
    //     String salesPayablesExpectedValue = DBUtil.salesPayablesGetDBValue();
    //     System.out.println("--------Sales Payables--------");
    //     System.out.println("Expected Value: " + salesPayablesExpectedValue);
    //     String salesPayablesActualValue = payablesPage.getSales();
    //     System.out.println("Actual Value: " + salesPayablesActualValue);
    //     testUtil.assertEquals(salesPayablesActualValue, salesPayablesExpectedValue, "Sales Payables");
    // }

    // @Test(priority = 4, enabled = true)
    // public void getServicePayablesTest() throws Exception {
    //     String servicePayablesExpectedValue = DBUtil.servicePayablesGetDBValue();
    //     System.out.println("--------Service Payables--------");
    //     System.out.println("Expected Value: " + servicePayablesExpectedValue);
    //     String servicePayablesActualValue = payablesPage.getService();
    //     System.out.println("Actual Value: " + servicePayablesActualValue);
    //     testUtil.assertEquals(servicePayablesActualValue, servicePayablesExpectedValue, "Service Payables");
    // }

    // @Test(priority = 5, enabled = true)
    // public void getSparesPayablesTest() throws Exception {
    //     String sparesPayablesExpectedValue = DBUtil.sparesPayablesGetDBValue();
    //     System.out.println("--------Spares Payables--------");
    //     System.out.println("Expected Value: " + sparesPayablesExpectedValue);
    //     String sparesPayablesActualValue = payablesPage.getSpares();
    //     System.out.println("Actual Value: " + sparesPayablesActualValue);
    //     testUtil.assertEquals(sparesPayablesActualValue, sparesPayablesExpectedValue, "Spares Payables");
    // }

    // @Test(priority = 6, enabled = true)
    // public void getOthersPayablesTest() throws Exception {
    //     String othersPayablesExpectedValue = DBUtil.othersPayablesGetDBValue();
    //     System.out.println("--------Others Payables--------");
    //     System.out.println("Expected Value: " + othersPayablesExpectedValue);
    //     String othersPayablesActualValue = payablesPage.getOthers();
    //     System.out.println("Actual Value: " + othersPayablesActualValue);
    //     testUtil.assertEquals(othersPayablesActualValue, othersPayablesExpectedValue, "Others Payables");
    // }

    // @Test(priority = 7, enabled = true)
    // public void getGreaterThan60DaysPendingPayablesTest() throws Exception {
    //     String greaterThan60DaysPendingPayablesExpectedValue = DBUtil.greaterThan60DaysPendingPayablesGetDBValue();
    //     System.out.println("--------Greater Than 60 Days Pending Payables--------");
    //     System.out.println("Expected Value: " + greaterThan60DaysPendingPayablesExpectedValue);
    //     String greaterThan60DaysPendingPayablesActualValue = payablesPage.getGreaterThan60DaysPendingPayables();
    //     System.out.println("Actual Value: " + greaterThan60DaysPendingPayablesActualValue);
    //     testUtil.assertEquals(greaterThan60DaysPendingPayablesActualValue,
    //             greaterThan60DaysPendingPayablesExpectedValue,
    //             "Greater Than 60 Days Pending Payables");
    // }

    // @Test(priority = 8, enabled = true)
    // public void getGreaterThan30DaysPendingPayablesTest() throws Exception {
    //     String greaterThan30DaysPendingPayablesExpectedValue = DBUtil.greaterThan30DaysPendingPayablesGetDBValue();
    //     System.out.println("--------Greater Than 30 Days Pending Payables--------");
    //     System.out.println("Expected Value: " + greaterThan30DaysPendingPayablesExpectedValue);
    //     String greaterThan30DaysPendingPayablesActualValue = payablesPage.getGreaterThan30DaysPendingPayables();
    //     System.out.println("Actual Value: " + greaterThan30DaysPendingPayablesActualValue);
    //     testUtil.assertEquals(greaterThan30DaysPendingPayablesActualValue,
    //             greaterThan30DaysPendingPayablesExpectedValue,
    //             "Greater Than 30 Days Pending Payables");
    // }

    @Test(priority = 9, enabled = true)
    public void currentMonthPayablesTrendTest() throws Exception {
        String currentMonthPayablesTrendExpectedValue = DBUtil.currentMonthPayablesTrendGetDBValue();
        System.out.println("--------Current Month Payables Trend--------");
        System.out.println("Expected Value: " + currentMonthPayablesTrendExpectedValue);
        String currentMonthPayablesTrendActualValue = payablesPage.currentMonthPayablesTrend();
        System.out.println("Actual Value: " + currentMonthPayablesTrendActualValue);
        testUtil.assertEquals(currentMonthPayablesTrendActualValue,
                currentMonthPayablesTrendExpectedValue,
                "Current Month Payables Trend");
    }

    @Test(priority = 10, enabled = true)
    public void lastMonthPayablesTrendTest() throws Exception {
        String lastMonthPayablesTrendExpectedValue = DBUtil.lastMonthPayablesTrendGetDBValue();
        System.out.println("--------Last Month Payables Trend--------");
        System.out.println("Expected Value: " + lastMonthPayablesTrendExpectedValue);
        String lastMonthPayablesTrendActualValue = payablesPage.lastMonthPayablesTrend();
        System.out.println("Actual Value: " + lastMonthPayablesTrendActualValue);
        testUtil.assertEquals(lastMonthPayablesTrendActualValue,
                lastMonthPayablesTrendExpectedValue,
                "Last Month Payables Trend");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}