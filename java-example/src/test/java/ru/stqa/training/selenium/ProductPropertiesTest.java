package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.ShopObjects.Product;

public class ProductPropertiesTest extends TestBase {

//    @BeforeClass
//    public void getAllElementsFromMainAndProductPages () {
//        //get elements from Main Page
//        driver.get("http://localhost/litecart");
//        WebElement campaign = driver.findElement(By.id("box-campaigns"));
//        WebElement productNameOnMainPage = campaign.findElement(By.className("name"));
//        WebElement productSalePriceOnMainPage =
//        //get elements from Product Page
//    }



    @Test
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

        Assert.assertEquals(productOnMainPage, productOnProductPage);
    }
}
