package ru.stqa.training.selenium.page.object.pattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addProductToShopCart() {
        // if sizes dropdown list is present (e.g. as for yellow duck), some value should be selected
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            Select size = new Select(driver.findElement(By.name("options[Size]")));
            size.selectByIndex(1);
        }
        addToCartButton.click();
    }

    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    @FindBy(className = "quantity")
    public WebElement productCounter;
}
