package com.qq.common;

import java.util.ArrayList;

public class Message implements java.io.Serializable{

	//消息类型
	private String mesType;
	//消息内容
	private String sender;//发送消息的人
	private String senderName;//发送消息人的昵称
	private String getter;//接收消息的人
	private String getterName;//接收消息人的昵称
	private String con;//消息内容
	private String sendTime;//消息发送时间
	private String userName;//成功登陆用户的昵称
	private ArrayList<User> onLineFriends;
	private ArrayList<User> offLineFriends;

	//构造函数
	public Message(){
		
	}
	public Message(String mesType){
		this.mesType = mesType;
	}
		
	public String getMesType() {
		return mesType;
	}
	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public ArrayList<User> getOnLineFriends() {
		return onLineFriends;
	}
	public void setOnLineFriends(ArrayList<User> onLineFriends) {
		this.onLineFriends = onLineFriends;
	}
	public ArrayList<User> getOffLineFriends() {
		return offLineFriends;
	}
	public void setOffLineFriends(ArrayList<User> offLineFriends) {
		this.offLineFriends = offLineFriends;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getGetterName() {
		return getterName;
	}
	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}
}
