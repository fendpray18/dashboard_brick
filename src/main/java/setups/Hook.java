package setups;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends BrowserSetup{
    @Before
    public void before() throws Exception {
        startBrowser();
    }

    @After
    public void after() throws Exception {
        stopBrowser();
    }
}