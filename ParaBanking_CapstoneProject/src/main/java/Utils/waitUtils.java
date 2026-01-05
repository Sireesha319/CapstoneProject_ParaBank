package Utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Utility class that provides reusable explicit wait methods for handling 
 * synchronization issues, dynamic web elements, and AJAX operations in Selenium tests.
 * 
 * Designed to improve test stability and readability within the Page Object Model (POM).
 */
public class waitUtils {

    /**
     * Waits for an element to become clickable and returns it once it is interactable.
     * 
     * @param driver  the active WebDriver instance
     * @param element the WebElement to wait for
     * @return WebElement that is now clickable
     */
	
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until a specific element becomes visible on the page.
     * 
     * @param element the WebElement to wait for
     * @param timeoutInSeconds number of seconds to wait before timing out
     * @param driver  the active WebDriver instance
     */
    
    public static void waitForVisibility(WebElement element, int timeoutInSeconds, WebDriver driver) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for all active jQuery AJAX requests on the page to complete.
     * This ensures that background operations finish before proceeding.
     * 
     * @param driver  the active WebDriver instance
     */
    public static void untilJqueryIsDone(WebDriver driver) 
    {
        untilJqueryIsDone(driver, 20); // default timeout 20 seconds
    }

    /**
     * Waits for all jQuery AJAX requests to finish with a custom timeout.
     * 
     * @param driver  the active WebDriver instance
     * @param timeoutInSeconds number of seconds to wait before timing out
     */
    public static void untilJqueryIsDone(WebDriver driver, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(d -> {
            Boolean jqueryDone = (Boolean) js.executeScript(
                "return (typeof jQuery !== 'undefined') && (jQuery.active === 0);"
            );
            if (!jqueryDone) {
                System.out.println("⏳ jQuery call is still in progress...");
            }
            return jqueryDone;
        });
    }

    /**
     * Waits until the entire page has finished loading.
     * Uses JavaScript's document.readyState property.
     * 
     * @param driver  the active WebDriver instance
     */
    public static void untilPageLoadComplete(WebDriver driver) 
    {
        untilPageLoadComplete(driver, 20); // default timeout 20 seconds
    }
    
    

    /**
     * Waits for the page to load completely with a custom timeout.
     * Ensures that document.readyState equals 'complete'.
     * 
     * @param driver  the active WebDriver instance
     * @param timeoutInSeconds number of seconds to wait before timing out
     */
    
    
    public static void untilPageLoadComplete(WebDriver driver, long timeoutInSeconds) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(d -> {
            Boolean isPageLoaded = js.executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) 
            {
                System.out.println("Document is still loading...");
            }
            return isPageLoaded;
        });
    }

}
