package com.qq.server.dao;

import java.sql.*;
import java.util.ArrayList;

import com.qq.common.User;
import com.qq.server.util.DBCon;

public class UserAccountDao {

	
	//��֤�û��Ƿ�Ϸ�
	public boolean checkUser(User user){
		
		boolean res = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//�˻�id������
		String userId = user.getUserId();
		String passWd = user.getPassWd();
		//��ѯ���
		String sql = "select * from user_info where user_id=?";
		try {
			//��ȡ����
			con = DBCon.getConnection();
			//����ʵ����Ԥ����
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			//ִ��sql
			rs = ps.executeQuery();
			if(rs.next()){
				User u = new User();
				u.setPassWd(rs.getString("user_password"));
				if(passWd.equals(u.getPassWd())){
					//�˺�������ȷ
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
	
	//ͨ���˻�ȡ���ǳ�
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
	
	//�û�����
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
				System.out.println("�޸��û����߳ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
	}
	
	//�û�����
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
				System.out.println("�޸��û����߳ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBCon.closePre(ps);
			DBCon.closeCon(con);
		}
	}
	
	//��ѯ���ߺ����б�
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
	
	//��ѯ�����ߺ����б�
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
