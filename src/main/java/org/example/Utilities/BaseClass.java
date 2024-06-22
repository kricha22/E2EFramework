package org.example.Utilities;

import org.example.pageobjects.MyCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {
    WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[@class='btn btn-custom'])[3]/parent::li")
    WebElement btnCart;

    public void waitForVisibleElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public MyCart clickCart() {
        btnCart.click();
        return new MyCart(driver);
    }

    public void enterText(WebElement element, String value) {
        Actions action = new Actions(driver);
        action.sendKeys(element, value).build().perform();
    }
}
