import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.InetAddress;

public class HomeTask3 {

    static WebDriver driver;
    String testHost;


    @BeforeClass
    public static void start() {

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

    }

    @Before
    public void loginPage() {
// This block validate if my home test source is available. If not used localhost/
        try {
            if (InetAddress.getByName("10.0.10.15").isReachable(5000)) {
                testHost = "http://10.0.10.15/litecart/admin/";
            } else
                testHost = "http://localhost/litecart/admin/";
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get(testHost);
//This block to login to test source
        WebElement userNameField = driver.findElement(By.cssSelector("input[name=username]"));
        userNameField.sendKeys("admin");

        WebElement passwordFIed = driver.findElement(By.cssSelector("input[name=password]"));
        passwordFIed.sendKeys("admin");

        WebElement login = driver.findElement(By.cssSelector("button[name=login]"));
        login.click();
    }

    @Test
    public void appearance() {

        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-adjust.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-template > a > span")).click();
            driver.findElement(By.cssSelector("#doc-logotype > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void catalog() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-th.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-catalog > a > span")).click();
            driver.findElement(By.cssSelector("#doc-product_groups > a > span")).click();
            driver.findElement(By.cssSelector("#doc-option_groups  > a > span")).click();
            driver.findElement(By.cssSelector("#doc-manufacturers > a > span")).click();
            driver.findElement(By.cssSelector("#doc-suppliers > a > span")).click();
            driver.findElement(By.cssSelector("#doc-delivery_statuses > a > span")).click();
            driver.findElement(By.cssSelector("#doc-sold_out_statuses > a > span")).click();
            driver.findElement(By.cssSelector("#doc-quantity_units > a > span")).click();
            driver.findElement(By.cssSelector("#doc-csv > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void countries() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-flag.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void currencies() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-money.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void customers() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-user.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-customers > a > span")).click();
            driver.findElement(By.cssSelector("#doc-csv > a > span")).click();
            driver.findElement(By.cssSelector("#doc-newsletter > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void geoZones() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-globe.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void languages() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-comments.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-languages > a > span")).click();
            driver.findElement(By.cssSelector("#doc-storage_encoding > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void modules() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-cube.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-jobs > a > span")).click();
            driver.findElement(By.cssSelector("#doc-customer > a > span")).click();
            driver.findElement(By.cssSelector("#doc-shipping > a > span")).click();
            driver.findElement(By.cssSelector("#doc-payment > a > span")).click();
            driver.findElement(By.cssSelector("#doc-order_total > a > span")).click();
            driver.findElement(By.cssSelector("#doc-order_success > a > span")).click();
            driver.findElement(By.cssSelector("#doc-order_action > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void orders() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-shopping-cart.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-orders > a > span")).click();
            driver.findElement(By.cssSelector("#doc-order_statuses > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void pages() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-file-text.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void reports() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-pie-chart.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-monthly_sales > a > span")).click();
            driver.findElement(By.cssSelector("#doc-most_sold_products > a > span")).click();
            driver.findElement(By.cssSelector("#doc-most_shopping_customers > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void settings() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-cogs.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-store_info > a > span")).click();
            driver.findElement(By.cssSelector("#doc-defaults > a > span")).click();
            driver.findElement(By.cssSelector("#doc-general > a > span")).click();
            driver.findElement(By.cssSelector("#doc-listings > a > span")).click();
            driver.findElement(By.cssSelector("#doc-images > a > span")).click();
            driver.findElement(By.cssSelector("#doc-checkout > a > span")).click();
            driver.findElement(By.cssSelector("#doc-advanced > a > span")).click();
            driver.findElement(By.cssSelector("#doc-security > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void slides() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-picture-o.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void tax() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-university.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-tax_classes > a > span")).click();
            driver.findElement(By.cssSelector("#doc-tax_rates > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void translations() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-book.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-search > a > span")).click();
            driver.findElement(By.cssSelector("#doc-scan > a > span")).click();
            driver.findElement(By.cssSelector("#doc-csv > a > span")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void users() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-star.fa-stack-1x.icon")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @Test
    public void vQmods() {
        if (driver.findElements(By.tagName("h1")) != null) {
            driver.findElement(By.cssSelector("#app- > a > span.fa-stack.fa-lg.icon-wrapper > i.fa.fa-plug.fa-stack-1x.icon")).click();
            driver.findElement(By.cssSelector("#doc-vqmods")).click();
            System.out.println("There is at least one h1 tag on current page!");
            System.out.println(driver.findElement(By.tagName("h1")).getText());
        } else
            System.out.println("No ant h1 tag on current page");
    }

    @After

    public void logout() {
        WebElement logout = driver.findElement(By.cssSelector("#sidebar > div.header > a:nth-child(5) > i"));
        logout.click();
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
