package org.example.pageobjects;

import org.example.Utilities.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyCart extends BaseClass {
    WebDriver driver;

    public MyCart(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='cartSection']//h3")
    List<WebElement> listProduct;

    @FindBy(xpath = "(//*[@class='btn btn-primary'])[3]")
    WebElement btnCheckout;

    public List<WebElement> getProductList() {
        return listProduct;
    }

    public Boolean validatePresenceOfProductByName(String productName) {
        return getProductList().stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
    }

    public Checkout clickBtnCheckout() {
       btnCheckout.click();
       return new Checkout(driver);
    }
}
