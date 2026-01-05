package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders;
import Drivers.DriverManager;
import Pages.HomePage;
import Pages.OverviewPage;
import Pages.UpdateProfilePage;
import Utils.waitUtils;


public class TS05_Verify_Profile_Update extends BaseTest {

	@Test(dataProvider = "updateProfileData", dataProviderClass=DataProviders.class)
    public void testUpdateProfile(String username, String password, String phone) {

        logger.info("-------------- Starting test: testUpdateProfile ------------------------");

        WebDriver driver = DriverManager.getWebDriver();
        HomePage homePage = new HomePage(driver);

        // Wait for Home Page to load completely
        waitUtils.untilPageLoadComplete(driver);

        // Verify Home Page
        logger.info("Checking if Home Page is displayed");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page should be displayed, but it is not");
        logger.info("Home Page displayed successfully");

        logger.info("Verifying Home Page title");
        Assert.assertEquals(homePage.getHomePageTiltle(), "ParaBank | Welcome | Online Banking", "Home Page title mismatch");
        logger.info("Home Page title verified successfully");

        logger.info("Verifying Home Page URL");
        Assert.assertTrue(homePage.getHomePageUrl().contains("index.htm"), "Home Page URL mismatch");
        logger.info("Home Page URL verified successfully");

        // Login
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickOnLogin();
        logger.info("Clicked on login button");

        // Wait for Account Overview page and AJAX calls to complete
        //OverviewPage overviewPage = new OverviewPage(driver);
        waitUtils.untilPageLoadComplete(driver);
        if (driver.getPageSource().contains("The username and password could not be verified")) {
            Assert.fail("Login failed for user: " + username);
        }
        //waitUtils.untilJqueryIsDone(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("/overview.htm"), "Accounts Overview page URL does not match!");
        OverviewPage overviewPage = new OverviewPage(driver);
        logger.info("Accounts Overview URL verified");

        Assert.assertEquals(overviewPage.overviewPageTitle(), "ParaBank | Accounts Overview", "Accounts Overview page title does not match!");
        logger.info("Accounts Overview page title verified");

        Assert.assertTrue(overviewPage.isAccOverViewDisplayed(), "Account overview is not displayed!");
        logger.info("Navigated to Account overview page");

        // Navigate to Update Profile Page
        overviewPage.clickContactInfo();
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);
        logger.info("Navigated to Update Profile page");

        // Update Profile
        UpdateProfilePage update = new UpdateProfilePage(driver);
        update.updatePersonalDetails().enterPhone(phone);
        logger.info("Updated phone number ");

        String updatedPhone = update.updatePersonalDetails().getPhone().trim();
        logger.info("Phone number entered: " + updatedPhone);
        System.out.println("Phone after update: " + updatedPhone);

        update.clickOnUpdateProfile();
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);

        Assert.assertTrue(update.isProfileUpdatedDisplayed(), "Profile Updated message not displayed!");
        logger.info("Profile updated message verified");

        // Logout
        overviewPage.clickOnLogout();
        waitUtils.untilPageLoadComplete(driver);
        logger.info("Clicked on logout button and redirected to Home Page");

        // Login again to verify updated phone
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickOnLogin();
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);
        logger.info("Logged in again to verify updated profile");

        Assert.assertTrue(overviewPage.isAccOverViewDisplayed(), "Account overview is not displayed after re-login");
        logger.info("Navigated to Account overview page after re-login");

        // Navigate to Update Profile Page again
        overviewPage.clickContactInfo();
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);
        logger.info("Navigated to Update Profile page again");

        // Verify phone number
        update = new UpdateProfilePage(driver);
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);

        String phoneAfterLogin = update.updatePersonalDetails().getPhone().trim();
        System.out.println("Phone after re-login: " + phoneAfterLogin);
        logger.info("Phone number after re-login: " + phoneAfterLogin);
        Assert.assertEquals(phoneAfterLogin, updatedPhone, "Phone number mismatch after re-login");

        logger.info("Phone number verification successful");
        logger.info("-------------Test completed: testUpdateProfile ----------");
    }
}