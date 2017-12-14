package com.qq.client.model;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.qq.client.manager.ManageClientChat;
import com.qq.client.manager.ManageClientFriendList;
import com.qq.client.view.ClientChat;
import com.qq.client.view.ClientFriendList;
import com.qq.common.*;

public class ClientConServerThread extends Thread{

	//与服务器通讯的线程socket
	private Socket socket;
	//该客户端的id
	String userId;
	
	public ClientConServerThread(Socket socket,String userId){
		this.socket = socket;
		this.userId = userId;
	}
	
	public void run(){
		while(true){
			//不断读取从服务器发来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				if(message.getMesType().equals(MessageType.MESSAGE_COM_MES)){
					//普通消息包，显示在对应的聊天窗口中
					String sender = message.getSender();//谁发来消息
					String getter = message.getGetter();//发给我
					String senderName = message.getSenderName();
					String getterName = message.getGetterName();
					//String con = message.getCon();
					//判断与该好友聊天的窗口是否已经打开
					HashMap<String, ClientChat> hm = ManageClientChat.hm;
					ClientChat chat = null;
					if(hm.containsKey(sender)){
						//已经打开了与该好友聊天的窗口
						//取出与该好友聊天的窗口
						chat = ManageClientChat.getClientChat(sender);
					} else {
						//没有打开与该好友聊天的窗口
						//程序直接弹出与该好友的聊天窗口
						chat = new ClientChat(getter,sender,getterName,senderName);
						ManageClientChat.addClientChat(sender, chat);
					}					
					chat.showMessage(message);
				} else if(message.getMesType().equals(MessageType.MESSAGE_RET_FRIENDLIST)){
					//用户列表有更新，返回最新用户列表，主要包括是否在线信息
					ArrayList<User> onLineFriends = message.getOnLineFriends();
					ArrayList<User> offLineFriends = message.getOffLineFriends();				
					ClientFriendList friendList = ManageClientFriendList.getClientFriendList(userId);
					friendList.updateFriendList(onLineFriends, offLineFriends);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//发生异常，一般是与服务器连接发生中断，ois.readObject()出问题
				//发生异常不会中断线程，也不会使得连接中断，只有进程死亡才会中断连接，进而使服务器也发生连接异常
				System.out.println("连接异常！QQ将关闭！");	
				//得到FriendList面板
				ClientFriendList friendList = ManageClientFriendList.getClientFriendList(userId);
				//提示服务器异常信息
				friendList.showServerException();
			} 
		}
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
