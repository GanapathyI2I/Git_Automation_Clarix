package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class Billing_PurchasePages extends TestBase {
    
    @FindBy(xpath = "//span[contains(text(), 'Vehicle Qty')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Purchase Vechiles Qty')]")
    List<WebElement> vehicleQty;

    @FindBy(xpath = "//span[contains(text(), 'Vehicle Qty')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Purchase Value')]")
    WebElement totalPurchaseValue;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> unitsCurrentMonth;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> unitsPastMonth;

    @FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text() = 'Total Value']/ancestor::div[@class='contentOuterContainer']/..//*[@class='tileSVG']//*//*[@class='tile']//*[@class='fill ui-role-button-fill sub-selectable']")
    WebElement totalValueClick;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> totalValueCurrentMonth;

    @FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text()='Units']/ancestor::visual-modern/ancestor::visual-container/..//visual-container//*[local-name()='tspan' and @class='label-tspan']")
    List<WebElement> totalValuePastMonth;

    @FindBy(xpath = "//div[h3[text()='Top Vehicle']]//following::div[@role='grid']//div[@role='columnheader' and text()='Qty']/ancestor::div[@role='grid']/div[@class='bot-viewport']//div[@column-index='1'][1]")
    List<WebElement> topVehicleQtyTotal;

    @FindBy(xpath = "//div[h3[text()='Top Vehicle']]//following::div[@role='grid']//div[@role='columnheader' and text()='Qty']/ancestor::div[@role='grid']/div[@class='bot-viewport']//div[@column-index='2'][1]")
    List<WebElement> topVehiclePurchaseValueTotal;

    @FindBy(xpath = "//div[h3[text()='Parts Accessories']]//following::div[@role='grid']//div[@role='columnheader' and text()='Qty']/ancestor::div[@role='grid']/div[@class='bot-viewport']//div[@column-index='1']")
    WebElement partsAccessoriesQtyTotal;

    @FindBy(xpath = "//div[h3[text()='Parts Accessories']]//following::div[@role='grid']//div[@role='columnheader' and text()='Qty']/ancestor::div[@role='grid']/div[@class='bot-viewport']//div[@column-index='2']")
    WebElement partsAccessoriesPurchaseValueTotal;

    //Clicking for New Order Tile(Vehicles)
    @FindBy(xpath = "//*[@class='tileSVG']")
    List<WebElement> newOrderVehiclesTile;

    //Clicking for New Order (P & A)
    @FindBy(xpath = "//*[@class='tileSVG']")
    List<WebElement> newOrderP_ATile;

    // initializing the page objects

    public Billing_PurchasePages() {
        PageFactory.initElements(driver, this);
    }

        public String getVehicleQty() {
            String vehicleQty = this.vehicleQty.get(0).getText();
            return vehicleQty;
        }

        public String getTotalPurchaseValue() {
            String totalPurchaseValue = this.totalPurchaseValue.getText();
            return totalPurchaseValue;
        }

        public String getUnitsCurrentMonth() {
            String unitsCurrentMonth = this.unitsCurrentMonth.get(8).getText();
            return unitsCurrentMonth;
        }

        public String getUnitsPastMonth() {
            String unitsPastMonth = this.unitsPastMonth.get(11).getText();
            return unitsPastMonth;
        }

        public void clickTotalValue() {
            WebElement totalValue = this.totalValueClick;
            totalValue.click();
        }

        public String getTotalValueCurrentMonth(){
            String totalValueCurrentMonth = this.totalValueCurrentMonth.get(8).getText();
            return totalValueCurrentMonth;
        }

        public String getTotalValuePastMonth() {
            String totalValuePastMonth = this.totalValuePastMonth.get(10).getText();
            return totalValuePastMonth;
        }

        public String getTopVehicleQtyTotal() {
            String topVehicleQtyTotal = this.topVehicleQtyTotal.get(0).getText();
            return topVehicleQtyTotal;
        }

        public String getTopVehiclePurchaseValueTotal(){
            String topVehiclePurchaseValueTotal = this.topVehiclePurchaseValueTotal.get(0).getText();
            return topVehiclePurchaseValueTotal;
        }

        public String getPartsAccessoriesQtyTotal() {
            String partsAccessoriesQtyTotal = this.partsAccessoriesQtyTotal.getText();
            return partsAccessoriesQtyTotal;
        }

        public String getPartsAccessoriesPurchaseValueTotal() {
            String partsAccessoriesPurchaseValueTotal = this.partsAccessoriesPurchaseValueTotal.getText();
            return partsAccessoriesPurchaseValueTotal;
        }

        public void clickNewOrderVehiclesTile() {
            WebElement newOrderVehiclesTile = this.newOrderVehiclesTile.get(10);
            newOrderVehiclesTile.click();
        }

        public void clickNewOrderP_ATile() {
            WebElement newOrderP_ATile = this.newOrderP_ATile.get(12);
            newOrderP_ATile.click();
        }
    }


