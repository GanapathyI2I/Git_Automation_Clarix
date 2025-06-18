package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.Billing_PurchasePages;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.NewOrderVehiclesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class NewOrderVehiclesPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    Billing_PurchasePages billing_PurchasePages;
    NewOrderVehiclesPage newOrderVehiclesPage;

    public NewOrderVehiclesPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        testUtil = new TestUtil();
        billing_PurchasePages = new Billing_PurchasePages();
        newOrderVehiclesPage = new NewOrderVehiclesPage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickBillingPurchaseTile();
        billing_PurchasePages.clickNewOrderVehiclesTile();

    }
    
    @Test(priority = 1, enabled=false)
    public void vehicleNameFirstRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Vehicle Name First Row--------");
        String vehicleNameExpected = DBUtil.vehicleNameFirstRowDBValue();
        System.out.println("Expected Value: " + vehicleNameExpected);
        String vehicleNameActual = newOrderVehiclesPage.getVehicleNameFirstRow();
        System.out.println("Actual Value: " + vehicleNameActual);
        testUtil.assertEquals(vehicleNameActual, vehicleNameExpected, "Vehicle Name First Row");
    }

    @Test(priority = 2, enabled=false)
    public void vehicleNameSecondRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Vehicle Name Second Row--------");
        String vehicleNameExpected = DBUtil.averageMonthlySalesQtyFirstRowDBValue();
        System.out.println("Expected Value: " + vehicleNameExpected);
        String vehicleNameActual = newOrderVehiclesPage.getVehicleNameSecondRow();
        System.out.println("Actual Value: " + vehicleNameActual);
        testUtil.assertEquals(vehicleNameActual, vehicleNameExpected, "Vehicle Name Second Row");
    }

    @Test(priority = 3, enabled=false)
    public void averageMonthlySalesQtyFirstRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Average Monthly Sales Qty First Row--------");
        String averageMonthlySalesQtyExpected = DBUtil.averageMonthlySalesQtyFirstRowDBValue();
        System.out.println("Expected Value: " + averageMonthlySalesQtyExpected);
        String averageMonthlySalesQtyActual = newOrderVehiclesPage.getAverageMonthlySalesQty();
        System.out.println("Actual Value: " + averageMonthlySalesQtyActual);
        testUtil.assertEquals(averageMonthlySalesQtyActual, averageMonthlySalesQtyExpected, "Average Monthly Sales Qty First Row");
    }

    @Test(priority = 4, enabled=false)
    public void currentStockFirstRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Current Stock First Row--------");
        String currentStockExpected = DBUtil.currentStockFirstRowDBValue();
        System.out.println("Expected Value: " + currentStockExpected);
        String currentStockActual = newOrderVehiclesPage.getCurrentStock();
        System.out.println("Actual Value: " + currentStockActual);
        testUtil.assertEquals(currentStockActual, currentStockExpected, "Current Stock First Row");
    }

    @Test(priority = 5, enabled=false)
    public void averageMonthlySalesQtySecondRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Average Monthly Sales Qty Second Row--------");
        String averageMonthlySalesQtyExpected = DBUtil.averageMonthlySalesQtySecondRowDBValue();
        System.out.println("Expected Value: " + averageMonthlySalesQtyExpected);
        String averageMonthlySalesQtyActual = newOrderVehiclesPage.getAverageMonthlySalesQtySecondRow();
        System.out.println("Actual Value: " + averageMonthlySalesQtyActual);
        testUtil.assertEquals(averageMonthlySalesQtyActual, averageMonthlySalesQtyExpected, "Average Monthly Sales Qty Second Row");
    }

    @Test(priority = 6, enabled=true)
    public void currentStockSecondRowTest() throws Exception {
        System.out.println("---------New Order Vehicles Page/ Current Stock Second Row--------");
        String currentStockExpected = DBUtil.currentStockSecondRowDBValue();
        System.out.println("Expected Value: " + currentStockExpected);
        String currentStockActual = newOrderVehiclesPage.getCurrentStockSecondRow();
        System.out.println("Actual Value: " + currentStockActual);
        testUtil.assertEquals(currentStockActual, currentStockExpected, "Current Stock Second Row");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}