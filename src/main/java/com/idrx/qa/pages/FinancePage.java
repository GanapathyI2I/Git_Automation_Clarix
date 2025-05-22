package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;


public class FinancePage extends TestBase{
	
	@FindBy(xpath = "//span[contains(text(), 'No of Finance Qty')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label, 'New Vehicle Qty')]//*[local-name()='tspan']")
	WebElement noOfFinanceQty;
	
	@FindBy(xpath = "//span[contains(text(), 'Finance Penetration ')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg']//*[local-name()='tspan']")
	WebElement financePenetration;
	
	@FindBys({@FindBy(xpath = "//*[local-name()='svg' and contains(@aria-label, 'New Vehicle Qty')]//*[local-name()='tspan']")})
	List<WebElement> cashVsFinanceTotal;

	@FindBys({@FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]")})
	List<WebElement> monthWiseFinanceTrend;



// Initializing the page objects
			public FinancePage() {
				PageFactory.initElements(driver, this);
			}
			
			public String getNoOfFinanceQty() {
				String noOfFinanceQty = this.noOfFinanceQty.getText();
				return noOfFinanceQty;
			}
			
			public String getFinancePenetration() {
				String financePenetration = this.financePenetration.getText();
				return financePenetration;
			}
			
			public String getCashVsFinanceTotal() {
				WebElement cashVsFinanceTotal = this.cashVsFinanceTotal.get(1);
				return cashVsFinanceTotal.getText();
			}
			
            public String getCurrentMonthFinanceTrend() {
				WebElement currentMonthFinanceTrend = this.monthWiseFinanceTrend.get(1);		
				return currentMonthFinanceTrend.getText();
			}
			
			public String getPreviousMonthFinanceTrend() {
				WebElement previousMonthFinanceTrend = this.monthWiseFinanceTrend.get(5);			
				return previousMonthFinanceTrend.getText();
			}
            
             public String getCurrentMonthFinancePenetrationTrend() {
				WebElement currentMonthFinancePenetrationTrend = this.monthWiseFinanceTrend.get(9);		
				return currentMonthFinancePenetrationTrend.getText();
			}

             public String getPreviousMonthFinancePenetrationTrend() {
				WebElement previousMonthFinancePenetrationTrend = this.monthWiseFinanceTrend.get(13);		
				return previousMonthFinancePenetrationTrend.getText();
			}
           
			
}
