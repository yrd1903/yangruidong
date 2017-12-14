package com.qq.server.model;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.qq.common.*;
import com.qq.server.dao.UserAccountDao;
import com.qq.server.manager.ManageServerConClientThread;

public class ServerMain extends Thread{

	public void run(){
		try {
			ServerSocket sc = new ServerSocket(9999);
			UserAccountDao userAccountDao = new UserAccountDao();
			System.out.println("��������������ʼ��9999�˿ڼ���������");
			while(true){
				//ѭ�������Ƿ��пͻ��˵�½
				Socket socket = sc.accept();
				//ֻҪ��socket���ӣ��Ͷ�ȡ��½��Ϣ
				ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
				User user = (User)ois.readObject();
				//��֤��½��Ϣ
				//boolean legal = checkUser(user);
				boolean legal = userAccountDao.checkUser(user);
				//���ͻ��˷�����Ϣ������������
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());				
				if(legal){
					//�û��Ϸ�
					//�ж��û��Ƿ��Ѿ�����
					boolean isOnLine = isOnLine(user);
					if(isOnLine){
						//���ߣ������ظ���¼��Ϣ
						Message message = new Message(MessageType.MESSAGE_REPEAT_LOGIN);
						oos.writeObject(message);						
					} else {
						//û�����ߣ�������½
						
						//���������̺߳͸ÿͻ���ͨѶ�������߳�
						ServerConClientThread scct = new ServerConClientThread(socket,user.getUserId());
						scct.start();
						//�ѺͿͻ���ͨѶ���̼߳������
						ManageServerConClientThread.addServerConClientThread(user.getUserId(), scct);
						//�޸����ݿ�is_online�ֶ�
						userAccountDao.userOnLine(user.getUserId());
						
						//���ص�½�ɹ���Ϣ
						Message message = new Message(MessageType.MESSAGE_LOGIN_SUCCESS);
						//��ѯ�ɹ���½�û����ǳ�
						String userName = userAccountDao.getUserName(user);
						message.setUserName(userName);
						//��ѯ�����б�Ĭ��������֮�䶼�Ǻ��ѣ�
						ArrayList<User> onLineFriends = userAccountDao.getOnLineFriends();
						ArrayList<User> offLineFriends = userAccountDao.getOffLineFriends();
						message.setOnLineFriends(onLineFriends);
						message.setOffLineFriends(offLineFriends);						
						oos.writeObject(message);
						
						//������֪ͨ������ͻ������ӵ��̷߳���Ŀǰ���û��б�
						//�û�����ҲӦ�����·��غ����б�************************
						this.notifyThreadReturnList(user.getUserId(),onLineFriends, offLineFriends);						
					} 					
					
				} else {
					//�û��Ƿ�
					//���ص�½ʧ����Ϣ���ص�����
					Message message = new Message(MessageType.MESSAGE_LOGIN_FAIL);
					oos.writeObject(message);
					socket.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//����û��Ƿ��Ѿ�����
	public boolean isOnLine(User user){
		boolean res = false;
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		if(hm.containsKey(user.getUserId())){
			res = true;
		}
		return res;
	}
	
	//�û�����ʱ��֪ͨ�����Լ����������ߵģ���ͻ������ӵ��̣߳��������µ��û��б�
	//���֪ͨ�Լ����ͻ��˸ոյ�½�����ܻ�û�д���ClientFriendList,ˢ���б�������ָ��
	public void notifyThreadReturnList(String self,ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();	
		//it.next()ִ���ˣ�hm�е�ָ��Ż������ƶ����������it.next()��ִ�У�it.hasNext()��ԶΪ��
		while(it.hasNext()){
			String userId = it.next().toString();			
			if(self.equals(userId)){
				continue;
			}
			ServerConClientThread scct = ManageServerConClientThread.getServerConClientThread(userId);
			//����ͻ������ӵ��̷߳��غ����б�
			scct.returnFriendListNow(onLineFriends, offLineFriends);
		}
	}
	
}
