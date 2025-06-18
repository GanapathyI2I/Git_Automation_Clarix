package com.idrx.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class NewOrderP_APage extends TestBase {

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='2']")
    WebElement partsNameFirstRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='3']")
    WebElement partNoFirstRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='4']")
    WebElement averageMonthlySalesQtyFirstRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='2']//div[@aria-colindex='5']")
    WebElement currentStockFirstRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='2']")
    WebElement partsNameSecondRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='3']")
    WebElement partNoSecondRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='4']")
    WebElement averageMonthlySalesQtySecondRow;

    @FindBy(xpath = "//div[@class='interactive-grid innerContainer']//div[@aria-rowindex='3']//div[@aria-colindex='5']")
    WebElement currentStockSecondRow;

    //Initializing the page objects
    public NewOrderP_APage() {
        PageFactory.initElements(driver, this);
    }

    public String getPartsNameFirstRow() {
        return partsNameFirstRow.getText();
    }

    public String getPartNoFirstRow() {
        return partNoFirstRow.getText();
    }

    public String getAverageMonthlySalesQtyFirstRow() {
        return averageMonthlySalesQtyFirstRow.getText();
    }

    public String getCurrentStockFirstRow() {
        return currentStockFirstRow.getText();
    }

    public String getPartsNameSecondRow() {
        return partsNameSecondRow.getText();
    }

    public String getPartNoSecondRow() {
        return partNoSecondRow.getText();
    }

    public String getAverageMonthlySalesQtySecondRow() {
        return averageMonthlySalesQtySecondRow.getText();
    }

    public String getCurrentStockSecondRow() {
        return currentStockSecondRow.getText();
    }
    
    
}


