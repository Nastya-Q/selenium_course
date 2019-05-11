package ru.stqa.training.selenium.page.object.pattern.tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShopcartTest extends TestBase {

    @Test
    public void checkShopcart() {

        //add products to the shopcart
        int productsToAddCount = 3;
        for (int i = 1; i <= productsToAddCount; i++) {
            String expectedCounterValue = String.valueOf(i);
            app.addProductToShopcart();
            app.waitForCounterUpdate(expectedCounterValue);
        }

        //remove products from the shopcart and checks summary table until last element
        app.openShopCartPage();
        while (app.getElementsToRemoveCount() > 0) {
            int itemRowsBefore = app.getCheckoutSummaryRows();
            app.removeElementFromShopcart();
            if (app.getElementsToRemoveCount() > 0) { //if there are products to remove for next iteration
                int itemRowsAfter = app.getCheckoutSummaryRows();
                assertEquals(1, itemRowsBefore - itemRowsAfter);
            }
        }

        //after all products were removed from the shopcart
        assertEquals("There are no items in your cart.", app.getEmptyShopcartNotification());
    }

}
