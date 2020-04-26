package Pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BasePage {
    static final Logger logger = Logger.getLogger(BasePage.class);

    private By popularPrice = By.xpath("//div[@class='product-description']//span[contains(@class,'price')]");
    private By currencyDropdownButtom = By.xpath("//div[@class='currency-selector dropdown js-dropdown']//a[@class='hidden-sm-down']");
    private By bottomUAH = By.xpath("//a[contains(text(),'UAH ₴')]");
    private By buttomEUR = By.xpath("//a[contains(text(),'EUR €')]");
    private By buttonUSD = By.xpath("//a[contains(text(),'USD $')]");
    private By searchField = By.className("ui-autocomplete-input");
    private By buttomSearch = By.cssSelector("button[type=submit]");


    //step 2 + step 3 - check currency and choice product in USD
    @Step
    public boolean checkAllCurrency(Currency currency) {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Checking that currency of all products is displayed correct on main page");
        switch (currency) {
            case UAH:
                waitElementToBeVisible(currencyDropdownButtom);
                logger.info("Clicking on currency dropdown bottom ");
                click(currencyDropdownButtom);
                waitElementToBeVisible(bottomUAH);
                logger.info("Clicking on bottom UAH");
                click(bottomUAH);
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
                waitElementToBeVisible(currencyDropdownButtom);
                logger.info("Clicking on currency dropdown bottom ");
                click(currencyDropdownButtom);
                waitElementToBeVisible(buttomEUR);
                logger.info("Clicking on bottom EUR");
                click(buttomEUR);
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
                waitElementToBeVisible(currencyDropdownButtom);
                logger.info("Clicking on currency dropdown bottom ");
                click(currencyDropdownButtom);
                waitElementToBeVisible(buttonUSD);
                logger.info("Clicking on bottom USD");
                click(buttonUSD);
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
@Step
    public SearchPage findProducts(String product) {
        logger.info("---------------------------------------------------------------------------------------------------------");
        logger.info("Try found product");
        waitElementToBeClickable(searchField);
        logger.info("Enter name products : " + product);
        driver.findElement(searchField).sendKeys(product);
        logger.info("Click on search bottom");
        click(buttomSearch);
        logger.info("Founded products : " + product);
        return new SearchPage();
    }
}
