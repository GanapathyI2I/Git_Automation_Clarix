package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class BookingsPage extends TestBase {

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Yesterday_Booking')]")
    WebElement yesterdayBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'Current_Month_Booking')]//*[local-name()='tspan']")
    WebElement currentMonthBooking;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'Booking_Count')]//*[local-name()='tspan']")
    WebElement pendingBookings;

    @FindBy(xpath = "//*[text()='Bookings Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> currentMonth;

    @FindBy(xpath = "//*[text()='Bookings Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> previousMonth;


    //initializing the page objects
    public BookingsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getYesterdayBooking() {
        return yesterdayBooking.getText();
    }

    public String getCurrentMonthBooking() {
        return currentMonthBooking.getText();
    }

    public String getPendingBookings() {
        return pendingBookings.getText();
    }

    public String getCurrentMonth() {
        return currentMonth.get(1).getText();
    }

    public String getPreviousMonth() {
        return previousMonth.get(2).getText();
}


}
