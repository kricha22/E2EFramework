package org.example.tests;

import org.example.pageobjects.*;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderTest extends BaseTest {

    @Test(dataProvider = "getData1", groups = {"purchase"})
    public void submitOrder(String email, String password, String productName) {
        String country = "india";
        String successMessage = "THANKYOU FOR THE ORDER.";

        ProductCatalogue productCatalogue=loginPage.loginToApplication(email, password);

        productCatalogue.addProductToCart(productName);
        MyCart myCart =productCatalogue.clickCart();

        Boolean cartMatch=myCart.validatePresenceOfProductByName(productName);
        Assert.assertTrue(cartMatch);
        Checkout checkout= myCart.clickBtnCheckout();

        checkout.enterCountry(country);
        OrderConfirmation orderConfirmation=checkout.goToConfirmationPage();

        String msg= orderConfirmation.getSuccessMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(successMessage));
    }


    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrder() {
        String email = "richa.kumari@gmail.com";
        String password = "Richa22@";
        String productName ="ZARA COAT 3";
        String country = "india";
        String successMessage = "THANKYOU FOR THE ORDER.";

        ProductCatalogue productCatalogue=loginPage.loginToApplication(email, password);

        productCatalogue.addProductToCart(productName);
     /*   MyCart myCart =productCatalogue.clickCart();

        Boolean cartMatch=myCart.validatePresenceOfProductByName(productName);
        Assert.assertTrue(cartMatch);
        Checkout checkout= myCart.clickBtnCheckout();

        checkout.enterCountry(country);
        OrderConfirmation orderConfirmation=checkout.goToConfirmationPage();

        String msg= orderConfirmation.getSuccessMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(successMessage));*/
    }

    @DataProvider
    public Object[][] getData1() {
        return new Object[][] {
                {"richa.kumari@gmail.com","Richa22@","ZARA COAT 3"},
                {"richa.kumari@gmail.com","Richa22@","IPHONE 13 PRO"}
        };
    }

    @DataProvider
    public Object[][] getData2() {
        HashMap<String,String> input1 = new HashMap<>();
        input1.put("email","richa.kumari@gmail.com");
        input1.put("password","Richa22@");
        input1.put("productName","ZARA COAT 3");

        HashMap<String,String> input2 = new HashMap<>();
        input2.put("email","richa.kumari@gmail.com");
        input2.put("password","Richa22@");
        input2.put("productName","ZARA COAT 3");
        return new Object[][] {
                {input1},
                {input2}
        };
    }


    @Test(dataProvider = "getData2")
    public void submitOrder2(HashMap<String, String> input) {
        String country = "india";
        String successMessage = "THANKYOU FOR THE ORDER.";

        ProductCatalogue productCatalogue=loginPage.loginToApplication(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));
        MyCart myCart =productCatalogue.clickCart();

        Boolean cartMatch=myCart.validatePresenceOfProductByName(input.get("productName"));
        Assert.assertTrue(cartMatch);
        Checkout checkout= myCart.clickBtnCheckout();

        checkout.enterCountry(country);
        OrderConfirmation orderConfirmation=checkout.goToConfirmationPage();

        String msg= orderConfirmation.getSuccessMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(successMessage));
    }

    @DataProvider
    public Object[][] getData3() throws IOException {
        List<HashMap<String,String>> input = getDataFromJson(System.getProperty("user.dir")+"//src/test/java/org/example/data/purchase.json");
        return new Object[][] {
                {input.get(0)},
                {input.get(1)}
        };
    }


    @Test(dataProvider = "getData3")
    public void submitOrder3(HashMap<String, String> input) {
        String country = "india";
        String successMessage = "THANKYOU FOR THE ORDER.";

        ProductCatalogue productCatalogue=loginPage.loginToApplication(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));
        MyCart myCart =productCatalogue.clickCart();

        Boolean cartMatch=myCart.validatePresenceOfProductByName(input.get("productName"));
        Assert.assertTrue(cartMatch);
        Checkout checkout= myCart.clickBtnCheckout();

        checkout.enterCountry(country);
        OrderConfirmation orderConfirmation=checkout.goToConfirmationPage();

        String msg= orderConfirmation.getSuccessMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(successMessage));
    }
}
