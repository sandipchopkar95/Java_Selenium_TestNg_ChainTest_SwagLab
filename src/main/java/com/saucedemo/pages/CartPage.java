package com.saucedemo.pages;

import com.saucedemo.javautility.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends UtilityClass {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'CHECKOUT')]")
    private WebElement button_Checkout;

    @FindBy(id = "first-name")
    private WebElement textField_FirstName;

    @FindBy(id = "last-name")
    private WebElement textField_SecondName;

    @FindBy(id = "postal-code")
    private WebElement textField_PostalCode;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    private WebElement button_Continue;

    @FindBy(xpath = "//a[contains(text(), 'CANCEL')]")
    private WebElement button_Cancel;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> productNameInList;

    public void clickCheckoutButton(){
        click(driver,button_Checkout);
    }

    public void enterPersonalDetails(String firstName,String secondName, String postalCode){
        sendKeys(driver,textField_FirstName,firstName);
        sendKeys(driver,textField_SecondName,secondName);
        sendKeys(driver,textField_PostalCode,postalCode);
    }

    public void clickCancelButton(){
        click(driver,button_Cancel);
    }

    public void clickContinueButton(){
        click(driver,button_Continue);
    }

    public boolean checkWhichProductIsAvailable(String searched_Valued) {
        boolean isProductAvailable = false;
        for (WebElement product : productNameInList) {
            if (product.getText().contains(searched_Valued)) {
                isProductAvailable = true;
            }
        }
        return isProductAvailable;
    }

    public CheckoutPage navigateToCheckOutPage(){
        clickCheckoutButton();
        enterPersonalDetails("firstName","lastName","123456");
        clickContinueButton();
        waitForSeconds(driver,5);
        return new CheckoutPage(driver);
    }
}
