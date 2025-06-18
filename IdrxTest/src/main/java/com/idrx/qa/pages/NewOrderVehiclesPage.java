package com.idrx.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class NewOrderVehiclesPage extends TestBase {

    //1st Row
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='2']")
    WebElement vehicleNameFirstRow;  // 1st Row
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='2']//following::div[@column-index='1'][1]")
    WebElement averageMonthlySalesQty;  // 1st Row, 2nd Column
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='2']//following::div[@column-index='2'][1]")
    WebElement currentStock;  // 1st Row, 3rd Column

    //2nd Row
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='2']")
    WebElement vehicleNameSecondRow;  // 2nd Row
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='2']//following::div[@column-index='1'][1]")
    WebElement averageMonthlySalesQtySecondRow;  // 2nd Row, 2nd Column
    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='2']//following::div[@column-index='2'][1]")
    WebElement currentStockSecondRow;  // 2nd Row, 3rd Column


    //Initializing the page objects
    public NewOrderVehiclesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getVehicleNameFirstRow() {
        String vehicleNameFirstRow = this.vehicleNameFirstRow.getText();
        return vehicleNameFirstRow;
    }

    public String getAverageMonthlySalesQty() {
        String averageMonthlySalesQty = this.averageMonthlySalesQty.getText();
        return averageMonthlySalesQty;
    }

    public String getCurrentStock() {
        String currentStock = this.currentStock.getText();
        return currentStock;
    }

    public String getVehicleNameSecondRow() {
        String vehicleNameSecondRow = this.vehicleNameSecondRow.getText();
        return vehicleNameSecondRow;
    }

    public String getAverageMonthlySalesQtySecondRow() {
        String averageMonthlySalesQtySecondRow = this.averageMonthlySalesQtySecondRow.getText();
        return averageMonthlySalesQtySecondRow;
    }
    
    public String getCurrentStockSecondRow() {
        String currentStockSecondRow = this.currentStockSecondRow.getText();
        return currentStockSecondRow;
    }

    

}
