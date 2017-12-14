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

	//�������ͨѶ���߳�socket
	private Socket socket;
	//�ÿͻ��˵�id
	String userId;
	
	public ClientConServerThread(Socket socket,String userId){
		this.socket = socket;
		this.userId = userId;
	}
	
	public void run(){
		while(true){
			//���϶�ȡ�ӷ�������������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				if(message.getMesType().equals(MessageType.MESSAGE_COM_MES)){
					//��ͨ��Ϣ������ʾ�ڶ�Ӧ�����촰����
					String sender = message.getSender();//˭������Ϣ
					String getter = message.getGetter();//������
					String senderName = message.getSenderName();
					String getterName = message.getGetterName();
					//String con = message.getCon();
					//�ж���ú�������Ĵ����Ƿ��Ѿ���
					HashMap<String, ClientChat> hm = ManageClientChat.hm;
					ClientChat chat = null;
					if(hm.containsKey(sender)){
						//�Ѿ�������ú�������Ĵ���
						//ȡ����ú�������Ĵ���
						chat = ManageClientChat.getClientChat(sender);
					} else {
						//û�д���ú�������Ĵ���
						//����ֱ�ӵ�����ú��ѵ����촰��
						chat = new ClientChat(getter,sender,getterName,senderName);
						ManageClientChat.addClientChat(sender, chat);
					}					
					chat.showMessage(message);
				} else if(message.getMesType().equals(MessageType.MESSAGE_RET_FRIENDLIST)){
					//�û��б��и��£����������û��б���Ҫ�����Ƿ�������Ϣ
					ArrayList<User> onLineFriends = message.getOnLineFriends();
					ArrayList<User> offLineFriends = message.getOffLineFriends();				
					ClientFriendList friendList = ManageClientFriendList.getClientFriendList(userId);
					friendList.updateFriendList(onLineFriends, offLineFriends);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//�����쳣��һ��������������ӷ����жϣ�ois.readObject()������
				//�����쳣�����ж��̣߳�Ҳ����ʹ�������жϣ�ֻ�н��������Ż��ж����ӣ�����ʹ������Ҳ���������쳣
				System.out.println("�����쳣��QQ���رգ�");	
				//�õ�FriendList���
				ClientFriendList friendList = ManageClientFriendList.getClientFriendList(userId);
				//��ʾ�������쳣��Ϣ
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
