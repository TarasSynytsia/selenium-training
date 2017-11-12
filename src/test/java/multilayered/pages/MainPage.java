package multilayered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends Page {

    public int beforeAddQuantity;
    public int afterAddQuantity;

    private ProductPage productPage = new ProductPage(driver);

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    public void openMainPage() {
        driver.get("http://10.0.5.10/litecart/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rslides1_s0 > a > img")));
    }


    public void mainPageOperations() {

//      Get quantity of products should be added to cart
        List<WebElement> listOfDucks = driver.findElements(By.xpath("//*[@id='box-most-popular']/div/ul/li"));


//      Get quantity of product element already added products before initial adding procedure
        beforeAddQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));

//      Pick the ducks quant to add the same quant to the cart
        for (int i = 1; i <= listOfDucks.size(); i++) {

//      Open first product from the list
            driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[" + i + "]")).click();
//      Open product page of particular element
            productPage.productPage();
        }
//      Get quantity of product element on main page after adding procedure
        afterAddQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));
    }
}
