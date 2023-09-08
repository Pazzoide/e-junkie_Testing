package US_EJT_02;

import Utility.BaseDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC_EJT_02 extends BaseDriver {
    @Test
    public void EJT_02() {
        driver.get("https://www.e-junkie.com/wiki/demo/paypal");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='btn'])[2]")));
        WebElement addToCart = driver.findElement(By.xpath("(//a[@class='btn'])[2]"));
        addToCart.click();

        driver.switchTo().frame(0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='Payment-Button CC']")));
        WebElement debitCart = driver.findElement(By.cssSelector("button[class='Payment-Button CC']"));
        debitCart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='Pay-Button']")));
        ScreenshotCapture();

        WebElement payButton = driver.findElement(By.cssSelector("button[class='Pay-Button']"));
        payButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='SnackBar']")));
        driver.switchTo().parentFrame();
        ScreenshotCapture();
        BekleVeKapat();

    }
    public void ScreenshotCapture() {

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.YYYY-hh.mm.ss");
        LocalDateTime dt = LocalDateTime.now();


        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File hafizadakiHali = ts.getScreenshotAs(OutputType.FILE);

            String dosyaYolu = "src\\US_EJT_02\\ekranGoruntuleri\\"+dt.format(f)+".png";
            FileUtils.copyFile(hafizadakiHali, new File(dosyaYolu));

        } catch (Exception ex) {
            System.out.print("ex.getMessage() = " + ex.getMessage());
            System.out.println("EKRAN GÖRÜNTÜSÜ ALMA HATASI");
        }

    }
}
