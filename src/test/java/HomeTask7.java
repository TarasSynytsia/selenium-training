import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

@RunWith(Parameterized.class)
public class HomeTask7 {

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
    public void openNewWindow() {

//  1.	Login to admin panel
        driver.get("http://10.0.5.10/litecart/admin/");
        driver.findElement(By.cssSelector("input[type='text'][name='username']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name='login']")).click();

//  2.	Open Countries
        driver.get("http://10.0.5.10/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countiesList = driver.findElements(By.xpath("//tr/td/a[@title='Edit']"));

//  3.	Open on Edit any country
        driver.findElement(By.xpath("//tr/td/a[@title='Edit']")).click();
//      get list of external links
        List<WebElement> extLinks = driver.findElements(By.cssSelector("tr > td > a[target='_blank']"));
//  7.	Repeat for all links on a page
        for (WebElement e : extLinks) {
            String originalW = driver.getWindowHandle();
            Set<String> existWs = driver.getWindowHandles();
            e.click();
//      Wait until new window opened
            String newW = wait.until(anyWindowOtherThan(existWs));
            driver.switchTo().window(newW);
//   6.	Close new window
            driver.close();
            driver.switchTo().window(originalW);
        }


        driver.findElement(By.cssSelector("button[name=cancel]")).click();


    }

    //stolen wait method (from presentation)
    private ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @After
    public void dismantle() {
        driver.quit();
        driver = null;

    }
}


