package org.example.tests;

import org.example.pageobjects.ProductCatalogue;
import org.junit.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"LoginError"}, retryAnalyzer = RetryListener.class)
    public void loginErrorValidations() {
        String email = "richa.kum@gmail.com";
        String password = "Richa22@";
        String loginErrorMessage = "Incorrect email or password.";

        loginPage.loginToApplication(email, password);
        String msg= loginPage.getLoginErrorMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(loginErrorMessage));
    }

    @Test
    public void productErrorValidations() {
        String email = "richa.kumari@gmail.com";
        String password = "Richa22@";
        String productName ="ZARA COAT 33";

        ProductCatalogue productCatalogue=loginPage.loginToApplication(email, password);
        Boolean match = productCatalogue.validatePresenceOfProduct(productName);
        Assert.assertFalse(match);
    }
}
