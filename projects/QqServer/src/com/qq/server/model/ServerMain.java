/**
 * ����QQ���������ڼ������ȴ�ĳ��QQ�ͻ��������� 
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;
import com.qq.common.*;
import com.qq.server.manager.*;

public class ServerMain extends Thread{

	public void run(){
		
		try {
			//��9999����
			System.out.println("���Ƿ���������9999����");
			ServerSocket ss=new ServerSocket(9999);			
			while(true){
				//�������ȴ�����
				Socket s=ss.accept();
				//���տͻ��˷�������Ϣ
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();				
				System.out.println("���������յ��û�id:"+u.getUserId()+"  ����:"+u.getPasswd());				
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456")){
					//����һ���ɹ���½����Ϣ��
					m.setMesType(MessageType.message_success);
					oos.writeObject(m);
					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
					ServerConClientThread scct = new ServerConClientThread(s);
					ManageServerConClientThread.addServerConClienThread(u.getUserId(), scct);
					//������ÿͻ���ͨѶ���߳�
					scct.start();					
					//֪ͨ���������û�
					scct.notifyOther(u.getUserId());
					
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�����
					s.close();
				}	
			}										
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}

