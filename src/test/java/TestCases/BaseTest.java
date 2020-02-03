package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public static WebDriver driver;

    @BeforeTest
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Prestashop_Automation\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/ru");
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }
}

