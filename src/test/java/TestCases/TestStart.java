package TestCases;

import Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeUnit;

public class TestStart {

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Prestashop_Automation\\src\\test\\resources\\chromedriver.exe");
        HelpClass.getDriver().manage().window().maximize();
        HelpClass.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        HelpClass.getDriver().get("http://prestashop-automation.qatestlab.com.ua/ru");
    }

    @Test
    public void start() {
        MainPage main = new MainPage(HelpClass.getDriver());
        Assert.assertTrue("Some product is't in UAH", main.checkAllCurrency(HelpClass.Currency.UAH));
        Assert.assertTrue("Some product is't in EUR", main.checkAllCurrency(HelpClass.Currency.EUR));
        Assert.assertTrue("Some product is't in USD", main.checkAllCurrency(HelpClass.Currency.USD));
        SecondPage secondPage = main.findProducts("dress");
        Assert.assertEquals("Label <Товари: > is not present or contain wrong amount of products", secondPage.getLabel(), "Товаров: " + secondPage.getAmountOfFoundedProducts() + ".");
        Assert.assertTrue("Some founded product is not in USD", secondPage.checkFoundedProductsInUSD());
        secondPage.sortFromHighToLow();
        Assert.assertTrue("Wrong sort", secondPage.checkSortFromHigh());
        Assert.assertTrue("Label discount is't contain % or not displayed prices before/after discount", secondPage.displayedPricesAndDiscount());
        Assert.assertTrue("Discount and prices are calculated wrong",secondPage.checkDiscountAndPrices());
    }

    @After
    public void quit() {
        HelpClass.getDriver().quit();
    }
}
