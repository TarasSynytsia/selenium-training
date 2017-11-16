package HomeTask9.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckOutPage extends Page {

    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckOutPage open(String url) {
        driver.get(url + "en/checkout");
        wait.until(ExpectedConditions.visibilityOf(tableHeader));
        return this;
    }

    public void removeElement() {
//        WebElement tableInitState = table;
        WebElement table = driver.findElement(By.cssSelector("#order_confirmation-wrapper > table"));
        removeItem.click();
        wait.until(ExpectedConditions.stalenessOf(table)); //  After each removal wait until table at the bottom of the page refreshed
    }

    public boolean zeroElements() {
        return tableHeaderIsNotPresent.size() == 0;
    }

    @FindBy(css = "#box-checkout-summary > h2")
    private WebElement tableHeader;

    @FindBy(css = "tr > td.item")
    public List<WebElement> tableSize;

//    @FindBy(css = "#order_confirmation-wrapper > table")
//    public WebElement table;

    @FindBy(css = "button[name=remove_cart_item]")
    private WebElement removeItem;


    @FindBy(xpath = "//p/em[contains(text(), 'There are no items in your cart.')]")
    private WebElement zeroElements;

    @FindBy(css = "#box-checkout-summary > h2")
    private List<WebElement> tableHeaderIsNotPresent;


}
