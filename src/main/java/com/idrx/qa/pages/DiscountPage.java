package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class DiscountPage extends TestBase {

	@FindBy(xpath = "//span[contains(text(), 'No of Discount Units')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='tspan']")
	WebElement noOfDiscountUnits;

	@FindBy(xpath = "//span[contains(text(), 'Total Discount Value')]/ancestor::visual-container[1]/..//visual-modern//*[local-name()='tspan']")
	WebElement totalDiscountValue;

	@FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text() = 'Units']/ancestor::div[@class='contentOuterContainer']/..//*[@class='fill ui-role-button-fill sub-selectable']")
	WebElement unitsButton;

	@FindBy(xpath = "//*[@class='content text ui-role-button-text selected' and text() = 'Value']/ancestor::div[@class='contentOuterContainer']/..//*[@class='fill ui-role-button-fill sub-selectable']")
	WebElement valueButton;

	@FindBy(xpath = "//div[h3[text()='Model wise Discounts']]//following::div[@role='grid']//div[@role='columnheader' and text()='QTY']/ancestor::div[@role='grid']//div[@role='gridcell' and @column-index='0' and text()='Total']//following-sibling::div[@column-index='1']")
	WebElement totalDiscountQty;

	@FindBy(xpath = "//div[h3[text()='Model wise Discounts']]//following::div[@role='grid']//div[@role='columnheader' and text()='QTY']/ancestor::div[@role='grid']//div[@role='gridcell' and @column-index='0' and text()='Total']//following-sibling::div[@column-index='2']")
	WebElement totalDiscountAmount;

	@FindBys({
			@FindBy(xpath = "//*[@class = \"labelContainerGraphicsContext\"]//*[@class=\"label-container\"]//*[@class=\"label-tspan\"]") })
	List<WebElement> monthWiseDiscounts;

	// Initializing the page objects
	public DiscountPage() {
		PageFactory.initElements(driver, this);
	}

	public String getNoOfDiscountUnits() {
		String noOfDiscountUnits = this.noOfDiscountUnits.getText();
		return noOfDiscountUnits;
	}

	public String getTotalDiscountValue() {
		String totalDiscountValue = this.totalDiscountValue.getText();
		return totalDiscountValue;
	}

	public String getTotalDiscountQty() {
		String totalDiscountQty = this.totalDiscountQty.getText();
		return totalDiscountQty;
	}

	public String getTotalDiscountAmount() {
		String totalDiscountAmount = this.totalDiscountAmount.getText();
		return totalDiscountAmount;
	}

	public String getCurrentMonthDiscount() {
		WebElement currentMonthDiscount = this.monthWiseDiscounts.get(2);
		return currentMonthDiscount.getText();
	}

	public String getPreviousMonthDiscount() {
		WebElement previousMonthDiscount = this.monthWiseDiscounts.get(6);
		return previousMonthDiscount.getText();
	}

	public void clickUnitsButton() {
		WebElement unitsButton = this.unitsButton;
		unitsButton.click();
	}

	public void clickValueButton() {
		WebElement valueButton = this.valueButton;
		valueButton.click();
	}

}
