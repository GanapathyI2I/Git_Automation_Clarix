package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ReceivablesPages extends TestBase {

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')][1]")
    List<WebElement> creditOutstanding;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')]")
    List<WebElement> mahindra;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')]")
    List<WebElement> sales;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')]")
    List<WebElement> Service;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')]")
    List<WebElement> supplierAdvance;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Rece Total amount')]")
    List<WebElement> others;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> receivablesTrendCurrentMonth;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> receivablesTrendPastMonth;

    @FindBy(xpath = "//*[local-name()='svg']/..//*[@class='tileSVG']//following::div[@aria-roledescription='Card']")
    List<WebElement> riskyReceivablesGreaterThan60Days;

    @FindBy(xpath = "//*[local-name()='svg']/..//*[@class='tileSVG']//following::div[@aria-roledescription='Card']")
    List<WebElement> riskyReceivablesGreaterThan30Days;


    //Initializing the page objects
    public ReceivablesPages() {
        PageFactory.initElements(driver, this);
    }

    public String getCreditOutstanding() {
        String creditOutstanding = this.creditOutstanding.get(0).getText();
        return creditOutstanding;
    }

    public String getMahindra() {
        String mahindra = this.mahindra.get(1).getText();
        return mahindra;
    }

    public String getSales() {
        String sales = this.sales.get(2).getText();
        return sales;
    }

    public String getService() {
        String service = this.Service.get(5).getText();
        return service;
    }

    public String getSupplierAdvance() {
        String supplierAdvance = this.supplierAdvance.get(4).getText();
        return supplierAdvance;
    }

    public String getOthers() {
        String others = this.others.get(3).getText();
        return others;
    }

    public String getReceivablesTrendCurrentMonth() {
        String receivablesTrendCurrentMonth = this.receivablesTrendCurrentMonth.get(1).getText();
        return receivablesTrendCurrentMonth;
    }

    public String getReceivablesTrendPastMonth() {
        String receivablesTrendPastMonth = this.receivablesTrendPastMonth.get(3).getText();
        return receivablesTrendPastMonth;
    }

    public String getRiskyReceivablesGreaterThan60Days() {
        String riskyReceivablesGreaterThan60Days = this.riskyReceivablesGreaterThan60Days.get(9).getText();
        return riskyReceivablesGreaterThan60Days;
    }

    public String getRiskyReceivablesGreaterThan30Days() {
        String riskyReceivablesGreaterThan30Days = this.riskyReceivablesGreaterThan30Days.get(10).getText();
        return riskyReceivablesGreaterThan30Days;
    }
}