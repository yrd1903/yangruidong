package com.qq.common;

public class User implements java.io.Serializable{

	//�����ֶ�
	private String userId;	
	private String passWd;
	private String userName;
	private int isOnLine;//1Ϊ���ߣ�0Ϊ������
	


	//���캯��
	public User(){
		
	}	
	public User(String userId,String passWd){
		this.userId = userId;
		this.passWd = passWd;
	}
	
	//get��set
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getIsOnLine() {
		return isOnLine;
	}
	
	public void setIsOnLine(int isOnLine) {
		this.isOnLine = isOnLine;
	}
	
}
