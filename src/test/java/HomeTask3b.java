import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class HomeTask3b {

    private WebDriver driver;

    @Before
    public void initiate() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.get("http://10.0.5.10/litecart/admin");

        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=login]")).click();
    }

    @Test
    public void clickEachElement() {
        List<WebElement> li = driver.findElements(By.xpath("//*[@id='app-']"));
// i'm proud of myself as i did it f* task (even no matter tha fact i saw the hint before)
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
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
