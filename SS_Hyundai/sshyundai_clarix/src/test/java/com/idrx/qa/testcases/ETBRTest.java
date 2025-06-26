package com.idrx.qa.testcases;

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
        System.out.println("Enquiry: " + enquiry);
        String enquiryVal = etbrPages.getEnquiry();
        int enquiryExpectedValue = Integer.parseInt(enquiry);
        int enquiryActualValue = Integer.parseInt(enquiryVal);
        testUtil.assertEquals(enquiryActualValue, enquiryExpectedValue, "Enquiry");
    }

    @Test(priority = 2, enabled = true)
    public void testDriveTest() throws Exception {
        String testDrive = DBUtil.testDriveGetDBValue();
        System.out.println("Test Drive: " + testDrive);
        String testDriveVal = etbrPages.getTestDrive();
        int testDriveExpectedValue = Integer.parseInt(testDrive);
        int testDriveActualValue = Integer.parseInt(testDriveVal);
        testUtil.assertEquals(testDriveActualValue, testDriveExpectedValue, "Test Drive");
    }

}
