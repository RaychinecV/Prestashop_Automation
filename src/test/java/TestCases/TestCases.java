package TestCases;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseTest {

    @Test(priority = 1)
    public void start() {
        HomePage main = new HomePage();
        Assert.assertTrue(main.checkAllCurrency(BasePage.Currency.UAH), "Some product is't in UAH");
        Assert.assertTrue(main.checkAllCurrency(BasePage.Currency.EUR), "Some product is't in EUR");
        Assert.assertTrue(main.checkAllCurrency(BasePage.Currency.USD), "Some product is't in USD");
    }

    @Test(priority = 2)
    public void searchProduct() {
        SearchPage secondPage = new SearchPage();
        secondPage.findProducts("dress");
        Assert.assertEquals(secondPage.getLabel(), "Товаров: " + secondPage.getAmountOfFoundedProducts() + ".", "Label <Товари: > is not present or contain wrong amount of products");
        Assert.assertTrue(secondPage.checkFoundedProductsInUSD(), "Some founded product is not in USD");
    }

    @Test(priority = 3)
    public void sorting() {
        SearchPage secondPage = new SearchPage();
        secondPage.sortFromHighToLow();
        Assert.assertTrue(secondPage.checkSortFromHigh(), "Wrong sort");
        Assert.assertTrue(secondPage.displayedPricesAndDiscount(), "Label discount is't contain % or not displayed prices before/after discount");
        Assert.assertTrue(secondPage.checkDiscountAndPrices(), "Discount and prices are calculated wrong");
    }
}





