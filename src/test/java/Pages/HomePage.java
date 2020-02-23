package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BasePage {
    private By popularPrice = By.xpath("//div[@class='product-description']//span[contains(@class,'price')]");
    private By currencyDropdownBottom = By.xpath("//div[@class='currency-selector dropdown js-dropdown']//a[@class='hidden-sm-down']");
    private By bottomUAH = By.xpath("//a[contains(text(),'UAH ₴')]");
    private By bottomEUR = By.xpath("//a[contains(text(),'EUR €')]");
    private By bottomUSD = By.xpath("//a[contains(text(),'USD $')]");
    private By searchField = By.className("ui-autocomplete-input");
    private By bottomSearch = By.cssSelector("button[type=submit]");


    //step 2 + step 3 - check currency and choice product in USD
    public boolean checkAllCurrency(Currency currency) {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Checking that currency of all products is displayed correct on main page");
        switch (currency) {
            case UAH:
                waitElementToBeVisible(currencyDropdownBottom);
                logger.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeVisible(bottomUAH);
                logger.info("Clicking on bottom UAH");
                driver.findElement(bottomUAH).click();
                List<WebElement> listPopularProductsUAH = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsUAH) {
                    logger.info("Price " + productsPopularPrice.getText() + " in UAH - PASS");
                    if (!productsPopularPrice.getText().contains("₴")) {
                        logger.info("Price " + productsPopularPrice.getText() + " in not UAH - FAIL");
                        return false;
                    }
                }
                logger.info("All products are displayed in UAH");
                return true;

            case EUR:
                waitElementToBeVisible(currencyDropdownBottom);
                logger.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeVisible(bottomEUR);
                logger.info("Clicking on bottom EUR");
                driver.findElement(bottomEUR).click();
                List<WebElement> listPopularProductsEUR = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsEUR) {
                    logger.info("Price " + productsPopularPrice.getText() + " in EUR - PASS");
                    if (!productsPopularPrice.getText().contains("€")) {
                        logger.info("Price " + productsPopularPrice.getText() + " in not EUR - FAIL");
                        return false;
                    }
                }
                logger.info("All products are displayed in EUR");
                return true;

            case USD:
                waitElementToBeVisible(currencyDropdownBottom);
                logger.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeVisible(bottomUSD);
                logger.info("Clicking on bottom USD");
                driver.findElement(bottomUSD).click();
                List<WebElement> listPopularProductsUSD = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsUSD) {
                    logger.info("Price " + productsPopularPrice.getText() + " in USD - PASS");
                    if (!productsPopularPrice.getText().contains("$")) {
                        logger.info("Price " + productsPopularPrice.getText() + " in not USD - FAIL");
                        return false;
                    }
                }
                logger.info("All products are displayed in USD");

        }
        return true;

    }

    public SearchPage findProducts(String product) {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Try found product");
        waitElementToBeClickable(searchField);
        logger.info("Enter name products : " + product);
        driver.findElement(searchField).sendKeys(product);
        logger.info("Click on search bottom");
        driver.findElement(bottomSearch).click();
        logger.info("Founded products : " + product);
        return new SearchPage();
    }
}
