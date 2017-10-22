import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;

import static org.junit.Assume.assumeThat;

public class HomeTask1 {

    String os = System.getProperty("os.name");/*.toLowerCase();*/


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
        if (os.contains("Windows 10")){

            EdgeDriverManager.getInstance().setup();
            WebDriver driver = new EdgeDriver();
            driver.get("http://google.com/ncr");

            driver.quit();
        }
        else System.out.println("OS is unsupportable");
    }

    @Test
    public void testIE() {
        if (os.contains("Windows 10")){
        InternetExplorerDriverManager.getInstance().setup();
        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://google.com/ncr");

        driver.quit();
        }
        else System.out.println("OS is unsupportable");
    }


//Safari browser still not close after testÒ
    @Test
    public void testSafari() {
        if (os.contains("Mac OS X")){
        WebDriver driver = new SafariDriver();
        driver.get("http://google.com/ncr");

//        driver.quit();
        driver.close();
        }
        else System.out.println("OS is unsupportable");
    }
}