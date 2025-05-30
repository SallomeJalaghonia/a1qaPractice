package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.UUID;

public class DynamicControlsTest extends BaseTest {
    private final By dynamicControls = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Controls"));
    private final By enable = By.xpath(String.format(PRECISE_TEXT_XPATH, "Enable"));
    private final By INPUT = By.xpath("//*[@id='input-example']//input");
    private final String RANDOM_TEXT = UUID.randomUUID().toString();
    private WebDriverWait wait;


    @Test
    public void dynamicControlsTest() {
        driver.findElement(dynamicControls).click();
        driver.findElement(enable).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputField = driver.findElement(INPUT);
        Assert.assertTrue(isClickable(inputField), "element wasn't enabled");
        inputField.sendKeys(RANDOM_TEXT);
        Assert.assertEquals(driver.findElement(INPUT).getAttribute("value"), RANDOM_TEXT,
                "Text is not displayed");

    }
    private boolean isClickable(WebElement element){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (TimeoutException exception){
            return false;
        }
        return true;
    }
}
