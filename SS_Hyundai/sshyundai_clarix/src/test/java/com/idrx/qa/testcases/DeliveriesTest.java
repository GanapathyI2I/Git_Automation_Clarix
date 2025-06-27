package com.idrx.qa.testcases;

import org.testng.annotations.BeforeClass;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.pages.BookingsPage;
import com.idrx.qa.pages.HomePage;
import com.idrx.qa.pages.LoginPage;
import com.idrx.qa.pages.DeliveriesPage;
import com.idrx.qa.util.TestUtil;


public class DeliveriesTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    BookingsPage bookingsPage;
    DeliveriesPage deliveriesPage;
    TestUtil testUtil;

    public DeliveriesTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws Exception {
        initialization();
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        homePage = new HomePage();
        deliveriesPage = new DeliveriesPage();
        bookingsPage = new BookingsPage();
        
    }

}
