package com.saucedemo.pages;

import com.saucedemo.constants.AppConstants;
import com.saucedemo.javautility.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends UtilityClass {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product_label']")
    private WebElement pageHeader_product;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> list_Products;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private WebElement single_Product;

    @FindBy(xpath = "//button[text()='ADD TO CART']")
    private List<WebElement> buttons_AddToCart;

    @FindBy(xpath = "//button[text()='ADD TO CART']")
    private WebElement button_AddToCart;

    @FindBy(xpath = "//button[text()='REMOVE']")
    private WebElement button_Remove;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> price_Products;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private WebElement price_Product;

    @FindBy(xpath = "//img[@class='inventory_item_img']")
    private WebElement label_Price;

    @FindBy(id = "shopping_cart_container")
    private WebElement container_ShoppingCart;

    public String getProductPageHeading() {
        return getText(driver, pageHeader_product);
    }

    private String locatorStringForProductName(){
       return  "//div[@class='inventory_item_name']";
    }

    public boolean validateSearchedDataAvailableForAllProducts(String searchedData) {
        switch (searchedData) {
            case AppConstants.ADD_TO_CART_BUTTON:
                return checkEachProductForAttribute(list_Products, button_AddToCart, searchedData);
            case AppConstants.PRODUCT_IMAGE:
                return checkEachProductForAttribute(list_Products, price_Product, searchedData);
            case AppConstants.PRICE_LABEL:
                return checkEachProductForAttribute(list_Products, label_Price, searchedData);

            default:
                System.out.println("Invalid product attribute: " + searchedData);
                return false;
        }
    }

    public String add_CheckButtonForProduct(String productName, String testCase) {
        boolean productFound = false;
        String sendValue = null;
        for (WebElement product : list_Products) {
            String currentProductName = product.findElement(By.xpath(locatorStringForProductName())).getText();
            System.out.println(currentProductName);
            if (currentProductName.equalsIgnoreCase(productName)) {
                productFound = true;
                switch (testCase) {
                    case AppConstants.CLICK_ADD_TO_CART_BUTTON:
                        click(driver, button_AddToCart);
                        System.out.println("Clicked on 'Add to Cart' button for product: " + productName);
                        sendValue = String.valueOf(true);
                        break;

                    case AppConstants.VISIBLE_ADD_TO_CART_BUTTON:
                        sendValue = String.valueOf(isVisible(driver, button_AddToCart));
                        break;

                    case AppConstants.CLICK_REMOVE_BUTTON:
                        click(driver, button_Remove);
                        System.out.println("Clicked on 'Remove' button for product: " + productName);
                        sendValue = String.valueOf(true);
                        break;

                    case AppConstants.VISIBLE_REMOVE_BUTTON:
                        sendValue = String.valueOf(isVisible(driver, button_Remove));
                        break;

                    default:
                        System.out.println("Invalid action case: " + testCase);
                        sendValue = "Invalid action";
                }
                break;
            }
        }
        if (!productFound) {
            throw new RuntimeException("Product with name '" + productName + "' not found on the page.");
        }
        return sendValue;
    }

    public void clickShoppingCartContainer(){
        click(driver,container_ShoppingCart);
    }

    private boolean checkEachProductForAttribute(@NotNull List<WebElement> list_Element, WebElement element, String searched_Valued) {
        boolean allProductsHaveSearchedAttribute = true;
        for (WebElement product : list_Element) {
            try {
                if (element != null && element.isDisplayed()) {
                    System.out.println("Product: " + product.getText() + " has an " + searched_Valued);
                } else {
                    System.out.println("Product: " + product.getText() + " does NOT have a visible " + searched_Valued);
                    allProductsHaveSearchedAttribute = false;
                }
            } catch (Exception e) {
                System.out.println("Product: " + product.getText() + " does NOT have an " + searched_Valued);
                allProductsHaveSearchedAttribute = false;
            }
        }
        return allProductsHaveSearchedAttribute;
    }

    public CartPage navigateToCartPage(){
        click(driver, button_AddToCart);
        add_CheckButtonForProduct(AppConstants.PRODUCT_TO_ADD,AppConstants.CLICK_ADD_TO_CART_BUTTON);
        clickShoppingCartContainer();
        waitForSeconds(driver,5);
        return new CartPage(driver);
    }

}
