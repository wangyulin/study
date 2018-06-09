package com.wyl.other.wyl.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 创建Hive table
 * @author wangyulin
 */
public class HiveTable {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		Connection con = DriverManager.getConnection("jdbc:hive2://wangyulin-test-host:10000/userdb", "hadoop", "");
		Statement stmt = con.createStatement();

		stmt.execute(
				"CREATE TABLE IF NOT EXISTS employee "
				+ "(eid int, name String,salary String, destination String) "
				+ "COMMENT 'Employee details' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' "
				+ "LINES TERMINATED BY '\n' STORED AS TEXTFILE");

		System.out.println("Table employee created.");
		con.close();
	}

}
