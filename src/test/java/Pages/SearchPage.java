package Pages;

import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    private By amountOfFoundedProducts = By.xpath("//div[@class='products row']//span[@class='price']");
    private By labelProducts = By.xpath("//p[contains(text(),'Товаров')]");
    private By sortDropBoxBottom = By.xpath("//a[@class='select-title']");
    private By fromHighToLow = By.xpath("//a[contains(text(),'от высокой к низкой')]");
    private By regularPrice = By.xpath("//div[@class='product-price-and-shipping']/span[1]");
    private By discount = By.xpath("//div[@class='products row']//span[@class='discount-percentage']");
    private By labelDiscount = By.xpath("//div[@class='products row']//span[@class='discount-percentage']/ancestor::div['product-description']/h1");
    private By beforeDiscount = By.xpath("//span[@class='discount-percentage']/preceding-sibling::span");
    private By afterDiscount = By.xpath("//span[@class='discount-percentage']/following-sibling::span");
    private By searchField = By.xpath("//input[@placeholder='Поиск в каталоге']");
    private By bottomSearch = By.xpath("//div[@id='search_widget']//i[@class='material-icons search']");

    public SearchPage findProducts(String product) {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Try found product");
        waitElementToBeClickable(searchField);
        log.info("Enter name products : " + product);
        driver.findElement(searchField).sendKeys(product);
        log.info("Click on search bottom");
        driver.findElement(bottomSearch).click();
        log.info("Founded products : " + product);
        return new SearchPage();
    }
    //step 5.1 found label
    public String getLabel() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Try found label <Товаров: >");
        waitElementToBeVisible(labelProducts);
        log.info("Label is present - " + driver.findElement(labelProducts).getText());
        return driver.findElement(labelProducts).getText();
    }

    //step 5.2 number of founded products
    public int getAmountOfFoundedProducts() {
        log.info("How many products founded");
        waitElementToBeVisible(labelProducts);
        log.info("Amount of founded products : " + driver.findElements(amountOfFoundedProducts).size());
        return driver.findElements(amountOfFoundedProducts).size();
    }

    // step 6 - check founded price in USD
    public boolean checkFoundedProductsInUSD() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Checking that all founded products are displayed in USD");
        List<WebElement> checkProducts = driver.findElements(amountOfFoundedProducts);
        for (WebElement productsPopularPrice : checkProducts) {
            log.info("Price " + productsPopularPrice.getText() + " on USD - PASS");
            if (!productsPopularPrice.getText().contains("$")) {
                log.error("Price " + productsPopularPrice.getText() + " is not on USD - FAIL");
                return false;
            }
        }
        log.info("All founded products are displayed in USD");
        return true;
    }


    //step 7 - sort price from high to low
    public void sortFromHighToLow() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Sorting products from high to low prices");
        waitElementToBeClickable(sortDropBoxBottom);
        log.info("Click on sort drop down list ");
        driver.findElement(sortDropBoxBottom).click();
        log.info("Click on bottom from high to low ");
        driver.findElement(fromHighToLow).click();
    }

    //step 8 - check sort
    public boolean checkSortFromHigh() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Checking that sort is correct");
        wait.until(ExpectedConditions.urlContains("product.price.desc&s=dress"));
        List<WebElement> listRegularPrice = driver.findElements(regularPrice);
        for (int i = 0; i + 1 < listRegularPrice.size(); i++) {
            double priceProduct = Double.parseDouble(listRegularPrice.get(i).getText().replace(',', '.').substring(0, 4));
            double nextPriceProduct = Double.parseDouble(listRegularPrice.get(i + 1).getText().replace(',', '.').substring(0, 4));
            log.info("Price " + priceProduct + " >=" + nextPriceProduct + " - PASS");
            if (priceProduct < nextPriceProduct) {
                log.error("Price " + priceProduct + " < " + nextPriceProduct + " - FAIL");
                return false;
            }
        }
        log.info("Sort is correct");

        return true;
    }

    //step 9 - check displayed discount and prices
    public boolean displayedPricesAndDiscount() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Check that all products with discount contain label with % and prices before/after discount");
        waitElementToBeVisible(discount);
        List<WebElement> listDiscount = driver.findElements(discount);
        List<WebElement> listLabelDiscount = driver.findElements(labelDiscount);
        List<WebElement> listBeforeDiscount = driver.findElements(beforeDiscount);
        List<WebElement> listAfterDiscount = driver.findElements(afterDiscount);
        for (int i = 0; i < listDiscount.size(); i++) {
            if (listDiscount.get(i).getText().contains("%") &&
                    (listDiscount.size() == listBeforeDiscount.size()
                            && listDiscount.size() == listAfterDiscount.size())) {
                log.info("Product  " + listLabelDiscount.get(i).getText() + " is contain discount label " + listDiscount.get(i).getText() +
                        " before price " + listBeforeDiscount.get(i).getText() + " and after price " + listAfterDiscount.get(i).getText() + " - PASS");
            } else {
                log.info("Product  " + listLabelDiscount.get(i).getText() + " is't contain discount label " + listDiscount.get(i).getText() +
                        " before price " + listBeforeDiscount.get(i).getText() + " or after price " + listAfterDiscount.get(i).getText() + " - FAIL");
                return false;
            }
        }
        log.info("All products with discount contains prices before and after discount");
        return true;
    }

    // step 10 - check that discount calculated correctly
    public boolean checkDiscountAndPrices() {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Start checking that discount and prices calculated correctly");
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
            log.info("Price of product: " + listLabelDiscount.get(i).getText() + " calculated correct - PASS");
            if (sumPercentAndAfterDiscount != beforeDiscount) {
                log.error("Price of product: " + listLabelDiscount.get(i).getText() + " calculated wrong - FAIL");
                return false;
            }
        }
        log.info("All prices and discounts are calculated correctly");
        log.info("---------------------------------------------------------------------------------------------------------");
        return true;
    }
}
