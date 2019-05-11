package ru.stqa.training.selenium.page.object.pattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopcartPage extends Page{

    public ShopcartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/checkout");
    }

    public WebElement getSummaryTable() {
        return driver.findElement(By.className("dataTable"));
    }

    @FindBy(name = "remove_cart_item")
    public List<WebElement> removeButtons;

    public String  getTextForEmptyShopcart() {
        return driver.findElement(By.tagName("em")).getAttribute("textContent");
    }

}
