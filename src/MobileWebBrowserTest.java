import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class MobileWebBrowserTest {

	private AppiumDriver driver;
	// private WebDriver driver;
	public static String baseUrl;

	@BeforeMethod
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "samsung-sgh_m919-93884d7c");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("appPackage", "com.android.chorme");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"),
				capabilities);
	}

	@Test
	public void testWebsite() throws InterruptedException {

		baseUrl = "http://www.britannica.com";
		driver.get(baseUrl + "/");

		Thread.sleep(3000);
		
		//homeTest();
		//joinTest();
		//loginTest();
		//logoutTest();
		//searchTest();
		galleriesTest();
		
		Thread.sleep(5000);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	public void homeTest() throws InterruptedException {
		for (int i = 0; i < 15; i++) {
			swipeByScreenRatio(0.1, 0.8, 0.1, 0.2);
		}
		
		WebElement element = driver.findElementById("md-footer");
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//footer[@id='md-footer']/div[2]/div[1]/div[1]/dl[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//footer[@id='md-footer']/div[2]/div[1]/div[1]/dl[1]/dt[1]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//footer[@id='md-footer']/div[2]/div[1]/div[1]/dl[2]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//footer[@id='md-footer']/div[2]/div[1]/div[1]/dl[2]/dt[1]").click();
		Thread.sleep(1000);
	}

	public void joinTest() throws InterruptedException {
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Join").click();
		Thread.sleep(1500);
		driver.findElementByXPath("//input[@id='signup-form-fname']").sendKeys(
				"Taesoo");
		driver.findElementByXPath("//input[@id='signup-form-lname']").sendKeys(
				"Kim");
		driver.findElementByXPath("//input[@id='signup-form-email']").sendKeys(
				"kcoolsoo@gmail.com");
		driver.findElementByXPath("//input[@id='signup-form-password']")
				.sendKeys("test1234");
		driver.findElementByXPath("//input[@id='signup-form-confirm-password']")
				.sendKeys("test1234");

		driver.navigate().back();

		Thread.sleep(1500);
	}

	public void loginTest() throws InterruptedException {
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Login").click();
		Thread.sleep(1500);
		driver.findElementByXPath("//input[@id='loginbox-username']").sendKeys(
				"kcoolsoo@gmail.com");
		driver.findElementByXPath("//input[@id='loginbox-password']").sendKeys(
				"test1234");
		driver.findElementByXPath("//input[@id='loginbox-login-btn']").click();
		Thread.sleep(1500);
	}

	public void logoutTest() throws InterruptedException {
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Logout").click();
		Thread.sleep(1500);
	}

	public void searchTest() throws InterruptedException {
		driver.findElementByXPath("//input[@id='header-query']").sendKeys(
				"encyclopaedia britannica");
		driver.findElementByXPath("//button[@class='search-submit']").click();
		Thread.sleep(2000);
		
		for (int i = 0; i < 5; i++) {
			swipeByScreenRatio(0.1, 0.8, 0.1, 0.2);
		}
		
		WebElement element = driver.findElementById("md-footer");
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		Thread.sleep(1000);
		
		driver.findElementByLinkText("Next Results").click();
		Thread.sleep(1000);
		
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Home").click();
		Thread.sleep(1500);
	}
	
	public void galleriesTest() throws InterruptedException {
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Galleries").click();
		Thread.sleep(1500);
		
		WebElement element = driver.findElementByClassName("ui-dropdown");
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		Action action =  actions.build();
		action.perform();
		Thread.sleep(1500);
		
	    swipeByScreenRatio(0.1, 0.9, 0.1, 0.4);
		Thread.sleep(1000);

		driver.findElementByClassName("ui-dropdown").click();;
		Thread.sleep(1000);
		driver.findElementByXPath("//li[@data-value='16']").click();
		Thread.sleep(1000);
		driver.findElementByLinkText("Machine Guns").click();
		
		Thread.sleep(1500);
		driver.findElementByClassName("md-menu-button").click();
		Thread.sleep(1500);
		driver.findElementByLinkText("Home").click();
		Thread.sleep(1500);
	}

	public void swipeByScreenRatio(double startXRatio, double startYRatio, 
			double endXRatio, double endYRatio) throws InterruptedException {
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		  if (contextName.contains("NATIVE")){
		    driver.context(contextName);
		  }
		}
		
		Dimension size = driver.manage().window().getSize();
		int startX = (int)(size.width * startXRatio);
		int endX = (int)(size.width * endXRatio);
		int startY = (int)(size.height * startYRatio);
		int endY = (int)(size.height * endYRatio);
		driver.swipe(startX, startY, endX, endY, 1000);
		
		for (String contextName : contextNames) {
		  if (contextName.contains("WEBVIEW")){
		    driver.context(contextName);
		  }
		}
	}
}
