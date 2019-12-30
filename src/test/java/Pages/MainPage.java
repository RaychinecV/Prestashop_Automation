package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends HelpClass {
    private WebDriver driver;
    private By popularPrice = By.xpath("//div[@class='product-description']//span[contains(@class,'price')]");
    private By currencyDropdownBottom = By.xpath("//div[@class='currency-selector dropdown js-dropdown']//a[@class='hidden-sm-down']");
    private By bottomUAH = By.xpath("//a[contains(text(),'UAH ₴')]");
    private By bottomEUR = By.xpath("//a[contains(text(),'EUR €')]");
    private By bottomUSD = By.xpath("//a[contains(text(),'USD $')]");
    private By searchField = By.xpath("//input[@placeholder='Поиск в каталоге']");
    private By bottomSearch = By.xpath("//div[@id='search_widget']//i[@class='material-icons search']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //step 2 + step 3 - check currency and choice product in USD
    public boolean checkAllCurrency(Currency currency) {
        waitElementToBeClickable(currencyDropdownBottom);
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Checking that currency of all products is displayed correct on main page");
        switch (currency) {
            case UAH:
                log.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeClickable(bottomUAH);
                log.info("Clicking on bottom UAH");
                driver.findElement(bottomUAH).click();
                List<WebElement> listPopularProductsUAH = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsUAH) {
                    log.info("Price "+productsPopularPrice.getText()+" in UAH - PASS");
                    if (!productsPopularPrice.getText().contains("₴")) {
                        log.error("Price "+productsPopularPrice+" in not UAH - FAIL");
                        return false;
                    }
                }
                log.info("All products are displayed in UAH");
                return true;

            case EUR:
                log.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeClickable(bottomEUR);
                log.info("Clicking on bottom EUR");
                driver.findElement(bottomEUR).click();
                List<WebElement> listPopularProductsEUR = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsEUR) {
                    log.info("Price "+productsPopularPrice.getText()+" in EUR - PASS");
                    if (!productsPopularPrice.getText().contains("€")) {
                        log.error("Price "+productsPopularPrice+" in not EUR - FAIL");
                        return false;
                    }
                }
                log.info("All products are displayed in EUR");
                return true;

            case USD:
                log.info("Clicking on currency dropdown bottom ");
                driver.findElement(currencyDropdownBottom).click();
                waitElementToBeClickable(bottomUSD);
                log.info("Clicking on bottom USD");
                driver.findElement(bottomUSD).click();
                List<WebElement> listPopularProductsUSD = driver.findElements(popularPrice);
                for (WebElement productsPopularPrice : listPopularProductsUSD) {
                    log.info("Price "+productsPopularPrice.getText()+" in USD - PASS");
                    if (!productsPopularPrice.getText().contains("$")) {
                        log.error("Price "+productsPopularPrice+" in not USD - FAIL");
                        return false;
                    }
                }
                log.info("All products are displayed in USD");

        }
        return true;
    }

    // step 4 - found product
    public SecondPage findProducts(String product) {
        log.info("---------------------------------------------------------------------------------------------------------");
        log.info("Try found product");
        waitElementToBeClickable(searchField);
        log.info("Enter name products : "+product);
        driver.findElement(searchField).sendKeys(product);
        log.info("Click on search bottom");
        driver.findElement(bottomSearch).click();
        log.info("Founded products : " + product);
        return new SecondPage(HelpClass.getDriver());
    }
}
