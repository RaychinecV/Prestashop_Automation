package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpClass {
    private static WebDriver driver = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver, 10);

    static final Logger log = Logger.getLogger(HelpClass.class);

    public static WebDriver getDriver() {
        return driver;
    }

    void waitElementToBeClickable(By xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    void waitElementToBeVisible(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public enum Currency {
        EUR, UAH, USD
    }
}




