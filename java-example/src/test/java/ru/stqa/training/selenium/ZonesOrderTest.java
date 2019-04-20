package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ZonesOrderTest extends TestBase {

    @Test
    public void checksZonesOrder() {
        //find and save links to pages for countries with zones
        List<String> countriesLinks = new ArrayList<>();
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        WebElement countriesTable = driver.findElement(By.className("dataTable"));
        List<WebElement> countriesRows = countriesTable.findElements(By.className("row"));
        for (WebElement country : countriesRows) {
            String link = country.findElement(By.tagName("a")).getAttribute("href");
            countriesLinks.add(link);
        }

        //go to each country page and check if zones are ordered by alphabet
        for (String page : countriesLinks) {
            List<String> zonesFromPage = new ArrayList<>();
            driver.get(page);
            WebElement zonesTable = driver.findElement(By.id("table-zones"));
            List<WebElement> zonesCell = zonesTable.findElements(By.cssSelector("select[name*=\"zone_code\"]"));
            //get selected zone from dropdown lists
            for (WebElement zoneDropDown : zonesCell) {
                List<WebElement> zones = zoneDropDown.findElements(By.tagName("option"));
                for (WebElement option : zones) {
                    if (option.isSelected()) {
                        zonesFromPage.add(option.getText());
                    }
                }
            }
            //check that zones are ordered
            List<String> sortedZones = new ArrayList<>();
            sortedZones.addAll(zonesFromPage);
            Collections.sort(sortedZones);
            assertEquals(sortedZones, zonesFromPage);
        }
    }

    private void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
