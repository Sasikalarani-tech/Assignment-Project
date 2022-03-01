package Assignment1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Facebook_Home extends Assignment1Test{
	WebDriver driver;
	
//Page Object Model with Page Factory
	@FindBy(id="email")
	WebElement userName;

	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(name="login")
	WebElement loginbtn;
	
	@FindBy(xpath="//div[@class='fsl fwb fcb']")
	WebElement invalidUserPwd;
	
	@FindBy(xpath="//div[@class='bp9cbjyn j83agx80 byvelhso l9j0dhe7']")
	WebElement fbHomeBtn;
	
	@FindBy(xpath="//div[@class='oajrlxb2 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x i1ao9s8h esuyzwwr f1sip0of abiwlrkh p8dawk7l lzcic4wl bp9cbjyn b3i9ofy5 orhb3f3m czkt41v7 fmqxjp7s emzo65vh j83agx80 btwxx1t3 buofh1pr jifvfom9 l9j0dhe7 idiwt2bm kbf60n1y cxgpxx05 d1544ag0 sj5x9vvc tw6a2znq']")
	WebElement creatPost;
	
	@FindBy(xpath="//div[@class='_1mf _1mj']")
	WebElement statusArea;
	
	@FindBy(xpath="//div[@aria-label='Post']")
	WebElement postBtn;
	
	@FindBy(xpath="//div[@aria-label='Account']")
	WebElement accountMenu;
	
	@FindBy(xpath="//span[text()='Log Out']")
	WebElement logOut;
	
//Constructor
	public Facebook_Home(WebDriver driver)
		{
	       this.driver=driver;
	       driver.get("https://www.facebook.com/");
	       PageFactory.initElements(driver, this);
	   }

//UserName entered	
	public void setUserName(String user_name)
	{
		userName.sendKeys(user_name);
	}
//Password entered	
	public void setPassword(String pword)
	{
		password.sendKeys(pword);
	}
	
//LogIn buttton clicked
	public void clickLogIn()
	{
		loginbtn.click();
	}

//Navigate to Home page in FB	
	public void fbHomeButton()
	{
		fbHomeBtn.click();
	}
//Click on CreatPost section	
	public void createPost()
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(creatPost)));
		
	}
//Click on Post area and enter the text to be posted
	public void statusText(String msg)
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(statusArea)));
		statusArea.sendKeys(msg);
		
	}
//Click on Post button
	public void clickPostButton()
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(postBtn)));
		
	}

//Logout from FaceBook	
	public void fbLogOut()
	{
		WebDriverWait wait=new WebDriverWait(driver,10);
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(accountMenu)));
		//WebDriverWait wait=new WebDriverWait(driver,10);
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(logOut)));
		//logOut.click();
	}
	
//Error message displayed for Invalid UserName and Password	
	public String invalidUserCase()
	{
		String errorMsg=invalidUserPwd.getText();
		return errorMsg;
	}
}
