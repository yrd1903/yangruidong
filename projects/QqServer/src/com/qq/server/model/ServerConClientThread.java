/**
 * 
 * 功能：服务器和某个客户端的通讯线程
 * 
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;

import com.qq.common.*;

import java.util.*;

import com.qq.server.manager.*;

public class ServerConClientThread extends Thread{

	private Socket s;
	
	public ServerConClientThread(Socket s){
		//把服务器和该客户端的连接赋值给s
		this.s = s;
	}
	
	//让该线程去通知其他用户
	public void notifyOther(String iam){
		HashMap hm = ManageServerConClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()){
			
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			
			//取出在线的人的id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageServerConClientThread.getServerConClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		
		while(true){
			//这里该线程就可以接受客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println(m.getSender()+" 给  "+m.getGetter()+" 说 :"+m.getCon());
				
				//对从客户端取得的消息进行类型判断，然后做相应的处理
				if(m.getMesType().equals(MessageType.message_com_mes)){
					//完成转发
					//取得接收人的通讯线程
					ServerConClientThread sc = ManageServerConClientThread.getServerConClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if(m.getMesType().equals(MessageType.message_get_onLineFriend)){
					//把在服务器在线的好友给该客户端返回
					String res = ManageServerConClientThread.getAllOnLineUserId();
					Message ms = new Message();
					ms.setMesType(MessageType.message_ret_onLineFriend);
					ms.setCon(res);
					ms.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(ms);
				}					
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
