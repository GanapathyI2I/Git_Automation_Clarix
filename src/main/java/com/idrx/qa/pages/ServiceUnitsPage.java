package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ServiceUnitsPage extends TestBase {

    @FindBys({
            @FindBy(xpath = "//span[contains(text(), 'No of Vehicle')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Service Jobcard')]//*[local-name()='tspan']") })
    List<WebElement> serviceUnitsFlowWebElements;

    @FindBys({
            @FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]") })
    List<WebElement> monthwiseOutflowWebElements;

    // Initializing the page objects

    public ServiceUnitsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNoOfVehicleInflow() {
        WebElement noOfVehicleInflow = this.serviceUnitsFlowWebElements.get(2);
        return noOfVehicleInflow.getText();
    }

    public String getNoOfVehicleOutflow() {
        WebElement noOfVehicleOutflow = this.serviceUnitsFlowWebElements.get(3);
        return noOfVehicleOutflow.getText();
    }

    public String getNoOfVehicleInflowYesterday() {
        WebElement noOfVehicleInflowYesterday = this.serviceUnitsFlowWebElements.get(0);
        return noOfVehicleInflowYesterday.getText();
    }

    public String getNoOfVehicleOutflowYesterday() {
        WebElement noOfVehicleOutflowYesterday = this.serviceUnitsFlowWebElements.get(1);
        return noOfVehicleOutflowYesterday.getText();
    }

    public String getNoOfVehicleOutflowCurrentMonth() {
        WebElement noOfVehicleOutflowCurrentMonth = this.monthwiseOutflowWebElements.get(9);
        return noOfVehicleOutflowCurrentMonth.getText();
    }

    public String getNoOfVehicleOutflowLastMonth() {
        WebElement noOfVehicleOutflowLastMonth = this.monthwiseOutflowWebElements.get(10);
        return noOfVehicleOutflowLastMonth.getText();
    }

}
