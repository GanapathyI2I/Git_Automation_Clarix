package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class RetailsPage extends TestBase {

    @FindBy(xpath = "//span[contains(text(), 'Vehicles Sold')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Vehicle_Qty')]//*[local-name()='tspan']")
    WebElement vehiclesSold;

    @FindBy(xpath = "//span[contains(text(), 'Avg sales')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Avg_Vehicle_Qty')]//*[local-name()='tspan']")
    WebElement avgSales;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Yesterday_Enquries')]//*[local-name()='tspan']")
    WebElement yesterdayEnquries;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Yesterday_Booking')]//*[local-name()='tspan']")
    WebElement yesterdayBooked;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Yesterday_Invoiced')]//*[local-name()='tspan']")
    WebElement yesterdayInvoiced;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Vehicle_Qty')]//*[local-name()='tspan']")
    List<WebElement> invoicedThisMonth;

    @FindBy(xpath = "//*[text()='Qty Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> currentMonth; 

    @FindBy(xpath = "//*[text()='Qty Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> previousMonth;

    //Initializing the page objects
    public RetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getVehiclesSold() {
        return vehiclesSold.getText();
    }

    public String getAvgSales() {
        return avgSales.getText();
    }

    public String getYesterdayEnquries() {
        return yesterdayEnquries.getText();
    }

    public String getYesterdayBooked() {
        return yesterdayBooked.getText();
    }

    public String getYesterdayInvoiced() {
        return yesterdayInvoiced.getText();
    }

    public String getInvoicedThisMonth() {
        return invoicedThisMonth.get(0).getText();
    }

    public String getCurrentMonth() {
        return currentMonth.get(1).getText();
    }

    public String getPreviousMonth() {
        return previousMonth.get(5).getText();
    }
}
