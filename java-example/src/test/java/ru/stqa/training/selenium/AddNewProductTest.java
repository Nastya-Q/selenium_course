package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class AddNewProductTest extends TestBase {

    @Test
    public void addNewProduct() throws IOException {
        loginAsAdmin();
        //go to Catalog and click "Add New Product"
        driver.findElement(By.id("box-apps-menu")).findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();

        //fill in General tab
        driver.findElement(By.cssSelector("input[name=\"status\"][value=\"1\"]")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Chicken");
        driver.findElement(By.name("code")).sendKeys("888");
        driver.findElement(By.cssSelector("input[name=\"product_groups[]\"][value=\"1-3\"]")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("100");
        driver.findElement(By.name("date_valid_from")).sendKeys("26.04.2019");
        driver.findElement(By.name("date_valid_to")).sendKeys("26.04.2029");
        String imagePath = new File("src/test/resources/chicken.jpg").getCanonicalPath();
        driver.findElement(By.name("new_images[]")).sendKeys(imagePath);

        //fill in Information tab
        driver.findElement(By.cssSelector("a[href=\"#tab-information\"]")).click();
        wait.until((WebDriver d) -> d.findElement(By.name("manufacturer_id")));
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("toy");
        driver.findElement(By.name("short_description[en]")).sendKeys("Nice toy");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Really nice toy");
        driver.findElement(By.name("head_title[en]")).sendKeys("Funny Chicken");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Cool Chicken");

        //fill in Prices tab
        //submit and go to Catalog and check that product added
        System.out.println("test");
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }



}
