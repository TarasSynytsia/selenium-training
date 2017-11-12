package multilayered.test;

import multilayered.app.Application;
import org.junit.After;
import org.junit.Before;

public class testBase {

    private static ThreadLocal<Application> DRIVER = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (DRIVER.get() != null) {
            app = DRIVER.get();
            return;
        }

        app = new Application();
        DRIVER.set(app);
    }

    @After
    public void tearDown() {
        if (DRIVER.get() != null) {
            app.quit();
            app = null;
        }
    }
}
