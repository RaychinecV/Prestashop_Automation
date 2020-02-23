package Pages;

import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    private By amountOfFoundedProducts = By.cssSelector(".price");
    private By labelProducts = By.cssSelector("#js-product-list-top p");
    private By sortDropBoxBottom = By.cssSelector(".select-title");
    private By fromHighToLow = By.xpath("//a[contains(text(),'от высокой к низкой')]");
    private By fromLowToHigh = By.xpath("//a[contains(text(),'от низкой к высокой')]");
    private By regularPrice = By.xpath("//div[@class='product-price-and-shipping']/span[1]");
    private By discount = By.xpath("//div[@class='products row']//span[@class='discount-percentage']");
    private By labelDiscount = By.xpath("//span [@class='discount-percentage']//ancestor::div/h1");
    private By beforeDiscount = By.xpath("//span[@class='discount-percentage']/preceding-sibling::span");
    private By afterDiscount = By.xpath("//span[@class='discount-percentage']/following-sibling::span");

    //step 5.1 found label
    public String getLabel() {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Try found label <Товаров: >");
        waitElementToBeVisible(labelProducts);
        logger.info("Label is present - " + driver.findElement(labelProducts).getText());
        return driver.findElement(labelProducts).getText();
    }

    //step 5.2 number of founded products
    public int getAmountOfFoundedProducts() {
        logger.info("How many products founded");
        waitElementToBeVisible(labelProducts);
        logger.info("Amount of founded products : " + driver.findElements(amountOfFoundedProducts).size());
        return driver.findElements(amountOfFoundedProducts).size();
    }

    // step 6 - check founded price
    public boolean checkCurrencyOfFoundedProducts(Currency currency) {
        HomePage homePage = new HomePage();
        return homePage.checkAllCurrency(currency);
    }

    //sort
    public void choiceSort(By sort) {
        waitElementToBeClickable(sortDropBoxBottom);
        logger.info("Click on sort drop down list ");
        driver.findElement(sortDropBoxBottom).click();
        driver.findElement(sort).click();
    }


    public boolean checkSortFromHighToLow() {
        logger.info("Checking that sort is correct");
        wait.until(ExpectedConditions.urlContains("product.price.desc&s=dress"));
        List<WebElement> listRegularPrice = driver.findElements(regularPrice);
        for (int i = 0; i + 1 < listRegularPrice.size(); i++) {
            double priceProduct = Double.parseDouble(listRegularPrice.get(i).getText().replace(',', '.').substring(0, 4));
            double nextPriceProduct = Double.parseDouble(listRegularPrice.get(i + 1).getText().replace(',', '.').substring(0, 4));
            logger.info("Price " + priceProduct + " >=" + nextPriceProduct + " - PASS");
            if (priceProduct < nextPriceProduct) {
                logger.info("Price " + priceProduct + " < " + nextPriceProduct + " - FAIL");
                return false;
            }
        }
        logger.info("Sort is correct");

        return true;
    }


    public boolean checkSortFromLowToHigh() {
        logger.info("Checking that sort is correct");
        wait.until(ExpectedConditions.urlContains("product.price.asc&s=dress"));
        List<WebElement> listRegularPrice = driver.findElements(regularPrice);
        for (int i = 0; i + 1 < listRegularPrice.size(); i++) {
            double priceProduct = Double.parseDouble(listRegularPrice.get(i).getText().replace(',', '.').substring(0, 4));
            double nextPriceProduct = Double.parseDouble(listRegularPrice.get(i + 1).getText().replace(',', '.').substring(0, 4));
            logger.info("Price " + priceProduct + " <=" + nextPriceProduct + " - PASS");
            if (priceProduct > nextPriceProduct) {
                logger.info("Price " + priceProduct + " < " + nextPriceProduct + " - FAIL");
                return false;
            }
        }
        logger.info("Sort is correct");

        return true;
    }

    public boolean checkSort(Sort sort) {
        switch (sort) {
            case FROM_HIGH_TO_LOW:
                logger.info("---------------------------------------------------------------------------------------------------------");
                logger.info("Sort from high to low");
                choiceSort(fromHighToLow);
                return checkSortFromHighToLow();

            case FROM_lOW_TO_HIGH:
                logger.info("---------------------------------------------------------------------------------------------------------");
                logger.info("Sort from low to high");
                choiceSort(fromLowToHigh);
                return checkSortFromLowToHigh();
        }
        return true;
    }


    //step 9 - check displayed discount and prices
    public boolean displayedPricesAndDiscount() {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Check that all products with discount contain label with % and prices before/after discount");
        waitElementToBeVisible(discount);
        List<WebElement> listDiscount = driver.findElements(discount);
        List<WebElement> listLabelDiscount = driver.findElements(labelDiscount);
        List<WebElement> listBeforeDiscount = driver.findElements(beforeDiscount);
        List<WebElement> listAfterDiscount = driver.findElements(afterDiscount);
        for (int i = 0; i < listDiscount.size(); i++) {
            if (listDiscount.get(i).getText().contains("%") &&
                    (listDiscount.size() == listBeforeDiscount.size()
                            && listDiscount.size() == listAfterDiscount.size())) {
                logger.info("Product  " + listLabelDiscount.get(i).getText() + " is contain discount label " + listDiscount.get(i).getText() +
                        " before price " + listBeforeDiscount.get(i).getText() + " and after price " + listAfterDiscount.get(i).getText() + " - PASS");
            } else {
                logger.info("Product  " + listLabelDiscount.get(i).getText() + " is't contain discount label " + listDiscount.get(i).getText() +
                        " before price " + listBeforeDiscount.get(i).getText() + " or after price " + listAfterDiscount.get(i).getText() + " - FAIL");
                return false;
            }
        }
        logger.info("All products with discount contains prices before and after discount");
        return true;
    }

    // step 10 - check that discount calculated correctly
    public boolean checkDiscountAndPrices() {
        logger.info("Start checking that discount and prices calculated correctly");
        List<WebElement> listDiscount = driver.findElements(discount);
        List<WebElement> listLabelDiscount = driver.findElements(labelDiscount);
        List<WebElement> listBeforeDiscount = driver.findElements(beforeDiscount);
        List<WebElement> listAfterDiscount = driver.findElements(afterDiscount);
        for (int i = 0; i < listDiscount.size(); i++) {
            int percent = Integer.parseInt(listDiscount.get(i).getText().replace("%", ""));
            double beforeDiscount = Double.parseDouble(listBeforeDiscount.get(i).getText().substring(0, 4).replace(",", "."));
            double afterDiscount = Double.parseDouble(listAfterDiscount.get(i).getText().substring(0, 4).replace(",", "."));
            double calculatePercent = DoubleRounder.round(beforeDiscount * Math.abs(percent) / 100, 2);
            double sumPercentAndAfterDiscount = DoubleRounder.round(afterDiscount + calculatePercent, 2);
            logger.info("Price of product: " + listLabelDiscount.get(i).getText() + " calculated correct - PASS");
            if (sumPercentAndAfterDiscount != beforeDiscount) {
                logger.info("Price of product: " + listLabelDiscount.get(i).getText() + " calculated wrong - FAIL");
                return false;
            }
        }
        logger.info("All prices and discounts are calculated correctly");
        logger.info("---------------------------------------------------------------------------------------------------------");
        return true;
    }
}

