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

public class CreateContact {
	//Loading main Method
	public static void main(String[] args) throws IOException {
		//Connect Java code with properties file
		FileInputStream fis=new FileInputStream("D:\\External Resourse\\CommonData.properties");
		//Loading properties file
		Properties pobj=new Properties();
		//Get data From Properties file
		pobj.load(fis);
		String URL=pobj.getProperty("url");
		String USERNAME=pobj.getProperty("Username");
		String PASSWORD=pobj.getProperty("Password");
		String BROWSER=pobj.getProperty("Browser");
		String NAME=pobj.getProperty("dash");
		//Load data in output
		System.out.println("Url: "+URL);
		System.out.println("Username: "+USERNAME);
		System.out.println("Password: "+PASSWORD);
		System.out.println("Browser: "+BROWSER);
		System.out.println("Name: "+NAME);
		//Luncing WebBroser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		//Synchronizing Implicit time.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Loading Url
		driver.get(URL);
		//Maximize Browser
		driver.manage().window().maximize();
		//Login Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//create contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("dash");
		//save contact
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify via getText
		String text=driver.findElement(By.xpath("//span[@class='dvHeaderText']/../../../..")).getText();
		System.out.println(text);
		if(text.contains("Contact Information")) {
			System.out.println("Verify Sucessful😜😜✌✌");
		}else {
			System.out.println("Verify Unsucessful");
		}
		//logout application
		WebElement logout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		//close browser
		driver.close(); 
	}
}
