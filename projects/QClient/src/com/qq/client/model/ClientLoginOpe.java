/**
 * 
 * ���ܣ����ڿͻ��˵�½���������������ͨѶ����
 * 
 */
package com.qq.client.model;

import java.io.*;
import java.net.*;

import com.qq.client.manager.ManageClientConServerThread;
import com.qq.common.*;

public class ClientLoginOpe {

	private Socket socket;
	
	//�û���¼
	public Message userLoginIn(User user){

		Message message = null;
		//����Socket�ͷ��������ӣ�����������9999�˿�
		try {
			socket = new Socket("127.0.0.1",9999);
			//�����û���Ϣ
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(user);
			//���շ��������ص���֤���
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			message = (Message)ois.readObject();
			if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
				//�Ϸ��û�
				//�����ͷ��������ӵ��̲߳�����
				ClientConServerThread ccst = new ClientConServerThread(socket,user.getUserId());
				ccst.start();
				//���̼߳��뵽����
				ManageClientConServerThread.addClientConServerThread(user.getUserId(), ccst);
			} else {
				//�û����Ϸ�(�������û���������󣬻����ظ���¼�����ﲻ��Ҫ����������login����)
				socket.close();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	//�û����ߣ���Ӧ�ý���ClientConServerThread��
//	public void userLoginOut(String userId){
//		//�����������������Ϣ
//		Message message = new Message(MessageType.USER_LOGINOUT);
//		//���̹߳�������ȡ���̣߳�����ȡ��socket���������ݵ�������
//		Socket userSocket = ManageClientConServerThread.getClientConServerThread(userId).getSocket();
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(userSocket.getOutputStream());
//			oos.writeObject(message);			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}		
//	}

}
