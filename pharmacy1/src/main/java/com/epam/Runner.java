package com.epam;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.dbconnection.СonnectionManager;

public class Runner {
	public static void main(String[] args) throws SQLException {	
		try (Connection connection = СonnectionManager.get();
				Statement statement = connection.createStatement()){
				String sql = "create table info("
						+ "id int auto_increment primary key"
						+ ");";
				boolean executeResult = statement.execute(sql);
				System.out.println(executeResult);
			}
//		
//			Connection connection = null;
//		try {
//			connection = СonnectionManager.get();
//			Statement statement = connection.createStatement();
//			String sql = "create table info("
//			+ "id int auto_increment primary key"
//			+ ");";
//			boolean executeResult = statement.execute(sql);
//			System.out.println(executeResult);
//		} finally {
//			connection.close();
//		}
		
	}
}
