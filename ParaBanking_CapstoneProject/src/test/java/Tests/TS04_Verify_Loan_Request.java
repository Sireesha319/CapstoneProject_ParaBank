package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders;
import Drivers.DriverManager;
import Pages.HomePage;
import Pages.LoanRequestPage;
import Pages.OverviewPage;
import Utils.waitUtils;



public class TS04_Verify_Loan_Request extends BaseTest {

    @Test(dataProvider="loanRequestData",dataProviderClass=DataProviders.class)
    public void testVerifyLoanRequest(String username, String password, String loanAmount, String downPayment, int accountIndex) {
    	
    	logger.info("------------------------- Starting test: testVerifyLoanRequest -------------------------------");
    	
        WebDriver driver = DriverManager.getWebDriver();
        HomePage homePage = new HomePage(driver);

        logger.info("Checking if Home Page is displayed");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page should be displayed, but it is not");
        logger.info("Home Page displayed successfully");

        logger.info("Verifying Home Page title");
        Assert.assertEquals(homePage.getHomePageTiltle(), "ParaBank | Welcome | Online Banking", "Title mismatch");
        logger.info("Home Page title verified successfully");

        logger.info("Verifying Home Page URL");
        Assert.assertTrue(homePage.getHomePageUrl().contains("index.htm"), "URL mismatch");
        logger.info("Home Page URL verified successfully");

        // Login
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.clickOnLogin();
        logger.info("Clicked on login button");

        // Wait until AJAX or page load completes after login
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);

        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.overviewPageUrl().contains("/overview.htm"), "Accounts Overview page URL does not match!");
        logger.info("Accounts Overview URL verified");
        Assert.assertEquals(overviewPage.overviewPageTitle(), "ParaBank | Accounts Overview", "Accounts Overview title does not match!");
        logger.info("Accounts Overview page title verified");
        Assert.assertTrue(overviewPage.isAccOverViewDisplayed(), "Account overview page is not displayed!");
        logger.info("Navigated to Account overview page");

        overviewPage.clickLoanRequest();
        logger.info("Clicked on Loan Request link");

        // Wait for loan page to load (handles AJAX transitions)
        waitUtils.untilPageLoadComplete(driver);
        waitUtils.untilJqueryIsDone(driver);

        LoanRequestPage loan = new LoanRequestPage(driver);
        Assert.assertTrue(loan.loanRequestPageUrl().contains("/requestloan.htm"), "Loan Request page URL does not match!");
        logger.info("Loan Request URL verified");
        Assert.assertEquals(loan.loanRequestPageTitle(), "ParaBank | Loan Request", "Loan Request title does not match!");
        logger.info("Loan Request page title verified");

        Assert.assertTrue(loan.isLoanRequestHeadingDisplayed(), "Loan Request page is not displayed!");
        logger.info("Loan Request page displayed successfully");

        // Fill in form details
        loan.enterLoanAmount(loanAmount);
        logger.info("Entered loan amount");
        loan.enterdownPayment(downPayment);
        logger.info("Entered down payment");
        loan.selecOption(accountIndex);
        logger.info("Selected account for loan");
        loan.clickOnApplyNow();
        logger.info("Clicked on Apply Now button");

        // Wait until AJAX completes after submission
        waitUtils.untilJqueryIsDone(driver);

        // Validate loan processed
        Assert.assertTrue(loan.isLoanReqProcessedHeadingDisplayed(), "Expected 'Loan Request Processed' heading is not displayed.");
        logger.info("'Loan Request Processed' heading displayed.");
        Assert.assertTrue(loan.isLoanRequestSuccessMsg(), "Expected loan success message is not displayed.");
        logger.info("Loan success message displayed successfully.");
        
        logger.info("-------------Test completed: testVerifyLoanRequest ----------");
    }
}