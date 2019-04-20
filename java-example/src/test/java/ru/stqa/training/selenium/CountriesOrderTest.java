package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesOrderTest extends TestBase {

    List<String> countriesWithZones= new ArrayList<>();

    @Test
    public void checkCountriesAndZonesOrdered() {
        loginAsAdmin();
        List<String> countriesFromPage = new ArrayList<>();
        List<String> countriesWithZones= new ArrayList<>();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //get countries from table
        WebElement countryTable = driver.findElement(By.className("dataTable"));
        List<WebElement> countryTableRows = countryTable.findElements(By.className("row"));

        //get country name from each row and zones if not zero
        for (WebElement row: countryTableRows) {
            String country  = row.findElement(By.xpath(".//td[5]")).getText();
            countriesFromPage.add(country);
            //checking zones
            Integer zonesCount = Integer.valueOf(row.findElement(By.xpath(".//td[6]")).getText());
            if (zonesCount > 0) {
                countriesWithZones.add(row.findElement(By.xpath(".//td[5]")).getCssValue("href"));
            }
        }

        //check countries ordered
        List<String> sortedCountries = new ArrayList<>();
        for(String s: countriesFromPage){
            sortedCountries.add(s);
        }
        Collections.sort(sortedCountries);
        Assert.assertTrue(sortedCountries.equals(countriesFromPage));

        //go to countries with Zones pages and check that Zones are ordered:

    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
