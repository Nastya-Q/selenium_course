package ru.stqa.training.selenium.page.object.pattern.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShopcartTest extends TestBase {

    @Test
    public void checkShopcart() {
        int productsToAddCount = 3;
        //add products to the shopcart
        for (int i = 1; i <= productsToAddCount; i++) {
            String expectedCounterValue = String.valueOf(i);
            app.addProductToShopcart();
            app.waitForCounterUpdate(expectedCounterValue);
        }
//        //remove products from the shopcart
//        driver.get("http://localhost/litecart");
//        driver.findElement(By.linkText("Checkout »")).click();
//        while (driver.findElements(By.name("remove_cart_item")).size() > 0) { //if there are products to remove
//            removeItemFromShopCartAndCheckSummaryTable();
//        }
//        //after all products were removed from the shopcart
//        assertTrue(isElementPresent(driver, By.tagName("em")));
//        String finalText = driver.findElement(By.tagName("em")).getAttribute("textContent");
//        assertEquals("There are no items in your cart.", finalText);
    }

}
