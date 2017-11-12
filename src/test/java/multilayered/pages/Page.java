package multilayered.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    WebDriver driver;
    WebDriverWait wait;

    Page(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver,10);
    }
}
