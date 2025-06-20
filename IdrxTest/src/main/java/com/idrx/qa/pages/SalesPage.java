package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class SalesPage extends TestBase{
	
	@FindBy(xpath = "//span[contains(text(), 'Vehicle Sold')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'New Vehicle Qty')]//*[local-name()='tspan']")
	WebElement vechicalSold;
	
	@FindBy(xpath = "//span[contains(text(), 'Vehicle Sales')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg']//*[local-name()='tspan']")
	WebElement SalesRevenue;
	
	@FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, \"Yesterday's Booking\")]//*[local-name()='tspan']")
	WebElement vechicalBookedYesterday;
	
	@FindBy(xpath = "(//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan'])[7]")
	WebElement vechicalInvoicedYesterday;
	
	@FindBy(xpath = "(//*[local-name()='svg' and contains(@aria-label,'')]//*[local-name()='tspan'])[8]")
	WebElement vechicalInvoicedThisMonth;
	
	@FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'Delivery Blank')]//*[local-name()='tspan']")
	WebElement vehicleInvoicedNotDelivered;
	
	@FindBy(xpath = "//div[@id = 'sandbox-host']//*[@class = 'overlayGroup']//*[@class = 'calloutLabel']")
	WebElement salesTargetAchieved;
	
	@FindBys({@FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]")})
	List<WebElement> monthWiseSalesTrend;

	//Clicking Bookings tile
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> bookingTile;

	//Clicking Delivery tile
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> deliveryTile;

	//Clicking Discount tile
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> discountTile;

	//Clicking Finance tile
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> financeTile;

	
	// Initializing the page objects
			public SalesPage() {
				PageFactory.initElements(driver, this);
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
			
			public String getVehicleInvoicedYesterday() {
				String vehicleInvoicedNotDelivered = this.vechicalInvoicedYesterday.getText();			
				return vehicleInvoicedNotDelivered;
			}
			
			public String getVehicleInvoicedThisMonth() {
				String vehicleInvoicedNotDelivered = this.vechicalInvoicedThisMonth.getText();
				return vehicleInvoicedNotDelivered;
			}
			
			public String getVehicleInvoicedNotDelivered() {
				String vehicleInvoicedNotDelivered = this.vehicleInvoicedNotDelivered.getText();
				return vehicleInvoicedNotDelivered;
			}
			
			public String getSalesTargetAchieved() {
				String salesTargetAchieved = this.salesTargetAchieved.getText();
				return salesTargetAchieved;
			}
			
			public String getCurrentMonthWiseSalesTrend() {
				WebElement currentMonthSales = this.monthWiseSalesTrend.get(1);		
				return currentMonthSales.getText();
			}
			
			public String getLastMonthWiseSalesTrend() {
				WebElement lastMonthSales = this.monthWiseSalesTrend.get(3);			
				return lastMonthSales.getText();
			}

			public void clickBookingTab() {
				WebElement bookingTab = this.bookingTile.get(1);
				bookingTab.click();
			}

			public void clickDeliveryTab() {
				WebElement deliveryTab = this.deliveryTile.get(3);
				deliveryTab.click();
			}

			public void clickDiscountTab() {
				WebElement discountTab = this.discountTile.get(5);
				discountTab.click();
			}

			public void clickFinanceTab() {
				WebElement financeTab = this. financeTile.get(6);
				financeTab.click();
			}

}
