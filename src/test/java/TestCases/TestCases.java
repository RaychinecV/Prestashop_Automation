package TestCases;

import Pages.*;
import org.junit.Assert;
import org.junit.Test;

public class TestCases extends BaseTest{

    @Test
    public void start() {
        HomePage main = new HomePage();
        Assert.assertTrue("Some product is't in UAH", main.checkAllCurrency(BasePage.Currency.UAH));
        Assert.assertTrue("Some product is't in EUR", main.checkAllCurrency(BasePage.Currency.EUR));
        Assert.assertTrue("Some product is't in USD", main.checkAllCurrency(BasePage.Currency.USD));
        SearchPage secondPage = main.findProducts("dress");
        Assert.assertEquals("Label <Товари: > is not present or contain wrong amount of products", secondPage.getLabel(), "Товаров: " + secondPage.getAmountOfFoundedProducts() + ".");
        Assert.assertTrue("Some founded product is not in USD", secondPage.checkFoundedProductsInUSD());
        secondPage.sortFromHighToLow();
        Assert.assertTrue("Wrong sort", secondPage.checkSortFromHigh());
        Assert.assertTrue("Label discount is't contain % or not displayed prices before/after discount", secondPage.displayedPricesAndDiscount());
        Assert.assertTrue("Discount and prices are calculated wrong", secondPage.checkDiscountAndPrices());
    }
}


