package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage 
{
	WebDriver driver;
	JavascriptExecutor js;
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void scrollIntoView(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	 public void jsClick(WebElement element)
	 {
		 js.executeScript("arguments[0].click();", element);
	 }
	
	
	
	 public void click(WebElement element) 
	{
		      element.click();
	 }

	
	public void type(String text, WebElement element)
	{
		element.clear();
		element.sendKeys(text);
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public String getText(WebElement element)
	{
		return element.getText();
	}
	
	 public String getCurrentUrl()
	 {
	        return driver.getCurrentUrl();
	 }
	
	 public void selectDropDown(WebElement element, String text)
	 {
		 Select select = new Select(element);
		 select.selectByVisibleText(text);
	 }
	 
	 public Select select(WebElement element)
	 {
		 Select select = new Select(element);
		 return select;
		 
	 }
	 
	 public void selectByIndex(WebElement element,int index)
	 {
		 select(element).selectByIndex(index);
	 }
	 
	 public List<WebElement> getAllDropdownOptions(WebElement dropdownElement) 
	 {
		    return select(dropdownElement).getOptions();
	 }
	 
	 
	 
	public boolean isDisplayed(WebElement element) 
	 {
	     try 
	     {
	         return element.isDisplayed();
	     } catch (Exception e) {
	         return false; // Element not found or not visible
	     }
	 }
}