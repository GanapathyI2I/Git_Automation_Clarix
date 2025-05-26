package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class VehicleStocksPage extends TestBase {

    @FindBy(xpath = "//visual-container[.//div[contains(@aria-label, 'Vehicle Qty')]]//div[contains(@class, 'caption')]")
    WebElement vehicleQty;

    @FindBys({
            @FindBy(xpath = "//*[local-name()='svg' and starts-with(@aria-label, 'New Vehicle Value')]//*[local-name()='tspan']") })
    List<WebElement> value;

    @FindBys({
            @FindBy(xpath = "//*[local-name()='svg' and starts-with(@aria-label, 'Vehicle Qty')]//*[local-name()='tspan']") })
    List<WebElement> stockQty;

    @FindBys({
            @FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]") })
    List<WebElement> monthWiseStock;

    @FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text() = 'Value']/ancestor::div[@class='contentOuterContainer']/..//*[@class='tileSVG']//*//*[@class='tile']//*[@class='fill ui-role-button-fill sub-selectable']")
    WebElement valueButton;

    @FindBy(xpath = "//*[local-name()='svg' and starts-with(@aria-label, 'Vehicle Value')]//*[local-name()='tspan']")
    WebElement accessoriesAndPartsValue;

    // Initializing the page objects

    public VehicleStocksPage() {
        PageFactory.initElements(driver, this);
    }

    public String getVehiclQty() {
        return vehicleQty.getText();
    }

    public String getStockValue() {
        WebElement stockValue = this.value.get(1);
        return stockValue.getText();
    }

    public String getaccessoriesAndPartsValue() {
        return accessoriesAndPartsValue.getText();
    }

    public String getCurrentMonthStock() {
        WebElement currentMonthStock = this.monthWiseStock.get(3);
        return currentMonthStock.getText();
    }

    public String getLastMonthStock() {
        WebElement lastMonthStock = this.monthWiseStock.get(2);
        return lastMonthStock.getText();
    }

    public void clickValueButton() {
        WebElement valueButton = this.valueButton;
        valueButton.click();
    }

    public String getGreaterThan30DaysStocksValue() {
        WebElement greaterThan30DaysStocksValue = this.monthWiseStock.get(2);
        return greaterThan30DaysStocksValue.getText();
    }

    public String getGreaterThan60DaysStocksValue() {
        WebElement greaterThan60DaysStocksValue = this.monthWiseStock.get(3);
        return greaterThan60DaysStocksValue.getText();
    }

    public String getGreaterThan30DaysStocksUnits() {
        WebElement greaterThan30DaysStocksunits = this.monthWiseStock.get(3);
        return greaterThan30DaysStocksunits.getText();
    }

    public String getGreaterThan60DaysStocksUnits() {
        WebElement greaterThan60DaysStocksUnits = this.monthWiseStock.get(4);
        return greaterThan60DaysStocksUnits.getText();
    }

}