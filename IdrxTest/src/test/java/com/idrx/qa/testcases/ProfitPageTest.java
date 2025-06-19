//----------------------------------On hold for now----------------------------------
// package com.idrx.qa.testcases;

// import org.testng.annotations.Test;
// import com.idrx.qa.base.TestBase;
// import com.idrx.qa.pages.HomePage;
// import com.idrx.qa.pages.LoginPage;
// import com.idrx.qa.pages.ProfitPage;
// import com.idrx.qa.util.DBUtil;
// import com.idrx.qa.util.TestUtil;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;

// public class ProfitPageTest extends TestBase {
//     LoginPage loginPage;
//     HomePage homePage;
//     TestUtil testUtil;
//     ProfitPage profitPage;

//     @BeforeClass
//     public void setUp() throws Exception {
//         initialization();
//         loginPage = new LoginPage();
//         homePage = new HomePage();
//         profitPage = new ProfitPage();
//         testUtil = new TestUtil();
//         loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
//         Thread.sleep(10000);
//         TestUtil.switchToFrame();
//         homePage.clickProfitTab();
//     }

//     @Test(priority = 1, enabled = true)
//     public void getNetProfitTest() throws Exception {
//         String netProfitExpectedValue = DBUtil.netProfitGetDBValue();
//         System.out.println("--------Net Profit--------");
//         System.out.println("Expected Value: " + netProfitExpectedValue);
//         String netProfitActualValue = profitPage.getNetProfit();
//         System.out.println("Actual Value: " + netProfitActualValue);
//         testUtil.assertEquals(netProfitActualValue, netProfitExpectedValue, "Net Profit");
//     }

//     @Test(priority = 2, enabled = true)
//     public void getProfitPercentageTest() throws Exception {
//         String profitPercentageExpectedValue = DBUtil.profitPercentageGetDBValue();
//         System.out.println("--------Profit Percentage--------");
//         System.out.println("Expected Value: " + profitPercentageExpectedValue);
//         String profitPercentageActualValue = profitPage.getProfitPercentage();
//         System.out.println("Actual Value: " + profitPercentageActualValue);
//         testUtil.assertEquals(profitPercentageActualValue, profitPercentageExpectedValue, "Profit Percentage");
//     }

//     @Test(priority = 3, enabled = true)
//     public void getCurrentMonthProfitValueTest() throws Exception {
//         String currentMonthProfitValueExpectedValue = DBUtil.currentMonthProfitValueGetDBValue();
//         System.out.println("--------Current Month Profit Value--------");
//         System.out.println("Expected Value: " + currentMonthProfitValueExpectedValue);
//         String currentMonthProfitValueActualValue = profitPage.getCurrentMonthProfitValue();
//         System.out.println("Actual Value: " + currentMonthProfitValueActualValue);
//         testUtil.assertEquals(currentMonthProfitValueActualValue, currentMonthProfitValueExpectedValue,
//                 "Current Month Profit Value");
//     }

//     @Test(priority = 4, enabled = true)
//     public void getLastMonthProfitValueTest() throws Exception {
//         String lastMonthProfitValueExpectedValue = DBUtil.lastMonthProfitValueGetDBValue();
//         System.out.println("--------Last Month Profit Value--------");
//         System.out.println("Expected Value: " + lastMonthProfitValueExpectedValue);
//         String lastMonthProfitValueActualValue = profitPage.getLastMonthProfitValue();
//         System.out.println("Actual Value: " + lastMonthProfitValueActualValue);
//         testUtil.assertEquals(lastMonthProfitValueActualValue, lastMonthProfitValueExpectedValue,
//                 "Last Month Profit Value");
//     }

//     @Test(priority = 5, enabled = true)
//     public void getCurrentMonthProfitPercentageTest() throws Exception {
//         String currentMonthProfitPercentageExpectedValue = DBUtil.currentMonthProfitPercentageGetDBValue();
//         System.out.println("--------Current Month Profit Percentage--------");
//         System.out.println("Expected Value: " + currentMonthProfitPercentageExpectedValue);
//         String currentMonthProfitPercentageActualValue = profitPage.getCurrentMonthProfitPercentage();
//         System.out.println("Actual Value: " + currentMonthProfitPercentageActualValue);
//         testUtil.assertEquals(currentMonthProfitPercentageActualValue, currentMonthProfitPercentageExpectedValue,
//                 "Current Month Profit Percentage");
//     }

//     @Test(priority = 6, enabled = true)
//     public void getLastMonthProfitPercentageTest() throws Exception {
//         String lastMonthProfitPercentageExpectedValue = DBUtil.lastMonthProfitPercentageGetDBValue();
//         System.out.println("--------Last Month Profit Percentage--------");
//         System.out.println("Expected Value: " + lastMonthProfitPercentageExpectedValue);
//         String lastMonthProfitPercentageActualValue = profitPage.getLastMonthProfitPercentage();
//         System.out.println("Actual Value: " + lastMonthProfitPercentageActualValue);
//         testUtil.assertEquals(lastMonthProfitPercentageActualValue, lastMonthProfitPercentageExpectedValue,
//                 "Last Month Profit Percentage");
//     }

//     @AfterClass
//     public void tearDown() {
//         driver.quit();
//     }
// }
