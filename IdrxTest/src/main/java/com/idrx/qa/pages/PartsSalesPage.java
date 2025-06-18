package com.idrx.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.util.TestUtil;
import java.util.List;

public class PartsSalesPage extends TestBase {

    TestUtil testUtil = new TestUtil();

    @FindBy(xpath = "//span[contains(text(), 'No of Qty')]/ancestor::visual-container/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Parts Qty Sales')]")
    WebElement NoofQty;

    @FindBy(xpath = "//span[contains(text(), 'Parts Sales')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Parts Sales')]")
    List<WebElement> partsSales;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> currentMonth;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> previousMonth;

    //Initializing the page objects
    public PartsSalesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNoofQty() {
        return NoofQty.getText();
    }

    public String getPartsSales() {
        String PartsSales = partsSales.get(1).getText();
        return PartsSales;
    }

    public String getCurrentMonth() {
        String currentMonth = this.currentMonth.get(1).getText();
        return currentMonth;
    }

    public String getPreviousMonth() {
        String previousMonth = this.previousMonth.get(2).getText();
        return previousMonth;
    }
}
