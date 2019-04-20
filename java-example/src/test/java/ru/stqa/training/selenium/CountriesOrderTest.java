package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountriesOrderTest extends TestBase {

    @Test
    public void checkCountriesAndZonesOrdered() {
        loginAsAdmin();
        List<String> countriesFromPage = new ArrayList<>();
        List<String> countriesWithZones= new ArrayList<>();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //get rows from countries table
        WebElement countryTable = driver.findElement(By.className("dataTable"));
        List<WebElement> countryTableRows = countryTable.findElements(By.className("row"));

        //get country name from each row and zones if not zero
        for (WebElement row: countryTableRows) {
            String country  = row.findElement(By.xpath(".//td[5]")).getText();
            countriesFromPage.add(country);
            //checking zones
            Integer zonesCount = Integer.valueOf(row.findElement(By.xpath(".//td[6]")).getText());
            if (zonesCount > 0) {
                countriesWithZones.add(row.findElement(By.tagName("a")).getAttribute("href"));
            }
        }

        //check that countries are ordered
        List<String> sortedCountries = new ArrayList<>();
        sortedCountries.addAll(countriesFromPage);
        Collections.sort(sortedCountries);
        assertEquals(sortedCountries, countriesFromPage);

        //go to countries with Zones pages and check that Zones are ordered:
        if (countriesWithZones.size() > 0) {
            for (int i = 0; i < countriesWithZones.size(); i++) {
                List<String> zonesFromPage = new ArrayList<>();
                driver.get(countriesWithZones.get(i));
                //get table cells with zone names
                WebElement zonesTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zoneCells = zonesTable.findElements(By.cssSelector("input[name$=\"][name]\"]"));
                //get zone name from each row
                for (WebElement zone: zoneCells) {
                    zonesFromPage.add(zone.getAttribute("value"));
                }
                //check that zones are ordered
                List<String> sortedZones = new ArrayList<>();
                sortedZones.addAll(zonesFromPage);
                Collections.sort(sortedZones);
                assertEquals(sortedZones, zonesFromPage);
            }
        }
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
