package ru.stqa.training.selenium.page.object.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart");
    }

    public void clickAtProduct() {
        product.click();
    }

    @FindBy(className = "product")
    public WebElement product;

}
