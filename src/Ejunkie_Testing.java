import Utility.BaseDriver;
import Utility.Fonksiyon;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ejunkie_Testing extends BaseDriver {
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

            String dosyaYolu = "src\\US_EJT_02\\ekranGoruntuleri\\" + dt.format(f) + ".png";
            FileUtils.copyFile(hafizadakiHali, new File(dosyaYolu));

        } catch (Exception ex) {
            System.out.print("ex.getMessage() = " + ex.getMessage());
            System.out.println("EKRAN GÖRÜNTÜSÜ ALMA HATASI");
        }

    }

    @Test
    public void testCreditCardPaymentAndConfirmation() throws Exception {
        driver.get("https://www.e-junkie.com/wiki/demo/paypal");

        WebElement addToCard = driver.findElement(By.xpath("(//a[@onclick='return EJEJC_lc(this);'])[2]"));
        addToCard.click();

        driver.switchTo().frame(0);
        Fonksiyon.bekle(3);
        WebElement debit = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        debit.click();

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        email.sendKeys("test.ejt@example.com");
        Fonksiyon.bekle(2);

        WebElement cEmail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        cEmail.sendKeys("test.ejt@example.com");
        Fonksiyon.bekle(2);

        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        name.sendKeys("Logan Burnage");
        Fonksiyon.bekle(2);

        WebElement phone = driver.findElement(By.xpath("(//input[@autocomplete='phone'])[1]"));
        phone.sendKeys("+1-504-236-5158");
        Fonksiyon.bekle(2);

        WebElement company = driver.findElement(By.xpath("(//input[@autocomplete='company'])[1]"));
        company.sendKeys("Megatronic Plus");
        Fonksiyon.bekle(2);

        WebElement adres1 = driver.findElement(By.xpath("(//input[@placeholder='Address Line 1'])[1]"));
        adres1.sendKeys("3989 Rose Avenue");
        Fonksiyon.bekle(2);

        WebElement city = driver.findElement(By.xpath("(//input[@placeholder='City'])[1]"));
        city.sendKeys("Los Angeles");
        Fonksiyon.bekle(2);

        WebElement country = driver.findElement(By.xpath("(//select[@autocomplete='country'])[1]"));
        Select cMenu = new Select(country);
        cMenu.selectByVisibleText("United States");
        Fonksiyon.bekle(2);

        WebElement state = driver.findElement(By.xpath("(//select[@autocomplete='state'])[1]"));
        Select sMenu = new Select(state);
        sMenu.selectByVisibleText("CALIFORNIA");
        Fonksiyon.bekle(2);

        WebElement zip = driver.findElement(By.xpath("(//input[@placeholder='ZIP/Postal Code'])[1]"));
        zip.sendKeys("70062");
        Fonksiyon.bekle(2);

        WebElement cardNumber = driver.findElement(By.xpath("//input[@data-cardinal-field='AccountNumber']"));
        cardNumber.sendKeys("4242424242424242");
        Fonksiyon.bekle(2);

        WebElement cardExpiry = driver.findElement(By.xpath("//input[@placeholder='MM / YY']"));
        cardExpiry.sendKeys("1223");
        Fonksiyon.bekle(2);

        WebElement cvv = driver.findElement(By.xpath("//input[@autocomplete='cc-csc']"));
        cvv.sendKeys("000");
        takeScreenshot(driver);
        Fonksiyon.bekle(2);

        WebElement pay = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        pay.click();
        Fonksiyon.bekle(2);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='green_text_margin']")));
        WebElement success = driver.findElement(By.xpath("//span[@class='green_text_margin']"));
        String msj = "Logan, your order is confirmed. Thank you!";
        takeScreenshot(driver);
        Assert.assertTrue(success.getText().equals(msj), "Doğrulama Başarısız!");

        BekleVeKapat();
    }

    public static void takeScreenshot(WebDriver driver) throws Exception {
        SimpleDateFormat tarihFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        TakesScreenshot ss = (TakesScreenshot) driver;
        String tarih = tarihFormat.format(date);
        File screenShot = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("src/TC_EJT_04/screenshots/screenshot_" + tarih + ".png"));
    }
}
