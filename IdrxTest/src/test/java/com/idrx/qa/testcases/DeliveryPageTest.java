package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.BookingsPage;
import com.idrx.qa.pages.DeliveryPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.SalesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class DeliveryPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    BookingsPage bookingsPage;
    DeliveryPage deliveryPage;
    TestUtil testUtil;
    SalesPage salesPage;

    public DeliveryPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        //    driver.get(prop.getProperty("url"));
        loginPage = new LoginPage();
        homePage = new HomePage();
        bookingsPage = new BookingsPage();
        deliveryPage = new DeliveryPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickSalesTab();
        salesPage.clickDeliveryTab();
    }

    @Test(priority = 1, enabled=true)
    public void deliveryYesterdayTest() throws Exception {
        String deliveryYesterday = DBUtil.deliveriesYesterdayTestDBValue();
        System.out.println("--------Delivery/No of Deliveries Yesterday--------");
        int deliveryYesterdayExpectedValue = Integer.parseInt(deliveryYesterday);
        String deliveryYesterdayVal = deliveryPage.getYesterdayDeliveries();
        int deliveryYesterdayActualValue = Integer.parseInt(deliveryYesterdayVal);
        testUtil.assertEquals(deliveryYesterdayActualValue, deliveryYesterdayExpectedValue, "No of Deliveries Yesterday");  
    }

    @Test(priority = 2, enabled=true)
    public void deliveryThisMonthTest() throws Exception {
        String deliveryThisMonth = DBUtil.deliveriesThisMonthTestDBValue();
        System.out.println("--------Delivery/No of Deliveries This Month--------");
        int deliveryThisMonthExpectedValue = Integer.parseInt(deliveryThisMonth);
        String deliveryThisMonthVal = deliveryPage.getThisMonthDeliveries();
        int deliveryThisMonthActualValue = Integer.parseInt(deliveryThisMonthVal);
        testUtil.assertEquals(deliveryThisMonthActualValue, deliveryThisMonthExpectedValue, "No of Deliveries This Month");
    }

    @Test(priority = 3, enabled=true)
    public void deliveryPendingTest() throws Exception {
        String deliveryPending = DBUtil.pendingDeliveriesTestDBValue();
        System.out.println("--------Delivery/Pending Deliveries--------");
        int deliveryPendingExpectedValue = Integer.parseInt(deliveryPending);
        String deliveryPendingVal = deliveryPage.getPendingDeliveries();
        int deliveryPendingActualValue = Integer.parseInt(deliveryPendingVal);
        testUtil.assertEquals(deliveryPendingActualValue, deliveryPendingExpectedValue, "No of Pending Deliveries");
    }

    @Test(priority = 4, enabled=true)
    public void deliveryPreviousMonthTest() throws Exception {
        String deliveryPreviousMonth = DBUtil.previousMonthTestDBValue();
        System.out.println("--------Delivery/Previous Month--------");
        int deliveryPreviousMonthExpectedValue = Integer.parseInt(deliveryPreviousMonth);
        String deliveryPreviousMonthVal = deliveryPage.getPreviousMonth();
        int deliveryPreviousMonthActualValue = Integer.parseInt(deliveryPreviousMonthVal);
        testUtil.assertEquals(deliveryPreviousMonthActualValue, deliveryPreviousMonthExpectedValue, "No of Deliveries Previous Month");
    }

    @Test(priority = 5, enabled=true)
    public void deliveryCurrentMonthTest() throws Exception {
        String deliveryCurrentMonth = DBUtil.currentMonthTestDBValue();
        System.out.println("--------Delivery/Current Month--------");
        int deliveryCurrentMonthExpectedValue = Integer.parseInt(deliveryCurrentMonth);
        String deliveryCurrentMonthVal = deliveryPage.getCurrentMonth();
        int deliveryCurrentMonthActualValue = Integer.parseInt(deliveryCurrentMonthVal);
        testUtil.assertEquals(deliveryCurrentMonthActualValue, deliveryCurrentMonthExpectedValue, "No of Deliveries Current Month");
    }

    @Test(priority = 6, enabled=true)
    public void deliverySalesmanWisePendingTest() throws Exception {
        String deliverySalesmanWisePending = DBUtil.salesmanWisePendingDeliveriesTestDBValue();
        System.out.println("--------Delivery/Salesman Wise Pending Deliveries--------");
        int deliverySalesmanWisePendingExpectedValue = Integer.parseInt(deliverySalesmanWisePending);
        String deliverySalesmanWisePendingVal = deliveryPage.getSalesmanWisePendingDeliveries();
        int deliverySalesmanWisePendingActualValue = Integer.parseInt(deliverySalesmanWisePendingVal);
        testUtil.assertEquals(deliverySalesmanWisePendingActualValue, deliverySalesmanWisePendingExpectedValue, "No of Salesman Wise Pending Deliveries");
    }

    @AfterClass
    public void tearDown() {
            driver.quit();
        }
    }
    