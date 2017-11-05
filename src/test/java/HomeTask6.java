import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@RunWith(Parameterized.class)
public class HomeTask6 {

    private WebDriverWait wait;

    @Parameterized.Parameter
    public WebDriver driver;


    @Parameterized.Parameters
    public static WebDriver[] getDriver() {

        ChromeDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();

        return new WebDriver[]{new FirefoxDriver(), new ChromeDriver()};
    }

    @Before
    public void init() {
        wait = new WebDriverWait(driver, 5);

        driver.manage().window().maximize();
    }


    @Test
    public void cartOperations() {
//        1.	Open main page
        driver.get("http://10.0.5.10/litecart/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rslides1_s0 > a > img")));

        List<WebElement> listOfDucks = driver.findElements(By.xpath("//*[@id='box-most-popular']/div/ul/li"));

//       loop to pick each duck and add it to the cart
        for (int i = 1; i <= listOfDucks.size(); i++) {

//      2.	Open first product from the list
            driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[" + i + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=add_cart_product]")));

//      get init counter state
            WebElement counter = driver.findElement(By.cssSelector("span.quantity"));
            String initCountState = counter.getAttribute("textContent");

            if (driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent").equals("Yellow Duck")) {
                Select dropdown = new Select(driver.findElement(By.cssSelector("select[name=options\\[Size\\]]")));
                dropdown.selectByVisibleText("Small");
            }

//      3.	Add the product to the cart
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();

//      4.	Wait until counter of the items in the cart changes

            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement counter = driver.findElement(By.cssSelector("span.quantity"));
                    String currentCountState = counter.getAttribute("textContent");
                    if (initCountState.equals(currentCountState))
                        return false;
                    else
                        return true;
                }
            });

//      5.	Back to the main page
            driver.findElement(By.cssSelector("#logotype-wrapper > a > img")).click();

        }

//      6.	Open the Cart
        driver.findElement(By.cssSelector("#cart > a.link")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-summary > h2")));

//get quantity of rows in the table
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
//     Wait until all elements removed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart-wrapper > p > a")));
//      8.	Verify that 0 items in the cart after removal
        Assert.assertTrue(driver.findElement(By.xpath("//p/em[contains(text(), 'There are no items in your cart.')]")).isDisplayed());
    }

    @After
    public void dismantle() {
        driver.quit();
        driver = null;

    }
}
