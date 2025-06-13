package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ProfitPage extends TestBase {

    @FindBys({
            @FindBy(xpath = "//span[contains(text(), 'Net Profit')]/ancestor::visual-container/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'netpro_pl_Lk ')]") })
    List<WebElement> netProfit;

    @FindBy(xpath = "//span[contains(text(), 'Profit %')]/ancestor::visual-container/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'New MarginPercentage')]")
    WebElement profitPercentage;

    @FindBys({
            @FindBy(xpath = "//*[text()='Profit Value']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']") })
    List<WebElement> monthWiseProfitValue;

    @FindBys({
            @FindBy(xpath = "//*[text()='Profit %']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']") })
    List<WebElement> monthWiseProfitPercentage;

    public ProfitPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNetProfit() {
        WebElement netProfit = this.netProfit.get(0);
        return netProfit.getText();
    }

    public String getProfitPercentage() {
        return profitPercentage.getText();
    }

    public String getCurrentMonthProfitValue() throws InterruptedException {
        WebElement currentMonthProfitValue = this.monthWiseProfitValue.get(1);
        return currentMonthProfitValue.getText();
    }

    public String getLastMonthProfitValue() {
        WebElement lastMonthProfitValue = this.monthWiseProfitValue.get(5);
        return lastMonthProfitValue.getText();
    }

    public String getCurrentMonthProfitPercentage() {
        WebElement currentMonthProfitPercentage = this.monthWiseProfitPercentage.get(1);
        return currentMonthProfitPercentage.getText();
    }

    public String getLastMonthProfitPercentage() {
        WebElement lastMonthProfitPercentage = this.monthWiseProfitPercentage.get(5);
        return lastMonthProfitPercentage.getText();
    }
}