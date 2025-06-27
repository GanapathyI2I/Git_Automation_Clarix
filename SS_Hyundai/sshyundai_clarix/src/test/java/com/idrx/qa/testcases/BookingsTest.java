package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.BookingsPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import com.idrx.qa.pages.ETBRPages;

public class BookingsTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    BookingsPage bookingsPage;
    TestUtil testUtil;
    ETBRPages etbrPages;

    public BookingsTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        homePage = new HomePage();
        bookingsPage = new BookingsPage();
        etbrPages = new ETBRPages();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickSalesTab();
        Thread.sleep(5000);
        etbrPages.clickBookingsTile();
    }

    @Test(priority = 1, enabled = true)
    public void bookingsYesterdayTest() throws Exception {
        String bookingsYesterday = DBUtil.noOfBookingsYdayGetDBValue();
        System.out.println("--------Bookings Yesterday--------");
        int bookingsYesterdayExpectedValue = Integer.parseInt(bookingsYesterday);
        String bookingsYesterdayVal = bookingsPage.getYesterdayBooking();
        int bookingsYesterdayActualValue = Integer.parseInt(bookingsYesterdayVal);
        testUtil.assertEquals(bookingsYesterdayActualValue, bookingsYesterdayExpectedValue, "Bookings Yesterday");
    }

    @Test(priority = 2, enabled = true)
    public void bookingsThisMonthTest() throws Exception {
        String bookingsThisMonth = DBUtil.noOfBookingsThisMonthGetDBValue();
        System.out.println("--------Bookings This Month--------");
        int bookingsThisMonthExpectedValue = Integer.parseInt(bookingsThisMonth);
        String bookingsThisMonthVal = bookingsPage.getCurrentMonthBooking();
        int bookingsThisMonthActualValue = Integer.parseInt(bookingsThisMonthVal);
        testUtil.assertEquals(bookingsThisMonthActualValue, bookingsThisMonthExpectedValue, "Bookings This Month");
    }

    @Test(priority = 3, enabled = true)
    public void pendingBookingsTest() throws Exception {
        String pendingBookings = DBUtil.noOfPendingBookingsGetDBValue();
        System.out.println("--------Pending Bookings--------");
        int pendingBookingsExpectedValue = Integer.parseInt(pendingBookings);
        String pendingBookingsVal = bookingsPage.getPendingBookings();
        int pendingBookingsActualValue = Integer.parseInt(pendingBookingsVal);
        testUtil.assertEquals(pendingBookingsActualValue, pendingBookingsExpectedValue, "Pending Bookings");
    }

    @Test(priority = 4, enabled = true)
    public void currentMonthTest() throws Exception {
        String currentMonth = DBUtil.currentMonthGetDBValue();
        System.out.println("--------Current Month--------");
        int currentMonthExpectedValue = Integer.parseInt(currentMonth);
        String currentMonthVal = bookingsPage.getCurrentMonth();
        int currentMonthActualValue = Integer.parseInt(currentMonthVal);
        testUtil.assertEquals(currentMonthActualValue, currentMonthExpectedValue, "Current Month");
    }

    @Test(priority = 5, enabled = true)
    public void pastMonthTest() throws Exception {
        String pastMonth = DBUtil.pastMonthGetDBValue();
        System.out.println("--------Past Month--------");
        int pastMonthExpectedValue = Integer.parseInt(pastMonth);
        String pastMonthVal = bookingsPage.getPreviousMonth();
        int pastMonthActualValue = Integer.parseInt(pastMonthVal);
        testUtil.assertEquals(pastMonthActualValue, pastMonthExpectedValue, "Past Month");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
