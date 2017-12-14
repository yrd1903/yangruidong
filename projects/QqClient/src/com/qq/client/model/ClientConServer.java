/**
 * ���ǿͻ������ӷ������ĺ�̨��Ϊ��¼֮ǰ�ͷ���������ͨѶ����
 */
package com.qq.client.model;

import java.net.*;
import java.io.*;
import com.qq.client.manager.*;
import com.qq.common.*;

public class ClientConServer{

	private Socket s;
	
	//���͵�һ�������û���¼
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
		//�ѿͻ������������ͨѶ�߳�������һ��qq�˺Ž����ϣ�������ÿһ�����촰��,������qqchat
		//����һ����qq����ͷ������˱���ͨѶ���ӵ��߳�
		ClientConServerThread ccst = new ClientConServerThread(s);
		//�������߳�
		ccst.start();
		//��ͨѶ�̼߳������
		ManageClientConServerThread.addClientConServerThread(((User)obj).getUserId(), ccst);
	}
	
	
	//ע���û��ȡ�����
	public void sendInfoToServer(Object o){
		
	}
	
}

