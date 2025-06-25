package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.PartsStocksPage;
import com.idrx.qa.pages.VehicleStocksPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class PartsStocksTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    PartsStocksPage partsStocksPage;
    VehicleStocksPage vehicleStocksPage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        vehicleStocksPage = new VehicleStocksPage();
        partsStocksPage = new PartsStocksPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        Thread.sleep(5000);
        homePage.clickStocksTab();
        vehicleStocksPage.clickPartsStocksTab();
    }

    @Test(priority = 1, enabled = true)
    public void getPartsQtyTest() throws Exception {
        String partsQty = DBUtil.partsQtyGetDbValue();
        System.out.println("--------Parts Qty--------");
        int partsQtyExpectedValue = Integer.parseInt(partsQty);
        System.out.println("Expected Value: " + partsQtyExpectedValue);
        String partsQtyVal = partsStocksPage.getpartsQty();
        int partsQtyActualValue = Integer.parseInt(partsQtyVal);
        System.out.println("Actual Value: " + partsQtyActualValue);
        testUtil.assertEquals(partsQtyActualValue, partsQtyExpectedValue, "Parts Qty");
    }

    @Test(priority = 2, enabled = true)
    public void getTotalStockValueTest() throws Exception {
        String totalStockValueExpectedValue = "₹ " + DBUtil.totalStockValueGetDBValue();
        System.out.println("--------Total Stock Value--------");
        System.out.println("Expected Value: " + totalStockValueExpectedValue);
        String totalStockValueActualValue = partsStocksPage.getTotalStockValue();
        System.out.println("Actual Value: " + totalStockValueActualValue);
        testUtil.assertEquals(totalStockValueActualValue, totalStockValueExpectedValue, "Total Stock Value");
    }

    @Test(priority = 3, enabled = true)
    public void getAccessoriesAndPartsValueTest() throws Exception {
        String accessoriesAndPartsValueExpectedValue = "₹ " + DBUtil.accessoriesAndPartsValueGetDBValue();
        System.out.println("--------Accessories And Parts Value--------");
        System.out.println("Expected Value: " + accessoriesAndPartsValueExpectedValue);
        String accessoriesAndPartsValueActualValue = partsStocksPage.getAccessoriesAndPartsValue();
        System.out.println("Actual Value: " + accessoriesAndPartsValueActualValue);
        testUtil.assertEquals(accessoriesAndPartsValueActualValue, accessoriesAndPartsValueExpectedValue,
                "Accessories And Parts Value");
    }

    // @Test(priority = 4, enabled = true)
    // public void getExcessStockQtyTest() throws Exception {
    // String excessStockQty = DBUtil.excessStockQtyGetDBValue();
    // System.out.println("--------Excess Stock Qty--------");
    // int excessStockQtyExpectedValue = Integer.parseInt(excessStockQty);
    // System.out.println("Expected Value: " + excessStockQtyExpectedValue);
    // String excessStockQtyVal = partsStocksPage.getExcessStockQty();
    // int excessStockQtyActualValue = Integer.parseInt(excessStockQtyVal);
    // System.out.println("Actual Value: " + excessStockQtyActualValue);
    // testUtil.assertEquals(excessStockQtyActualValue, excessStockQtyExpectedValue,
    // "Excess Stock Qty");
    // }

    // @Test(priority = 5, enabled = true)
    // public void getExcessStockValueTest() throws Exception {
    // String excessStockValueExpectedValue = DBUtil.excessStockValueGetDBValue();
    // System.out.println("--------Excess Stock Value--------");
    // System.out.println("Expected Value: " + excessStockValueExpectedValue);
    // String excessStockValueActualValue = partsStocksPage.getExcessStockValue();
    // System.out.println("Actual Value: " + excessStockValueActualValue);
    // testUtil.assertEquals(excessStockValueActualValue,
    // excessStockValueExpectedValue, "Excess Stock Value");
    // }

    @Test(priority = 6, enabled = true)
    public void getCurrentMonthPartsQtyTrendTest() throws Exception {
        String currentMonthPartsQtyTrend = DBUtil.currentMonthPartsQtyTrendGetDBValue();
        System.out.println("--------Current Month Parts Qty Trend--------");
        int currentMonthPartsQtyTrendExpectedValue = Integer.parseInt(currentMonthPartsQtyTrend);
        System.out.println("Expected Value: " + currentMonthPartsQtyTrendExpectedValue);
        String currentMonthPartsQtyTrendVal = partsStocksPage.getCurrentMonthPartsQtyTrend();
        int currentMonthPartsQtyTrendActualValue = Integer.parseInt(currentMonthPartsQtyTrendVal);
        System.out.println("Actual Value: " + currentMonthPartsQtyTrendActualValue);
        testUtil.assertEquals(currentMonthPartsQtyTrendActualValue, currentMonthPartsQtyTrendExpectedValue,
                "Current Month Parts Qty Trend");
    }

    @Test(priority = 7, enabled = true)
    public void getLastMonthPartsQtyTrendTest() throws Exception {
        String lastMonthPartsQtyTrend = DBUtil.lastMonthPartsQtyTrendGetDBValue();
        System.out.println("--------Last Month Parts Qty Trend--------");
        int lastMonthPartsQtyTrendExpectedValue = Integer.parseInt(lastMonthPartsQtyTrend);
        System.out.println("Expected Value: " + lastMonthPartsQtyTrendExpectedValue);
        String lastMonthPartsQtyTrendVal = partsStocksPage.getLastMonthPartsQtyTrend();
        int lastMonthPartsQtyTrendActualValue = Integer.parseInt(lastMonthPartsQtyTrendVal);
        System.out.println("Actual Value: " + lastMonthPartsQtyTrendActualValue);
        testUtil.assertEquals(lastMonthPartsQtyTrendActualValue, lastMonthPartsQtyTrendExpectedValue,
                "Last Month Parts Qty Trend");
    }

    @Test(priority = 8, enabled = true)
    public void getCurrentMonthPartsValueTrendTest() throws Exception {
        String currentMonthPartsValueTrendExpectedValue = "₹ " + DBUtil.currentMonthPartsValueTrendGetDBValue();
        System.out.println("--------Current Month Parts Value Trend--------");
        System.out.println("Expected Value: " + currentMonthPartsValueTrendExpectedValue);
        String currentMonthPartsValueTrendActualValue = partsStocksPage.getCurrentMonthPartsValueTrend();
        System.out.println("Actual Value: " + currentMonthPartsValueTrendActualValue);
        testUtil.assertEquals(currentMonthPartsValueTrendActualValue, currentMonthPartsValueTrendExpectedValue,
                "Current Month Parts Value Trend");
    }

    @Test(priority = 9, enabled = true)
    public void getLastMonthPartsValueTrendTest() throws Exception {
        String lastMonthPartsValueTrendExpectedValue = "₹ " + DBUtil.lastMonthPartsValueTrendGetDBValue();
        System.out.println("--------Last Month Parts Value Trend--------");
        System.out.println("Expected Value: " + lastMonthPartsValueTrendExpectedValue);
        String lastMonthPartsValueTrendActualValue = partsStocksPage.getLastMonthPartsValueTrend();
        System.out.println("Actual Value: " + lastMonthPartsValueTrendActualValue);
        testUtil.assertEquals(lastMonthPartsValueTrendActualValue, lastMonthPartsValueTrendExpectedValue,
                "Last Month Parts Value Trend");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
