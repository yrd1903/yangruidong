/**
 * 
 * ���ܣ���������ĳ���ͻ��˵�ͨѶ�߳�
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
		//�ѷ������͸ÿͻ��˵����Ӹ�ֵ��s
		this.s = s;
	}
	
	//�ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam){
		HashMap hm = ManageServerConClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()){
			
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			
			//ȡ�����ߵ��˵�id
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
			//������߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println(m.getSender()+" ��  "+m.getGetter()+" ˵ :"+m.getCon());
				
				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_com_mes)){
					//���ת��
					//ȡ�ý����˵�ͨѶ�߳�
					ServerConClientThread sc = ManageServerConClientThread.getServerConClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if(m.getMesType().equals(MessageType.message_get_onLineFriend)){
					//���ڷ��������ߵĺ��Ѹ��ÿͻ��˷���
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
