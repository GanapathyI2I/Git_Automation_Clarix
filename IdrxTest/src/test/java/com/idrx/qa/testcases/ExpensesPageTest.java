package com.idrx.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.ExpensesPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;

public class ExpensesPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ExpensesPage expensesPage;
    TestUtil testUtil;

    public ExpensesPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        expensesPage = new ExpensesPage();
        testUtil = new TestUtil();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(10000);
        TestUtil.switchToFrame();
        homePage.clickExpensesTile();
        Thread.sleep(5000);
    }

    @Test(priority = 1, enabled=true)
    public void totalExpensesTest() throws Exception {
        String totalExpenses = DBUtil.totalExpensesDBValue();
        System.out.println("---------Expenses/ Total Expenses----------");
        String totalExpensesExpected = "₹ " + totalExpenses;
        System.out.println("Expected Value: " + totalExpensesExpected);
        String totalExpensesActual = expensesPage.getTotalExpenses();
        System.out.println("Actual Value: " + totalExpensesActual);
        testUtil.assertEquals(totalExpensesActual, totalExpensesExpected, "Total Expenses");
    }

    // @Test(priority = 2, enabled=true)
    // public void employeeExpTest() throws Exception {
    //     String employeeExp = DBUtil.employeeExpDBValue();
    //     System.out.println("---------Expenses/ Employee Exp----------");
    //     String employeeExpExpected = "₹ " + employeeExp;
    //     System.out.println("Expected Value: " + employeeExpExpected);
    //     String employeeExpActual = expensesPage.getEmployeeExp();
    //     System.out.println("Actual Value: " + employeeExpActual);
    //     testUtil.assertEquals(employeeExpActual, employeeExpExpected, "Employee Exp");
    // }

    // @Test(priority = 3, enabled=true)
    // public void financeAndChargesTest() throws Exception {
    //     String financeAndCharges = DBUtil.financeAndChargesDBValue();
    //     System.out.println("---------Expenses/ Finance and Charges----------");
    //     String financeAndChargesExpected = "₹ " + financeAndCharges;
    //     System.out.println("Expected Value: " + financeAndChargesExpected);
    //     String financeAndChargesActual = expensesPage.getFinanceAndCharges();
    //     System.out.println("Actual Value: " + financeAndChargesActual);
    //     testUtil.assertEquals(financeAndChargesActual, financeAndChargesExpected, "Finance and Charges");
    // }

    // @Test(priority = 4, enabled=true)
    // public void discountsTest() throws Exception {
    //     String discounts = DBUtil.discountsDBValue();
    //     System.out.println("---------Expenses/ Discounts----------");
    //     String discountsExpected = "₹ " + discounts;
    //     System.out.println("Expected Value: " + discountsExpected);
    //     String discountsActual = expensesPage.getDiscounts();
    //     System.out.println("Actual Value: " + discountsActual);
    //     testUtil.assertEquals(discountsActual, discountsExpected, "Discounts");
    // }

    // @Test(priority = 5, enabled=true)
    // public void repairAndMaintenanceTest() throws Exception {
    //     String repairAndMaintenance = DBUtil.repairAndMaintenanceDBValue();
    //     System.out.println("---------Expenses/ Repair and Maintenance----------");
    //     String repairAndMaintenanceExpected = "₹ " + repairAndMaintenance;
    //     System.out.println("Expected Value: " + repairAndMaintenanceExpected);
    //     String repairAndMaintenanceActual = expensesPage.getRepairAndMaintenance();
    //     System.out.println("Actual Value: " + repairAndMaintenanceActual);
    //     testUtil.assertEquals(repairAndMaintenanceActual, repairAndMaintenanceExpected, "Repair and Maintenance");
    // }

    // @Test(priority = 6, enabled=true)
    // public void othersTest() throws Exception {
    //     String others = DBUtil.othersDBValue();
    //     System.out.println("---------Expenses/ Others----------");
    //     String othersExpected = "₹ " + others;
    //     System.out.println("Expected Value: " + othersExpected);
    //     String othersActual = expensesPage.getOthers();
    //     System.out.println("Actual Value: " + othersActual);
    //     testUtil.assertEquals(othersActual, othersExpected, "Others");
    // }

    @Test(priority = 7, enabled=true)
    public void expensesTrendCurrentMonthTest() throws Exception {
        String expensesTrendCurrentMonth = DBUtil.totalExpensesTrendCurrentMonthDBValue();
        System.out.println("---------Expenses/ Expenses Trend Current Month----------");
        String expensesTrendCurrentMonthExpected = "₹ " + expensesTrendCurrentMonth;
        System.out.println("Expected Value: " + expensesTrendCurrentMonthExpected);
        String expensesTrendCurrentMonthActual = expensesPage.getExpensesTrendCurrentMonth();
        System.out.println("Actual Value: " + expensesTrendCurrentMonthActual);
        testUtil.assertEquals(expensesTrendCurrentMonthActual, expensesTrendCurrentMonthExpected, "Expenses Trend Current Month");
    }

    @Test(priority = 8, enabled=true)
    public void expensesTrendPastMonthTest() throws Exception {
        String expensesTrendPastMonth = DBUtil.totalExpensesTrendPastMonthDBValue();
        System.out.println("---------Expenses/ Expenses Trend Past Month----------");
        String expensesTrendPastMonthExpected = "₹ " + expensesTrendPastMonth;
        System.out.println("Expected Value: " + expensesTrendPastMonthExpected);
        String expensesTrendPastMonthActual = expensesPage.getExpensesTrendPastMonth();
        System.out.println("Actual Value: " + expensesTrendPastMonthActual);
        testUtil.assertEquals(expensesTrendPastMonthActual, expensesTrendPastMonthExpected, "Expenses Trend Past Month");
    }
    
    
    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
