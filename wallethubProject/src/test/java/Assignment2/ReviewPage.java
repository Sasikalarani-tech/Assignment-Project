package Assignment2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utility.Log;

public class ReviewPage {

//Page Object Model with Page Factory	
	WebDriver driver;
	Log log=new Log(); 
	String url="https://wallethub.com/profile/13732055i";
	
	@FindBy(xpath="//span[text()='Login']")
	WebElement loginButton;
	
	@FindBy(id="email")
	WebElement emailAddress;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//button[contains(@data-hm-tap,'doLogin')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//button[text()='Write a Review']")
	WebElement reviewSection;
	
	@FindBy(xpath="//review-star[@class='rvs-svg']")
	WebElement ratingStar;
	
	@FindBy(xpath="//div[@class='dropdown second']")
	WebElement policyDropDown;

	@FindBy(xpath="//textarea[@placeholder='Write your review...']")
	WebElement writeaReview;
	
	@FindBy(xpath="//div[text()='Submit']")
	WebElement submitReview;
	
	@FindBy(xpath="//span[@aria-label='Most Recent']")
	WebElement mostRecent;
	
	@FindBy(xpath="//div/span[text()='Most Recent']")
	WebElement mostRecentList;
	
	@FindBy(xpath="//span[text()='Your Review']")
	WebElement postedReview;
	
//Constructor	
	public ReviewPage(WebDriver driver)
	{
	   log.info("*****************Entering POM for Wallehub-Review Page************************");
       this.driver=driver;
       driver.get(url);
       PageFactory.initElements(driver, this);
	}
//Login section	
	public  void clickLogin()
	{
		loginButton.click();
	}

//Email address entered	
	public void enterEmail(String email)
	{
		emailAddress.sendKeys(email);
	}

//Password entered	
	public void enterPassword(String pwd)
	{
		password.sendKeys(pwd);
	}

//After entering Emailaddress and Password click on Submit button	
	public void clickSubmit()
	{
		submitBtn.click();
	}

//Mouse hover on Star rating and click on star	
	public void ratingHover(int rating) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(ratingStar));
		Actions act=new Actions(driver);
		List<WebElement> ratingSelected = driver.findElements(By.xpath("(//review-star[@class='rvs-svg']//*[name()='svg'])"));
		for (int i = 0; i<rating; i++)
		{
		    Thread.sleep(2000);
		    act.moveToElement(ratingSelected.get(i)).build().perform();
		    if(i == (rating-1))
		    {
		    	wait.until(ExpectedConditions.visibilityOf(ratingSelected.get(i)));
		        act.moveToElement(ratingSelected.get(i)).click().build().perform();
		    }
		}
		Thread.sleep(5000);
	}
	
//Policy is selected from dropdown in Review page	
	public void selectPolicy(String policy)
	{
		policyDropDown.click();
		List<WebElement> policyOptions=driver.findElements(By.xpath("//ul[@role='listbox' and @tabindex=0]/li"));
		for(WebElement option:policyOptions)
		{
			if (option.getText().contains(policy)) 
			{
				option.click();
			 break;
			}
		}
	}

//Review entered	
	public void sendWriteReview(String review)
	{
		writeaReview.clear();
		writeaReview.sendKeys(review);
		
	}
	
//After selecting Policy and Review entered,submit button clicked	
	public void submitReviewBtn()
	{
		submitReview.click();
	}
	
//Verify Review in Profile Page	
	public String verifyEnteredReview()
	{
		driver.get(url);
		mostRecent.click();
		mostRecentList.click();
		return postedReview.getText();
		
	}

}
