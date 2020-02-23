package Pages;

import TestCases.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, 5);

    static final Logger logger = Logger.getLogger(BasePage.class);

    void waitElementToBeClickable(By xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    void waitElementToBeVisible(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public enum Currency {
        EUR,
        UAH,
        USD
    }

    public enum Sort {
        FROM_HIGH_TO_LOW,
        FROM_lOW_TO_HIGH
    }
}




