package com.saucedemo.tests;

import com.saucedemo.basetest.BaseTest;
import com.saucedemo.constants.AppConstants;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.testutility.AdditionalDescriptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.saucedemo.constants.AppConstants.HEADER_PRODUCT_PAGE;

public class ProductPageTest extends BaseTest {
    private ProductPage productPage;

    @BeforeMethod
    public void initializeProductPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        productPage = loginPage.loginToTheApplication(prop.getProperty("validUsername"), prop.getProperty("validPassword"));
    }

    @AdditionalDescriptions({"AT-007"})
    @Test
    public void verifyProductPageHeading() {
        String productPageHeading = productPage.getProductPageHeading();
        Assert.assertEquals(productPageHeading, HEADER_PRODUCT_PAGE);
    }

    @AdditionalDescriptions({"AT-008"})
    @Test
    public void verifyAllProductsHaveOwnAddToCartButton() {
        Assert.assertTrue(productPage.validateSearchedDataAvailableForAllProducts(AppConstants.ADD_TO_CART_BUTTON), "Some products don't have Add To Cart buttons");
    }

    @AdditionalDescriptions({"AT-009"})
    @Test
    public void verifyAllProductsHaveOwnPriceLabel() {
        Assert.assertTrue(productPage.validateSearchedDataAvailableForAllProducts(AppConstants.PRICE_LABEL), "Some products don't have Price labels");
    }

    @AdditionalDescriptions({"AT-010"})
    @Test
    public void verifyAllProductsHaveOwnImages() {
        Assert.assertTrue(productPage.validateSearchedDataAvailableForAllProducts(AppConstants.PRODUCT_IMAGE), "Some products don't have Images");
    }

    @AdditionalDescriptions({"AT-011"})
    @Test
    public void verifyUserCanAddProductToCart() {
        productPage.add_CheckButtonForProduct(AppConstants.PRODUCT_TO_ADD, AppConstants.CLICK_ADD_TO_CART_BUTTON);
        String isRemoveButtonDisplay = productPage.add_CheckButtonForProduct(AppConstants.PRODUCT_TO_ADD, AppConstants.VISIBLE_REMOVE_BUTTON);
        Assert.assertEquals(isRemoveButtonDisplay, "true","Remove button not display");
    }


}
