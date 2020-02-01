package Pages;

import TestCases.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends BaseTest {
    public static WebDriver getDriver() {
        return driver;
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);

    static final Logger log = Logger.getLogger(BasePage.class);

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




