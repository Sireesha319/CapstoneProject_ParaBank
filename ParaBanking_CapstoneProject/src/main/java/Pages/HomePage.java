package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class HomePage extends BasePage {

	@FindBy(css = "img[src='images/logo.gif']")
	private WebElement paraBankLogo;
	
	@FindBy(css="a[href='register.htm']")
	private WebElement registerlink ;
	
	@FindBy(css="input[name='username']")
	private WebElement usernameEle ;
	
	@FindBy(css="input[name='password']")
	private WebElement passwordEle ;
	
	@FindBy(css="input[value='Log In']")
	private WebElement loginBtn;
	

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean isHomePageDisplayed() 
	{
		
		return isDisplayed(paraBankLogo);
	}
	
	public String getHomePageTiltle()
	{
		return getTitle();
	}
	
	public String getHomePageUrl()
	{
		return getCurrentUrl();
	}
	
	public void enterUsername(String username)
	{
		type(username,usernameEle);
	}
	
	public void enterPassword(String password)
	{
		type(password,passwordEle);
	}
	
	public void clickOnLogin()
	{
		click(loginBtn);
	}

	
	public void clickRegister()
	{
		
		scrollIntoView(registerlink);
		jsClick(registerlink);

	}
	
	

}