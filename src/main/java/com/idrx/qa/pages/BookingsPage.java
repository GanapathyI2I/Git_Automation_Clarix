package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class BookingsPage extends TestBase {
        

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, \"Yesterday's Booking\")]//*[local-name()='tspan']")
    WebElement yesterdayBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan']")
    List<WebElement> thisMonthBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan']")
    List<WebElement> pendingBookings;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> previousMonth;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> currentMonth;

    //Need to change xpath to get the correct value
    @FindBy(xpath = "//div[@class='scrollable-cells-viewport ']//div[text()='Total']/../div")
    List<WebElement> salesmanWisePendingBookings;

    
    

    //initializing the page objects
    
    public BookingsPage() {
        PageFactory.initElements(driver, this);
    }

    

    public String getYesterdayBooking() {
        String yesterdayBooking = this.yesterdayBooking.getText();
        return yesterdayBooking;
    }

    public String getThisMonthBooking() {
        String thisMonthBooking = this.thisMonthBooking.get(3).getText();
        return thisMonthBooking;
    }

    public String getPendingBookings() {
        String pendingBookings = this.pendingBookings.get(4).getText();
        return pendingBookings;
    }
    
    public String getPreviousMonth() {
        String previousMonth = this.previousMonth.get(5).getText();
        return previousMonth;
    }

    public String getCurrentMonth() {
        String currentMonth = this.currentMonth.get(2).getText();
        return currentMonth;
    }
    
    public String getSalesmanWisePendingBookings() {
        String salesmanWisePendingBookings = this.salesmanWisePendingBookings.get(8).getText();
	System.out.println("get Salesman wise peding bookings")
        return salesmanWisePendingBookings;
    }

    
}