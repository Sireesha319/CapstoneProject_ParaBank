package Tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Drivers.DriverFactory;
import Drivers.DriverManager;
import Pages.HomePage;
import Utils.ConfigReader;

public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters({"os","browser"})
    @BeforeMethod
    public void setUp(@Optional("windows") String os,
                      @Optional("chrome") String browser) {

        WebDriver driverRef = DriverFactory.createDriver(browser);
        DriverManager.setWebDriver(driverRef);

        DriverManager.getWebDriver().get(ConfigReader.get("base.url"));
    }

    // ✅ ADD THIS METHOD
    protected void login(String username, String password) {

        HomePage homePage = new HomePage(DriverManager.getWebDriver());

        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickOnLogin();

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getWebDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("overview.htm"));
    }

    @AfterMethod
    public void tearDown() {
        if (DriverManager.getWebDriver() != null) {
            DriverManager.getWebDriver().quit();
            DriverManager.removeDriver();
        }
    }
}
