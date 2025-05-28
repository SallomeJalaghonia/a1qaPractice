package Tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;


public class FileDownloadTest extends BaseTest {

    private final By fileDownloadLink = By.cssSelector("#content > ul > li:nth-child(17) > a");
    private final By fileName = By.cssSelector("#content > div > a:nth-child(110)");
    private final String relativeResourcePath = "C:\\Users\\Salome\\Downloads";
    private final int MAX_WAIT = 20;

    @Test
    public void fileDownloadTest() {
        driver.findElement(fileDownloadLink).click();
        driver.findElement(fileName).click();

        System.out.println("file download started");

        File downloadedFile = waitForFileDownload();

        Assert.assertNotNull(downloadedFile, "file download failed");
        System.out.println("file download: " + downloadedFile.getAbsolutePath());
    }

    private File waitForFileDownload() {

        File folder = new File(relativeResourcePath);
        File[] listOfFiles = folder.listFiles();
        long lastModifiedTime = 0;


        for (int i = 0; i < MAX_WAIT; i++) {
            for (File file : listOfFiles) {
                if (file.lastModified() > lastModifiedTime) {
                    lastModifiedTime = file.lastModified();
                    System.out.println("New file found: " + file.getName());
                    return file;
                }
            }

            try {
                Thread.sleep(1000); //
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listOfFiles = folder.listFiles(); //
        }

        return null;

    }

    @AfterMethod
    public void deleteFile() {
        File folder = new File(relativeResourcePath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.exists()) {
                boolean isDeleted = file.delete();
                if (isDeleted) {
                    System.out.println("file deleted : " + file.getName());
                } else {
                    System.out.println("file can't delete: " + file.getName());
                }
            }
        }
    }
}
