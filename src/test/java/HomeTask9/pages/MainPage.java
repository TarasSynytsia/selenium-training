package HomeTask9.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(lonelyDuck));
    }

    @FindBy(css = "#rslides1_s0 > a > img")
    private WebElement lonelyDuck;

    @FindBy(xpath = "//*[@id='box-most-popular']/div/ul/li")
    public List<WebElement> listOfElements;

}
