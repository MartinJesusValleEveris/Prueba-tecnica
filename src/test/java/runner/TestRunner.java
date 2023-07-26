package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CAMELCASE,
        glue = { "Steps", "actions" },
        features = { "src/test/resources/features" },
        plugin = {
                "pretty",
                "html:target/site/cucumber-pretty.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        stepNotifications = false

)
public class TestRunner {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
