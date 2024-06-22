package org.example.pageobjects;

import org.example.Utilities.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends BaseClass {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> listProducts;

    By btnAddToCart = By.xpath("//button[2]");

    @FindBy(css = "b")
    WebElement lblProduct;

    By lblPrdAdded = By.id("toast-container");



    By listProduct = By.cssSelector(".mb-3");
    By prdct= By.cssSelector("b");

    public List<WebElement> getProductList() {
        waitForVisibleElement(listProduct);
        return listProducts;
    }

    public WebElement getProductByName(String productName) {
        WebElement product = getProductList().stream().filter(prd -> prd.findElement(prdct).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(btnAddToCart).click();
        waitForVisibleElement(lblPrdAdded);
    }

    public Boolean validatePresenceOfProduct(String productName) {
        return getProductList().stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
    }
}
