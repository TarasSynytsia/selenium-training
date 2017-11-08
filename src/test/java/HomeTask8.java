import com.google.common.io.Files;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(Parameterized.class)
public class HomeTask8 {

    //    private WebDriverWait wait;
    static String browser;

    @Parameterized.Parameter
    public EventFiringWebDriver driver;


    @Parameterized.Parameters
    public static WebDriver[] getDriver() {

        ChromeDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();

        return new WebDriver[]{new EventFiringWebDriver(new FirefoxDriver()), new EventFiringWebDriver(new ChromeDriver())};
    }

    @Before
    public void init() {
//        wait = new WebDriverWait(driver, 5);
        driver.register(new mListener());
        driver.manage().window().maximize();


    }

    @Test
    public void logCapability() {
        driver.get("http://10.0.5.10/litecart/admin");

//        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=username1]")).sendKeys("admin"); // exception as element "username1" not exists - should be created screenshot for each browser
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


    //    added nested class which catch "before By", "after By" and exception (for exceptions store screenshots) but still don't know how to get current browser name to use in screenshot name
    public static class mListener extends AbstractWebDriverEventListener {

        private String fileNameTimeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("------------------Start searching " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found" +
                    "------------------");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File("/Users/taras/Desktop/Screenshots/screen" + browser + fileNameTimeStamp + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("------------------" +
                    "Screenshot of exception - screen " + fileNameTimeStamp + ".png" + " was captured ------------------");
            System.out.println(throwable);
        }
    }


}
