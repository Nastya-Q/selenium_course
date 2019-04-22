package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.ShopObjects.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductPropertiesTest extends TestBase {

    @Test
    //test checks that product name and prices match on Main and Product pages
    public void checkProductNameAndPricesMatch() {
        Product productOnMainPage = new Product();
        Product productOnProductPage = new Product();

        //Set Up Product attributes from main page
        driver.get("http://localhost/litecart");
        WebElement campaign = driver.findElement(By.id("box-campaigns"));
        String nameOnMainPage = campaign.findElement(By.className("name")).getText();
        String salePriceOnMainPage = campaign.findElement(By.className("campaign-price")).getText();
        String regularPriceOnMainPage = campaign.findElement(By.className("regular-price")).getText();
        productOnMainPage.setProductName(nameOnMainPage)
                .setRegularPrice(regularPriceOnMainPage).setSalesPrice(salePriceOnMainPage);

        //Set Up Product attributes from main page
        campaign.findElement(By.className("link")).click();
        WebElement productBox = driver.findElement(By.id("box-product"));
        String nameOnProductPage = productBox.findElement(By.className("title")).getText();
        String regularPriceOnProductPage = productBox.findElement(By.className("regular-price")).getText();
        String salePriceOnProductPage = productBox.findElement(By.className("campaign-price")).getText();
        productOnProductPage.setProductName(nameOnProductPage)
                .setRegularPrice(regularPriceOnProductPage).setSalesPrice(salePriceOnProductPage);

        assertEquals(productOnMainPage, productOnProductPage);
    }

    @Test
    //test checks Font Styles for regular and sale prices on main page
    public void checkPriceStylesOnMainPage() {
        driver.get("http://localhost/litecart");
        WebElement regularPrice = driver.findElement(By.id("box-campaigns"))
                .findElement(By.className("regular-price"));
        WebElement salePrice = driver.findElement(By.className("campaign-price"));
        //get Regular price properties
        String regularPriceColor = regularPrice.getCssValue("color");
        String regularPriceFontSize = regularPrice.getCssValue("font-size");
        String regularPriceFontStyle = regularPrice.getCssValue("text-decoration");

        //get Sales price properties
        String salePriceColor = salePrice.getCssValue("color");
        String salePriceFontSize = salePrice.getCssValue("font-size");
        String salePriceFontWeight = salePrice.getCssValue("font-weight");

        // check that sale price font is bigger than regular price font
        float salePriceFontSizeNumber = Float
                .parseFloat(salePriceFontSize.replaceAll("[^\\.0123456789]", ""));
        float regularPriceFontSizeNumber = Float
                .parseFloat(regularPriceFontSize.replaceAll("[^\\.0123456789]", ""));
        assertTrue(salePriceFontSizeNumber > regularPriceFontSizeNumber);

        // Checks for SALE price
        // check that sale price font is bold (700 in Chrome, 900 in FireFox)
        assertTrue(salePriceFontWeight.equals("700") || salePriceFontWeight.equals("900"));
        // check that color for Sale Price is red ( g = b = 0)
        String[] rgbaSalePrice = parseRgbaString(salePriceColor);
        String salePriceColorG = rgbaSalePrice[1];
        String salePriceColorB = rgbaSalePrice[2];
        String salePriceColorR = rgbaSalePrice[0];
        assertTrue(salePriceColorG.equals("0") && salePriceColorB.equals("0")
                && !salePriceColorR.equals("0"));

        // Checks for REGULAR price
        // check that font is strike-through text
        assertTrue(regularPriceFontStyle.contains("line-through"));
        // check that font color is grey (r = g = b)
        String[] rgbaRegularPrice = parseRgbaString(regularPriceColor);
        String regularPriceColorR = rgbaRegularPrice[0];
        String regularPriceColorG = rgbaRegularPrice[1];
        String regularPriceColorB = rgbaRegularPrice[2];
        assertTrue(regularPriceColorR.equals(regularPriceColorG)
                && regularPriceColorR.equals(regularPriceColorB));
    }

    @Test
    //test checks Font Styles for regular and sale prices on Product page
    public void checkPriceStylesOnProductPage() {
        driver.get("http://localhost/litecart");
        WebElement campaign = driver.findElement(By.id("box-campaigns"));
        campaign.findElement(By.className("link")).click();
        WebElement productBox = driver.findElement(By.id("box-product"));
        WebElement regularPrice = productBox.findElement(By.className("regular-price"));
        WebElement salePrice = productBox.findElement(By.className("campaign-price"));
        //get Regular price properties
        String regularPriceColor = regularPrice.getCssValue("color");
        String regularPriceFontSize = regularPrice.getCssValue("font-size");
        String regularPriceFontStyle = regularPrice.getCssValue("text-decoration");

        //get Sales price properties
        String salePriceColor = salePrice.getCssValue("color");
        String salePriceFontSize = salePrice.getCssValue("font-size");
        String salePriceFontWeight = salePrice.getCssValue("font-weight");

        // check that sale price font is bigger than regular price font
        float salePriceFontSizeNumber = Float
                .parseFloat(salePriceFontSize.replaceAll("[^\\.0123456789]", ""));
        float regularPriceFontSizeNumber = Float
                .parseFloat(regularPriceFontSize.replaceAll("[^\\.0123456789]", ""));
        assertTrue(salePriceFontSizeNumber > regularPriceFontSizeNumber);

        // Checks for SALE price
        // check that sale price font is bold (700 in Chrome, 900 in FireFox)
        assertTrue(salePriceFontWeight.equals("700") || salePriceFontWeight.equals("900"));
        // check that color for Sale Price is red ( g = b = 0, r != 0)
        String[] rgbaSalePrice = parseRgbaString(salePriceColor);
        String salePriceColorG = rgbaSalePrice[1];
        String salePriceColorB = rgbaSalePrice[2];
        String salePriceColorR = rgbaSalePrice[0];
        assertTrue(salePriceColorG.equals("0") && salePriceColorB.equals("0")
                && !salePriceColorR.equals("0"));
        // Checks for REGULAR price
        // check that font is strike-through text
        assertTrue(regularPriceFontStyle.contains("line-through"));
        // check that font color is grey (r = g = b)
        String[] rgbaRegularPrice = parseRgbaString(regularPriceColor);
        String regularPriceColorR = rgbaRegularPrice[0];
        String regularPriceColorG = rgbaRegularPrice[1];
        String regularPriceColorB = rgbaRegularPrice[2];
        assertTrue(regularPriceColorR.equals(regularPriceColorG)
                && regularPriceColorR.equals(regularPriceColorB));
    }

    private String[] parseRgbaString(String salePriceColor) {
        String rgbaStringForSalePrice = salePriceColor.replaceAll("[^\\,0123456789]", "");
        String[] rgbSalePriceRgbaArray = rgbaStringForSalePrice.split(",");
        return rgbSalePriceRgbaArray;
    }
}
