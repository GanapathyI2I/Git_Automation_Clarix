package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.BookingsPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.SalesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class BookingsPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    SalesPage salesPage;
    BookingsPage bookingsPage;

    public BookingsPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage();
        homePage = new HomePage();
        salesPage = new SalesPage();
        testUtil = new TestUtil();
        bookingsPage = new BookingsPage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickSalesTab();
        salesPage.clickBookingsTab();
    }

    @Test(priority = 1, enabled=true)
	public void bookingYesterdayTest() throws Exception {
		String vehicleBookedYesterday = DBUtil.vehicleBookedYesterdayTestDBValue();
		System.out.println("--------Bookings/No of Bookings Yesterday--------");
		int vehicleBookedYesterdayExpectedValue = Integer.parseInt(vehicleBookedYesterday);
		String vehicleBookedYesterdayVal = bookingsPage.getYesterdayBooking();
		int vehicleBookedYesterdayActualValue = Integer.parseInt(vehicleBookedYesterdayVal);
		testUtil.assertEquals(vehicleBookedYesterdayActualValue, vehicleBookedYesterdayExpectedValue, "No of  Bookings Yesterday");
	}

    @Test(priority = 2, enabled=true)
    public void bookingThisMonthTest() throws Exception {
        String vehicleBookedThisMonth = DBUtil.bookingsThisMonthTestDBValue();
        System.out.println("--------Bookings/No of Bookings This Month--------");
        int vehicleBookedThisMonthExpectedValue = Integer.parseInt(vehicleBookedThisMonth);
        String vehicleBookedThisMonthVal = bookingsPage.getThisMonthBooking();
        int vehicleBookedThisMonthActualValue = Integer.parseInt(vehicleBookedThisMonthVal);
        testUtil.assertEquals(vehicleBookedThisMonthActualValue, vehicleBookedThisMonthExpectedValue, "No of  Bookings This Month");
    }

    @Test(priority = 3, enabled=true)
    public void pendingBookingsTest() throws Exception {
        String pendingBookings = DBUtil.pendingBookingsTestDBValue();
        System.out.println("--------Bookings/Pending Bookings--------");
        int pendingBookingsExpectedValue = Integer.parseInt(pendingBookings);
        String pendingBookingsVal = bookingsPage.getPendingBookings();
        int pendingBookingsActualValue = Integer.parseInt(pendingBookingsVal);
        testUtil.assertEquals(pendingBookingsActualValue, pendingBookingsExpectedValue, "No of  Pending Bookings");
    }

    @Test(priority = 4, enabled=true)
    public void previousMonthTest() throws Exception {
        String previousMonth = DBUtil.previousMonthTestDBValue();
        System.out.println("--------Bookings/Previous Month--------");
        int previousMonthExpectedValue = Integer.parseInt(previousMonth);
        String previousMonthVal = bookingsPage.getPreviousMonth();
        int previousMonthActualValue = Integer.parseInt(previousMonthVal);
        testUtil.assertEquals(previousMonthActualValue, previousMonthExpectedValue, "No of  Previous Month");
    }

    @Test(priority = 5, enabled=true)
    public void currentMonthTest() throws Exception {
        String currentMonth = DBUtil.thisMonthTestDBValue();
        System.out.println("--------Bookings/Current Month--------");
        int currentMonthExpectedValue = Integer.parseInt(currentMonth);
        String currentMonthVal = bookingsPage.getCurrentMonth();
        int currentMonthActualValue = Integer.parseInt(currentMonthVal);
        testUtil.assertEquals(currentMonthActualValue, currentMonthExpectedValue, "No of  Current Month");
    }

    @Test(priority = 6, enabled=true)
    public void salesmanWisePendingBookingsTest() throws Exception {
        String salesmanWisePendingBookings = DBUtil.salesmanWisePendingBookingsTestDBValue();
        System.out.println("--------Bookings/Salesman Wise Pending Bookings--------");
        int salesmanWisePendingBookingsExpectedValue = Integer.parseInt(salesmanWisePendingBookings);
        String salesmanWisePendingBookingsVal = bookingsPage.getSalesmanWisePendingBookings();
        int salesmanWisePendingBookingsActualValue = Integer.parseInt(salesmanWisePendingBookingsVal);
        testUtil.assertEquals(salesmanWisePendingBookingsActualValue, salesmanWisePendingBookingsExpectedValue, "No of  Salesman Wise Pending Bookings");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
