package Tests;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders;
import Drivers.DriverManager;
import Pages.HomePage;
import Pages.RegisterPage;

public class TS01_Verify_New_User_Registration extends BaseTest
{
	@Test(description = "TC_001 - Verify user can register an account successfully", dataProvider="register",dataProviderClass=DataProviders.class)
	public void testRegisterAccountSuccessful(Map<String,String>data)
	{
	    logger.info("Starting test: testRegisterAccountSuccessful");

	    // Initialize WebDriver and navigate to Home Page
	    WebDriver driver = DriverManager.getWebDriver();
	    HomePage homePage = new HomePage(driver);

	    // Verify Home Page
	    logger.info("Checking if Home Page is displayed");
	    Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page should be displayed, but it is not");
	    logger.info("Home Page displayed successfully");

	    logger.info("Verifying Home Page title");
	    Assert.assertEquals(homePage.getHomePageTiltle(),
	            "ParaBank | Welcome | Online Banking",
	            "Home Page title mismatch");
	    logger.info("Home Page title verified successfully");

	    logger.info("Verifying Home Page URL");
	    Assert.assertTrue(homePage.getHomePageUrl().contains("index.htm"), "Home Page URL mismatch");
	    logger.info("Home Page URL verified successfully");

	    //Navigate to Registration Page
	    homePage.clickRegister();
	    logger.info("Clicked on Register link");

	    RegisterPage regPage = new RegisterPage(driver);

	    Assert.assertTrue(regPage.registerPageUrl().contains("/register.htm"),
	            "Registration page URL does not match!");
	    logger.info("Registration page URL verified");

	    Assert.assertEquals(regPage.registerPageTitle(),
	            "ParaBank | Register for Free Online Account Access",
	            "Registration page title mismatch");
	    logger.info("Registration page title verified");

	    logger.info("Navigated to Registration page successfully");

	    // Fill Personal Details
	    regPage.personalDetails().enterFirstName(data.get("FirstName"));
	    regPage.personalDetails().enterLastName(data.get("LastName"));
	    regPage.personalDetails().enterAddress(data.get("Address"));
	    regPage.personalDetails().enterCity(data.get("City"));
	    regPage.personalDetails().enterState(data.get("State"));
	    regPage.personalDetails().enterZipCode(data.get("ZipCode"));
	    regPage.personalDetails().enterPhone(data.get("Phone"));
	    regPage.enterSSN(data.get("SSN"));

	    logger.info("Filled personal details");

	    //Fill Account Credentials
	    regPage.enterUsername(data.get("Username"));
	    regPage.enterPassword(data.get("Password"));
	    regPage.enterConfirmPassword(data.get("Confirm"));

	    logger.info("Filled account credentials");

	    //Submit Registration Form
	    regPage.clickOnRegister();
	    logger.info("Clicked on Register button");

	    // Verify Account Creation
	    Assert.assertTrue(regPage.validateAccountCreation(),
	            "Account creation failed for user: " + data.get("Username"));
	    logger.info("Account created successfully for user: {}", data.get("Username"));

	    //Logout
	    regPage.clickOnLogout();
	    logger.info("Clicked on logout button and logged out successfully");

	    logger.info("Test completed successfully: testRegisterAccountSuccessful");
	}
}