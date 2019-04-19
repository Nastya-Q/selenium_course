package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckAdminMenusTest extends TestBase {

    @Test
    public void clickAllAdminMenus() {
        loginAsAdmin();
        List<WebElement> menus = driver.findElements(By.id("app-"));
        for (int i = 0; i < menus.size(); i++) {
            List<WebElement> allMenus = driver.findElements(By.id("app-"));
            allMenus.get(i).click();
            driver.navigate().back();
        }

    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
