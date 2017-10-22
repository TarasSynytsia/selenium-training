import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;

public class HomeTask1 {

    @Test
    public void testChrome() {

        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
    }

    @Test
    public void testFirefox() {

        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
    }

    @Test
    public void testEdge() {
        EdgeDriverManager.getInstance().setup();
        WebDriver driver = new EdgeDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
    }

    @Test
    public void testIE() {
        InternetExplorerDriverManager.getInstance().setup();
        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
    }

    /*@Test
    public void testSafari() {

        WebDriver driver = new SafariDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
    }*/
}