package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBys({@FindBy(css = "svg.tileSVG")})
	List<WebElement> salesButton;
	
	@FindBy(xpath = "//span[contains(text(), 'Vehicle Sold')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'New Vehicle Qty')]//*[local-name()='tspan']")
	WebElement vechicalSold;
	
	@FindBy(xpath = "//span[contains(text(), 'Vehicle Sales')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg']//*[local-name()='tspan']")
	WebElement SalesRevenue;
	
	@FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, \"Yesterday's Booking\")]//*[local-name()='tspan']")
	WebElement vechicalBookedYesterday;
	
	@FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Delivery Blank')]//*[local-name()='tspan']")
	WebElement vehicleInvoicedNotDelivered;
	
	@FindBy(xpath = "//div[@id = 'sandbox-host']//*[@class = 'overlayGroup']//*[@class = 'calloutLabel']")
	WebElement salesTargetAchieved;
	
	@FindBys({@FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]")})
	List<WebElement> monthWiseSalesTrend;
	
	
	// Initializing the page objects
		public HomePage() {
			PageFactory.initElements(driver, this);
		}
		
		public void clickSalesTab() {			 	
			 WebElement salesTab = this.salesButton.get(6);
			 salesTab.click();
		}
		
		public String getVechicalSold() {
			String vechicalSold = this.vechicalSold.getText();
			return vechicalSold;
		}
		
		public String getSalesRevenue() {
			String salesRevenue = this.SalesRevenue.getText();
			return salesRevenue;
		}
		
		public String getVechicalBookedYesterday() {
			String vechicalBookedYesterday = this.vechicalBookedYesterday.getText();
			return vechicalBookedYesterday;
		}
		
		public String getVehicleInvoicedNotDelivered() {
			String vehicleInvoicedNotDelivered = this.vehicleInvoicedNotDelivered.getText();
			return vehicleInvoicedNotDelivered;
		}
		
		public String getSalesTargetAchieved() {
			String salesTargetAchieved = this.salesTargetAchieved.getText();
			return salesTargetAchieved;
		}
		
		public String getAprilMonthWiseSalesTrend() {
			WebElement aprilMonthSales = this.monthWiseSalesTrend.get(6);			
			return aprilMonthSales.getText();
		}


}
