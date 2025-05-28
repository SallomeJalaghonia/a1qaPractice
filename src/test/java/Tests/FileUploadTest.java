package Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;


public class FileUploadTest extends BaseTest {

    private final By fileUpload = By.xpath("//*[@id=\"content\"]/ul/li[18]/a");

    private final By CHOOSE_FILE = By.id("file-upload");

    private final By FILE_SUBMIT =  By.id("file-submit");

    private final By UPLOADED_FILE = By.id("uploaded-files");

    private final String fileName = "Salome.Jalaghonia.Resume.pdf";

    private final String filePath = "C:\\Users\\Salome\\Desktop\\WORK\\Salome.Jalaghonia.Resume.pdf";

    @Test
    public void fileUploadTest() {

        driver.findElement(fileUpload).click();

        File fileToUpload = new File(filePath);

        driver.findElement(CHOOSE_FILE).sendKeys(fileToUpload.getAbsolutePath());

        driver.findElement(FILE_SUBMIT).click();


        Assert.assertEquals(driver.findElement(UPLOADED_FILE).getText(), fileName,
                "file name is invalid or missing.");
    }
}
