package TestCases;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public static WebDriver driver;

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Prestashop_Automation\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/ru");
    }

    @After
    public void quit() {
        driver.quit();
    }
}

