package multilayered.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void cartOperations() {
//      Open the Cart's page
        driver.findElement(By.cssSelector("#cart > a.link")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-summary > h2")));

//      get quantity of rows in the table
        List<WebElement> tableSize = driver.findElements(By.cssSelector("tr > td.item"));

//      verify if some items are presents in the table
        if (tableSize.size() == 0) Assert.fail("There are no elements in the cart");

//      7.	Remove all products from the cart one-by-one
        for (int i = 1; i <= tableSize.size(); i++) {
            WebElement table = driver.findElement(By.cssSelector("#order_confirmation-wrapper > table"));
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();

//      After each removal wait until table at the bottom of the page refreshed
            wait.until(ExpectedConditions.stalenessOf(table));
        }

//      Wait until all elements removed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart-wrapper > p > a")));
    }

    public int verifyAreNoElements() {
        List<WebElement> noElements = driver.findElements(By.xpath("//p/em[contains(text(), 'There are no items in your cart.')]"));

        return noElements.size();
    }

}
