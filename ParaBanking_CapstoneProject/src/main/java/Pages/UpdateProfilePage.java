package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.waitUtils;

public class UpdateProfilePage extends BasePage 
{
	
	private UserDetails userDetails;
	
	@FindBy(xpath="//h1[text()='Update Profile']")
	private WebElement updateProfileHeading;
	
	
	@FindBy(css="input[value='Update Profile']")
	private WebElement updateProfileBtn ;
	
	@FindBy(xpath="//h1[text()='Profile Updated']")
	private WebElement profileUpdatedMsg;
	
	public UpdateProfilePage(WebDriver driver)
	{
		super(driver);
		userDetails = new UserDetails(driver);
	}
	
	public boolean  isProfileUpdatedDisplayed()
	{
		waitUtils.waitForVisibility(profileUpdatedMsg, 10, driver);
		return isDisplayed(profileUpdatedMsg);
	}
	
	
	public String updateProfilePageTitle()
	{
		return getTitle();
	}
	
	public String updateProfilePageUrl()
	{
		return getCurrentUrl();
	}
	
	
	public boolean isUpdateProfile()
	{
		return isDisplayed(updateProfileHeading);
	}
	
    public UserDetails updatePersonalDetails() 
    {
        return userDetails;
    }


	
	public void clickOnUpdateProfile()
	{
		waitUtils.waitForVisibility(updateProfileBtn, 10, driver);
		waitUtils.waitForElementToBeClickable(driver, updateProfileBtn);
		click(updateProfileBtn);
	}
	
	
}