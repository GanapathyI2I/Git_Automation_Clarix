package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class BookingsPage extends TestBase {
        

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, \"Yesterday\'s Booking\")]")
    WebElement yesterdayBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'This Month Bookings')]//*[local-name()='tspan']")
    WebElement thisMonthBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Pending Bookings count')]")
    WebElement pendingBookings;

    @FindBy(xpath = "//*[text()='Bookings Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='svg' and @class='cartesianChart']//*[local-name()='svg' and @class='svgScrollable']//*[local-name()='g' and @class='labelContainerGraphicsContext']//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']")
    List<WebElement> previousMonth;

    @FindBy(xpath = "//*[text()='Bookings Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='svg' and @class='cartesianChart']//*[local-name()='svg' and @class='svgScrollable']//*[local-name()='g' and @class='labelContainerGraphicsContext']//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']")
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
        String thisMonthBooking = this.thisMonthBooking.getText();
        return thisMonthBooking;
    }

    public String getPendingBookings() {
        String pendingBookings = this.pendingBookings.getText();
        return pendingBookings;
    }
    
    public String getPreviousMonth() {
        String previousMonth = this.previousMonth.get(3).getText();
        return previousMonth;
    }

    public String getCurrentMonth() {
        String currentMonth = this.currentMonth.get(1).getText();
        return currentMonth;
    }
    
    public String getSalesmanWisePendingBookings() {
        String salesmanWisePendingBookings = this.salesmanWisePendingBookings.get(8).getText();
        return salesmanWisePendingBookings;
    }

    
}