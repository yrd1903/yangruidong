/**
 * 这是客户端和服务器端保持通讯的线程
 */
package com.qq.client.model;

import java.net.*;
import java.io.*;
import com.qq.common.*;
import com.qq.client.manager.*;
import com.qq.client.view.*;

public class ClientConServerThread extends Thread{

	private Socket s;	

	public ClientConServerThread(Socket s){
		this.s = s;
	}
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
	public void run(){
		while(true){
			//不停读取从服务器端发来的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println("读取到从服务器发来的消息："+m.getSender()+" 给  "+m.getGetter()+" 内容  "+m.getCon());
				
				if(m.getMesType().equals(MessageType.message_com_mes)){
					//把从服务器获得的消息显示到该显示的聊天界面上
					ClientChat clientChat = ManageClientChat.getClientChat(m.getGetter()+" "+m.getSender());
					clientChat.showMessage(m);
				} else if(m.getMesType().equals(MessageType.message_ret_onLineFriend)){	
					String getter = m.getGetter();
					//修改对应的好友列表
					ClientFriendList clientFriendList = ManageClientFriendList.getClientFriendList(getter);
					//更新在线好友
					if(clientFriendList!=null){
						clientFriendList.updateFriend(m);
					}	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}	
}
