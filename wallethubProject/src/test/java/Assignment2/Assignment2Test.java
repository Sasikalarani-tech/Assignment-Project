package Assignment2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utility.Log;

public class Assignment2Test {
	Log log=new Log();
	public WebDriver driver;
	ReviewPage rvwPage;
	
	public Assignment2Test()
	{
		super();
	}
//Seting up WebDriver	
	@BeforeTest
	@Parameters({"browser"})
	public void driverSetUp(String browser)
	{
		log.info("*****************************Assignment2-WalletHub Profile*****************************");
		log.info("*********************Driver Setup***********************");
		if(browser.equalsIgnoreCase("chrome"))
		{
			log.info("*********************Chrome Driver Setup***********************");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sasir\\chromedriver_win32\\chromedriver.exe");
			DesiredCapabilities dCapability = new DesiredCapabilities();
			dCapability.setJavascriptEnabled(true);
		    ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments("--disable-notifications");
		    driver=new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			rvwPage=new ReviewPage(driver);
			}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			log.info("*********************Firefox Driver Setup***********************");
			//System.setProperty("webdriver.gecko.driver",Path_of_Firefox_Driver"); // Setting system properties of FirefoxDriver
			//WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
			//driver.manage().window().maximize();
			//driver.manage().deleteAllCookies();
		}
		

	}

//Invoking Page Object Model for WalletHubprofile Url
	@Test (priority = 1)
	public void enterReview() throws IOException, InterruptedException
	{
		log.info("Driver Setup done.Execution started");
		String email="<Enter email address>";
		String password="<Enter Password>";
		String review="WalletHub is a worthwhile service for receiving a free credit report and daily credit score from TransUnion. It offers educational information about personal credit (but not as much editorial content as NerdWallet), and the targeting advertising is useful";
		rvwPage.clickLogin();
		rvwPage.enterEmail(email);
		rvwPage.enterPassword(password);
		rvwPage.clickSubmit();
		int rating=4;
		rvwPage.ratingHover(rating);
		rvwPage.selectPolicy("Health Insurance");
		rvwPage.sendWriteReview(review);
		rvwPage.submitReviewBtn();
		//Assertions to verify the Review added
		Assert.assertEquals(driver.getCurrentUrl(),"https://wallethub.com/confirm-review", "Incorrect page is displayed");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='rvc-header']/h4")).getText(), "Your review has been posted", "Review not submitted");
		Assert.assertEquals(rvwPage.verifyEnteredReview(),"Your Review", "Review not submitted");
		
		
	}
	
//
	@AfterTest
	public void closeDriver()
	{
		log.info("Executed all methods");
		log.info("Driver is closed successfully");
		driver.quit();
	}

}
