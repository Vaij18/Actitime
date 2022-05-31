package Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Browser.Base;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PomClasses.ApplicationHeaderpage;
import PomClasses.LoginPage;
import PomClasses.Taskspage;
import Utils.Utility;

public class testng  {
	WebDriver driver;
	ApplicationHeaderpage applicationHeaderpage;
	SoftAssert softassert;	
	LoginPage loginPage;
	
	@Parameters ("browserName")
	@BeforeTest

	public void launchcrossbrowsers (String browser)
	
	{
		//System.out.println(browser);
		if (browser.equals("Chrome"))
		{
		  driver=Base.openchrome();
		}
		if (browser.equals("Edge"))
		{
	      driver=Base.openedge();
		}
		if (browser.equals("Firefox"))
		{
	      driver=Base.openFirefox();
		}

	
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void allobjectsofpomclasses()
	{ 
		 applicationHeaderpage=new ApplicationHeaderpage(driver);
		 LoginPage loginPage= new LoginPage(driver);
	
	}
	@BeforeMethod
	public void loginpage() throws EncryptedDocumentException, IOException
	{
		driver.get("https://online.actitime.com/abc17/timetrack/enter.do");
		LoginPage loginPage= new LoginPage(driver);
		//String data=Utility.getdatafromexcel();
	    //loginPage.sendusername(data);
		//loginPage.sendpassword(data);
		//loginPage.clickonloginbutton();
		loginPage.sendusername();
		loginPage.sendpassword();
		loginPage.clickonloginbutton();

	    
	 softassert= new SoftAssert();
		
		applicationHeaderpage= new ApplicationHeaderpage(driver);
	}
	@Test
	public void verifyTaskTab() throws Exception
	{
		//applicationHeaderpage.clickontasks();
		
		Thread.sleep(3000);
		applicationHeaderpage.clickontasks();
		String url= driver.getCurrentUrl();
		String title= driver.getTitle();
		
		
				
		System.out.println(url);
		System.out.println(title);
		
		Assert.assertEquals("https://online.actitime.com/abc17/tasks/tasklist.do", url);
		//softassert.assertEquals("actiTIME -  Task List", title);
		softassert.assertEquals(" All Customers"," All Customers");
		softassert.assertEquals(" Tasks I Can Manage", " Tasks I Can Manage");
		softassert.assertAll();
				
		//if(url.equals("https://online.actitime.com/abc17/tasks/tasklist.doo") && (title.equals("actiTIME -  Task List")))
		
	}
	@Test 
	public void verifyReportTab() throws Exception
	{
		applicationHeaderpage= new ApplicationHeaderpage(driver);
		Thread.sleep(3000);;

		applicationHeaderpage.clickonreports();
		 String url= driver.getCurrentUrl();
		 String title= driver.getTitle();
		 System.out.println(url);
		 System.out.println(title);
		 Assert.assertEquals("https://online.actitime.com/abc17/reports/dashboard.do", url);
		// if(url.equals("https://online.actitime.com/abc17/reports/dashboard.do") && (title.equals("actiTIME -  Reports Dashboard")))
			
	}
	
	
@AfterMethod 
public void logoutbutton() throws Exception
{
	Thread.sleep(3000);;
	applicationHeaderpage.clickonlogoutbutton();
}
@AfterClass
public void clearobjects()
{
	applicationHeaderpage=null;
	loginPage=null;
	//driver.close();
}
@AfterTest
public void closebrowser()
{
	driver.close();
	driver=null;
    System.gc();
}
System.out.println("Changes made and send to local repo");

}
