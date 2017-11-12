package multilayered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page{
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void productPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=add_cart_product]")));

//      Get init counter state
        WebElement counter = driver.findElement(By.cssSelector("span.quantity"));
        int initCountState = Integer.parseInt(counter.getAttribute("textContent"));

//      Extra check for yellow duck as fot this element required additional steps to add to cart
        if (driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent").equals("Yellow Duck")) {
            Select dropdown = new Select(driver.findElement(By.cssSelector("select")));
            dropdown.selectByVisibleText("Small");
        }

//      Add the product to the cart
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();

//      Wait until counter of the items in the cart changes
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement counter = driver.findElement(By.cssSelector("span.quantity"));
                int currentCountState = Integer.parseInt(counter.getAttribute("textContent"));
                return initCountState < currentCountState;
            }
        });

//      Back to the main page after adding each element
        driver.findElement(By.cssSelector("#logotype-wrapper > a > img")).click();
    }
}
