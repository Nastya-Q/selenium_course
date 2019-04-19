package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class CheckAdminMenusTest extends TestBase {

    @Test
    public void checkElement() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
