package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

//    @Before
//    public void start() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-fullscreen");
//        options.setCapability("unexpectedAlertBehaviour", "dismiss");
//        driver = new ChromeDriver(options);
//        System.out.println(((HasCapabilities) driver).getCapabilities());
//        //driver = new InternetExplorerDriver();
//        //driver = new FirefoxDriver();
//        wait = new WebDriverWait(driver, 10);
//    }

    @Before
    //launch firefox by old schema (without geckodriver, till FF v. ESR 45)
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        driver = new FirefoxDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
