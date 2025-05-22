package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ServicePage extends TestBase {

    @FindBys({ @FindBy(xpath = "//*[@class='tileSVG']") })
    List<WebElement> menuTabs;

    // Initializing the page objects
    public ServicePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickServiceRevenueTab() {
        WebElement serviceRevenueTab = this.menuTabs.get(8);
        serviceRevenueTab.click();
    }

}
