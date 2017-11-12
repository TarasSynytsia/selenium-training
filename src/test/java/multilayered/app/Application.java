package multilayered.app;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import multilayered.pages.CartPage;
import multilayered.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    private WebDriver driver;
//    private WebDriverWait wait;

    private MainPage mainPage;
    private CartPage cartPage;

    public Application() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void openMainPage() {
        mainPage.openMainPage();
    }

    public void mainPageOperations() {
        mainPage.mainPageOperations();
    }

    public boolean quantityIsBigger() {
        return mainPage.beforeAddQuantity < mainPage.afterAddQuantity;

    }

    public void cartOperations() {
        cartPage.cartOperations();
    }

    public boolean noElementAfterRemoving() {
        return cartPage.verifyAreNoElements() > 0;
    }

}