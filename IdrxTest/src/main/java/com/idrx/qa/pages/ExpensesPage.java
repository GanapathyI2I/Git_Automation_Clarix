package com.idrx.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.idrx.qa.base.TestBase;

public class ExpensesPage extends TestBase {

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> totalExpenses;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> employeeExp;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> financeAndCharges;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> discounts;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> repairAndMaintenance;

    @FindBy(xpath = "//span[@class='textRun']/ancestor::visual-container[1]/..//visual-modern//*[local-name()='svg' and contains(@aria-label,'Expense Total amount')]")
    List<WebElement> others;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> expensesTrendCurrentMonth;

    @FindBy(xpath = "//*[@class = 'labelContainerGraphicsContext']//*[@class='label-container']//*[@class='label-tspan']")
    List<WebElement> expensesTrendPastMonth;

    //Initializing the page objects
    public ExpensesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getTotalExpenses() {
        String totalExpenses = this.totalExpenses.get(0).getText();
        return totalExpenses;
    }

    public String getEmployeeExp() {
        String employeeExp = this.employeeExp.get(1).getText();
        return employeeExp;
    }

    public String getFinanceAndCharges() {
        String financeAndCharges = this.financeAndCharges.get(2).getText();
        return financeAndCharges;
    }

    public String getDiscounts() {
        String discounts = this.discounts.get(4).getText();
        return discounts;
    }

    public String getRepairAndMaintenance() {
        String repairAndMaintenance = this.repairAndMaintenance.get(3).getText();
        return repairAndMaintenance;
    }

    public String getOthers() {
        String others = this.others.get(5).getText();
        return others;
    }

    public String getExpensesTrendCurrentMonth() {
        String expensesTrendCurrentMonth = this.expensesTrendCurrentMonth.get(1).getText();
        return expensesTrendCurrentMonth;
    }

    public String getExpensesTrendPastMonth() {
        String expensesTrendPastMonth = this.expensesTrendPastMonth.get(4).getText();
        return expensesTrendPastMonth;
    }
}
