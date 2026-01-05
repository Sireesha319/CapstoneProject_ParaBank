package Drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager 
{
	public static ThreadLocal<WebDriver>driver = new ThreadLocal<>();


	public static void setWebDriver(WebDriver driverRef)
	{
		driver.set(driverRef);
	}
	
	public static WebDriver getWebDriver()
	{
		return driver.get();
	}
	
	public static void removeDriver()
	{
		driver.remove();
	}

}