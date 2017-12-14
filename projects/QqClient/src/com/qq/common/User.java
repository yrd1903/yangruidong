/**
 * 这是用户信息类
 */
package com.qq.common;

//实现序列化，可以使对象在网络中传输
public class User implements java.io.Serializable{

	private String userId;
	private String passwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	
}
