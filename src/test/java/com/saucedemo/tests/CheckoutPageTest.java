package com.saucedemo.tests;

import com.saucedemo.basetest.BaseTest;
import com.saucedemo.constants.AppConstants;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.testutility.AdditionalDescriptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest {
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void initializeCheckoutPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        checkoutPage = loginPage.loginToTheApplication(prop.getProperty("validUsername"), prop.getProperty("validPassword"))
                .navigateToCartPage().navigateToCheckOutPage();
    }

    @AdditionalDescriptions({"AT-003"})
    @Test
    public void verifySameProductIsAvailableInCheckoutList() {
        Assert.assertTrue(checkoutPage.checkWhichProductIsAvailable(AppConstants.PRODUCT_TO_ADD));
    }

    @AdditionalDescriptions({"AT-004"})
    @Test
    public void verifyOrderPlacedSuccessfully() {
        checkoutPage.clickFinishButton();
        String completeMsg = checkoutPage.getCompleteHeaderText();
        Assert.assertEquals(completeMsg, "THANK YOU FOR YOUR ORDER");
    }

}
