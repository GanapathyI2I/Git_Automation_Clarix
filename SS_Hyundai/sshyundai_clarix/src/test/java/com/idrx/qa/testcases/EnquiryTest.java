package com.idrx.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.EnquiriesPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class EnquiryTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    EnquiriesPage enquiriesPage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        enquiriesPage = new EnquiriesPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        Thread.sleep(5000);
        homePage.clickSalesTab();
        enquiriesPage.clickEnquiryButton();
    }

    @Test(priority = 1, enabled = true)
    public void enquiryQtyTest() throws Exception {
        String enquiryQty = DBUtil.enquiryGetDBValue();
        String enquiryVal = enquiriesPage.getEnquiry();
        int enquiryExpectedValue = Integer.parseInt(enquiryQty);
        int enquiryActualValue = Integer.parseInt(enquiryVal);
        testUtil.assertEquals(enquiryActualValue, enquiryExpectedValue, "Enquiry");
    }

}
