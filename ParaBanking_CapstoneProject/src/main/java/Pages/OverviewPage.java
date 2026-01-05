package Pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class OverviewPage extends BasePage 
{
	@FindBy(xpath="//h1[contains(text(),'Accounts Overview')]")
	private WebElement accOverview ;
	
    @FindBy(css="a[href='logout.htm']")
    private WebElement logoutBtn  ;
    
    @FindBy(css="a[href='transfer.htm']")
    private WebElement transferlink ;
    
	@FindBy(css="a[href='openaccount.htm']")
	private WebElement openAccLink;
	
	@FindBy(css="a[href='overview.htm']")
	private WebElement overviewLink ;
	
	@FindBy(xpath = "//table[@id='accountTable']/tbody/tr[1]/td[1]/a")
	private WebElement firstAccountNumberLink;
	
	@FindBy(xpath="//table[@id='accountTable']/tbody/tr[2]/td[1]/a")
	private WebElement secondAccountNumberLink;
	
	
	@FindBy(css="a[href='requestloan.htm']")
	private WebElement loanRequestLink ;
	
	@FindBy(css="a[href='updateprofile.htm']")
	private WebElement contactInfoLink;

	
	public OverviewPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String overviewPageTitle()
	{
		return getTitle();
	}
	
	public String overviewPageUrl()
	{
		return getCurrentUrl();
	}
	
	
	 public void clickOnLogout() 
	 {
	       click( logoutBtn);
	 }
	 
	 public void clickTransfer()
	 {
		 click(transferlink);
	 }
	
	 public void clickOpenAccount()
	 {
		 click(openAccLink);
	 }
	 
		
	public void clickContactInfo()
	{
		click(contactInfoLink);
	}
	 
	 public void clickOnOverview()
	 {
		 click(overviewLink);
	 }
	 
	 public void clickLoanRequest()
	 {
		 click(loanRequestLink);
	 }
	 
	 public String getFirstAccountNumber() 
	 {
	        return getText( firstAccountNumberLink);
	 }
	  
	 public String getsecondAccountNumber() 
	 {
	        return getText(secondAccountNumberLink);
	 }
	 
	 
	 public boolean isAccOverViewDisplayed()
	 {
		return isDisplayed(accOverview) ;
	 }
}