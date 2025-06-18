package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.ReceivablesPages;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class ReceivablesPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ReceivablesPages receivablesPages;

    public ReceivablesPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        testUtil = new TestUtil();
        receivablesPages = new ReceivablesPages();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickReceivablesTile();
        Thread.sleep(5000);
    }

    @Test(priority = 1, enabled=true)
    public void creditOutstandingTest() throws Exception {
        String creditOutstanding = DBUtil.creditOutstandingDBValue();
        System.out.println("---------Receivables/ Credit Outstanding----------");
        String creditOutstandingExpected = "₹ " + creditOutstanding;
        System.out.println("Expected Value: " + creditOutstandingExpected);
        String creditOutstandingActual = receivablesPages.getCreditOutstanding();
        System.out.println("Actual Value: " + creditOutstandingActual);
        testUtil.assertEquals(creditOutstandingActual, creditOutstandingExpected, "Credit Outstanding");
    }

    // @Test(priority = 2, enabled=true)
    // public void mahindraTest() throws Exception {
    //     String mahindra = DBUtil.mahindraDBValue();
    //     System.out.println("---------Receivables/ Mahindra----------");
    //     String mahindraExpected = "₹ " + mahindra;
    //     System.out.println("Expected Value: " + mahindraExpected);
    //     String mahindraActual = receivablesPages.getMahindra();
    //     System.out.println("Actual Value: " + mahindraActual);
    //     testUtil.assertEquals(mahindraActual, mahindraExpected, "Mahindra");
    // }

    // @Test(priority = 3, enabled=true)
    // public void salesTest() throws Exception {
    //     String sales = DBUtil.salesDBValue();
    //     System.out.println("---------Receivables/ Sales----------");
    //     String salesExpected = "₹ " + sales;
    //     System.out.println("Expected Value: " + salesExpected);
    //     String salesActual = receivablesPages.getSales();
    //     System.out.println("Actual Value: " + salesActual);
    //     testUtil.assertEquals(salesActual, salesExpected, "Sales");
    // }

    // @Test(priority = 4, enabled=true)
    // public void serviceTest() throws Exception {
    //     String service = DBUtil.serviceDBValue();
    //     System.out.println("---------Receivables/ Service----------");
    //     String serviceExpected = "₹ " + service;
    //     System.out.println("Expected Value: " + serviceExpected);
    //     String serviceActual = receivablesPages.getService();
    //     System.out.println("Actual Value: " + serviceActual);
    //     testUtil.assertEquals(serviceActual, serviceExpected, "Service");
    // }

    // @Test(priority = 5, enabled=true)
    // public void supplierAdvanceTest() throws Exception {
    //     String supplierAdvance = DBUtil.supplierAdvanceDBValue();
    //     System.out.println("---------Receivables/ Supplier Advance----------");
    //     String supplierAdvanceExpected = "₹ " + supplierAdvance;
    //     System.out.println("Expected Value: " + supplierAdvanceExpected);
    //     String supplierAdvanceActual = receivablesPages.getSupplierAdvance();
    //     System.out.println("Actual Value: " + supplierAdvanceActual);
    //     testUtil.assertEquals(supplierAdvanceActual, supplierAdvanceExpected, "Supplier Advance");
    // }

    // @Test(priority = 6, enabled=true)
    // public void othersTest() throws Exception {
    //     String others = DBUtil.othersDBValue();
    //     System.out.println("---------Receivables/ Others----------");
    //     String othersExpected = "₹ " + others;
    //     System.out.println("Expected Value: " + othersExpected);
    //     String othersActual = receivablesPages.getOthers();
    //     System.out.println("Actual Value: " + othersActual);
    //     testUtil.assertEquals(othersActual, othersExpected, "Others");
    // }

    // @Test(priority = 7, enabled=true)
    // public void receivablesTrentCurrentMonthTest() throws Exception {
    //     String receivablesTrentCurrentMonth = DBUtil.receivablesTrentCurrentMonthDBValue();
    //     System.out.println("---------Receivables/ Receivables Trent Current Month----------");
    //     String receivablesTrentCurrentMonthExpected = "₹ " + receivablesTrentCurrentMonth;
    //     System.out.println("Expected Value: " + receivablesTrentCurrentMonthExpected);
    //     String receivablesTrentCurrentMonthActual = receivablesPages.getReceivablesTrendCurrentMonth();
    //     System.out.println("Actual Value: " + receivablesTrentCurrentMonthActual);
    //     testUtil.assertEquals(receivablesTrentCurrentMonthActual, receivablesTrentCurrentMonthExpected, "Receivables Trent Current Month");
    // }

    // @Test(priority = 8, enabled=true)
    // public void receivablesTrentPastMonthTest() throws Exception {
    //     String receivablesTrentPastMonth = DBUtil.receivablesTrentPastMonthDBValue();
    //     System.out.println("---------Receivables/ Receivables Trent Past Month----------");
    //     String receivablesTrentPastMonthExpected = "₹ " + receivablesTrentPastMonth;
    //     System.out.println("Expected Value: " + receivablesTrentPastMonthExpected);
    //     String receivablesTrentPastMonthActual = receivablesPages.getReceivablesTrendPastMonth();
    //     System.out.println("Actual Value: " + receivablesTrentPastMonthActual);
    //     testUtil.assertEquals(receivablesTrentPastMonthActual, receivablesTrentPastMonthExpected, "Receivables Trent Past Month");
    // }

    // @Test(priority = 9, enabled=true)
    // public void riskyReceivablesGreaterThan60DaysTest() throws Exception {
    //     String riskyReceivablesGreaterThan60Days = DBUtil.riskyReceivablesGreaterThan60DaysDBValue();
    //     System.out.println("---------Receivables/ Risky Receivables Greater Than 60 Days----------");
    //     String riskyReceivablesGreaterThan60DaysExpected = "₹ " + riskyReceivablesGreaterThan60Days;
    //     System.out.println("Expected Value: " + riskyReceivablesGreaterThan60DaysExpected);
    //     String riskyReceivablesGreaterThan60DaysActual = receivablesPages.getRiskyReceivablesGreaterThan60Days();
    //     System.out.println("Actual Value: " + riskyReceivablesGreaterThan60DaysActual);
    //     testUtil.assertEquals(riskyReceivablesGreaterThan60DaysActual, riskyReceivablesGreaterThan60DaysExpected, "Risky Receivables Greater Than 60 Days");
    // }

    // @Test(priority = 10, enabled=true)
    // public void riskyReceivablesGreaterThan30DaysTest() throws Exception {
    //     String riskyReceivablesGreaterThan30Days = DBUtil.riskyReceivablesGreaterThan30DaysDBValue();
    //     System.out.println("---------Receivables/ Risky Receivables Greater Than 30 Days----------");
    //     String riskyReceivablesGreaterThan30DaysExpected = "₹ " + riskyReceivablesGreaterThan30Days;
    //     System.out.println("Expected Value: " + riskyReceivablesGreaterThan30DaysExpected);
    //     String riskyReceivablesGreaterThan30DaysActual = receivablesPages.getRiskyReceivablesGreaterThan30Days();
    //     System.out.println("Actual Value: " + riskyReceivablesGreaterThan30DaysActual);
    //     testUtil.assertEquals(riskyReceivablesGreaterThan30DaysActual, riskyReceivablesGreaterThan30DaysExpected, "Risky Receivables Greater Than 30 Days");
    // }
    
    
    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}