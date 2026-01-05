package Tests;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders;
import Drivers.DriverManager;
import Pages.HomePage;
import Pages.OverviewPage;


public class TS02_Verify_Login_Functionality extends BaseTest 
{
	@Test(description = "TS02_Verify Login Functionality", dataProvider="register",dataProviderClass=DataProviders.class)
	public void testVerifyLoginFunctionality(Map<String,String>data)
	{
	    logger.info("------------------------ Starting test: testRegisterAccountSuccessful ---------------------");

	    // Initialize WebDriver and HomePage
	    WebDriver driver = DriverManager.getWebDriver();
	    HomePage homePage = new HomePage(driver);

	    //Verify Home Page
	    logger.info("Checking if Home Page is displayed");
	    Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page should be displayed, but it is not");
	    logger.info("Home Page displayed successfully");

	    logger.info("Verifying Home Page title");
	    Assert.assertEquals(homePage.getHomePageTiltle(), 
	            "ParaBank | Welcome | Online Banking", 
	            "Title mismatch");
	    logger.info("Home Page title verified successfully");

	    logger.info("Verifying Home Page URL");
	    Assert.assertTrue(homePage.getHomePageUrl().contains("index.htm"), 
	            "Home Page URL mismatch");
	    logger.info("Home Page URL verified successfully");

	    //Perform Login
	    homePage.enterUsername(data.get("Username"));
	    homePage.enterPassword(data.get("Password"));
	    homePage.clickOnLogin();
	    logger.info("Entered username and password, clicked on login button");

	    //Verify Overview Page
	    OverviewPage overviewPage = new OverviewPage(driver);

	    Assert.assertTrue(overviewPage.overviewPageUrl().contains("/overview.htm"), 
	            "Accounts Overview page URL does not match!");
	    logger.info("Accounts Overview URL verified");

	    Assert.assertEquals(overviewPage.overviewPageTitle(), 
	            "ParaBank | Accounts Overview", 
	            "Accounts Overview title does not match!");
	    logger.info("Accounts Overview page title verified");

	    Assert.assertTrue(overviewPage.isAccOverViewDisplayed(), 
	            "Accounts Overview page not displayed correctly");
	    logger.info("Accounts Overview page displayed successfully");

	    logger.info("Navigated to Account Overview page successfully");

	    //  Logout and Verify Redirection
	    overviewPage.clickOnLogout();
	    logger.info("Clicked on logout button");

	    logger.info("Verifying Home Page after logout");
	    Assert.assertTrue(homePage.isHomePageDisplayed(), 
	            "Home Page should be displayed after logout, but it is not");
	    logger.info("Redirected to Home Page successfully after logout");

	    //  Test Completion
	    logger.info("--------------Test completed successfully: testRegisterAccountSuccessful ------------------------"); 
	}
}