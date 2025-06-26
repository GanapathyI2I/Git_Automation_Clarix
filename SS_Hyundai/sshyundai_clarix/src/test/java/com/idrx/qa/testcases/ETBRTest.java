package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.ETBRPages;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.TestUtil;
import com.idrx.qa.util.DBUtil;

public class ETBRTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ETBRPages etbrPages;
    TestUtil testUtil;

    public ETBRTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        etbrPages = new ETBRPages();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickSalesTab();
    }

    @Test(priority = 1, enabled = true)
    public void enquiryTest() throws Exception {
        String enquiry = DBUtil.enquiryGetDBValue();
        System.out.println("--------Enquiry--------");
        int enquiryExpectedValue = Integer.parseInt(enquiry);
        String enquiryVal = etbrPages.getEnquiry();
        int enquiryActualValue = Integer.parseInt(enquiryVal);
        testUtil.assertEquals(enquiryActualValue, enquiryExpectedValue, "Enquiry");
    }



    @Test(priority = 2, enabled = true)
    public void testDriveTest() throws Exception {
        String testDrive = DBUtil.testDriveGetDBValue();
        System.out.println("--------Test Drive--------");
        int testDriveExpectedValue = Integer.parseInt(testDrive);
        String testDriveVal = etbrPages.getTestDrive();
        int testDriveActualValue = Integer.parseInt(testDriveVal);
        testUtil.assertEquals(testDriveActualValue, testDriveExpectedValue, "Test Drive");
    }

    @Test(priority = 3, enabled = true)
    public void bookingTest() throws Exception {
        String booking = DBUtil.bookingGetDBValue();
        System.out.println("--------Booking--------");
        int bookingExpectedValue = Integer.parseInt(booking);
        String bookingVal = etbrPages.getBooking();
        int bookingActualValue = Integer.parseInt(bookingVal);
        testUtil.assertEquals(bookingActualValue, bookingExpectedValue, "Booking");
    }

    @Test(priority = 4, enabled = true)
    public void retailTest() throws Exception {
        String retail = DBUtil.retailGetDBValue();
        System.out.println("--------Retail--------");
        int retailExpectedValue = Integer.parseInt(retail);
        String retailVal = etbrPages.getRetail();
        int retailActualValue = Integer.parseInt(retailVal);
        testUtil.assertEquals(retailActualValue, retailExpectedValue, "Retail");
    }

    @Test(priority = 5, enabled = true)
    public void enquiriesToTdE2TTest() throws Exception {
        String enquiriesToTdE2T = DBUtil.enquiriesToTdE2TGetDBValue();
        System.out.println("--------Enquiries To Td E2T--------");
        int enquiriesToTdE2TExpectedValue = Integer.parseInt(enquiriesToTdE2T);
        String enquiriesToTdE2TVal = etbrPages.getEnquiriesToTdE2T();
        int enquiriesToTdE2TActualValue = Integer.parseInt(enquiriesToTdE2TVal);
        testUtil.assertEquals(enquiriesToTdE2TActualValue, enquiriesToTdE2TExpectedValue, "Enquiries To Td E2T");
    }

    @Test(priority = 6, enabled = true)
    public void enquiriesToBookingE2BTest() throws Exception {
        String enquiriesToBookingE2B = DBUtil.enquiriesToBookingE2BGetDBValue();
        System.out.println("--------Enquiries To Booking E2B--------");
        int enquiriesToBookingE2BExpectedValue = Integer.parseInt(enquiriesToBookingE2B);
        String enquiriesToBookingE2BVal = etbrPages.getEnquiriesToBookingE2B();
        int enquiriesToBookingE2BActualValue = Integer.parseInt(enquiriesToBookingE2BVal);
        testUtil.assertEquals(enquiriesToBookingE2BActualValue, enquiriesToBookingE2BExpectedValue, "Enquiries To Booking E2B");
    }

    @Test(priority = 7, enabled = true)
    public void enquiriesToRetailE2RTest() throws Exception {
        String enquiriesToRetailE2R = DBUtil.enquiriesToRetailE2RGetDBValue();
        System.out.println("--------Enquiries To Retail E2R--------");
        int enquiriesToRetailE2RExpectedValue = Integer.parseInt(enquiriesToRetailE2R);
        String enquiriesToRetailE2RVal = etbrPages.getEnquiriesToRetailE2R();
        int enquiriesToRetailE2RActualValue = Integer.parseInt(enquiriesToRetailE2RVal);
        testUtil.assertEquals(enquiriesToRetailE2RActualValue, enquiriesToRetailE2RExpectedValue, "Enquiries To Retail E2R");
    }

    @Test(priority = 8, enabled = true)
    public void bookingToRetailB2RTest() throws Exception {
        String bookingToRetailB2R = DBUtil.bookingToRetailB2RGetDBValue();
        System.out.println("--------Booking To Retail B2R--------");
        int bookingToRetailB2RExpectedValue = Integer.parseInt(bookingToRetailB2R);
        String bookingToRetailB2RVal = etbrPages.getBookingToRetailB2R();
        int bookingToRetailB2RActualValue = Integer.parseInt(bookingToRetailB2RVal);
        testUtil.assertEquals(bookingToRetailB2RActualValue, bookingToRetailB2RExpectedValue, "Booking To Retail B2R");
    }
    
    @Test(priority = 9, enabled = true)
    public void testDriveToBookingT2BTest() throws Exception {
        String testDriveToBookingT2B = DBUtil.testDriveToBookingT2BGetDBValue();
        System.out.println("--------Test Drive To Booking T2B--------");
        int testDriveToBookingT2BExpectedValue = Integer.parseInt(testDriveToBookingT2B);
        String testDriveToBookingT2BVal = etbrPages.getTestDriveToBookingT2B();
        int testDriveToBookingT2BActualValue = Integer.parseInt(testDriveToBookingT2BVal);
        testUtil.assertEquals(testDriveToBookingT2BActualValue, testDriveToBookingT2BExpectedValue, "Test Drive To Booking T2B");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
