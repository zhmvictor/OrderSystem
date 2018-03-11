package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDao {
	public static final String DDIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/ordersys?useUnicode=true&characterEncoding=utf-8";
	public static final String DBNAME = "root";
	public static final String DBPASS = "newpassword";
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(DDIVER);
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS);	
		}catch(Exception e){
		  e.printStackTrace();
		}
		return conn;
	}
	public void closeAll(Connection conn,PreparedStatement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}