package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.waitUtils;

public class UserDetails  extends BasePage
{

	
	@FindBy(id = "customer.firstName")
    private WebElement firstNameInput;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameInput;

    @FindBy(id = "customer.address.street")
    private WebElement addressInput;

    @FindBy(id = "customer.address.city")
    private WebElement cityInput;

    @FindBy(id = "customer.address.state")
    private WebElement stateInput;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneInput;
    
    
	public UserDetails(WebDriver driver)
	{
		super(driver);
	}
	
	 //Personal Details
    public void enterFirstName(String firstName) 
    {
        type(firstName, firstNameInput);
    }

    public void enterLastName(String lastName) 
    {
        type(lastName, lastNameInput);
    }

    public void enterAddress(String address) 
    {
        type(address, addressInput);
    }

    public void enterCity(String city) {
        type(city, cityInput);
        
    }

    public void enterState(String state) 
    {
        type(state, stateInput);
    }

    public void enterZipCode(String zip) 
    {
        type(zip, zipCodeInput);
    }

    public void enterPhone(String phone) 
    {
        type(phone, phoneInput);
    }
    
    public String getPhone()
    {
    	waitUtils.waitForVisibility(phoneInput, 20, driver);
    	return phoneInput.getAttribute("value");
    }
    
    public String getPhone1()
    {
    	waitUtils.waitForVisibility(phoneInput, 20, driver);
    	return getText(phoneInput);
    }
}