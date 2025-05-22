package com.idrx.qa.testcases;

import org.testng.annotations.Test;
import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.SalesPage;
import com.idrx.qa.util.DBUtil;
import com.idrx.qa.util.TestUtil;
import com.idrx.qa.pages.DiscountPage;
import com.idrx.qa.pages.FinancePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FinancePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SalesPage salesPage;
	TestUtil testUtil;
	DiscountPage discountPage;
	FinancePage financePage;

	public FinancePageTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		salesPage = new SalesPage();
		testUtil = new TestUtil();
		financePage = new FinancePage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(10000);
		TestUtil.switchToFrame();
		homePage.clickSalesTab();
		salesPage.clickFinanceTab();
	}

	@Test(priority = 1, enabled = true)
	public void noOfFinanceQtyTest() throws Exception {

		String noOfFinanceQty = DBUtil.noOfFinanceQtyGetDBValue();
		System.out.println("--------No. of Finance Qty--------");
		int noOfFinanceQtyExpectedValue = Integer.parseInt(noOfFinanceQty);
		System.out.println("Expected Value: " + noOfFinanceQtyExpectedValue);
		String noOfFinanceQtyVal = financePage.getNoOfFinanceQty();
		int noOfFinanceQtyActualValue = Integer.parseInt(noOfFinanceQtyVal);
		System.out.println("Actual Value: " + noOfFinanceQtyActualValue);
		testUtil.assertEquals(noOfFinanceQtyActualValue, noOfFinanceQtyExpectedValue, "No. of Finance Qty");
	}

	@Test(priority = 2, enabled = true)
	public void financePenetrationTest() throws Exception {

		System.out.println("--------Finance Penetration--------");
		String financePenetrationExpectedValue = DBUtil.financePenetrationGetDBValue();
		System.out.println("Expected Value: " + financePenetrationExpectedValue);
		String financePenetrationActualValue = financePage.getFinancePenetration();
		System.out.println("Actual Value: " + financePenetrationActualValue);
		testUtil.assertEquals(financePenetrationActualValue, financePenetrationExpectedValue, "Finance Penetration");
	}

	@Test(priority = 3, enabled = true)
	public void cashVsFinanceTotalTest() throws Exception {

		String cashVsFinanceTotal = DBUtil.cashVsFinanceTotalGetDBValue();
		System.out.println("--------Cash vs Finance Total--------");
		int cashVsFinanceTotalExpectedValue = Integer.parseInt(cashVsFinanceTotal);
		System.out.println("Expected Value: " + cashVsFinanceTotalExpectedValue);
		String cashVsFinanceTotalVal = financePage.getCashVsFinanceTotal();
		int cashVsFinanceTotalActualValue = Integer.parseInt(cashVsFinanceTotalVal);
		System.out.println("Actual Value: " + cashVsFinanceTotalActualValue);
		testUtil.assertEquals(cashVsFinanceTotalActualValue, cashVsFinanceTotalExpectedValue, "Cash vs Finance Total");
	}

	@Test(priority = 4, enabled = true)
	public void currentMonthFinanceTrendTest() throws Exception {

		String currentMonthFinanceTrend = DBUtil.currentMonthFinanceTrendGetDBValue();
		System.out.println("--------Current Month Finance Trend--------");
		int currentMonthFinanceTrendExpectedValue = Integer.parseInt(currentMonthFinanceTrend);
		System.out.println("Expected Value: " + currentMonthFinanceTrendExpectedValue);
		String currentMonthFinanceTrendVal = financePage.getCurrentMonthFinanceTrend();
		int currentMonthFinanceTrendActualValue = Integer.parseInt(currentMonthFinanceTrendVal);
		System.out.println("Actual Value: " + currentMonthFinanceTrendActualValue);
		testUtil.assertEquals(currentMonthFinanceTrendActualValue, currentMonthFinanceTrendExpectedValue,
				"Current Month Finance Trend");
	}

	@Test(priority = 5, enabled = true)
	public void previousMonthFinanceTrendTest() throws Exception {

		String previousMonthFinanceTrend = DBUtil.previousMonthFinanceTrendGetDBValue();
		System.out.println("--------Previous Month Finance Trend--------");
		int previousMonthFinanceTrendExpectedValue = Integer.parseInt(previousMonthFinanceTrend);
		System.out.println("Expected Value: " + previousMonthFinanceTrendExpectedValue);
		String previousMonthFinanceTrendVal = financePage.getPreviousMonthFinanceTrend();
		int previousMonthFinanceTrendActualValue = Integer.parseInt(previousMonthFinanceTrendVal);
		System.out.println("Actual Value: " + previousMonthFinanceTrendActualValue);
		testUtil.assertEquals(previousMonthFinanceTrendActualValue, previousMonthFinanceTrendExpectedValue,
				"Previous Month Finance Trend");
	}

	@Test(priority = 6, enabled = true)
	public void currentMonthFinancePenetrationTrendTest() throws Exception {

		System.out.println("--------Current Month Finance Penetration Trend--------");
		String currentMonthFinancePenetrationTrendExpectedValue = DBUtil
				.currentMonthFinancePenetrationTrendGetDBValue();
		System.out.println("Expected Value: " + currentMonthFinancePenetrationTrendExpectedValue);
		String currentMonthFinancePenetrationTrendActualValue = financePage.getCurrentMonthFinancePenetrationTrend();
		System.out.println("Actual Value: " + currentMonthFinancePenetrationTrendActualValue);
		testUtil.assertEquals(currentMonthFinancePenetrationTrendActualValue,
				currentMonthFinancePenetrationTrendExpectedValue, "Current Month Finance Penetration Trend");
	}

	@Test(priority = 7, enabled = true)
	public void previousMonthFinancePenetrationTrendTest() throws Exception {

		System.out.println("--------Previous Month Finance Penetration Trend--------");
		String previousMonthFinancePenetrationTrendExpectedValue = DBUtil
				.previousMonthFinancePenetrationTrendGetDBValue();
		System.out.println("Expected Value: " + previousMonthFinancePenetrationTrendExpectedValue);
		String previousMonthFinancePenetrationTrendActualValue = financePage.getPreviousMonthFinancePenetrationTrend();
		System.out.println("Actual Value: " + previousMonthFinancePenetrationTrendActualValue);
		testUtil.assertEquals(previousMonthFinancePenetrationTrendActualValue,
				previousMonthFinancePenetrationTrendExpectedValue, "Previous Month Finance Penetration Trend");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}