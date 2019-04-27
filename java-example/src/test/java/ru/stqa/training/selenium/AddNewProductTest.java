package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AddNewProductTest extends TestBase {

    @Test
    public void addNewProduct() throws IOException {

        loginAsAdmin();
        //go to Catalog and click "Add New Product"
        driver.findElement(By.id("box-apps-menu")).findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();

        //fill in General tab
        Random randomId = new Random();
        String productName = "Chicken" + randomId.nextInt();
        fillGeneralTab(productName);

        //fill in Information tab
        driver.findElement(By.cssSelector("a[href=\"#tab-information\"]")).click();
        wait.until((WebDriver d) -> d.findElement(By.id("tab-information")));
        fillInformationTab();

        //fill in Prices tab
        driver.findElement(By.cssSelector("a[href=\"#tab-prices\"]")).click();
        wait.until((WebDriver d) -> d.findElement(By.id("tab-prices")));
        fillPricesTab();

        //submit and go to Catalog and check that product added
        driver.findElement(By.name("save")).click();
        wait.until((WebDriver d) -> d.findElement(By.name("catalog_form")));
        WebElement addedProduct = driver.findElement(By.linkText(productName));
        Assert.assertTrue(addedProduct != null);
    }

    private void fillPricesTab() {
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("99,99");
        Select currency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currency.selectByValue("EUR");
        driver.findElement(By.name("prices[USD]")).sendKeys("88,88");
        driver.findElement(By.name("prices[EUR]")).sendKeys("99,99");
        WebElement grossPriceUsd = driver.findElement(By.name("gross_prices[USD]"));
        grossPriceUsd.clear();
        grossPriceUsd.sendKeys("91,23");
        WebElement grossPriceEur = driver.findElement(By.name("gross_prices[EUR]"));
        grossPriceEur.clear();
        grossPriceEur.sendKeys("111,23");
    }

    private void fillInformationTab() {
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("toy");
        driver.findElement(By.name("short_description[en]")).sendKeys("Nice toy");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Really nice toy");
        driver.findElement(By.name("head_title[en]")).sendKeys("Funny Chicken");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Cool Chicken");
    }

    private void fillGeneralTab(String productName) throws IOException {
        driver.findElement(By.cssSelector("input[name=\"status\"][value=\"1\"]")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.name("code")).sendKeys("888");
        driver.findElement(By.cssSelector("input[name=\"product_groups[]\"][value=\"1-3\"]")).click();
        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("100");
        driver.findElement(By.name("date_valid_from")).sendKeys("26.04.2019");
        driver.findElement(By.name("date_valid_to")).sendKeys("26.04.2029");
        String imagePath = new File("src/test/resources/chicken.jpg").getCanonicalPath();
        driver.findElement(By.name("new_images[]")).sendKeys(imagePath);
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }



}
