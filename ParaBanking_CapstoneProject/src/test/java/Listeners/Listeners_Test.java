package Listeners;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Drivers.DriverManager;
import Tests.BaseTest;
import Utils.ExtentManager;

public class Listeners_Test extends BaseTest implements ITestListener{
	private static final ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	

	@Override
	public void onTestStart(ITestResult result)
	{
		test.set(extent.createTest(result.getMethod().getMethodName()));
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	    test.get().log(Status.FAIL, result.getThrowable());
	    WebDriver driver = DriverManager.getWebDriver();
	    
	    if (driver != null) {
	        String base64Screenshot = takeScreenshotAsBase64(driver);
	        test.get().fail("Screenshot of failure:",
	        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	    }
	    
//	    if(driver!=null)
//	    {
//	    	TakesScreenshot ts = (TakesScreenshot) driver;
//	    	File src = ts.getScreenshotAs(OutputType.FILE);
//	    	String path = "test-output/screenshots/" + UUID.randomUUID() + ".png";
//	    	File dest = new File(path);
//	    	dest.getParentFile().mkdirs();
//	    	try {
//				Files.copy(src.toPath(), dest.toPath());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	test.get().addScreenCaptureFromPath(dest.getAbsolutePath());
//	    }
	    
	}
	
	 private String takeScreenshotAsBase64(WebDriver driver) {
	        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	 }
	 
	
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	}
 

}