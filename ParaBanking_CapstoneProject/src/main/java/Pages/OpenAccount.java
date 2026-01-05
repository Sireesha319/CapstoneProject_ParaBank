package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.waitUtils;

public class OpenAccount extends BasePage{
	
	@FindBy(id="type")
	private WebElement accType;
	
	@FindBy(xpath="//input[@type='button']")
//	@FindBy(xpath="//input[@value='Open New Account']")
	private WebElement openNewAccBtn;
	
	@FindBy(xpath="//h1[text()='Account Opened!']")
	private WebElement accOpenedMsg ;
	
	@FindBy(xpath="//div[@id='openAccountResult']//p[contains(text(),'Congratulations, your account is now open.')]")
	private WebElement congratsOpenAccMsg ;
	
	@FindBy(id="newAccountId")
	private WebElement newAccNo ;
	
	
	
	public OpenAccount(WebDriver driver)
	{
		super(driver);
	}
	
	public void selectAccType(String accountType )
	{
		selectDropDown(accType, accountType);
	}
	


	public void clickOnNewAcc() 
	{
	    waitUtils.waitForElementToBeClickable(driver, openNewAccBtn);
	    jsClick(openNewAccBtn);
	    waitUtils.waitForVisibility( openNewAccBtn,10,driver);  // wait until message appears
	}

	
	public String getNewAccNumber()
	{
	    return getText(newAccNo);
	}
	
	public boolean isAccOpenedMsgDisplayed() 
	{
		waitUtils.waitForVisibility(accOpenedMsg,10,driver);
	    return isDisplayed(accOpenedMsg);
	}

	public boolean isCongratsMsgDisplayed() 
	{
	    return isDisplayed(congratsOpenAccMsg);
	}

	public boolean isNewAccNoDisplayed() 
	{
	    return newAccNo.isDisplayed();
	}



}