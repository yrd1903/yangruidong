/**
 * 这是客户端连接服务器的后台，为登录之前和服务器进行通讯的类
 */
package com.qq.client.model;

import java.net.*;
import java.io.*;
import com.qq.client.manager.*;
import com.qq.common.*;

public class ClientConServer{

	private Socket s;
	
	//发送第一次请求，用户登录
	public boolean checkUser(Object obj){
		boolean b=false;
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			System.out.println(ms.getMesType());
			if(ms.getMesType().equals(MessageType.message_success))
			{				
				b=true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	public void connectServer(Object obj){
		//把客户端与服务器的通讯线程捆绑在一个qq账号界面上，而不是每一个聊天窗口,即不是qqchat
		//创建一个该qq号码和服务器端保持通讯连接的线程
		ClientConServerThread ccst = new ClientConServerThread(s);
		//启动该线程
		ccst.start();
		//把通讯线程加入管理
		ManageClientConServerThread.addClientConServerThread(((User)obj).getUserId(), ccst);
	}
	
	
	//注册用户等。。。
	public void sendInfoToServer(Object o){
		
	}
	
}

