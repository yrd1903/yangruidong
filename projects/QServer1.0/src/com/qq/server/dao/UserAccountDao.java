package com.qq.server.dao;

import java.sql.*;
import java.util.ArrayList;

import com.qq.common.User;
import com.qq.server.util.DBCon;

public class UserAccountDao {

	
	//验证用户是否合法
	public boolean checkUser(User user){
		
		boolean res = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//账户id和密码
		String userId = user.getUserId();
		String passWd = user.getPassWd();
		//查询语句
		String sql = "select * from user_info where user_id=?";
		try {
			//获取连接
			con = DBCon.getConnection();
			//创建实例，预编译
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			//执行sql
			rs = ps.executeQuery();
			if(rs.next()){
				User u = new User();
				u.setPassWd(rs.getString("user_password"));
				if(passWd.equals(u.getPassWd())){
					//账号密码正确
					res = true;
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closeRes(rs);
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}

		return res;
	}
	
	//通过账户取得昵称
	public String getUserName(User user){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userName = "";
		String sql = "select user_name from user_info where user_id = ?";
		try {
			con = DBCon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			rs = ps.executeQuery();
			if(rs.next()){
				userName = rs.getString("user_name");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closeRes(rs);
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
		return userName;
	}
	
	//用户上线
	public void userOnLine(String userId){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update user_info set is_online=1 where user_id=?";
		try {
			con = DBCon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			int res = ps.executeUpdate();	
			if(res>0){
				System.out.println("修改用户上线成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
	}
	
	//用户下线
	public void userOffLine(String userId){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update user_info set is_online=0 where user_id=?";
		try {
			con = DBCon.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			int res = ps.executeUpdate();	
			if(res>0){
				System.out.println("修改用户下线成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
	}
	
	//查询在线好友列表
	public ArrayList<User> getOnLineFriends(){
		ArrayList<User> onLineFriends = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user_info where is_online=1";
		try {
			con = DBCon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setIsOnLine(rs.getInt("is_online"));
				onLineFriends.add(user);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closeRes(rs);
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
		return onLineFriends;
	}
	
	//查询不在线好友列表
	public ArrayList<User> getOffLineFriends(){
		ArrayList<User> offLineFriends = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user_info where is_online=0";
		try {
			con = DBCon.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setIsOnLine(rs.getInt("is_online"));
				offLineFriends.add(user);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closeRes(rs);
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
		return offLineFriends;
	}	
	
	
}
