package org.example.pageobjects;

import org.example.Utilities.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkout extends BaseClass {
    WebDriver driver;

    public Checkout(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[@class='input txt text-validated'])[2]")
    WebElement inputCountry;

    By listCountry = By.xpath("(//*[@class='ta-results list-group ng-star-inserted'])");

    @FindBy(xpath = "(//*[@class='ta-item list-group-item ng-star-inserted'])[2]")
    WebElement btnIndia;

    @FindBy(xpath = "(//*[@class='btnn action__submit ng-star-inserted'])")
    WebElement btnPlaceOrder;

    public void enterCountry(String country) {
        enterText(inputCountry, country);
        waitForVisibleElement(listCountry);
        btnIndia.click();
    }

    public OrderConfirmation goToConfirmationPage() {
        btnPlaceOrder.click();
        return new OrderConfirmation(driver);
    }
}
