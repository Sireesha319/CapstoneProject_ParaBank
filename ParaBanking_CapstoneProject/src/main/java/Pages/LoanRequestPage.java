package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.waitUtils;

public class LoanRequestPage extends BasePage 
{
	@FindBy(xpath="//h1[text()='Loan Request Processed']")
	private WebElement loanReqProcessedHeading;
	
	@FindBy(xpath="//p[text()='Congratulations, your loan has been approved.']")
	private WebElement successMsg;
	
	
	@FindBy(xpath="//h1[contains(text(),'Apply for a Loan')]")
	private WebElement loanReqHeading;
	
	@FindBy(id="amount")
	private WebElement amountInput;
	
	
	@FindBy(id="downPayment")
	private WebElement downPaymentInput;
	
	@FindBy(id="fromAccountId")
	private WebElement fromAcc;
	
//	@FindBy(xpath="//input[@value='Apply Now']")
//	private WebElement applyNowBtn ;
	
	@FindBy(xpath = "//input[@value='Apply Now' and @class='button']")
	private WebElement applyNowBtn;
	
	public LoanRequestPage (WebDriver driver)
	{
		super(driver);
	}
	
	public String loanRequestPageTitle()
	{
		return getTitle();
	}
	
	public String loanRequestPageUrl()
	{
		return getCurrentUrl();
	}
	
    public void enterLoanAmount(String amount) 
    {
        type(amount, amountInput);   
    }
    
    public void enterdownPayment(String amount) 
    {
        type(amount, downPaymentInput);   
    }
    
    
    public void clickOnApplyNow() 
    {
  	
        click(applyNowBtn); 
	
    	// Small delay for JS event binding (instead of large Thread.sleep)
        try 
       {
            Thread.sleep(400); // short and safe
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    public boolean isLoanRequestHeadingDisplayed() 
    {
    	waitUtils.waitForVisibility(loanReqHeading,20,driver);
        return isDisplayed(loanReqHeading);
    }
    
    public boolean isLoanReqProcessedHeadingDisplayed() 
    {
        return isDisplayed(loanReqProcessedHeading);
    }
    
    public boolean isLoanRequestSuccessMsg() 
    {
        return isDisplayed(successMsg);
    }


	
	public void selecOption(int indexToselect)
	{
		List<WebElement>options = getAllDropdownOptions(fromAcc);
		for (int i = 0; i < options.size(); i++)
		{
            String optionText = options.get(i).getText();
            if(String.valueOf(indexToselect).equals(optionText))
            	selectByIndex(fromAcc, indexToselect);
            
        }
		
		
	}
	
	
	
}