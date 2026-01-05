package Drivers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.ConfigReader;



public class DriverFactory 
{
	public static WebDriver createDriver(String browser)
	{
		WebDriver driver = null;
		switch(browser.toLowerCase())
		{
			case "chrome": driver = new ChromeDriver();
						   break;
			case "firefox": driver = new FirefoxDriver();
							break;
			case "edge" : driver = new EdgeDriver();
						  break;
			default     : throw new IllegalArgumentException("Invalid browser name: "+browser+ ". Supported browsers are: chrome, firefox, edge");
					   
		}
		
		// Standard setup applied for all drivers
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait"))));; // Apply implicit wait to handle element loading delays
		
		return driver;
	}
}