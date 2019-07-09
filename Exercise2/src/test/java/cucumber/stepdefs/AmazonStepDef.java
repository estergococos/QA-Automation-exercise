package cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import cucumber.runners.TestState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static selenium.locators.Locators.*;

public class AmazonStepDef {
    WebDriver driver;
    private static float articlePrice;
    private static String articleName;
    private static String articleID;
    private static int articleQuantity;
    private static float totalPrice;

    public AmazonStepDef(TestState state) {
        state.setupState();
        driver = state.getDriver();
    }

    @Given("I go to \"([^\"]*)\"$")
    public void go_to(String url) {
        driver.navigate().to(url);
    }

    @Given("I search for \"([^\"]*)\" in home page$")
    public void search_for(String keyword) {
        driver.findElement(HOME_SEARCH_TEXT_BOX.by()).sendKeys(keyword);
        driver.findElement(HOME_SEARCH_BUTTON.by()).click();
    }

    //------------------------------------------------------------------------------------------------------------------

    @When("I add first result to cart with quantity \"([^\"]*)\"$")
    public void add_first_result(String quantity) {
        driver.findElement(SEARCH_RESULTS_FIRST_OPTION.by()).click();
        String url[] = driver.getCurrentUrl().split("/");
        articlePrice = Float.parseFloat(driver.findElement(ARTICLE_UNITY_PRICE.by()).getText().substring(1));
        articleName = driver.findElement(ARTICLE_UNITY_PRICE.by()).getText();
        articleQuantity = Integer.parseInt(quantity);
        articleID = url[5];
        Select quantityDropDown = new Select(driver.findElement(ARTICLE_QUANTITY_SELECT.by()));
        quantityDropDown.selectByValue(quantity);
        driver.findElement(ARTICLE_ADD_CART_BUTTON.by()).click();
    }

    @When("I change the quantity of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void change_quantity(String order, String newQuantity) {
        driver.findElement(SHOPPING_CART_HEADER_BUTTON.by()).click();
        List<WebElement> elementsList = driver.findElements(SHOPPING_CART_ARTICLES.by());
        articlePrice = Float.parseFloat(elementsList.get(elementsList.size() - Integer.parseInt(order)).getAttribute("data-price"));
        articleID = elementsList.get(elementsList.size() - Integer.parseInt(order)).getAttribute("data-asin");
        articleQuantity = Integer.parseInt(newQuantity);
        List<WebElement> dropDowns = driver.findElements(SHOPPING_CART_QUANTITY_DROPDOWNS.by());
        Select quantityDropDown = new Select(dropDowns.get(elementsList.size() - Integer.parseInt(order)));
        quantityDropDown.selectByValue(newQuantity);
        try {
            Thread.sleep(1000);//This is not a good practice. A conditional wait should replace this sleep time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    @Then("Products appears on shopping cart and price is correct$")
    public void shopping_cart_is_correct() {
        boolean articleFound = false;
        if (!driver.getCurrentUrl().contains("cart")) {
            driver.findElement(CONFIRMATION_GO_TO_CART_BUTTON.by()).click();
        }
        List<WebElement> elementsList = driver.findElements(SHOPPING_CART_ARTICLES.by());
        totalPrice = 0;
        for (int index = 0; index < elementsList.size(); index++) {
            if (elementsList.get(index).getAttribute("data-asin").equals(articleID)) {
                assertEquals("Price doesn't correspond to product added", "" + articlePrice, elementsList.get(index).getAttribute("data-price"));
                assertEquals("Quantity doesn't correspond to product added", "" + articleQuantity, elementsList.get(index).getAttribute("data-quantity"));
                articleFound = true;
            }
            totalPrice = totalPrice + Float.parseFloat(elementsList.get(index).getAttribute("data-price")) * Integer.parseInt(elementsList.get(index).getAttribute("data-quantity"));
        }
        assertTrue("Article is not in Shopping Cart", articleFound);
        assertEquals("Total price is not correct", "" + totalPrice, driver.findElement(SHOPPING_CART_TOTAL_PRICE.by()).getText().substring(4));
    }
}
