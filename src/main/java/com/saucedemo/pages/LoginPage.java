package com.saucedemo.pages;

import com.saucedemo.javautility.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends UtilityClass {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement textField_username;

    @FindBy(id = "password")
    private WebElement textField_password;

    @FindBy(id = "login-button")
    private WebElement button_LogIn;

    public void enterUsername(String username) {
        sendKeys(driver, textField_username, username);
    }

    public void enterPassword(String password) {
        sendKeys(driver, textField_password, password);
    }

    public void clickLoginButton() throws InterruptedException {
        click(driver,button_LogIn);
        Thread.sleep(1000);
        waitForSeconds(driver,20);
    }

    public ProductPage loginToTheApplication(String username,String password){
        sendKeys(driver, textField_username, username);
        sendKeys(driver, textField_password, password);
        click(driver,button_LogIn);
        waitForSeconds(driver,5);
        return new ProductPage(driver);
    }

}
