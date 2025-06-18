package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.Billing_PurchasePages;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.NewOrderP_APage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class NewOrderP_APageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    Billing_PurchasePages billing_PurchasePages;
    NewOrderP_APage newOrderP_APage;

    public NewOrderP_APageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        testUtil = new TestUtil();
        billing_PurchasePages = new Billing_PurchasePages();
        newOrderP_APage = new NewOrderP_APage();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickBillingPurchaseTile();
        billing_PurchasePages.clickNewOrderP_ATile();
        Thread.sleep(5000);
    }



    @Test(priority = 1, enabled=false)
    public void partsNameFirstRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Parts Name First Row--------");
        String partsNameExpected = DBUtil.partsNameFirstRowDBValue();
        System.out.println("Expected Value: " + partsNameExpected);
    String partsNameActual = newOrderP_APage.getPartsNameFirstRow();
        System.out.println("Actual Value: " + partsNameActual);
        testUtil.assertEquals(partsNameActual, partsNameExpected, "Parts Name First Row");
    }

    @Test(priority = 2, enabled=false)
    public void partNoFirstRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Part No First Row--------");
        String partNoExpected = DBUtil.partNoFirstRowDBValue();
        System.out.println("Expected Value: " + partNoExpected);
        String partNoActual = newOrderP_APage.getPartNoFirstRow();
        System.out.println("Actual Value: " + partNoActual);
        testUtil.assertEquals(partNoActual, partNoExpected, "Part No First Row");
    }

    @Test(priority = 3, enabled=false)
    public void averageMonthlySalesQtyFirstRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Average Monthly Sales Qty First Row--------");
        String averageMonthlySalesQtyExpected = DBUtil.averageMonthlySalesQtyFirstRowDBValue();
        System.out.println("Expected Value: " + averageMonthlySalesQtyExpected);
        String averageMonthlySalesQtyActual = newOrderP_APage.getAverageMonthlySalesQtyFirstRow();
        System.out.println("Actual Value: " + averageMonthlySalesQtyActual);
        testUtil.assertEquals(averageMonthlySalesQtyActual, averageMonthlySalesQtyExpected, "Average Monthly Sales Qty First Row");
    }

    @Test(priority = 4, enabled=false)
    public void currentStockFirstRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Current Stock First Row--------");
        String currentStockExpected = DBUtil.currentStockFirstRowDBValue();
        System.out.println("Expected Value: " + currentStockExpected);
        String currentStockActual = newOrderP_APage.getCurrentStockFirstRow();
        System.out.println("Actual Value: " + currentStockActual);
        testUtil.assertEquals(currentStockActual, currentStockExpected, "Current Stock First Row");
    }

    @Test(priority = 5, enabled=false)
    public void partsNameSecondRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Parts Name Second Row--------");
        String partsNameExpected = DBUtil.partsNameSecondRowDBValue();
        System.out.println("Expected Value: " + partsNameExpected);
        String partsNameActual = newOrderP_APage.getPartsNameSecondRow();
        System.out.println("Actual Value: " + partsNameActual);
        testUtil.assertEquals(partsNameActual, partsNameExpected, "Parts Name Second Row");
    }

    @Test(priority = 6, enabled=false)
    public void partNoSecondRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Part No Second Row--------");
        String partNoExpected = DBUtil.partNoSecondRowDBValue();
        System.out.println("Expected Value: " + partNoExpected);
        String partNoActual = newOrderP_APage.getPartNoSecondRow();
        System.out.println("Actual Value: " + partNoActual);
        testUtil.assertEquals(partNoActual, partNoExpected, "Part No Second Row");
    }

    @Test(priority = 7, enabled=true)
    public void averageMonthlySalesQtySecondRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Average Monthly Sales Qty Second Row--------");
        String averageMonthlySalesQtyExpected = DBUtil.averageMonthlySalesQtySecondRowDBValue();
        System.out.println("Expected Value: " + averageMonthlySalesQtyExpected);
        String averageMonthlySalesQtyActual = newOrderP_APage.getAverageMonthlySalesQtySecondRow();
        System.out.println("Actual Value: " + averageMonthlySalesQtyActual);
        testUtil.assertEquals(averageMonthlySalesQtyActual, averageMonthlySalesQtyExpected, "Average Monthly Sales Qty Second Row");
    }

    @Test(priority = 8, enabled=true)
    public void currentStockSecondRowTest() throws Exception {
        System.out.println("---------New Order P_A Page/ Current Stock Second Row--------");
        String currentStockExpected = DBUtil.currentStockSecondRowDBValue();
        System.out.println("Expected Value: " + currentStockExpected);
        String currentStockActual = newOrderP_APage.getCurrentStockSecondRow();
        System.out.println("Actual Value: " + currentStockActual);
        testUtil.assertEquals(currentStockActual, currentStockExpected, "Current Stock Second Row");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
