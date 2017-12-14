/**
 * 
 * 功能：数据库连接对象
 * 日期：2016/1/14
 * 
 */
package com.qq.server.util;

import java.sql.*;

public class DBCon {

	/**
	 * 
	 * 获取数据库连接
	 * 
	 * 
	 */
	public static Connection getConnection(){
		try {			
			String url = "jdbc:mysql://127.0.0.1:3306/qqserver?user=root&password=root";
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			Connection con = DriverManager.getConnection(url);
			return con;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 
	 * 关闭连接
	 * 
	 * 
	 */
	public static void closeCon(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 关闭实例
	 * 
	 * 
	 */
	public static void closePre(PreparedStatement ps){
		try {
			ps.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 * 关闭结果集
	 * 
	 * 
	 */
	public static void closeRes(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
