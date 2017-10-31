import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;

import static org.junit.Assume.assumeThat;

public class HomeTask1 {

    private WebDriver driver;

    @Before
    public void init() {
        FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();
        EdgeDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().arch32().setup();
    }


    @Test
    public void testChrome() {
        driver = new ChromeDriver();
        driver.get("http://google.com/ncr");
    }

    @Test
    public void testFirefox() {
        driver = new FirefoxDriver();
        driver.get("http://google.com/ncr");
    }

    @Test
    public void testEdge() {
        driver = new EdgeDriver();
        driver.get("http://google.com/ncr");
    }

    @Test
    public void testIE() {
        driver = new InternetExplorerDriver();
        driver.get("http://google.com/ncr");
    }

    @Test
    public void testSafari() {
        driver = new SafariDriver();
        driver.get("http://google.com/ncr");
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}