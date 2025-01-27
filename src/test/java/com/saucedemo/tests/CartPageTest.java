package com.saucedemo.tests;

import com.saucedemo.basetest.BaseTest;
import com.saucedemo.constants.AppConstants;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.testutility.AdditionalDescriptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {
    private CartPage cartPage;

    @BeforeMethod
    public void initializeCartPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        cartPage= loginPage.loginToTheApplication(prop.getProperty("validUsername"), prop.getProperty("validPassword")).navigateToCartPage();
    }

    @AdditionalDescriptions({"AT-001"})
    @Test
    public void verifyAddedProductInCart(){
      boolean isProductIsSame= cartPage.checkWhichProductIsAvailable(AppConstants.PRODUCT_TO_ADD);
        Assert.assertTrue(isProductIsSame,"Added product & available product is different");
    }

    @AdditionalDescriptions({"AT-002"})
    @Test
    public void verifyUserCanNavigateToCheckout(){
        cartPage.clickCheckoutButton();
        cartPage.enterPersonalDetails("test firstname","test lastname","123456");
        cartPage.clickContinueButton();
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        Assert.assertEquals(checkoutPage.getCheckoutHeader(),"Checkout: Overview");
    }
}
