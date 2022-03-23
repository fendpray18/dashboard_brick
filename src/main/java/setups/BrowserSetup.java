package setups;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BrowserSetup extends BrowserPool{
    WebDriver browser;
    Properties prop = new Properties();

    protected void startBrowser() throws IOException {
        /*
        This state read envi.properties.
         */
        InputStream input = BrowserSetup.class.getClassLoader().getResourceAsStream("env.properties");
        /*
        Download chrome driver latest local chrome.
         */
        WebDriverManager.chromedriver().setup();
        System.out.println(input);
        prop.load(input);
        ChromeOptions options = new ChromeOptions();
        String properties = prop.getProperty("env.status");
        System.out.println(properties);

        if (properties.equals("debug") ) {
            options.addArguments("window-size=1920,1080");
            System.out.println(properties);
        } else {
            options.addArguments("--headless");
            options.addArguments("window-size=1920,1080");
        }

        browser = new ChromeDriver(options);
        setBrowserInstance(browser);

        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        //browser.manage().window().maximize();

    }

    protected void stopBrowser() {
        browser.close();
        browser.quit();
    }
}