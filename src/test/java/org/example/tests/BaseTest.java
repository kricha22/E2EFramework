package org.example.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pageobjects.LoginPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Properties prop;
    public LoginPage loginPage;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src/main/java/org/example/resources/GlobalData.properties");
        prop.load(fis);
        String browser =System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
        if(browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browser.contains("headless")) {
                options.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if(browser.equals("firefox")) {
            //firefox
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goToApplication();
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.close();
    }

    public List<HashMap<String, String>> getDataFromJson(String filePath) throws IOException {
        //json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),  StandardCharsets.UTF_8);

        //String to list hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =(TakesScreenshot) driver;
        File sourcePath = ts.getScreenshotAs(OutputType.FILE);
        String destinationPath=System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(sourcePath, new File(destinationPath));
        return destinationPath;
    }
}
