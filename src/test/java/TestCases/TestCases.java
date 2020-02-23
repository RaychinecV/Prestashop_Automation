package TestCases;

import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseTest {

    @Description("Checking that currency in UAH")
    @Test(priority = 1)
    public void checkCurrencyUAH() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.checkAllCurrency(BasePage.Currency.UAH), "Some product is't in UAH");
    }

    @Description("Checking that currency in EUR")
    @Test(priority = 2)
    public void checkCurrencyEUR() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.checkAllCurrency(BasePage.Currency.EUR), "Some product is't in EUR");
    }

    @Description("Checking that currency in USD")
    @Test(priority = 3)
    public void checkCurrencyUSD() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.checkAllCurrency(BasePage.Currency.USD), "Some product is't in USD");
    }

    @Description("Check searching of products")
    @Test(priority = 4)
    public void searchProduct() {
        HomePage homePage = new HomePage();
        SearchPage searchPage = homePage.findProducts("dress");
        Assert.assertEquals(searchPage.getLabel(), "Товаров: " + searchPage.getAmountOfFoundedProducts() + ".", "Label <Товари: > is not present or contain wrong amount of products");
    }

    @Description("Checking that currency of founded products in USD")
    @Test(dependsOnMethods = "searchProduct")
    public void checkingCurrencyOfFoundedProducts() {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.checkCurrencyOfFoundedProducts(BasePage.Currency.USD), "Some founded product is not in USD");
    }

    @Description("Checking sort from high to low")
    @Test(priority = 5)
    public void checkSortFromHighToLow() {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.checkSort(BasePage.Sort.FROM_HIGH_TO_LOW), "Wrong sort");
    }

    @Description("Checking sort from low to high")
    @Test(priority = 6)
    public void checkSortFromLowToHigh() {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.checkSort(BasePage.Sort.FROM_lOW_TO_HIGH), "Wrong sort");
    }

    @Description("Checking that amount of discount and prices is equal")
    @Test(priority = 7)
    public void checkAmountOfDiscountAndPricesEqual() {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.displayedPricesAndDiscount(), "Label discount is't contain % or not displayed prices before/after discount");
    }

    @Description("Checking that discount is calculated correct")
    @Test(priority = 8)
    public void checkCalculateOfDiscount() {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.checkDiscountAndPrices(), "Discount and prices are calculated wrong");
    }
}





