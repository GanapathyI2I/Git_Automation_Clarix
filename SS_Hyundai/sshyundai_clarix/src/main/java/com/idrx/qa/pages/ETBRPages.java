package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ETBRPages extends TestBase {

	@FindBy(xpath = "//span[contains(text(), 'Enquiry')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Enq')]//*[local-name()='tspan']")
	WebElement Enquiry;

    @FindBy(xpath = "//span[contains(text(), 'Test Drive')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'TestDrive')]//*[local-name()='tspan']")
    WebElement TestDrive;

    @FindBy(xpath = "//span[contains(text(), 'Booking')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Booking')]//*[local-name()='tspan']")
    WebElement Booking;

    @FindBy(xpath = "//span[contains(text(), 'Retails')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'Retail')]//*[local-name()='tspan']")
    WebElement Retail;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'E2T')]//*[local-name()='tspan']")
    WebElement EnquiriesToTdE2T;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'E2B')]//*[local-name()='tspan']")
    WebElement EnquiriesToBookingE2B;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'E2R')]//*[local-name()='tspan']")
    WebElement EnquiriesToRetailE2R;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'T2B')]//*[local-name()='tspan']")
    WebElement TestDriveToBookingT2B;

    @FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label,'B2R')]//*[local-name()='tspan']")
    WebElement BookingToRetailB2R;

    //Clicking element for Bookings tile from ETBR tile
    @FindBy(xpath = "//*[@class='tileSVG']")
    List<WebElement> BookingsTile;

    //Clicking element for Retails tile from ETBR tile
    @FindBy(xpath = "//*[@class='tileSVG']")
    List<WebElement> RetailsTile;

    //Clicking element for Deliveries tile from ETBR tile
    @FindBy(xpath = "//*[@class='tileSVG']")
    List<WebElement> DeliveriesTile;


    //Initialize the page objects
    public ETBRPages() {
        PageFactory.initElements(driver, this);
    }

    //Get the value of the element
    public String getEnquiry() {
        return Enquiry.getText();
    }

    public String getTestDrive() {
        return TestDrive.getText();
    }

    public String getBooking() {
        return Booking.getText();
    }

    public String getRetail() {
        return Retail.getText();
    }

    public String getEnquiriesToTdE2T() {
        return EnquiriesToTdE2T.getText();
    }

    public String getEnquiriesToBookingE2B() {
        return EnquiriesToBookingE2B.getText();
    }

    public String getEnquiriesToRetailE2R() {
        return EnquiriesToRetailE2R.getText();
    }

    public String getTestDriveToBookingT2B() {
        return TestDriveToBookingT2B.getText();
    }

    public String getBookingToRetailB2R() {
        return BookingToRetailB2R.getText();
    }

    //Initializing object for Clicking element for Bookings tile from ETBR tile
    public void clickBookingsTile() {
        BookingsTile.get(3).click();
    }

    //Initializing object for Clicking element for Retails tile from ETBR tile
    public void clickRetailsTile() {
        RetailsTile.get(4).click();
    }

    //Initializing object for Clicking element for Deliveries tile from ETBR tile
    public void clickDeliveriesTile() {
        DeliveriesTile.get(5).click();
    }



    
    
    
}
