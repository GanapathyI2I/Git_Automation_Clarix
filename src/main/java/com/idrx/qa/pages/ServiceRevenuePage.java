package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ServiceRevenuePage extends TestBase {

    @FindBy(xpath = "(//span[contains(text(), 'Service Revenue')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Revenue')]//*[local-name()='tspan'])[1]")
    WebElement serviceRevenue;

    @FindBy(xpath = "//span[contains(text(), 'Avg Rev Per Vehicle')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'arpu')]//*[local-name()='tspan']")
    WebElement avgRevPerVehicle;

    @FindBys({
            @FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]") })
    List<WebElement> monthWiseRevenue;

    // Initializing the page objects

    public ServiceRevenuePage() {
        PageFactory.initElements(driver, this);
    }

    public String getServiceRevenue() {
        return serviceRevenue.getText();
    }

    public String getAvgRevPerVehicle() {
        return avgRevPerVehicle.getText();
    }

    public String getCurrentMonthRevenue() {
        WebElement currentMonthRevenue = this.monthWiseRevenue.get(1);
        return currentMonthRevenue.getText();
    }

    public String getPreviousMonthRevenue() {
        WebElement previousMonthRevenue = this.monthWiseRevenue.get(5);
        return previousMonthRevenue.getText();
    }

}
