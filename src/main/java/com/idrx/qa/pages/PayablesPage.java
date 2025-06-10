package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class PayablesPage extends TestBase {

    @FindBys({
            @FindBy(xpath = "//span[contains(text(), 'Total Payables')]/ancestor::visual-container/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Payable Total amount')]") })
    List<WebElement> Payables;

    @FindBys({
            @FindBy(xpath = "//*[local-name()='svg' and starts-with(@aria-label, 'Pay_value')]//*[local-name()='tspan']") })
    List<WebElement> pendingPayables;

    @FindBys({
            @FindBy(xpath = "//*[text()='Payables Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='svg' and @class='cartesianChart']//*[local-name()='svg' and @class='svgScrollable']//*[local-name()='g' and @class='labelContainerGraphicsContext']//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']") })
    List<WebElement> monthwisePayablesTrend;

    public PayablesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getTotalPayables() {
        WebElement totalPayables = this.Payables.get(0);
        return totalPayables.getText();
    }

    public String getSpares() {
        WebElement spares = this.Payables.get(1);
        return spares.getText();
    }

    public String getSales() {
        WebElement sales = this.Payables.get(2);
        return sales.getText();
    }

    public String getMahindra() {
        WebElement mahindra = this.Payables.get(3);
        return mahindra.getText();
    }

    public String getService() {
        WebElement service = this.Payables.get(4);
        return service.getText();
    }

    public String getOthers() {
        WebElement others = this.Payables.get(5);
        return others.getText();
    }

    public String getGreaterThan60DaysPendingPayables() {
        WebElement greaterThan60DaysPendingPayables = this.pendingPayables.get(0);
        return greaterThan60DaysPendingPayables.getText();
    }

    public String getGreaterThan30DaysPendingPayables() {
        WebElement greaterThan30DaysPendingPayables = this.pendingPayables.get(1);
        return greaterThan30DaysPendingPayables.getText();
    }

    public String currentMonthPayablesTrend() {
        WebElement currentMonthPayablesTrend = this.monthwisePayablesTrend.get(1);
        return currentMonthPayablesTrend.getText();
    }

    public String lastMonthPayablesTrend() {
        WebElement lastMonthPayablesTrend = this.monthwisePayablesTrend.get(5);
        return lastMonthPayablesTrend.getText();
    }
}
