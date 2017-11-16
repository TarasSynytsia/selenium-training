package HomeTask9.apps;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import HomeTask9.pages.CheckOutPage;
import HomeTask9.pages.MainPage;
import HomeTask9.pages.ProductPage;

public class Application {

    private WebDriver driver;
    private String url = "http://10.0.5.10/litecart/";

    private String initCountOfDucks;
    private String currentCountsOfDucks;
    public int zeroDucksInTheCart;

    public int beforeAddQuantity;
    public int afterAddQuantity;

    private MainPage mainPage;
    private CheckOutPage checkOutPage;
    private ProductPage productPage;

    public Application() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        checkOutPage = new CheckOutPage(driver);
        productPage = new ProductPage(driver);
    }

    public void openMainPage() {
        mainPage.open(url);
        driver.manage().window().maximize();
    }

    public void addProductToCart() {

        beforeAddQuantity = Integer.parseInt(productPage.countOnPage());

        for (int i = 1; i <= mainPage.listOfElements.size(); i++) {
            WebElement Duck = driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[" + i + "]"));

            productPage.open(url, productPage.determineDuckType(Duck));

            initCountOfDucks = productPage.countOnPage();//      Get init counter state

            if (productPage.productTitle.getAttribute("textContent").equals("Yellow Duck")) {
                Select dropdown = new Select(productPage.productSizeDropDown);
                dropdown.selectByVisibleText("Small");
            }

            productPage.addProductToCart.click(); //      Add the product to the cart

            currentCountsOfDucks = productPage.countOnPage();

            productPage.isProductAddedToCart(currentCountsOfDucks);

            productPage.backToMainPage.click(); // back to mainPage
        }

        afterAddQuantity = Integer.parseInt(productPage.countOnPage());
    }

    public void removeItemsFromCart() {

        checkOutPage.open(url);  // Open the Cart

        if (checkOutPage.tableSize.size() == 0)
//            Assert.fail("There are no elements in the cart");
            System.out.println("There are no elements in the cart"); // check if some items are present in the cart

        for (int i = 1; i <= checkOutPage.tableSize.size(); i++) {
            checkOutPage.removeElement();
        }

        if (checkOutPage.zeroElements())
            zeroDucksInTheCart = 0;
    }

    public void quit() {
        driver.quit();
    }
}
