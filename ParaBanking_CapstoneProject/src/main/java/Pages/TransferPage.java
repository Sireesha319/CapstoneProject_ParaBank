package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.waitUtils;

public class TransferPage extends BasePage 
{
	
	@FindBy(id="amount")
	private WebElement amountInput ;
	
	@FindBy(id="fromAccountId")
	private WebElement fromAcc ;
	
	@FindBy(id="toAccountId")
	private WebElement toAcc ;
	
	@FindBy(css="input[value='Transfer']")
	private WebElement transferBtn ;
	
    @FindBy(xpath = "//h1[@class='title' and text()='Transfer Complete!']")
    private WebElement transferCompleteTitle;

    @FindBy(id = "amountResult")
    private WebElement amountResult;

    @FindBy(id = "fromAccountIdResult")
    private WebElement fromAccountResult;

    @FindBy(id = "toAccountIdResult")
    private WebElement toAccountResult;
	
	
	public String transferPageTitle()
	{
		return getTitle();
	}
	
	public String transferPageUrl()
	{
		return getCurrentUrl();
	}
	
	public TransferPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void enterAmount(String amount)
	{
		type(amount,amountInput);
	}
	
	public void selectFromAcc(String fromAccount)
	{
		selectDropDown(fromAcc, fromAccount);
	}
	
	public void selectToAcc(String toAccount )
	{
		selectDropDown(toAcc, toAccount);
	}
	
	public boolean isTransferCompleteDisplayed() 
	{
        waitUtils.waitForVisibility(transferCompleteTitle,10,driver);

	        return isDisplayed(transferCompleteTitle);
	}

	  
	public String getTransferredAmount() 
	{
	        return getText(amountResult);
	}

	
   public String getFromAccountNumber() 
   {
	        return getText(fromAccountResult);
   }

    public String getToAccountNumber() 
    {
	     return getText(toAccountResult);
	   
    }
	
	public void clickOnTransfer()
	{
		waitUtils.waitForElementToBeClickable(driver, transferBtn);
		jsClick(transferBtn);
		waitUtils.waitForVisibility(transferBtn,10,driver);
	}
	

}