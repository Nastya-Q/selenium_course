package ru.stqa.training.selenium.page.object.pattern.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.page.object.pattern.pages.MainPage;
import ru.stqa.training.selenium.page.object.pattern.pages.ProductPage;
import ru.stqa.training.selenium.page.object.pattern.pages.ShopcartPage;

import java.util.List;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;

    private MainPage mainPage;
    private ProductPage productPage;
    private ShopcartPage shopcartPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        shopcartPage = new ShopcartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductToShopcart() {
        mainPage.open();
        mainPage.clickAtProduct();
        productPage.addProductToShopCart();
    }

    public void waitForCounterUpdate(String expectedCounterValue) {
        wait.until(ExpectedConditions.attributeToBe(productPage.productCounter, "textContent", expectedCounterValue));
    }

    public int getElementsToRemoveCount() {
        return shopcartPage.removeButtons.size();
    }

    public int getCheckoutSummaryRows() {
        List<WebElement> tableRows = shopcartPage.getSummaryTable().findElements(By.className("item"));
        return tableRows.size();
    }

    public void removeElementFromShopcart() {
        WebElement tableBeforeUpdate = shopcartPage.getSummaryTable();
        wait.until(ExpectedConditions.elementToBeClickable(
                shopcartPage.removeButtons.get(0))).click(); //click remove when button is shown
        wait.until(ExpectedConditions.stalenessOf(tableBeforeUpdate));
    }

    public void openShopCartPage() {
        shopcartPage.open();
    }

    public String getEmptyShopcartNotification() {
        return shopcartPage.getTextForEmptyShopcart();
    }
}
