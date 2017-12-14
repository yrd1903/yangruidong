/**
 * 这是QQ服务器，在监听，等待某个QQ客户端来连接 
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;
import com.qq.common.*;
import com.qq.server.manager.*;

public class ServerMain extends Thread{

	public void run(){
		
		try {
			//在9999监听
			System.out.println("我是服务器，在9999监听");
			ServerSocket ss=new ServerSocket(9999);			
			while(true){
				//阻塞，等待连接
				Socket s=ss.accept();
				//接收客户端发来的信息
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();				
				System.out.println("服务器接收到用户id:"+u.getUserId()+"  密码:"+u.getPasswd());				
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456")){
					//返回一个成功登陆的信息包
					m.setMesType(MessageType.message_success);
					oos.writeObject(m);
					//这里就单开一个线程，让该线程与该客户端保持通讯
					ServerConClientThread scct = new ServerConClientThread(s);
					ManageServerConClientThread.addServerConClienThread(u.getUserId(), scct);
					//启动与该客户端通讯的线程
					scct.start();					
					//通知其他在线用户
					scct.notifyOther(u.getUserId());
					
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//关闭连接
					s.close();
				}	
			}										
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}

