package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.VehicleStocksPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class VehicleStocksTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    VehicleStocksPage vehicleStocksPage;

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        vehicleStocksPage = new VehicleStocksPage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickStocksTab();
    }

    @Test(priority = 1, enabled = true)
    public void vehicleQtyTest() throws Exception {
        String vehicleQty = DBUtil.vehicleQtyGetDbValue();
        System.out.println("--------Vehicle Qty--------");
        int vehicleQtyExpectedValue = Integer.parseInt(vehicleQty);
        System.out.println("Expected Value: " + vehicleQtyExpectedValue);
        String vehicleQtyVal = vehicleStocksPage.getVehiclQty();
        int vehicleQtyActualValue = Integer.parseInt(vehicleQtyVal);
        System.out.println("Actual Value: " + vehicleQtyActualValue);
        testUtil.assertEquals(vehicleQtyActualValue, vehicleQtyExpectedValue, "Vehicle Qty");
    }

    @Test(priority = 2, enabled = true)
    public void stockValueTest() throws Exception {
        String stockValueExpectedValue = DBUtil.stockValueGetDBValue();
        System.out.println("--------Stock Value--------");
        System.out.println("Expected Value: " + stockValueExpectedValue);
        String stockValueActualValue = vehicleStocksPage.getStockValue();
        System.out.println("Actual Value: " + stockValueActualValue);
        testUtil.assertEquals(stockValueActualValue, stockValueExpectedValue, "Stock Value");
    }

    @Test(priority = 3, enabled = true)
    public void accessoriesAndPartsValueTest() throws Exception {
        String accessoriesAndPartsValueExpectedValue = DBUtil.accessoriesAndPartsGetDBValue();
        System.out.println("--------Accessories And Parts Value--------");
        System.out.println("Expected Value: " + accessoriesAndPartsValueExpectedValue);
        String accessoriesAndPartsValueActualValue = vehicleStocksPage.getaccessoriesAndPartsValue();
        System.out.println("Actual Value: " + accessoriesAndPartsValueActualValue);
        testUtil.assertEquals(accessoriesAndPartsValueActualValue, accessoriesAndPartsValueExpectedValue,
                "Accessories And Parts Value");
    }

    @Test(priority = 4, enabled = true)
    public void greaterThan30DaysStocksUnitsTest() throws Exception {
        String greaterThan30DaysStocksUnits = DBUtil.greaterThan30DaysStocksUnitsGetDbValue();
        System.out.println("--------Greater Than 30 Days Stocks Units--------");
        int greaterThan30DaysStocksUnitsExpectedValue = Integer.parseInt(greaterThan30DaysStocksUnits);
        System.out.println("Expected Value: " + greaterThan30DaysStocksUnitsExpectedValue);
        String greaterThan30DaysStocksUnitsVal = vehicleStocksPage.getGreaterThan30DaysStocksUnits();
        int greaterThan30DaysStocksUnitsActualValue = Integer.parseInt(greaterThan30DaysStocksUnitsVal);
        System.out.println("Actual Value: " + greaterThan30DaysStocksUnitsActualValue);
        testUtil.assertEquals(greaterThan30DaysStocksUnitsActualValue, greaterThan30DaysStocksUnitsExpectedValue,
                "Greater Than 30 Days Stocks Units");
    }

    @Test(priority = 5, enabled = true)
    public void greaterThan60DaysStocksUnitsTest() throws Exception {
        String greaterThan60DaysStocksUnits = DBUtil.greaterThan60DaysStocksUnitsGetDbValue();
        System.out.println("--------Greater Than 60 Days Stocks Units--------");
        int greaterThan60DaysStocksUnitsExpectedValue = Integer.parseInt(greaterThan60DaysStocksUnits);
        System.out.println("Expected Value: " + greaterThan60DaysStocksUnitsExpectedValue);
        String greaterThan60DaysStocksUnitsVal = vehicleStocksPage.getGreaterThan60DaysStocksUnits();
        int greaterThan60DaysStocksUnitsActualValue = Integer.parseInt(greaterThan60DaysStocksUnitsVal);
        System.out.println("Actual Value: " + greaterThan60DaysStocksUnitsActualValue);
        testUtil.assertEquals(greaterThan60DaysStocksUnitsActualValue, greaterThan60DaysStocksUnitsExpectedValue,
                "Greater Than 60 Days Stocks Units");
    }

    @Test(priority = 6, enabled = true)
    public void greaterThan30DaysStockValueTest() throws Exception {
        String greaterThan30DaysStocksValueExpectedValue = DBUtil.greaterThan30DaysStocksUnitsGetDbValue();
        System.out.println("--------Greater Than 30 Days Stocks Value--------");
        System.out.println("Expected Value: " + greaterThan30DaysStocksValueExpectedValue);
        String greaterThan30DaysStocksValueActualValue = vehicleStocksPage.getGreaterThan30DaysStocksValue();
        System.out.println("Actual Value: " + greaterThan30DaysStocksValueActualValue);
        testUtil.assertEquals(greaterThan30DaysStocksValueActualValue, greaterThan30DaysStocksValueExpectedValue,
                "Greater Than 30 Days Stocks Value");
    }

    @Test(priority = 7, enabled = true)
    public void greaterThan60DaysStockValueTest() throws Exception {
        String greaterThan60DaysStocksValueExpectedValue = DBUtil.greaterThan60DaysStocksUnitsGetDbValue();
        System.out.println("--------Greater Than 60 Days Stocks Value--------");
        System.out.println("Expected Value: " + greaterThan60DaysStocksValueExpectedValue);
        String greaterThan60DaysStocksValueActualValue = vehicleStocksPage.getGreaterThan60DaysStocksValue();
        System.out.println("Actual Value: " + greaterThan60DaysStocksValueActualValue);
        testUtil.assertEquals(greaterThan60DaysStocksValueActualValue, greaterThan60DaysStocksValueExpectedValue,
                "Greater Than 60 Days Stocks Value");
    }

    @Test(priority = 8, enabled = true)
    public void currentMonthStockUnitsTest() throws Exception {
        String currentMonthStockUnits = DBUtil.currentMonthStocksUnitsGetDbValue();
        System.out.println("--------Current Month Stock Units--------");
        int currentMonthStockUnitsExpectedValue = Integer.parseInt(currentMonthStockUnits);
        System.out.println("Expected Value: " + currentMonthStockUnitsExpectedValue);
        String currentMonthStockUnitsVal = vehicleStocksPage.getCurrentMonthStock();
        int currentMonthStockUnitsActualValue = Integer.parseInt(currentMonthStockUnitsVal);
        System.out.println("Actual Value: " + currentMonthStockUnitsActualValue);
        testUtil.assertEquals(currentMonthStockUnitsActualValue, currentMonthStockUnitsExpectedValue,
                "Current Month Stock Units");
    }

    @Test(priority = 9, enabled = true)
    public void lastMonthStockUnitsTest() throws Exception {
        String lastMonthStockUnits = DBUtil.lastMonthStocksUnitsGetDbValue();
        System.out.println("--------Last Month Stock Units--------");
        int lastMonthStockUnitsExpectedValue = Integer.parseInt(lastMonthStockUnits);
        System.out.println("Expected Value: " + lastMonthStockUnitsExpectedValue);
        String lastMonthStockUnitsVal = vehicleStocksPage.getLastMonthStock();
        int lastMonthStockUnitsActualValue = Integer.parseInt(lastMonthStockUnitsVal);
        System.out.println("Actual Value: " + lastMonthStockUnitsActualValue);
        testUtil.assertEquals(lastMonthStockUnitsActualValue, lastMonthStockUnitsExpectedValue,
                "Last Month Stock Units");
    }

    @Test(priority = 10, enabled = true)
    public void currentMonthStockValueTest() throws Exception {
        vehicleStocksPage.clickValueButton();
        String currentMonthStockValueExpectedValue = DBUtil.currentMonthStockValueGetDBValue();
        System.out.println("--------Current Month Stock Value--------");
        System.out.println("Expected Value: " + currentMonthStockValueExpectedValue);
        String currentMonthStockValueActualValue = vehicleStocksPage.getCurrentMonthStock();
        System.out.println("Actual Value: " + currentMonthStockValueActualValue);
        testUtil.assertEquals(currentMonthStockValueActualValue, currentMonthStockValueExpectedValue,
                "Current Month Stock Value");
    }

    @Test(priority = 11, enabled = true)
    public void lastMonthStockValueTest() throws Exception {
        String lastMonthStockValueExpectedValue = DBUtil.lastMonthStockValueGetDBValue();
        System.out.println("--------Last Month Stock Value--------");
        System.out.println("Expected Value: " + lastMonthStockValueExpectedValue);
        String lastMonthStockValueActualValue = vehicleStocksPage.getLastMonthStock();
        System.out.println("Actual Value: " + lastMonthStockValueActualValue);
        testUtil.assertEquals(lastMonthStockValueActualValue, lastMonthStockValueExpectedValue,
                "Last Month Stock Value");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
