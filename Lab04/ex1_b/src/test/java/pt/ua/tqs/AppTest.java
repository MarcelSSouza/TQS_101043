package pt.ua.tqs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;



public class AppTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertTrue(title.equals("Hands-On Selenium WebDriver with Java"));
    }

}
