package HomeTask9.tests;

import org.junit.Assert;
import org.junit.Test;

public class TestCartOperations extends TestBase {

    @Test
    public void cartOperations() {
        apps.openMainPage();

        int beforeAddQuantity = apps.beforeAddQuantity;

        apps.addProductToCart();

        int afterAddQuantity = apps.afterAddQuantity;

        apps.removeItemsFromCart();

        Assert.assertTrue(apps.zeroDucksInTheCart == 0);
        Assert.assertTrue(beforeAddQuantity < afterAddQuantity);
    }

}
