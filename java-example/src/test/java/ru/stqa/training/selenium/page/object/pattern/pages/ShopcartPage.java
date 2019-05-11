package ru.stqa.training.selenium.page.object.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ShopcartPage extends Page{

    public ShopcartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart");
    }
}
