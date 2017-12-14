/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�
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
			//��ͣ��ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println("��ȡ���ӷ�������������Ϣ��"+m.getSender()+" ��  "+m.getGetter()+" ����  "+m.getCon());
				
				if(m.getMesType().equals(MessageType.message_com_mes)){
					//�Ѵӷ�������õ���Ϣ��ʾ������ʾ�����������
					ClientChat clientChat = ManageClientChat.getClientChat(m.getGetter()+" "+m.getSender());
					clientChat.showMessage(m);
				} else if(m.getMesType().equals(MessageType.message_ret_onLineFriend)){	
					String getter = m.getGetter();
					//�޸Ķ�Ӧ�ĺ����б�
					ClientFriendList clientFriendList = ManageClientFriendList.getClientFriendList(getter);
					//�������ߺ���
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
