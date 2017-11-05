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

public class HomeTask5 {
    private String productName = "Test Product";
    private WebDriver driver;

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
        driver.findElement(By.xpath("//*[@id='app-'][2]")).click();
//        driver.findElement(By.xpath("//*[@id='app-']/a/span[contains(text(), 'Catalog')]")).click();

        driver.findElement(By.xpath("//*[@id='content']/div/a[contains(text(), ' Add New Product')]")).click();
//General tab filling
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[1]/td/label[1]/input")).click();
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[2]/td/span/input")).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[3]/td/input")).sendKeys("TestCode");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[7]/td/div/table/tbody/tr[4]/td[1]/input")).click();
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[8]/td/table/tbody/tr/td[1]/input")).sendKeys(Keys.DELETE + "55");
//put “Product picture”
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys("/Users/taras/Downloads/download.jpeg");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[10]/td/input")).sendKeys("01012018");
        driver.findElement(By.xpath("//*[@id='tab-general']/table/tbody/tr[11]/td/input")).sendKeys("02032019");


//Prices tab filling

        driver.findElement(By.xpath("//*[@id='content']/form/div/ul/li/a[contains(text(), 'Prices')]")).click();
        driver.findElement(By.xpath("//*[@id='tab-prices']/table[1]/tbody/tr/td/input")).sendKeys(Keys.DELETE + "15");
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='tab-prices']/table[1]/tbody/tr/td/select")));
        dropdown.selectByVisibleText("Euros");

        driver.findElement(By.xpath("//*[@id='tab-prices']/table[3]/tbody/tr[2]/td[2]/input")).sendKeys(Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE + "21.06");
        driver.findElement(By.xpath("//*[@id='tab-prices']/table[3]/tbody/tr[3]/td[2]/input")).sendKeys(Keys.DELETE + "18");


        driver.findElement(By.xpath("//*[@id='content']/form/p/span/button[contains(text(), 'Save')]")).click();


//Verify that new product appeared in catalog (on admin page)
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='content']/form/table/tbody/tr/td/a[contains(text(), '" + productName + "')]")).getText().equals(productName));

    }

    @After
    public void disMantle() {
        driver.quit();
        driver = null;
    }
}
