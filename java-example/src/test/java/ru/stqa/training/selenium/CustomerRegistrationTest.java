package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class CustomerRegistrationTest extends TestBase {

    @Test
    public void registerCustomer() {
        Random randomId = new Random();
        String email = "test" + randomId.nextInt() + "@test.com" ;
        String password = "test";
        driver.get("http://localhost/litecart");
        driver.findElement(By.name("login_form")).findElement(By.linkText("New customers click here")).click();
        //fill in the form and submit
        fillRegistrationForm(email, password);
        driver.findElement(By.name("create_account")).click();
        // logout and then login-logout again
        driver.findElement(By.id("box-account")).findElement(By.linkText("Logout")).click();
        WebElement loginForm = driver.findElement(By.name("login_form"));
        loginForm.findElement(By.name("email")).sendKeys(email);
        loginForm.findElement(By.name("password")).sendKeys(password);
        loginForm.findElement(By.name("login")).click();
        driver.findElement(By.id("box-account")).findElement(By.linkText("Logout")).click();
    }

    private void fillRegistrationForm(String email, String password) {
        driver.findElement(By.name("firstname")).sendKeys("Ana");
        driver.findElement(By.name("lastname")).sendKeys("Qa");
        driver.findElement(By.name("address1")).sendKeys("100 Park Ave");
        driver.findElement(By.name("postcode")).sendKeys("98989");
        driver.findElement(By.name("city")).sendKeys("New York");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+456577888");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        Select countrySelector = new Select(driver.findElement(By.name("country_code")));
        countrySelector.selectByValue("US");
    }
}
