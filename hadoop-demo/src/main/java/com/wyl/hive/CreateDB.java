package com.wyl.hive;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class CreateDB {
	
     
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Register driver and create driver instance

		Class.forName(driverName);
		
		// get connection
		Connection con = DriverManager.getConnection("jdbc:hive2://wangyulin-test-host:10000/default", "hadoop", "");
		Statement stmt = con.createStatement();
		stmt.execute("DROP DATABASE IF EXISTS userdb CASCADE");
		
		//executeQuery() 回有异常：Exception in thread "main" java.sql.SQLException: The query did not generate a result set!
		stmt.execute("CREATE DATABASE userdb");
		
		System.out.println("Database userdb created successfully.");

		con.close();
	}

}
