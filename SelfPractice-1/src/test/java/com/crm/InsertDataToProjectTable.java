package com.crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertDataToProjectTable {
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		try {
			//resister database
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			//establish connection to the database
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			//issue statement
			Statement statement=connection.createStatement();
			//execute update
			int result=statement.executeUpdate("insert into project(project_id, created_by, created_on, project_name, status, team_size) value('TY_PROJ_205','Akash Gupta','18/12/2021','IT Hub','Processing','10')");
			//verify database
			if(result==1) {
				System.out.println("Data sucessfully loaded to the database");
			}else {
				System.out.println("Data Not loaded to the Database!!!");
			}
		} finally {
			//close the connection from the DataBase
			connection.close();
		}
		
	}
}
