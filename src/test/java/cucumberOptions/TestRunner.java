package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
            features={"src/test/java/features"},
            glue={""},
            //monochrome = true,
            //plugin = {"pretty","html:target/HTML/Report1.html"},
            //plugin = {"json:target/cucumber.json","html:target/HTML/report.html"},
            tags = "@leave"
)
public class TestRunner {

}
