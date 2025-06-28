package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.BookingsPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.DeliveriesPage;
import com.idrx.qa.pages.ETBRPages;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;


public class DeliveriesTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    ETBRPages etbrPages;
    DeliveriesPage deliveriesPage;
    TestUtil testUtil;

    public DeliveriesTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        homePage = new HomePage();
        etbrPages = new ETBRPages();
        deliveriesPage = new DeliveriesPage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        Thread.sleep(5000);
        homePage.clickSalesTab();
        etbrPages.clickDeliveriesTile();
    }

    @Test(priority = 1, enabled=true)
    public void deliveriesYesterdayTest() throws Exception {
        String deliveriesYesterday = DBUtil.deliveriesYesterdayGetDBValue();
        System.out.println("--------Delivery/No of Deliveries Yesterday--------");
        int deliveriesYesterdayExpectedValue = Integer.parseInt(deliveriesYesterday);
        String deliveriesYesterdayVal = deliveriesPage.getYesterdayDeliveries();
        int deliveriesYesterdayActualValue = Integer.parseInt(deliveriesYesterdayVal);
        testUtil.assertEquals(deliveriesYesterdayActualValue, deliveriesYesterdayExpectedValue, "No of Deliveries Yesterday");  
    }

    @Test(priority = 2, enabled=true)
    public void deliveriesThisMonthTest() throws Exception {
        String deliveriesThisMonth = DBUtil.deliveriesThisMonthGetDBValue();
        System.out.println("--------Delivery/No of Deliveries This Month--------");
        int deliveriesThisMonthExpectedValue = Integer.parseInt(deliveriesThisMonth);
        String deliveriesThisMonthVal = deliveriesPage.getCurrentMonthDeliveries();
        int deliveriesThisMonthActualValue = Integer.parseInt(deliveriesThisMonthVal);
        testUtil.assertEquals(deliveriesThisMonthActualValue, deliveriesThisMonthExpectedValue, "No of Deliveries This Month");  
    }

    @Test(priority = 3, enabled=true)
    public void deliveriesPendingTest() throws Exception {
        String deliveriesPending = DBUtil.deliveriesPendingGetDBValue();
        System.out.println("--------Delivery/No of Deliveries Pending--------");
        int deliveriesPendingExpectedValue = Integer.parseInt(deliveriesPending);
        String deliveriesPendingVal = deliveriesPage.getPendingDeliveries();
        int deliveriesPendingActualValue = Integer.parseInt(deliveriesPendingVal);
        testUtil.assertEquals(deliveriesPendingActualValue, deliveriesPendingExpectedValue, "No of Deliveries Pending");  
    }

    @Test(priority = 4, enabled=true)
    public void deliveriesPreviousMonthTest() throws Exception {
        String deliveriesPreviousMonth = DBUtil.deliveriesPreviousMonthGetDBValue();
        System.out.println("--------Delivery/No of Deliveries Previous Month--------");
        int deliveriesPreviousMonthExpectedValue = Integer.parseInt(deliveriesPreviousMonth);
        String deliveriesPreviousMonthVal = deliveriesPage.getPreviousMonth();
        int deliveriesPreviousMonthActualValue = Integer.parseInt(deliveriesPreviousMonthVal);
        testUtil.assertEquals(deliveriesPreviousMonthActualValue, deliveriesPreviousMonthExpectedValue, "No of Deliveries Previous Month");  
    }

    @Test(priority = 5, enabled=true)
    public void deliveriesCurrentMonthTest() throws Exception {
        String deliveriesCurrentMonth = DBUtil.deliveriesCurrentMonthGetDBValue();
        System.out.println("--------Delivery/No of Deliveries Current Month--------");
        int deliveriesCurrentMonthExpectedValue = Integer.parseInt(deliveriesCurrentMonth);
        String deliveriesCurrentMonthVal = deliveriesPage.getCurrentMonth();
        int deliveriesCurrentMonthActualValue = Integer.parseInt(deliveriesCurrentMonthVal);
        testUtil.assertEquals(deliveriesCurrentMonthActualValue, deliveriesCurrentMonthExpectedValue, "No of Deliveries Current Month");  
    }

    @Test(priority = 6, enabled=true)
    public void deliveriesModelWisePendingTest() throws Exception {
        String deliveriesModelWisePending = DBUtil.deliveriesModelWisePendingGetDBValue();
        System.out.println("--------Delivery/No of Deliveries Model Wise Pending--------");
        int deliveriesModelWisePendingExpectedValue = Integer.parseInt(deliveriesModelWisePending);
        String deliveriesModelWisePendingVal = deliveriesPage.getModelWisePendingTotal();
        int deliveriesModelWisePendingActualValue = Integer.parseInt(deliveriesModelWisePendingVal);
        testUtil.assertEquals(deliveriesModelWisePendingActualValue, deliveriesModelWisePendingExpectedValue, "No of Deliveries Model Wise Pending");  
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
