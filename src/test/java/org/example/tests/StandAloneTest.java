package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobjects.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String []args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("richa.kumari@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Richa22@");
        driver.findElement(By.id("login")).click();
        List<WebElement> listProduct=driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = listProduct.stream().filter(prd->prd.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        assert product != null;
        product.findElement(By.xpath("//button[2]")).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        driver.findElement(By.xpath("(//*[@class='btn btn-custom'])[3]/parent::li")).click();
        List<WebElement> cart=driver.findElements(By.xpath("//*[@class='cartSection']//h3"));
        Boolean cartMatch=cart.stream().anyMatch(c->c.getText().equalsIgnoreCase("ZARA COAT 3"));
        Assert.assertTrue(cartMatch);
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[3]")).click();
        Actions action=new Actions(driver);
        action.sendKeys(driver.findElement(By.xpath("(//*[@class='input txt text-validated'])[2]")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='ta-results list-group ng-star-inserted'])")));
        driver.findElement(By.xpath("(//*[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();
        driver.findElement(By.xpath("(//*[@class='btnn action__submit ng-star-inserted'])")).click();
        String msg= driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("passed");
        driver.close();
    }
}
