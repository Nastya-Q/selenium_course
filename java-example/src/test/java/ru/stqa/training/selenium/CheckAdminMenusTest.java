package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckAdminMenusTest extends TestBase {

    @Test
    public void clickAllAdminMenus() {
        loginAsAdmin();
        List<WebElement> mainMenus = driver.findElements(By.id("app-"));
        for (WebElement menu: mainMenus) {
            menu.click();
        }
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
