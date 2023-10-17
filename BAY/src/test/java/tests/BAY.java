package tests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BAY {
	static AppiumDriver driver;
	ExtentSparkReporter spark;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUP() {

		spark = new ExtentSparkReporter("target/BAY.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("MyReport");
		spark.config().setReportName("BAY Report");


		try {
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 8 Pro");
			cap.setCapability(MobileCapabilityType.UDID, "prinibpzrgtgwcz9");
			cap.setCapability("appPackage", "com.best.alyousifi");
			cap.setCapability("appActivity", ".ui.SplashScreen");


			URL url = new URL("http://127.0.0.1:4723/wd/hub");

			driver = new AppiumDriver(url, cap);


		} catch (Exception e) {
			System.out.println("Cause is : "+e.getCause());
			System.out.println("Message is : "+e.getMessage());
			e.printStackTrace();
		} 
	}

	@Test
	public void OpenApp() throws InterruptedException {
		ExtentTest test1 = extent.createTest("OpenApp Test").assignAuthor("Darshan").assignDevice("Android").assignCategory("Smoke");
		test1.log(Status.INFO, "BAY App opened");

		System.out.println("I am inside BAY App");
		WebElement Skip = driver.findElement(By.id("com.best.alyousifi:id/skipBtn"));
		if (Skip.isDisplayed()){
			System.out.println("Skip button is displayed");
		}else {
			System.out.println("Skip button is not displayed");
		}
		if (Skip.isEnabled()){
			System.out.println("Skip button is enabled");
		}else {
			System.out.println("Skip button is not enabled");
		}Skip.click();
		
		test1.log(Status.FAIL, "Splash screen skipped sucessfully");
	}
	
	@AfterSuite
	public void teardown() {
		extent.flush();
		//		driver.quit();
	}
}
