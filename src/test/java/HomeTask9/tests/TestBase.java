package HomeTask9.tests;

import org.junit.After;
import org.junit.Before;
import HomeTask9.apps.Application;

public class TestBase {

    private static ThreadLocal<Application> DRIVER = new ThreadLocal<>();
    Application apps;

    @Before
    public void start() {
        if (DRIVER.get() != null) {
            apps = DRIVER.get();
            return;
        }

        apps = new Application();
        DRIVER.set(apps);
    }

    @After
    public void tearDown() {
        if (DRIVER.get() != null) {
            apps.quit();
            apps = null;
        }
    }

}
