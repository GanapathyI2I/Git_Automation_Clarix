package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.Billing_PurchasePages;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class Billing_PurchasePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    Billing_PurchasePages billing_PurchasePages;

    public Billing_PurchasePageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        testUtil = new TestUtil();
        billing_PurchasePages = new Billing_PurchasePages();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(5000);
        TestUtil.switchToFrame();
        homePage.clickBillingPurchaseTile();

    }

    @Test(priority = 1, enabled=true)
    public void vehicleQtyTest() throws Exception {
        String vehicleQty = DBUtil.vehicleQtyDBValue();
        System.out.println("--------Billing & Purchase/ Vehicle Qty-----------");
        int vehicleQtyExpectedValue = Integer.parseInt(vehicleQty);
        String vehicleQtyVal = billing_PurchasePages.getVehicleQty();
        int vehicleQtyActualValue = Integer.parseInt(vehicleQtyVal);
        testUtil.assertEquals(vehicleQtyActualValue, vehicleQtyExpectedValue, "No of Vehicle Qty");
    }

    @Test(priority = 2, enabled=true)
    public void totalPurchaseValueTest() throws Exception {
        String totalPurchaseValue = DBUtil.totalPurchaseValueDBValue();
        System.out.println("---------Billing & Purchase/ Total Purchase Value---------- ");
        String totalPurchaseValueExpected = "₹ " + totalPurchaseValue;
        System.out.println("Expected Value: " + totalPurchaseValueExpected);
        String totalPurchaseValueActual = billing_PurchasePages.getTotalPurchaseValue();
        System.out.println("Actual Value: " + totalPurchaseValueActual);
        testUtil.assertEquals(totalPurchaseValueActual, totalPurchaseValueExpected, "Total Purchase Value");
    }

    @Test(priority = 3, enabled= true)
    public void unitsCurrentMonthTest() throws Exception {
        String unitsCurrentMonth = DBUtil.unitsCurrentMonthDBValue();
        System.out.println("--------------Billing & Purchase/ Units Current month-------------");
        int unitsCurrentMonthExpected = Integer.parseInt(unitsCurrentMonth);
        String unitsCurrentMonthVal = billing_PurchasePages.getUnitsCurrentMonth();
        int unitsCurrentMonthActual = Integer.parseInt(unitsCurrentMonthVal);
        testUtil.assertEquals(unitsCurrentMonthActual, unitsCurrentMonthExpected, "Units Current Month");
    }

    @Test(priority = 4, enabled= true)
    public void unitsPastMonthTest() throws Exception {
        String unitsPastMonth = DBUtil.unitsPastMonthDBValue();
        System.out.println("--------Billing & Purchase/ Units Last month----------");
        int unitsLastMonthExpected = Integer.parseInt(unitsPastMonth);
        String unitsLastMonthVal = billing_PurchasePages.getUnitsPastMonth();
        int unitsPastMonthActual = Integer.parseInt(unitsLastMonthVal);
        testUtil.assertEquals(unitsPastMonthActual, unitsLastMonthExpected, "Units Past Month");
    }

    @Test(priority = 5, enabled= true)
    public void totalValueCurrentMonthTest() throws Exception {
        billing_PurchasePages.clickTotalValue();
        Thread.sleep(5000);
        String totalValueCurrentMonth = DBUtil.totalValueCurrentMonthDBValue();
        System.out.println("--------------Billing & Purchase/ Total Value Current Month-------------");
        String totalValueCurrentMonthExpected = "₹ " + totalValueCurrentMonth;
        System.out.println("Expected Value: " + totalValueCurrentMonthExpected);
        String totalValueCurrentMonthActual = billing_PurchasePages.getTotalValueCurrentMonth();
        System.out.println("Actual Value: " + totalValueCurrentMonthActual);
        testUtil.assertEquals(totalValueCurrentMonthActual, totalValueCurrentMonthExpected, "Total Value Current Month");
    }


    @Test(priority = 6, enabled= true)
    public void totalValuePastMonthTest() throws Exception {
        billing_PurchasePages.clickTotalValue();
        Thread.sleep(5000);
        String totalValuePastMonth = DBUtil.totalValuePastMonthDBValue();
        System.out.println("--------------Billing & Purchase/ Total Value Past Month-------------");
        String totalValuePastMonthExpected = "₹ " + totalValuePastMonth;
        System.out.println("Expected Value: " + totalValuePastMonthExpected);
        String totalValuePastMonthActual = billing_PurchasePages.getTotalValuePastMonth();
        System.out.println("Actual Value: " + totalValuePastMonthActual);
        testUtil.assertEquals(totalValuePastMonthActual, totalValuePastMonthExpected, "Total Value Past Month");
    }

    @Test(priority = 7, enabled= true)
    public void topVehicleQtyTotalTest() throws Exception {
        String topVehicleQtyTotal = DBUtil.topVehicleQtyTotalDBValue();
        System.out.println("--------------Billing & Purchase/ Top Vehicle Qty Total-------------");
        int topVehicleQtyTotalExpected = Integer.parseInt(topVehicleQtyTotal);
        String topVehicleQtyTotalVal = billing_PurchasePages.getTopVehicleQtyTotal();
        int topVehicleQtyTotalActual = Integer.parseInt(topVehicleQtyTotalVal);
        testUtil.assertEquals(topVehicleQtyTotalActual, topVehicleQtyTotalExpected, "Top Vehicle Qty Total");
    }

    @Test(priority = 8, enabled= true)
    public void topVehiclePurchaseValueTotalTest() throws Exception {
        String topVehiclePurchaseValueTotal = DBUtil.topVehiclePurchaseValueTotalDBValue();
        System.out.println("--------------Billing & Purchase/ Top Vehicle Purchase Value Total-------------");
        String topVehiclePurchaseValueTotalExpected = "₹ " + topVehiclePurchaseValueTotal;
        System.out.println("Expected Value: " + topVehiclePurchaseValueTotalExpected);
        String topVehiclePurchaseValueTotalActual = billing_PurchasePages.getTopVehiclePurchaseValueTotal();
        System.out.println("Actual Value: " + topVehiclePurchaseValueTotalActual);
        testUtil.assertEquals(topVehiclePurchaseValueTotalActual, topVehiclePurchaseValueTotalExpected, "Top Vehicle Purchase Value Total");
    }

    @Test(priority = 9, enabled= true)
    public void partsAccessoriesQtyTotalTest() throws Exception {
        String partsAccessoriesQtyTotal = DBUtil.partsAccessoriesQtyTotalDBValue();
        System.out.println("--------------Billing & Purchase/ Parts Accessories Qty Total-------------");
        int partsAccessoriesQtyTotalExpected = Integer.parseInt(partsAccessoriesQtyTotal);
        String partsAccessoriesQtyTotalVal = billing_PurchasePages.getPartsAccessoriesQtyTotal();
        int partsAccessoriesQtyTotalActual = Integer.parseInt(partsAccessoriesQtyTotalVal);
        testUtil.assertEquals(partsAccessoriesQtyTotalActual, partsAccessoriesQtyTotalExpected, "Parts Accessories Qty Total");
    }

    @Test(priority = 10, enabled= true)
    public void partsAccessoriesPurchaseValueTotalTest() throws Exception {
        String partsAccessoriesPurchaseValueTotal = DBUtil.partsAccessoriesPurchaseValueTotalDBValue();
        System.out.println("--------------Billing & Purchase/ Parts Accessories Purchase Value Total-------------");
        String partsAccessoriesPurchaseValueTotalExpected = "₹ " + partsAccessoriesPurchaseValueTotal;
        System.out.println("Expected Value: " + partsAccessoriesPurchaseValueTotalExpected);
        String partsAccessoriesPurchaseValueTotalActual = billing_PurchasePages.getPartsAccessoriesPurchaseValueTotal();
        System.out.println("Actual Value: " + partsAccessoriesPurchaseValueTotalActual);
        testUtil.assertEquals(partsAccessoriesPurchaseValueTotalActual, partsAccessoriesPurchaseValueTotalExpected, "Parts Accessories Purchase Value Total");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
