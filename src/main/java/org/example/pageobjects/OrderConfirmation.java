package org.example.pageobjects;

import org.example.Utilities.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmation extends BaseClass {
    WebDriver driver;

    public OrderConfirmation(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='hero-primary']")
    WebElement lblCnfText;

    public String getSuccessMessage() {
        return lblCnfText.getText();
    }
}
