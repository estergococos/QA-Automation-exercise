package selenium.locators;

import org.openqa.selenium.By;

public enum Locators {

    HOME_SEARCH_TEXT_BOX(By.id("twotabsearchtextbox")),
    HOME_SEARCH_BUTTON(By.xpath("//input[@value='Go']")),
    SEARCH_RESULTS_FIRST_OPTION(By.xpath("//div[@class='s-result-list s-search-results sg-row']//img")),
    ARTICLE_QUANTITY_SELECT(By.id("quantity")),
    ARTICLE_UNITY_PRICE(By.id("price_inside_buybox")),
    ARTICLE_ADD_CART_BUTTON(By.id("add-to-cart-button")),
    CONFIRMATION_GO_TO_CART_BUTTON(By.id("hlb-view-cart-announce")),
    SHOPPING_CART_ARTICLES(By.xpath("//div[@data-name='Active Items']/div")),
    SHOPPING_CART_TOTAL_PRICE(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span")),
    SHOPPING_CART_QUANTITY_DROPDOWNS(By.xpath("//select[@name='quantity']")),
    SHOPPING_CART_HEADER_BUTTON(By.id("nav-cart"));

    private By locator;

    Locators(By locator) {
        this.locator = locator;
    }

    public By by() {
        return locator;
    }
}
