package org.example.pageobjects;

import org.example.Utilities.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //Way 1
   /* WebElement inputEmail =  driver.findElement(By.id("userEmail"));
    WebElement inputPassword =  driver.findElement(By.id("userPassword"));
    WebElement btnLogin =  driver.findElement(By.id("login"));*/

    //way 2
    @FindBy(id="userEmail")
    WebElement inputEmail;

    @FindBy(id="userPassword")
    WebElement inputPassword;

    @FindBy(id="login")
    WebElement btnLogin;

    @FindBy(xpath = "//*[@role='alert']")
    WebElement lblLoginError;

    public ProductCatalogue loginToApplication(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLogin.click();
        return new ProductCatalogue(driver);
    }

    public void goToApplication() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getLoginErrorMessage() {
        return lblLoginError.getText();
    }
}
