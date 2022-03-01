package Assignment1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utility.Log;

public class Assignment1Test {

	Log log=new Log();
	public WebDriver driver;
	Facebook_Home fbHome;
	
//Setting up the Web Driver
	@BeforeTest
	@Parameters({"browser"})
	public void driverSetUp(String browser)
	{
		log.info("***************************Assignment1-Facebook Page-Posting Status***************************");
		log.info("*************Driver Setup*****************");
		if(browser.equalsIgnoreCase("chrome"))
		{
			log.info("*************Chrome Driver Setup****************");	
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sasir\\chromedriver_win32\\chromedriver.exe");
			DesiredCapabilities dCapability = new DesiredCapabilities();
			dCapability.setJavascriptEnabled(true);
		    ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments("--disable-notifications");
		    driver=new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			fbHome=new Facebook_Home(driver);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			log.info("*************Firefox Driver****************");	
			//System.setProperty("webdriver.gecko.driver",Path_of_Firefox_Driver"); // Setting system properties of FirefoxDriver
			//WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
			//driver.manage().window().maximize();
			//driver.manage().deleteAllCookies();
		}

	}
	
//Invoking Page Object Model methods with Valid UserName and Password
	@Test (priority = 1)
	public void validUnamePwd()
	{
		log.info("Driver Setup done.Execution started");
		String userName="<Enter UserName>";
		String password="<Enter Password>";
		fbHome.setUserName(userName);
		fbHome.setPassword(password);
		fbHome.clickLogIn();
		fbHome.fbHomeButton();
		fbHome.createPost();
		fbHome.statusText("Hello World");
		fbHome.clickPostButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		fbHome.fbLogOut();
	}
	
//Invoking Page Object Model methods with Invalid Username and Password	
	@Test (priority = 2)
	public void invalidUserPwd()
	{
		String userName="abc@gmail.com";
		String password="abc123";
		fbHome.setUserName(userName);
		fbHome.setPassword(password);
		fbHome.clickLogIn();
		String invalidUserError=fbHome.invalidUserCase();
		if(invalidUserError.contains("You can't log in at the moment"))
		{
			System.out.println("Invalid User");
		}
		log.info("Invalid User not abel to login to FaceBook account");
	}

//Closing the WebDriver
	@AfterTest
	public void closeDriver()
	{
		log.info("Executed all methods");
		log.info("Driver is closed successfully");
		driver.quit();
	}
	
}
