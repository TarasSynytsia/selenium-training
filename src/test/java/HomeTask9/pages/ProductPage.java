package HomeTask9.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Page {

    private String productUrl;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.images-wrapper > a")
    private WebElement duckPicture;

    @FindBy(css = "span.quantity")
    private WebElement ducksQuantity;

    @FindBy(css = "h1.title")
    public WebElement productTitle;

    @FindBy(css = "select[name=options\\[Size\\]]")
    public WebElement productSizeDropDown;

    @FindBy(css = "button[name=add_cart_product]")
    public WebElement addProductToCart;

    public ProductPage open(String url, String productUrl) {
        driver.get(url + productUrl);
        wait.until(ExpectedConditions.visibilityOf(duckPicture));
        return this;
    }

    public String countOnPage() {
        return ducksQuantity.getAttribute("textContent");
    }

    public String determineDuckType(WebElement x) {
        if (x.getText().contains("Purple Duck"))
            productUrl = "en/rubber-ducks-c-1/purple-duck-p-5";
        else if (x.getText().contains("Red Duck"))
            productUrl = "en/rubber-ducks-c-1/red-duck-p-3";
        else if (x.getText().contains("Green Duck"))
            productUrl = "en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2";
        else if (x.getText().contains("Yellow Duck"))
            productUrl = "en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1";
        else if (x.getText().contains("Blue Duck"))
            productUrl = "en/rubber-ducks-c-1/blue-duck-p-4";

        return productUrl;
    }

    public void isProductAddedToCart(String currentDuckCount) {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String currentCountState = ducksQuantity.getAttribute("textContent");
                return !currentDuckCount.equals(currentCountState);
            }
        });
    }


    @FindBy(css = "#logotype-wrapper > a > img")
    public WebElement backToMainPage;

}
