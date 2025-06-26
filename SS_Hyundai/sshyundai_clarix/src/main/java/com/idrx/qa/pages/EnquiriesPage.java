package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class EnquiriesPage extends TestBase {
    @FindBy(xpath = "//*[text()='Enquiry']/ancestor::visual-container/..//visual-container-group//*[local-name()='svg' and contains(@aria-label,'Enq')]")
    WebElement enquiry;

    @FindBy(xpath = "//*[text()='Avg Enq Per Month']/ancestor::visual-container/..//visual-container//*[local-name()='svg' and contains(@aria-label,'Avg_enq_Qty_Per_Month')]")
    WebElement avgEnqPerMonth;

    @FindBys({ @FindBy(css = "svg.tileSVG") })
    List<WebElement> menuButton;

    @FindBys({
            @FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text()='Leads']/ancestor::visual-container/..//visual-container//*[local-name()='tspan' and @class='label-tspan']") })
    List<WebElement> monthWiseLeads;

    public EnquiriesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getEnquiry() {
        return enquiry.getText();
    }

    public String getAvgEnqPerMonth() {
        return avgEnqPerMonth.getText();
    }

    public String getCurrentMonthLeads() {
        WebElement currentMonthLeads = this.monthWiseLeads.get(1);
        return currentMonthLeads.getText();
    }

    public String getLastMonthLeads() {
        WebElement lastMonthLeads = this.monthWiseLeads.get(2);
        return lastMonthLeads.getText();
    }

    public void clickEnquiryButton() {
        WebElement menuButton = this.menuButton.get(11);
        menuButton.click();
    }

}
