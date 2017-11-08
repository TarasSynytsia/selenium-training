import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@RunWith(Parameterized.class)
public class HomeTask8 {

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
    public void logCapability() {
        driver.get("http://10.0.5.10/litecart/admin");

        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=login]")).click();

        List<WebElement> li = driver.findElements(By.xpath("//*[@id='app-']"));

        for (int i = 1; i <= li.size(); i++) {
            driver.findElement(By.xpath("//*[@id='app-'][" + i + "]")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());

            List<WebElement> inLi = driver.findElements(By.xpath("//*[@id='app-']/ul[contains(@class, 'docs')]/li"));

            for (int j = 1; j <= inLi.size(); j++) {
                driver.findElement(By.xpath("//*[@id='app-']/ul[contains(@class, 'docs')]/li[" + j + "]")).click();
                Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
            }

        }

    }

    @After
    public void dismantle() {
        driver.quit();
        driver = null;
    }


}
