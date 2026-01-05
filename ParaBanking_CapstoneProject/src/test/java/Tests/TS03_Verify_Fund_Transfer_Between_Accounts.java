package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders;
import Drivers.DriverManager;
import Pages.HomePage;
import Pages.OpenAccount;
import Pages.OverviewPage;
import Pages.TransferPage;
import Utils.waitUtils;



public class TS03_Verify_Fund_Transfer_Between_Accounts extends BaseTest {

	@Test(dataProvider = "transferData", dataProviderClass=DataProviders.class)
    public void testTransferfunds(String username, String password, String accountType, String amount) {

		logger.info("------------------- Starting test: testTransferfunds --------------------------");
		
		
        // Get WebDriver instance
        WebDriver driver = DriverManager.getWebDriver();

        // Initialize Home Page object
        HomePage homePage = new HomePage(driver);

        //Verify that the Home Page is displayed
        logger.info("Checking if Home Page is displayed");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page should be displayed, but it is not");
        logger.info("Home Page displayed successfully");

        //Verify Home Page title
        logger.info("Verifying Home Page title");
        Assert.assertEquals(homePage.getHomePageTiltle(), "ParaBank | Welcome | Online Banking", "Title mismatch");
        logger.info("Home Page title verified successfully");

        // Verify Home Page URL
        logger.info("Verifying Home Page URL");
        Assert.assertTrue(homePage.getHomePageUrl().contains("index.htm"), "URL mismatch");
        logger.info("Home Page URL verified successfully");

        // Perform login
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickOnLogin();
        logger.info("Clicked on login button");

        // Wait for the page to finish loading and AJAX calls to complete
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);

        // Verify navigation to Overview Page
        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.overviewPageUrl().contains("/overview.htm"), "Accounts Overview page URL does not match!");
        logger.info("Accounts Overview URL verified");

        Assert.assertEquals(overviewPage.overviewPageTitle(), "ParaBank | Accounts Overview", "Accounts Overview title does not match!");
        logger.info("Accounts Overview page title verified");

        Assert.assertTrue(overviewPage.isAccOverViewDisplayed(), "Account overview not displayed!");
        logger.info("Navigated to Account Overview page successfully");

        // Navigate to 'Open New Account' page
        overviewPage.clickOpenAccount();
        logger.info("Navigated to Open Account page");

        //Open a new account
        OpenAccount openAcc = new OpenAccount(driver);
        openAcc.selectAccType(accountType);
        logger.info("Selected account type: "+accountType);
        
        // Wait for page load and AJAX completion
        waitUtils.untilJqueryIsDone(driver);
        waitUtils.untilPageLoadComplete(driver);

        openAcc.clickOnNewAcc();
        
        // Wait for page load and AJAX completion
        waitUtils.untilJqueryIsDone(driver);
        waitUtils.untilPageLoadComplete(driver);
        
        
        Assert.assertTrue(openAcc.isAccOpenedMsgDisplayed(), "Account open confirmation message not displayed!");
        logger.info("Clicked on 'Open New Account' button");

        // Verify account creation messages
        Assert.assertTrue(openAcc.isAccOpenedMsgDisplayed(), "Account opened message not displayed");
        logger.info("'Account Opened!' message verified");

        Assert.assertTrue(openAcc.isCongratsMsgDisplayed(), "Congratulations message not displayed");
        logger.info("'Congratulations, your account is now open' message verified");

        Assert.assertTrue(openAcc.isNewAccNoDisplayed(), "New account number not displayed");
        logger.info("New Account Number displayed: " + openAcc.getNewAccNumber());

        // Navigate back to Account Overview page
        overviewPage.clickOnOverview();
        logger.info("Navigated back to Account Overview page");

        //  Retrieve account numbers for fund transfer
        String from = overviewPage.getFirstAccountNumber();
        String to = overviewPage.getsecondAccountNumber();
        System.out.println("From Account: " + from);
        System.out.println("To Account: " + to);

        //  Navigate to Transfer Funds page
        overviewPage.clickTransfer();
        logger.info("Navigated to Transfer Funds page");

        //  Verify Transfer Page details
        TransferPage transfer = new TransferPage(driver);
        Assert.assertTrue(transfer.transferPageUrl().contains("/transfer.htm"), "Transfer page URL does not match!");
        logger.info("Transfer page URL verified");

        Assert.assertEquals(transfer.transferPageTitle(), "ParaBank | Transfer Funds", "Transfer page title does not match!");
        logger.info("Transfer page title verified");

        //  Perform fund transfer
        transfer.enterAmount(amount);
        logger.info("Entered transfer amount: "+amount);

        transfer.selectFromAcc(from);
        logger.info("Selected From Account: " + from);

        transfer.selectToAcc(to);
        logger.info("Selected To Account: " + to);

        transfer.clickOnTransfer();
        logger.info("Clicked on 'Transfer' button");

        // Wait for page load and AJAX completion
        waitUtils.untilJqueryIsDone(driver);
        waitUtils.untilPageLoadComplete(driver);

        //  Verify transfer success message
        Assert.assertTrue(transfer.isTransferCompleteDisplayed(), "Transfer Complete message not displayed");
        logger.info("Transfer Complete page displayed successfully");

        //  Final confirmation log
        logger.info("Fund Transfer verified successfully between accounts: From [" + from + "] to [" + to + "]");
   
        logger.info("-------------Test completed: testTransferfunds ----------");
	
	}
}