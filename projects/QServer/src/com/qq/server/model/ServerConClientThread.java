package com.qq.server.model;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.*;
import com.qq.server.dao.UserAccountDao;
import com.qq.server.manager.ManageServerConClientThread;
import com.qq.server.util.MyLog;

public class ServerConClientThread extends Thread{

	//���̵߳�socket
	private Socket socket;
	//���߳���ͻ���ͨѶ�Ŀͻ���id
	private String userId;
	//�̱߳�־λ
	private boolean flag = true;
	//UserAccountDao
	UserAccountDao userAccountDao;
		
	public ServerConClientThread(Socket socket,String userId){
		this.socket = socket;
		this.userId = userId;
		userAccountDao = new UserAccountDao();
	}
	
	public void stopThread(){
		this.flag = false;
	}
	
	public void run(){
		while(flag){
			try {
				//ѭ����ȡ�ӿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				//��ʾ
				//System.out.println(message.getSender()+" ��  "+message.getGetter()+" ˵��"+message.getCon());
				if(message.getMesType().equals(MessageType.MESSAGE_COM_MES)){
					//��ͨ����Ϣ���Ͱ���ת����Ҫ���͵��ĺ���
					String getter = message.getGetter();
					//���жϸú����Ƿ�����,ֻ�����߲�ת��
					if(ManageServerConClientThread.getServerConClientThread(getter)!=null){
						Socket getterSocket = ManageServerConClientThread.getServerConClientThread(getter).getSocket();
						ObjectOutputStream oos = new ObjectOutputStream(getterSocket.getOutputStream());
						oos.writeObject(message);
						//��ӡ��־
						MyLog.printLog("�û�"+message.getSender()+" ��  �û�"+message.getGetter()+"������Ϣ�����ݣ�"+message.getCon());
					} else {
						//������
						System.out.println("�ú��Ѳ����ߣ�");
						//��ӡ��־
						MyLog.printLog("�û�"+message.getSender()+" �� �û�"+message.getGetter()+"������Ϣ,�û�"+message.getGetter()+"������");
					}					
				} 							
			} catch (Exception e) {
				e.printStackTrace();
				//�����쳣��һ������ͻ��������жϡ��������û��˳����ͻ��˶�����ois.readObject()����
				System.out.println("�����쳣���̹߳رգ��û�����");
				MyLog.printLog("�����쳣���̹߳رգ��û�����");
				//�������ж����û����̣߳��û�����
				this.userLoginOut();
				//��ѯĿǰ�����û��б�����֪ͨ������ͻ������ӵ��̷߳��������û��б�
				//��ѯ�����б�Ĭ��������֮�䶼�Ǻ��ѣ�
				ArrayList<User> onLineFriends = userAccountDao.getOnLineFriends();
				ArrayList<User> offLineFriends = userAccountDao.getOffLineFriends();
				this.notifyThreadReturnList(userId, onLineFriends, offLineFriends);
				//�رմ��߳�
				this.stopThread();
			}
		}
	}
	
	//���̶߳�Ӧ���û�����
	public void userLoginOut(){
		//�õ������û�ͨѶ�̵߳�HashMap
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		//��Ҫ���ߵ��û���HashMap��ɾ��
		hm.remove(userId);
		//�޸����ݿ�is_online�ֶ�
		userAccountDao.userOffLine(userId);
	}
	
	//�û�����ʱ��֪ͨ�����Լ����������ߵģ���ͻ������ӵ��̣߳��������µ��û��б�	
	public void notifyThreadReturnList(String self,ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();	
		//it.next()ִ���ˣ�hm�е�ָ��Ż������ƶ����������it.next()��ִ�У�it.hasNext()��ԶΪ��
		while(it.hasNext()){
			String userId = it.next().toString();
			if(self.equals(userId)){
				return;
			}
			ServerConClientThread scct = ManageServerConClientThread.getServerConClientThread(userId);
			//����ͻ������ӵ��̷߳��غ����б�
			scct.returnFriendListNow(onLineFriends, offLineFriends);
		}
	}
	
	//���������û��б����������Ƿ�������Ϣ��
	public void returnFriendListNow(ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		//�����ص���Ϣ��
		Message message = new Message(MessageType.MESSAGE_RET_FRIENDLIST);
		message.setOnLineFriends(onLineFriends);
		message.setOffLineFriends(offLineFriends);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			oos.writeObject(message);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
