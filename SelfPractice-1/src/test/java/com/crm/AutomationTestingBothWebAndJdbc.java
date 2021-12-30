package com.crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AutomationTestingBothWebAndJdbc {
	public static void main(String[] args) throws SQLException {
		//launch chrome-browser
		WebDriver driver=new ChromeDriver();
		//provide implicitly wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//provide url path
		driver.get("http://localhost:8084/");
		//maximize windows
		driver.manage().window().maximize();
		//login application
		WebElement login=driver.findElement(By.id("usernmae"));
		login.sendKeys("rmgyantra"+Keys.TAB+"rmgy@9999"+Keys.ENTER);
		//lunching project
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		//create project 
		WebElement create=driver.findElement(By.xpath("//input[@name='projectName']"));
		create.sendKeys("CRM project"+Keys.TAB+"Rahul dey");
		WebElement list=driver.findElement(By.xpath("//label[text()='Project Status ']/../select"));
		Select s=new Select(list);
		s.selectByIndex(3);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//close chromebrowser
		driver.close();
		
		//verify database
		String expectedprojectsproject_name="CRM project";
		Connection connection=null;
		try {
			//register database
			Driver driver1=new Driver();
			DriverManager.registerDriver(driver1);
			//establish connection to the database
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			//issue statement
			Statement statement=connection.createStatement();
			//execute query
			ResultSet result=statement.executeQuery("select * from project");
			//verify database
			while(result.next()) {
				if(result.getString(4).equals(expectedprojectsproject_name)) {
					System.out.println("Data sucessfully loaded to the DataBase");
				}
			}
		} finally {
			//close the database connection
			connection.close();
		}
	}
}
