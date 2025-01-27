package com.saucedemo.tests;

import com.saucedemo.basetest.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.testutility.AdditionalDescriptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void initializeLoginPage(){
        loginPage=new LoginPage(getDriver());
    }

    @AdditionalDescriptions({"AT-005"})
    @Test
    public void verifyLoginWithValidCredentials() throws InterruptedException {
        loginPage.enterUsername(prop.getProperty("validUsername"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickLoginButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), prop.getProperty("productPageUrl"));
    }

    @AdditionalDescriptions({"AT-006"})
    @Test
    public void verifyLoginFailedWithInValidCredentials() throws InterruptedException {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce1");
        loginPage.clickLoginButton();
        Assert.assertNotEquals(getDriver().getCurrentUrl(), prop.getProperty("productPageUrl"));
    }


}
