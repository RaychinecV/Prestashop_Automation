package TestCases;

import Pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseTest {
    private HomePage homepage;
    private SearchPage searchPage;

    @Description("Checking that currency is correct")
    @Test(priority = 1)
    public void checkCurrency() {
        homepage = new HomePage();
        Assert.assertTrue(homepage.checkAllCurrency(BasePage.Currency.UAH), "Some product is't in UAH");
        Assert.assertTrue(homepage.checkAllCurrency(BasePage.Currency.EUR), "Some product is't in EUR");
        Assert.assertTrue(homepage.checkAllCurrency(BasePage.Currency.USD), "Some product is't in USD");
    }

    @Description("Checking find products")
    @Test(priority = 2)
    public void searchProduct() {
        searchPage = new SearchPage();
        homepage.findProducts("dress");
        Assert.assertEquals(searchPage.getLabel(), "Товаров: " + searchPage.getAmountOfFoundedProducts() + ".", "Label <Товари: > is not present or contain wrong amount of products");
        Assert.assertTrue(searchPage.checkFoundedProductsInUSD(), "Some founded product is not in USD");
    }

    @Description("Checking sort of products")
    @Test(priority = 3)
    public void sorting() {
        searchPage = new SearchPage();
        SearchPage searchPage = new SearchPage();
        searchPage.sortFromHighToLow();
        Assert.assertTrue(searchPage.checkSortFromHigh(), "Wrong sort");
        Assert.assertTrue(searchPage.displayedPricesAndDiscount(), "Label discount is't contain % or not displayed prices before/after discount");
        Assert.assertTrue(searchPage.checkDiscountAndPrices(), "Discount and prices are calculated wrong");
    }
}





