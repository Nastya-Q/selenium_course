package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class CheckBrowserLogTest extends TestBase {

    @Test
    public void checkBrowserLogOnProductPage() {
        String productCssSelector = "a[href*=\"product_id=\"][title=\"Edit\"]";
        String notEmptyCategoryLink = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
        loginAsAdmin();
        driver.get(notEmptyCategoryLink);
        List<WebElement> products = driver.findElements(By.cssSelector(productCssSelector));
        for (int i = 0; i < products.size(); i++) {
            driver.get(notEmptyCategoryLink);
            List<WebElement> allProducts = driver.findElements(By.cssSelector(productCssSelector));
            allProducts.get(i).click();
            List<LogEntry> allLogs = driver.manage().logs().get("browser").getAll();
            Assert.assertEquals(0, allLogs.size());

            //example how to get logs of specific level
//            List<LogEntry> logErrors = driver.manage().logs().get("browser").getAll()
//                    .stream().filter(logEntry -> logEntry.getLevel() == Level.SEVERE).collect(Collectors.toList());
        }

    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

}
