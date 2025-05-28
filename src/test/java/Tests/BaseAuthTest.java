package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class BaseAuthTest extends BaseTest {

    private final By BASIC_AUTH = By.xpath(String.format(PRECISE_TEXT_XPATH, "Basic Auth"));
    private final By SUCCESS_AUTH = By.xpath("//*[contains(text(), 'Congratulations! You must have the proper credentials.')]");

    @BeforeMethod
    public void basicAuth() {
        HasAuthentication authentication = (HasAuthentication) driver;
        authentication.register(() -> new UsernameAndPassword("admin", "admin"));
    }

    @Test
    public void basicAuthTest() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Basic Auth")).click();
        String username = "admin";
        String password = "admin";
        String basicAuthURL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
        driver.get(basicAuthURL);
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
        String successMessage = messageElement.getText();
        // Verify success message
        Assert.assertTrue(successMessage.contains("Congratulations! You must have the proper credentials."),
                "Success message is not as expected: " + successMessage);
    }
    private String buildBasicAuthURL(String username, String password, String baseURL) {
        return "https://" + username + ":" + password + "@" + baseURL;
    }

}
