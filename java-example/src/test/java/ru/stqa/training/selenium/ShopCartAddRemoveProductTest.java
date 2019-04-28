package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShopCartAddRemoveProductTest extends TestBase {

    @Test
    public void addAndRemoveProducts() {
        int productsToAddCount = 3;
        //add products to the shopcart
        for (int i = 1; i <= productsToAddCount; i++) {
            addProductToShopcart();
            //check that items counter in shopcart was updated
            WebElement productCounter = driver.findElement(By.className("quantity"));
            String expectedCounterValue = String.valueOf(i);
            wait.until(ExpectedConditions.attributeToBe(productCounter, "textContent", expectedCounterValue));
        }
        //remove products from the shopcart
        driver.get("http://localhost/litecart");
        driver.findElement(By.linkText("Checkout »")).click();
        while (driver.findElements(By.name("remove_cart_item")).size() > 0) { //if there are products to remove
            removeItemFromShopCartAndCheckSummaryTable();
        }
        //after all products were removed from the shopcart
        assertTrue(isElementPresent(driver, By.tagName("em")));
        String finalText = driver.findElement(By.tagName("em")).getAttribute("textContent");
        assertEquals("There are no items in your cart.", finalText);
    }

    private void removeItemFromShopCartAndCheckSummaryTable() {
        WebElement checkoutTableBeforeItemRemoval = driver.findElement(By.className("dataTable"));
        int itemRowsBeforeRemoval = checkoutTableBeforeItemRemoval.findElements(By.className("item")).size();
        List<WebElement> elementsToRemove = driver.findElements(By.name("remove_cart_item"));
        wait.until(ExpectedConditions.elementToBeClickable(elementsToRemove.get(0))).click(); //click remove when button is shown
        wait.until(ExpectedConditions.stalenessOf(checkoutTableBeforeItemRemoval)); //wait until old table disappears
        if (driver.findElements(By.name("remove_cart_item")).size() > 0) { //if there are products to remove
            WebElement checkoutTableAfterItemRemoval = driver.findElement(By.className("dataTable"));
            int itemRowsAfterRemoval = checkoutTableAfterItemRemoval.findElements(By.className("item")).size();
            assertEquals(1, (itemRowsBeforeRemoval - itemRowsAfterRemoval)); //check that items removed from table
        }
    }

    private void addProductToShopcart() {
        driver.get("http://localhost/litecart");
        driver.findElement(By.className("product")).click();
        // if sizes dropdown list is present (e.g. as for yellow duck), some value should be selected
        if (isElementPresent(driver, By.name("options[Size]"))) {
            Select size = new Select(driver.findElement(By.name("options[Size]")));
            size.selectByIndex(1);
        }
        driver.findElement(By.name("add_cart_product")).click();
    }
}
