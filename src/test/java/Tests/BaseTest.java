package Tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;

public class BaseTest {
    protected WebDriver driver;

    protected final String URL = "https://the-internet.herokuapp.com/";
    protected final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    protected final String PARTICULAR_TEXT_PATH = "//*contains(text(), '%s')]";
    protected WebDriverWait wait = null;
    protected final String RELATIVE_RESOURCE_PATH = "src/test/resources/%s";
    protected final String ABSOLUTE_RESOURCE_PATH = new File(RELATIVE_RESOURCE_PATH).getAbsolutePath();
    protected final int MAX_WAIT = 10;

    @BeforeMethod
    public void setup() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", ABSOLUTE_RESOURCE_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.of(MAX_WAIT, ChronoUnit.SECONDS));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
