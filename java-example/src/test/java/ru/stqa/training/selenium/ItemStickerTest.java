package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemStickerTest extends TestBase {

    @Test
    //test checks if all items on main page have exactly 1 sticker
    public void checkStickerTest() {
        int elementsWithoutStickers = 0;
        int elementsWithMoreThanOneSticker = 0;

        driver.get("http://localhost/litecart");
        List<WebElement> items = driver.findElements(By.cssSelector("[class^=\"product\"]"));
        for (WebElement item: items) {
            List<WebElement> stickers = item.findElements(By.cssSelector("[class^=\"sticker\"]"));
            if (stickers.size() == 0) {
                elementsWithoutStickers ++;
            }
            if (stickers.size() > 1) {
                elementsWithMoreThanOneSticker ++;
            }
        }

        Assert.assertEquals("Elements without stickers", 0, elementsWithoutStickers);
        Assert.assertEquals("Elements with more than one sticker", 0, elementsWithMoreThanOneSticker);
    }
}
