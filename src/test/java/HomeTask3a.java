import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class HomeTask3a {
    private WebDriver driver;

    @Before
    public void init() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://10.0.5.10/litecart/admin/");
    }

    @Test
    public void lookForH1() {

        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=login]")).click();

        List<WebElement> listElements = driver.findElements(By.cssSelector("#box-apps-menu li"));
        //Copy and convert WebElement list to String list
        List<String> strLi = new ArrayList<String>();
        for (WebElement e : listElements) {
            strLi.add(e.getText());
        }
        //iterate each element of list elements
        for (int i = 0; i < listElements.size(); i++) {
            //pick each list element using xapth (By element name - used from String list)
            driver.findElement(By.xpath("//*[@id=\"app-\"]/a/span[contains(text(), '" + strLi.get(i) + "')]")).click();
            // Check if 'h1' tag is present on current page
            Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
            //collect all sumbenu items into WebElement list
            List<WebElement> inListElements = driver.findElements(By.cssSelector("#box-apps-menu > #app- ul li"));
            //Copy and convert WebElement list to String list
            List<String> inStrLi = new ArrayList<String>();
            for (WebElement iE : inListElements) {
                inStrLi.add(iE.getText());
            }

            //pick each list element using xapth (By element name - used from String list)
            for (int j = 0; j < inStrLi.size(); j++) {
            //pick each list element using xapth (By element name - used from String list)
                driver.findElement(By.xpath("//*[@id=\"app-\"]/ul/li/a/span[contains(text(), '" + inStrLi.get(j) + "')]")).click();
            }
            // Check if 'h1' tag is present on current page
            Assert.assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}