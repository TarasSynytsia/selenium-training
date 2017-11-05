import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class HomeTask5 {
    private UUID randProdName = UUID.randomUUID();
    private String productName = "Test_Product_"+randProdName;
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void init() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
//        FirefoxDriverManager.getInstance().setup();
//        driver = new FirefoxDriver();
        driver.get("http://10.0.5.10/litecart/admin");

        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=login]")).click();
    }


    @Test
    public void addNewItem() {
//        driver.findElement(By.xpath("//*[@id='app-'][2]")).click();
        driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(text(), 'Catalog')]")).click();

        driver.findElement(By.xpath("//a[@class='button'][contains(text(), ' Add New Product')]")).click();
//General tab filling
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/label[1]/input")).click();
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/span/input")).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/input[@name='code']")).sendKeys("TestCode");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/div/table/tbody/tr/td/input[@name='product_groups[]'][@value='1-3']")).click();
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/table/tbody/tr/td/input[@name='quantity']")).sendKeys(Keys.DELETE + "55");
//put “Product picture”
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys("/Users/taras/Projects/selenium-training/src/test/java/download.jpeg");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/input[@name='date_valid_from']")).sendKeys("01012018");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr/td/input[@name='date_valid_to']")).sendKeys("02032019");


//open tab "Prices"
        driver.findElement(By.xpath("//*[@id='content']/form/div/ul/li/a[contains(text(), 'Prices')]")).click();

//filling "Prices" tab
        driver.findElement(By.xpath("//*[@id='tab-prices']/table/tbody/tr/td/input[@name='purchase_price']")).sendKeys(Keys.DELETE + "15");
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='tab-prices']/table/tbody/tr/td/select[@name='purchase_price_currency_code']")));
        dropdown.selectByVisibleText("Euros");

        driver.findElement(By.xpath("//*[@id='tab-prices']/table/tbody/tr/td/input[@name='gross_prices[USD]']")).sendKeys(Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE + "21.06");
        driver.findElement(By.xpath("//*[@id='tab-prices']/table/tbody/tr/td/input[@name='gross_prices[EUR]']")).sendKeys(Keys.ARROW_RIGHT, Keys.BACK_SPACE + "18");


        driver.findElement(By.xpath("//*[@id='content']/form/p/span/button[contains(text(), 'Save')]")).click();
//        wait.until()


//Verify that new product appeared in catalog (on admin page)
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/form/table/tbody/tr/td/a[contains(text(), '" + productName + "')]")).getText().equals(productName));

    }

    @After
    public void disMantle() {
        driver.quit();
        driver = null;
    }
}
