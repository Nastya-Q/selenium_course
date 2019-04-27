package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ShopCartAddRemoveProductTest extends TestBase {

    @Test
    public void addAndRemoveProducts() {
//        for (int i = 1; i < 4; i++) {
//
//        }
        //        //go to main page and open 1st product
        driver.get("http://localhost/litecart");
        driver.findElement(By.className("product")).click();
        //add product to the shopcart
        WebElement productCounter = driver.findElement(By.className("quantity"));
        // product size selected for products with sizes dropdown list (as yellow duck for example)
        if (isElementPresent(driver, By.name("options[Size]"))) {
            Select size = new Select(driver.findElement(By.name("options[Size]")));
            size.selectByIndex(1);
        }
        driver.findElement(By.name("add_cart_product")).click();
        //wait when shopcart product counter updated
        wait.until(ExpectedConditions.attributeToBe(productCounter, "textContent", "1"));
        String count = productCounter.getAttribute("textContent");

        System.out.println(count);

    }
}
