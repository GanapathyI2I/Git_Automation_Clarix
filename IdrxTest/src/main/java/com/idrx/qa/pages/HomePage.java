package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.util.TestUtil;

public class HomePage extends TestBase {

	TestUtil testUtil = new TestUtil();

	@FindBys({ @FindBy(css = "svg.tileSVG") })
	List<WebElement> salesButton;

	// Clicking Service button from home button
	@FindBys({ @FindBy(xpath = "//*[@class='tileSVG']") })
	List<WebElement> serviceTile;

	// Clicking Billing and purchase tile from home page
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> billingAndPurchase;

	// Clicking Receivables tile from home page
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> receivablesTile;

	// Clicking Expenses tile from home page
	@FindBy(xpath = "//*[@class='tileSVG']")
	List<WebElement> expensesTile;

	@FindBys({ @FindBy(css = "svg.tileSVG") })
	List<WebElement> menuButton;

	// Initializing the page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void clickSalesTab() {
		WebElement salesTab = this.salesButton.get(6);
		salesTab.click();
	}

	public void clickServiceTile(String tileName) {
		testUtil.clickElement(serviceTile.get(11), "Service Tile");
	}

	public void clickBillingPurchaseTile() {
		// testUtil.clickElement(billingAndPurchase.get(16), "billingAndPurchase");
		WebElement billingPurchaseTile = this.billingAndPurchase.get(16);
		billingPurchaseTile.click();
	}

	public void clickReceivablesTile() {
		testUtil.clickElement(receivablesTile.get(26), "Receivables Tile");
	}

	public void clickExpensesTile() {
		TestUtil.scrollToElement(driver, expensesTile.get(44));
		testUtil.clickElement(expensesTile.get(44), "Expenses Tile");
	}

	public void clickStocksTab() {
		WebElement stocksTab = this.menuButton.get(24);
		stocksTab.click();
	}

	public void clickPayablesTab() {
		WebElement payablesTab = this.menuButton.get(28);
		payablesTab.click();
	}

	public void clickServiceTab() {
		WebElement serviceTab = this.menuButton.get(11);
		serviceTab.click();
	}

	public void clickProfitTab() throws InterruptedException {
		Thread.sleep(5000);
		TestUtil.scrollToElement(driver, this.menuButton.get(48));
		WebElement profitTab = this.menuButton.get(48);
		profitTab.click();
	}
}
