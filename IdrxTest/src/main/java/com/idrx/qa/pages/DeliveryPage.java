package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class DeliveryPage extends TestBase {

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'Yesterday_Deliveries')]//*[local-name()='tspan']")
    WebElement yesterdayDeliveries;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan']")
    List<WebElement> thisMonthDeliveries;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan']")
    List<WebElement> pendingDeliveries;

    @FindBy(xpath = "//*[text()='Delivery Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> previousMonth;

    @FindBy(xpath = "//*[text()='Delivery Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> currentMonth;

    @FindBy(xpath = "//div[@class='scrollable-cells-viewport ']//div[text()='Total']/../div")
    List<WebElement> salesmanWisePendingDeliveries;

    // Initilaizing the page objects
    public DeliveryPage() {
        PageFactory.initElements(driver, this);
    }

    public String getYesterdayDeliveries() {
        String yesterdayDeliveries = this.yesterdayDeliveries.getText();
        return yesterdayDeliveries;
    }

    public String getThisMonthDeliveries() {
        String thisMonthDeliveries = this.thisMonthDeliveries.get(3).getText();
        return thisMonthDeliveries;
    }

    public String getPendingDeliveries() {
        String pendingDeliveries = this.pendingDeliveries.get(4).getText();
        return pendingDeliveries;
    }

    public String getPreviousMonth() {
        String previousMonth = this.previousMonth.get(4).getText();
        return previousMonth;
    }

    public String getCurrentMonth() {
        String currentMonth = this.currentMonth.get(1).getText();
        return currentMonth;
    }

    public String getSalesmanWisePendingDeliveries() {
        String salesmanWisePendingDeliveries = this.salesmanWisePendingDeliveries.get(8).getText();
        return salesmanWisePendingDeliveries;
    }

}
