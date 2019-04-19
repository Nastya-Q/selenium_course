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
            List<WebElement> mainMenus = driver.findElements(By.id("app-"));
            mainMenus.get(i).click();
            List<WebElement> subMenus = driver.findElements(By.cssSelector("li[id^=\"doc\"]"));

            if (subMenus.size() > 0) {
                for (int j = 0; j < subMenus.size(); j++) {
                    List<WebElement> allSubMenus = driver.findElements(By.cssSelector("li[id^=\"doc\"]"));
                    allSubMenus.get(j).click();
                    isElementPresent(driver, By.tagName("h1"));
                }
            }

            isElementPresent(driver, By.tagName("h1"));
        }
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
