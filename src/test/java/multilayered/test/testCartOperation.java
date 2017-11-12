package multilayered.test;

import org.junit.Assert;
import org.junit.Test;

public class testCartOperation extends testBase {

    @Test
    public void cartOperation() {
        app.openMainPage();
        app.mainPageOperations();

//      Assert that product elements were added
        Assert.assertTrue(app.quantityIsBigger());
        app.cartOperations();

//      Verify that product element were removed
        Assert.assertTrue(app.noElementAfterRemoving());
    }

}