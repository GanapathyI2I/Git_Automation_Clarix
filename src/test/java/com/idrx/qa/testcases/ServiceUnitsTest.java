package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.ServicePage;
import com.idrx.qa.pages.ServiceRevenuePage;
import com.idrx.qa.pages.ServiceUnitsPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ServiceUnitsTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ServiceRevenuePage serviceRevenuePage;
    ServicePage servicePage;
    ServiceUnitsPage serviceUnitsPage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        servicePage = new ServicePage();
        serviceUnitsPage = new ServiceUnitsPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickServiceTab();
    }

    @Test(priority = 1, enabled = true)
    public void noOfVehicleInflowTest() throws Exception {
        String noOfVehicleInflow = DBUtil.noOfVehicleInflowGetDbValue();
        System.out.println("--------No of Vehicle Inflow--------");
        int noOfVehicleInflowExpectedValue = Integer.parseInt(noOfVehicleInflow);
        System.out.println("Expected Value: " + noOfVehicleInflowExpectedValue);
        String noOfVehicleInflowVal = serviceUnitsPage.getNoOfVehicleInflow();
        int noOfVehicleInflowActualValue = Integer.parseInt(noOfVehicleInflowVal);
        System.out.println("Actual Value: " + noOfVehicleInflowActualValue);
        testUtil.assertEquals(noOfVehicleInflowActualValue, noOfVehicleInflowExpectedValue, "No of Vehicle Inflow");
    }

    @Test(priority = 2, enabled = true)
    public void noOfVehicleOutflowTest() throws Exception {
        String noOfVehicleOutflow = DBUtil.noOfVehicleOutflowGetDbValue();
        System.out.println("--------No of Vehicle Outflow--------");
        int noOfVehicleOutflowExpectedValue = Integer.parseInt(noOfVehicleOutflow);
        System.out.println("Expected Value: " + noOfVehicleOutflowExpectedValue);
        String noOfVehicleOutflowVal = serviceUnitsPage.getNoOfVehicleOutflow();
        int noOfVehicleOutflowActualValue = Integer.parseInt(noOfVehicleOutflowVal);
        System.out.println("Actual Value: " + noOfVehicleOutflowActualValue);
        testUtil.assertEquals(noOfVehicleOutflowActualValue, noOfVehicleOutflowExpectedValue, "No of Vehicle Outflow");
    }

    @Test(priority = 3, enabled = true)
    public void noOfVehicleInflowYesterdayTest() throws Exception {
        String noOfVehicleInflowYesterday = DBUtil.noOfVehicleInflowYesterdayGetDbValue();
        System.out.println("--------No Of Vehicle Inflow Yesterday--------");
        int noOfVehicleInflowYesterdayExpectedValue = Integer.parseInt(noOfVehicleInflowYesterday);
        System.out.println("Expected Value: " + noOfVehicleInflowYesterdayExpectedValue);
        String noOfVehicleInflowYesterdayVal = serviceUnitsPage.getNoOfVehicleInflowYesterday();
        int noOfVehicleInflowYesterdayActualValue = Integer.parseInt(noOfVehicleInflowYesterdayVal);
        System.out.println("Actual Value: " + noOfVehicleInflowYesterdayActualValue);
        testUtil.assertEquals(noOfVehicleInflowYesterdayActualValue, noOfVehicleInflowYesterdayExpectedValue,
                "No Of Vehicle Inflow Yesterday");
    }

    @Test(priority = 4, enabled = true)
    public void noOfVehicleOutflowYesterdayTest() throws Exception {
        String noOfVehicleOutflowYesterday = DBUtil.noOfVehicleOutflowYesterdayGetDbValue();
        System.out.println("--------No Of Vehicle Outflow Yesterday--------");
        int noOfVehicleOutflowYesterdayExpectedValue = Integer.parseInt(noOfVehicleOutflowYesterday);
        System.out.println("Expected Value: " + noOfVehicleOutflowYesterdayExpectedValue);
        String noOfVehicleOutflowYesterdayVal = serviceUnitsPage.getNoOfVehicleOutflowYesterday();
        int noOfVehicleOutflowYesterdayActualValue = Integer.parseInt(noOfVehicleOutflowYesterdayVal);
        System.out.println("Actual Value: " + noOfVehicleOutflowYesterdayActualValue);
        testUtil.assertEquals(noOfVehicleOutflowYesterdayActualValue, noOfVehicleOutflowYesterdayExpectedValue,
                "No Of Vehicle Outflow Yesterday");
    }

    @Test(priority = 5, enabled = true)
    public void noOfVehicleOutflowCurrentMonthTest() throws Exception {
        String noOfVehicleOutflowCurrentMonth = DBUtil.outflowCurrentMonthGetDbValue();
        System.out.println("--------No Of Vehicle Outflow Current Month--------");
        int noOfVehicleOutflowCurrentMonthExpectedValue = Integer.parseInt(noOfVehicleOutflowCurrentMonth);
        System.out.println("Expected Value: " + noOfVehicleOutflowCurrentMonthExpectedValue);
        String noOfVehicleOutflowCurrentMonthVal = serviceUnitsPage.getNoOfVehicleOutflowCurrentMonth();
        int noOfVehicleOutflowCurrentMonthActualValue = Integer.parseInt(noOfVehicleOutflowCurrentMonthVal);
        System.out.println("Actual Value: " + noOfVehicleOutflowCurrentMonthActualValue);
        testUtil.assertEquals(noOfVehicleOutflowCurrentMonthActualValue, noOfVehicleOutflowCurrentMonthExpectedValue,
                "No Of Vehicle Outflow Current Month");
    }

    @Test(priority = 6, enabled = true)
    public void noOfVehicleOutflowLastMonthTest() throws Exception {
        String noOfVehicleOutflowLastMonth = DBUtil.outflowLastMonthGetDbValue();
        System.out.println("--------No Of Vehicle Outflow Last Month--------");
        int noOfVehicleOutflowLastMonthExpectedValue = Integer.parseInt(noOfVehicleOutflowLastMonth);
        System.out.println("Expected Value: " + noOfVehicleOutflowLastMonthExpectedValue);
        String noOfVehicleOutflowLastMonthVal = serviceUnitsPage.getNoOfVehicleOutflowLastMonth();
        int noOfVehicleOutflowLastMonthActualValue = Integer.parseInt(noOfVehicleOutflowLastMonthVal);
        System.out.println("Actual Value: " + noOfVehicleOutflowLastMonthActualValue);
        testUtil.assertEquals(noOfVehicleOutflowLastMonthActualValue, noOfVehicleOutflowLastMonthExpectedValue,
                "No Of Vehicle Outflow Last Month");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
