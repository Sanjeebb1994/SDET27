package com.crm;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ViewContactTC_17 {
	//Loading Main Method
	public static void main(String[] args) throws IOException {
		
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
		
		//Before Create Duplicate
		driver.findElement(By.name("search_text")).sendKeys("admin");
		driver.findElement(By.name("search_field")).sendKeys("Last Name");
		driver.findElement(By.name("submit")).click();
		String text1=driver.findElement(By.xpath("(//td[@class='small']/../td)[33]")).getText();
		System.out.println(text1);
		
		//Select one Contact page 
		driver.findElement(By.xpath("(//input[@name='selected_id']/../../../tr/td/input)[7]")).click();
		driver.findElement(By.xpath("(//a[text()='admin'])[3]")).click();
		
		//Select Duplicate Contact
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();
		
		//Save Duplicate contact
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Click on Contact Link
		driver.findElement(By.linkText("Contacts")).click();
		
		//After Create Duplicate
		driver.findElement(By.name("search_text")).sendKeys("admin");
		driver.findElement(By.name("search_field")).sendKeys("Last Name");
		driver.findElement(By.name("submit")).click();
		String text2=driver.findElement(By.xpath("(//td[@class='small']/../td)[33]")).getText();
		System.out.println(text2);
		
		//Verify Duplicate
		if(text1.equals(text2)) {
			System.out.println("Create Duplicate Unsucessfull!!!");
		}else {
			System.out.println("Create Duplicate Sucessfull...");
		}
		
		//Logout Application
		WebElement logout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Close Browser
		driver.close();
	}

}
