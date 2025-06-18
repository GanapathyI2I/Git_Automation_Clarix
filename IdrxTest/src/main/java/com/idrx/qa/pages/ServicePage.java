package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.util.TestUtil;

public class ServicePage extends TestBase {

    TestUtil testUtil = new TestUtil();

    @FindBys({@FindBy(xpath = "//*[@class='tileSVG']//*[@class='tile']")})
    List<WebElement> serviceTile;

    //Initializing the page objects
    public ServicePage() {
        PageFactory.initElements(driver, this);
    }

    // public void clickOnServiceTile(String tileName) {
    //     for (WebElement tile : serviceTile) {
    //         if (tile.getText().equals(tileName)) {
    //             tile.click();
    //         }
    //     }
    // }
    
    public void clickPartsSalesTile(String tileName) {
        testUtil.clickElement(serviceTile.get(12), "Parts Sales Tile");
    }

}
