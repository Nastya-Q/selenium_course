package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RunTestInCloud {

    public static final String USERNAME = "secret"; //replace to real one
    public static final String AUTOMATE_KEY = "secret"; //replace to real one
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

   @Test
   public void runSeleniumTestsInCloud() throws MalformedURLException {
       DesiredCapabilities caps = new DesiredCapabilities();
       caps.setCapability("browser", "Chrome");
       caps.setCapability("browser_version", "62.0");
       caps.setCapability("os", "Windows");
       caps.setCapability("os_version", "10");
       caps.setCapability("resolution", "1024x768");

       WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
       driver.get("http://www.google.com");
       WebElement element = driver.findElement(By.name("q"));

       element.sendKeys("BrowserStack");
       element.submit();

       System.out.println(driver.getTitle());
       driver.quit();
   }
}