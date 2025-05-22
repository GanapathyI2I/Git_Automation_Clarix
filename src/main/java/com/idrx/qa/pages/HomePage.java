package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBys({ @FindBy(css = "svg.tileSVG") })
	List<WebElement> menuButton;

	// Initializing the page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void clickSalesTab() {
		WebElement salesTab = this.menuButton.get(6);
		salesTab.click();
	}

	public void clickServiceTab() {
		WebElement serviceTab = this.menuButton.get(11);
		serviceTab.click();
	}
}
