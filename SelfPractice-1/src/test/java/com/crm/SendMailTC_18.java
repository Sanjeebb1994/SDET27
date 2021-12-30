package com.crm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.vetiger.comcast.genericutility.JavaUtility;

public class SendMailTC_18 {
//Create Main Method
	public static void main(String[] args) throws IOException {
		JavaUtility ju=new JavaUtility();
		//Connect Java With PropertiesFile
		FileInputStream fis=new FileInputStream("D:\\External Resourse\\CommonData.properties");
		
		//Loading Object of Properties File
		Properties pobj=new Properties();
		pobj.load(fis);
		
		//Get Data From Properties File
		String Url=pobj.getProperty("url");
		String Username=pobj.getProperty("Username");
		String Password=pobj.getProperty("Password");
		String Browser=pobj.getProperty("Browser");
		
		//Fetch Data in Output
		System.out.println("Url: "+Url);
		System.out.println("Username: "+Username);
		System.out.println("Password: "+Password);
		System.out.println("Browser: "+Browser);
		
		//Load WebDriver
		WebDriver driver=null;
		
		//Lunching Browser
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else {
			driver=new EdgeDriver();
		}
		
		//Synchronizing ImplicitlyTime Wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Maximize WebBrowser
		driver.manage().window().maximize();
				
		//Navigate To Application
		driver.get(Url);
		
		//Login Application
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on contact Link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Select one Contact page 
		driver.findElement(By.xpath("(//input[@name='selected_id']/../../../tr/td/input)[7]")).click();
		
		//Select Mail
		driver.findElement(By.xpath("//input[@value='Send Mail']")).click();
		driver.findElement(By.name("Select")).click();
		
		//Driver Switching to Send Mail Window
		String mainID=driver.getWindowHandle();
		Set<String> allID=driver.getWindowHandles();
		Iterator <String> it=allID.iterator();
		while(it.hasNext()) {
			String currentID=it.next();
			driver.switchTo().window(currentID);
			String CurrentpageTitle=driver.getTitle();
			if(CurrentpageTitle.contains("Emails&action")) {
				break;
			}
		}
		
		//Fill mandatory Option
		driver.findElement(By.id("parent_name")).sendKeys("piyushsrivastaba");
		driver.findElement(By.name("subject")).sendKeys("adminName");
		
		//Send Mail
		driver.findElement(By.name("Send")).click();
		
		//Driver Switch to main Windows
		driver.switchTo().window(mainID);
		
		//Logout Application
		WebElement logout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
				
		//Close Browser
		driver.close();
	}
}
