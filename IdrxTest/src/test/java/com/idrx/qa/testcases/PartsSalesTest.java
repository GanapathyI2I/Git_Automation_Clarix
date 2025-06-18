package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.PartsSalesPage;
import com.idrx.qa.pages.ServicePage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class PartsSalesTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    PartsSalesPage partsSalesPage;
    ServicePage servicePage;
    TestUtil testUtil;

    public PartsSalesTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
      //  driver.get(prop.getProperty("url"));
        loginPage = new LoginPage();
        homePage = new HomePage();
        partsSalesPage = new PartsSalesPage();
        servicePage = new ServicePage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickServiceTile("Parts Sales");
        servicePage.clickPartsSalesTile("Parts Sales");
    }

    @Test(priority = 1, enabled=true)
    public void noofQtyTest() throws Exception {
        String noofQty = DBUtil.noofQtyTestDBValue();
        System.out.println("--------Parts Sales/No of Qty--------");
        int noofQtyExpectedValue = Integer.parseInt(noofQty);
        String noofQtyVal = partsSalesPage.getNoofQty();
        int noofQtyActualValue = Integer.parseInt(noofQtyVal);
        testUtil.assertEquals(noofQtyActualValue, noofQtyExpectedValue, "No of Qty");
    }

    @Test(priority = 2, enabled=true)
    public void partsSalesTest() throws Exception {
        String partsSales = DBUtil.partsSalesTestDBValue();
        System.out.println("--------Parts Sales/Parts Sales--------");
        String partsSalesExpectedValue = "₹ " + partsSales;
        System.out.println("Expected Value: " + partsSalesExpectedValue);
        String partsSalesActualValue = partsSalesPage.getPartsSales();
        System.out.println("Actual Value: " + partsSalesActualValue);
        testUtil.assertEquals(partsSalesActualValue, partsSalesExpectedValue, "Parts Sales");
    }

    @Test(priority = 3, enabled=true)
    public void thisMonthTest() throws Exception {
        String thisMonth = DBUtil.thisMonthTestDBValue();
        System.out.println("--------Parts Sales/This Month--------");
        String thisMonthExpectedValue = "₹ " + thisMonth;
        System.out.println("Expected Value: " + thisMonthExpectedValue);
        String thisMonthActualValue = partsSalesPage.getCurrentMonth();
        System.out.println("Actual Value: " + thisMonthActualValue);
        testUtil.assertEquals(thisMonthActualValue, thisMonthExpectedValue, "This Month");
    }

    @Test(priority = 4, enabled=true)
    public void previousMonthTest() throws Exception {
        String previousMonth = DBUtil.previousMonthTestDBValue();
        System.out.println("--------Parts Sales/Previous Month--------");
        String previousMonthExpectedValue = "₹ " + previousMonth;
        System.out.println("Expected Value: " + previousMonthExpectedValue);
        String previousMonthActualValue = partsSalesPage.getPreviousMonth();
        System.out.println("Actual Value: " + previousMonthActualValue);
        testUtil.assertEquals(previousMonthActualValue, previousMonthExpectedValue, "Previous Month");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}