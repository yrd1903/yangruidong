/**
 * 
 * 功能：用于客户端登陆界面与服务器进行通讯的类
 * 
 */
package com.qq.client.model;

import java.io.*;
import java.net.*;

import com.qq.client.manager.ManageClientConServerThread;
import com.qq.common.*;

public class ClientLoginOpe {

	private Socket socket;
	
	//用户登录
	public Message userLoginIn(User user){

		Message message = null;
		//创建Socket和服务器连接，本地主机，9999端口
		try {
			socket = new Socket("127.0.0.1",9999);
			//发送用户信息
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(user);
			//接收服务器返回的验证结果
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			message = (Message)ois.readObject();
			if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
				//合法用户
				//创建和服务器连接的线程并启动
				ClientConServerThread ccst = new ClientConServerThread(socket,user.getUserId());
				ccst.start();
				//把线程加入到管理
				ManageClientConServerThread.addClientConServerThread(user.getUserId(), ccst);
			} else {
				//用户不合法(可能是用户名密码错误，或者重复登录，这里不需要操作，交给login界面)
				socket.close();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	//用户下线，（应该交给ClientConServerThread）
//	public void userLoginOut(String userId){
//		//向服务器发送下线消息
//		Message message = new Message(MessageType.USER_LOGINOUT);
//		//从线程管理类中取出线程，进而取出socket，发送数据到服务器
//		Socket userSocket = ManageClientConServerThread.getClientConServerThread(userId).getSocket();
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(userSocket.getOutputStream());
//			oos.writeObject(message);			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}		
//	}

}
