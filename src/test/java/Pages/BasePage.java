package Pages;

import TestCases.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, 5);

    @Step
    void waitElementToBeClickable(By xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    @Step
    void waitElementToBeVisible(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    @Step()
    public void click(By xpath){
        driver.findElement(xpath).click();
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




