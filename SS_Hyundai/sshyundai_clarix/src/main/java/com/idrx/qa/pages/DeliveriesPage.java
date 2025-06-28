package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class DeliveriesPage extends TestBase {

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Yesterday_Delivery')]//*[local-name()='tspan']")
    WebElement yesterdayDelivery;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Current Month Delivery_count')]//*[local-name()='tspan']")
    WebElement currentMonthDeliveries;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Delivery_count')]//*[local-name()='tspan']")
    List<WebElement> pendingDeliveries; // index=2 =1

    @FindBy(xpath = "//*[text()='Delivery Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> currentMonth; //index=2 =1

    @FindBy(xpath = "//*[text()='Delivery Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']")
    List<WebElement> previousMonth; //index=6 =5

    @FindBy(xpath = "//div[@class='scrollable-cells-viewport ']//div[text()='Total']/../div")
    List<WebElement> modelWisePendingTotal; //index=6 =5

    //Initializing the page objects
    public DeliveriesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getYesterdayDeliveries() {
        return yesterdayDelivery.getText();
    }

    public String getCurrentMonthDeliveries() {
        return currentMonthDeliveries.getText();
    }

    public String getPendingDeliveries() {
        return pendingDeliveries.get(1).getText();
    }

    public String getCurrentMonth() {
        return currentMonth.get(1).getText();
    }

    public String getPreviousMonth() {
        return previousMonth.get(5).getText();
    }

    public String getModelWisePendingTotal() {
        return modelWisePendingTotal.get(5).getText();
    }

}
