/**
 * 
 * ���ܣ����ݿ����Ӷ���
 * ���ڣ�2016/1/14
 * 
 */
package com.qq.server.util;

import java.sql.*;

public class DBCon {

	/**
	 * 
	 * ��ȡ���ݿ�����
	 * 
	 * 
	 */
	public static Connection getConnection(){
		try {			
			String url = "jdbc:mysql://127.0.0.1:3306/qqserver?user=root&password=root";
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			Connection con = DriverManager.getConnection(url);
			return con;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 
	 * �ر�����
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
	 * �ر�ʵ��
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
	 * �رս����
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
