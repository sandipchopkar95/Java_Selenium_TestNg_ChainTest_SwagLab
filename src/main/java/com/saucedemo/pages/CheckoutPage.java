package com.saucedemo.pages;

import com.saucedemo.javautility.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends UtilityClass {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> productNameInList;

    @FindBy(xpath = "//a[contains(text(), 'FINISH')]")
    private WebElement button_Finish;

    @FindBy(xpath = "//a[contains(text(), 'CANCEL')]")
    private WebElement button_Cancel;

    @FindBy(xpath = "//div[@class='subheader']")
    private WebElement label_Header;

    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement header_Complete;

    public String getCheckoutHeader(){
        return getText(driver,label_Header);
    }

    public void clickFinishButton() {
        click(driver, button_Finish);
    }

    public void clickCancelButton() {
        click(driver, button_Cancel);
    }

    public String getCompleteHeaderText(){
        return getText(driver,header_Complete);
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
}
