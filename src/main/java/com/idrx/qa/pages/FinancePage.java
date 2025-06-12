package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class FinancePage extends TestBase {

	@FindBy(xpath = "//span[contains(text(), 'No of Finance Qty')]/ancestor::visual-container[1]/..//*[local-name()='tspan']")
	WebElement noOfFinanceQty;

	@FindBy(xpath = "//span[contains(text(), 'Finance Penetration ')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='tspan']")
	WebElement financePenetration;

	@FindBy(xpath = "//*[@aria-label='Cash vs Finance ']/ancestor::visual-container-group/following-sibling::visual-container//*[local-name()='svg' and contains(@aria-label,'New Vehicle Qty')]")
	WebElement cashVsFinanceTotal;

	@FindBys({
			@FindBy(xpath = "//*[text()='Finance Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']") })
	List<WebElement> monthWiseFinanceTrend;

	@FindBys({
			@FindBy(xpath = "//*[text()='Finance Penetration Trend']/ancestor::div[@class='visualTitleArea']/following-sibling::visual-modern//*[local-name()='tspan']") })
	List<WebElement> monthWiseFinancePenetrationTrend;

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
		String cashVsFinanceTotal = this.cashVsFinanceTotal.getText();
		return cashVsFinanceTotal;
	}

	public String getCurrentMonthFinanceTrend() {
		WebElement currentMonthFinanceTrend = this.monthWiseFinanceTrend.get(1);
		return currentMonthFinanceTrend.getText();
	}

	public String getPreviousMonthFinanceTrend() {
		WebElement previousMonthFinanceTrend = this.monthWiseFinanceTrend.get(3);
		return previousMonthFinanceTrend.getText();
	}

	public String getCurrentMonthFinancePenetrationTrend() {
		WebElement currentMonthFinancePenetrationTrend = this.monthWiseFinancePenetrationTrend.get(1);
		return currentMonthFinancePenetrationTrend.getText();
	}

	public String getPreviousMonthFinancePenetrationTrend() {
		WebElement previousMonthFinancePenetrationTrend = this.monthWiseFinancePenetrationTrend.get(3);
		return previousMonthFinancePenetrationTrend.getText();
	}

}
