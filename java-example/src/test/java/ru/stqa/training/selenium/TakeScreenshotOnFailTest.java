package ru.stqa.training.selenium;


import com.google.common.io.Files;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TakeScreenshotOnFailTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String watchedLog;

    @BeforeClass
    public static void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public static void stop() {
        driver.quit();
        driver = null;
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog+= description + " failed!\n";
            System.out.println(watchedLog);

            //takes screenshot on fail
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                System.out.println("test");
                Files.copy(tempFile, new File("screen" + System.currentTimeMillis() + ".png"));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    };

    @Test
    public void searchInGoogleSuccess() {
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Software testing");
        element.submit();
    }

    @Test
    public void searchInGoogleFail() {
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q_"));
        element.sendKeys("Software testing");
        element.submit();
    }
}
