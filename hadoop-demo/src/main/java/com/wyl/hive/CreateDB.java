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
		
		//DROP DATABASE StatementDROP (DATABASE|SCHEMA) [IF EXISTS] database_name [RESTRICT|CASCADE];
		stmt.execute("DROP DATABASE IF EXISTS userdb CASCADE");
		
		//executeQuery() 回有异常：Exception in thread "main" java.sql.SQLException: The query did not generate a result set!
		//CREATE DATABASE|SCHEMA [IF NOT EXISTS] <database name>
		System.out.println(stmt.execute("CREATE DATABASE IF NOT EXISTS userdb"));;
		
		System.out.println("Database userdb created successfully.");

		con.close();
	}

}
