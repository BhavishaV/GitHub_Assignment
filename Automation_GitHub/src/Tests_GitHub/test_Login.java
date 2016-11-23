package Tests_GitHub;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;


public class test_Login {

	//public static ExtentTest logger; 
	public static ExtentReports extent;
	
	
	public static void main(String[] args){
		
		//if u want to use chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		
		extent = new ExtentReports();
		
		try{
		extent.init("D:\\githublogin.html", true);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "HTML report cannont be created @ D drive");
		}
			
		ValidLigin("be.bhavisha01@gmail.com","Password@21","Valid Login");
		inValidLigin("be.bhavisha01@gmail.com","zzzzz","InValid Login");
	
	}
	
	public static void ValidLigin(String UserName, String Password, String testType){
		String expTitle = "GitHub";
		String Log="";
		
		WebDriver wd=null;
		
		//check driver
		try{
				wd=new ChromeDriver();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Issue with chrome driver. Please place chrome driver exe @ C:\\Selenium\\chromedriver.exe");
		}
		
		wd.get("http://github.com/");
		wd.manage().window().maximize();
		
				
		extent.startTest(testType);
		
		try{
		wd.findElement(By.xpath("//a[@class='btn site-header-actions-btn mr-1']")).click();
		
		wd.findElement(By.xpath("//*[@id='login_field']")).clear();
		wd.findElement(By.xpath("//*[@id='login_field']")).sendKeys(UserName);
		
		wd.findElement(By.xpath("//*[@id='password']")).clear();
		wd.findElement(By.xpath("//*[@id='password']")).sendKeys(Password);
		
		wd.findElement(By.xpath("//input[@name='commit']")).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			
			extent.log(LogStatus.FAIL, e.toString());
		}
		String actTitle = wd.getTitle();
		
		if (actTitle.equals(expTitle))
		{
			Log = "Valid Login Test Passed. User name:"+UserName+"  Password:"+Password;
			extent.log(LogStatus.PASS, Log);
		}
		else{
			Log = "Valid Login Test Login Failed. User name:"+UserName+"  Password:"+Password;
			extent.log(LogStatus.FAIL, Log);
		}
		System.out.println("End of Login");
		wd.close();
		
		
	}
	
	public static void inValidLigin(String UserName, String Password, String testType){
		String expErrorMessage = "Incorrect username or password.";
		String Log="";
		String actErrorMessage="";
		
		WebDriver wd=null;
		
		//check driver
		try{
				wd=new ChromeDriver();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Issue with chrome driver. Please place chrome driver exe @ C:\\Selenium\\chromedriver.exe");
		}
		
		wd.get("http://github.com/");
		wd.manage().window().maximize();
		
		
		extent.startTest(testType);
		
		try{
		wd.findElement(By.xpath("//a[@class='btn site-header-actions-btn mr-1']")).click();
		
		wd.findElement(By.xpath("//*[@id='login_field']")).clear();
		wd.findElement(By.xpath("//*[@id='login_field']")).sendKeys(UserName);
		
		wd.findElement(By.xpath("//*[@id='password']")).clear();
		wd.findElement(By.xpath("//*[@id='password']")).sendKeys(Password);
		
		wd.findElement(By.xpath("//input[@name='commit']")).click();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		actErrorMessage = wd.findElement(By.xpath("//*[@id='js-flash-container']/div")).getText();
		}
		catch(Exception e)
		{
			
			extent.log(LogStatus.FAIL, e.toString());
		}
		
		
		System.out.println(actErrorMessage);
		if (actErrorMessage.equals(expErrorMessage))
		{
			Log = "invalid Login test Passed. User name:"+UserName+"  Password:"+Password;
			extent.log(LogStatus.PASS, Log);
		}
		else{
			Log = "invalid Login test Failed. User name:"+UserName+"  Password:"+Password;
			extent.log(LogStatus.FAIL, Log);
		}
		System.out.println("End of Login");
		wd.close();
		
		
		
	}
	

}
