package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class PartsStocksPage extends TestBase {
    @FindBy(xpath = "//visual-container[.//div[contains(@aria-label, 'Parts Qty')]]//div[contains(@class, 'caption')]")
    WebElement partsQty;

    @FindBys({
            @FindBy(xpath = "//*[local-name()='svg' and starts-with(@aria-label, 'Vehicle Value')]//*[local-name()='tspan']") })
    List<WebElement> stockValue;

    @FindBys({
            @FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]") })
    List<WebElement> monthWiseTrend;

    @FindBys({
            @FindBy(xpath = "//*[@role='gridcell' and text() ='Total']//following-sibling::div[@column-index='2' and @aria-colindex='4']") })
    List<WebElement> excessStock;

    // Initializing the page objects

    public PartsStocksPage() {
        PageFactory.initElements(driver, this);
    }

    public String getpartsQty() {
        return partsQty.getText();
    }

    public String getTotalStockValue() {
        WebElement totalStockValue = this.stockValue.get(1);
        return totalStockValue.getText();
    }

    public String getAccessoriesAndPartsValue() {
        WebElement accessoriesAndPartsValue = this.stockValue.get(0);
        return accessoriesAndPartsValue.getText();
    }

    public String getExcessStockValue() {
        WebElement excessStockValue = this.excessStock.get(1);
        return excessStockValue.getText();
    }

    public String getExcessStockQty() {
        WebElement excessStockQty = this.excessStock.get(2);
        return excessStockQty.getText();
    }

    public String getCurrentMonthPartsQtyTrend() {
        WebElement currentMonthPartsQtyTrend = this.monthWiseTrend.get(2);
        return currentMonthPartsQtyTrend.getText();
    }

    public String getCurrentMonthPartsValueTrend() {
        WebElement currentMonthPartsValueTrend = this.monthWiseTrend.get(4);
        return currentMonthPartsValueTrend.getText();
    }

    public String getLastMonthPartsQtyTrend() {
        WebElement lastMonthPartsQtyTrend = this.monthWiseTrend.get(1);
        return lastMonthPartsQtyTrend.getText();
    }

    public String getLastMonthPartsValueTrend() {
        WebElement lastMonthPartsValueTrend = this.monthWiseTrend.get(3);
        return lastMonthPartsValueTrend.getText();
    }

}
