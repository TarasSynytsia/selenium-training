import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;

public class HomeTask4 {

    private WebDriver driver;

    @Before
    public void init() {
//        FirefoxDriverManager.getInstance().setup();
//        driver = new FirefoxDriver();

//        InternetExplorerDriverManager.getInstance().arch32().setup();
//        driver = new InternetExplorerDriver();

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

//        driver = new SafariDriver();

        //1.	Open main page
        driver.get("http://10.0.5.10/litecart/");
    }

    @Test
    public void checkPageCorrectness() {

        //2.	Select first product in Campaigns section
        WebElement campaignElements = driver.findElement(By.cssSelector("#box-campaigns li a"));
        WebElement campaignPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        WebElement regularPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));

        String productName = campaignElements.getAttribute("title");
        /*System.out.println("productName "+productName);*/

        String sCampaignPrice = campaignPrice.getAttribute("textContent");
        /*System.out.println("campaignPrice "+sCampaignPrice);*/

        String sRegularPrice = regularPrice.getAttribute("textContent");
        /*System.out.println("regularPrice "+sRegularPrice);*/

        String regularPriceColor = regularPrice.getCssValue("color");
        /*System.out.println("regularPriceColor "+regularPriceColor);*/
        String regularPriceStrike = regularPrice.getCssValue("text-decoration-line");
        /*System.out.println("regularPriceStrike "+regularPriceStrike);*/

        String campaignPriceColor = campaignPrice.getCssValue("color");
        /*System.out.println("campaignPriceColor "+campaignPriceColor);*/
        String campaignPriceBold = campaignPrice.getCssValue("font-weight");
        /*System.out.println("campaignPriceBold "+campaignPriceBold);*/

        //3.	Open Product Page (click on product)
        campaignElements.click();




        //Select product on Item Page
        WebElement itemPage = driver.findElement(By.cssSelector("#box-product h1"));
        WebElement itemRegularPrice = driver.findElement(By.cssSelector("#box-product div > .regular-price"));
        WebElement itemCampaignPrice = driver.findElement(By.cssSelector("#box-product div > .campaign-price"));

        //a.	Product Name is equal on Main page and Item Page
        Assert.assertEquals(productName, itemPage.getAttribute("textContent"));

        //b.	Prices (discount and regular) are equal on both pages
        Assert.assertEquals(sRegularPrice, itemRegularPrice.getAttribute("textContent"));
        Assert.assertEquals(sCampaignPrice, itemCampaignPrice.getAttribute("textContent"));

        //c.	Regular price is gray and strike
        Assert.assertEquals(regularPriceColor, itemRegularPrice.getCssValue("color"));
        Assert.assertEquals(regularPriceStrike, itemRegularPrice.getCssValue("text-decoration-line"));

        //d.	Campaigns price is red and bold on both pages
        Assert.assertEquals(campaignPriceColor, itemCampaignPrice.getCssValue("color"));
        Assert.assertEquals(campaignPriceBold, itemCampaignPrice.getCssValue("font-weight"));
    }

    @After
    public void tearDown() {
        driver.close();
        driver = null;
    }

}
