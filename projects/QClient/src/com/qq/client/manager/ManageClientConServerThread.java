/**
 * 
 * ���ܣ�����ͻ���������������̵߳���
 * 
 */
package com.qq.client.manager;

import java.util.HashMap;
import com.qq.client.model.ClientConServerThread;

public class ManageClientConServerThread {

	public static HashMap<String,ClientConServerThread> hm = new HashMap<String,ClientConServerThread>();
	//����߳�
	public static void addClientConServerThread(String userId,ClientConServerThread ccst){
		hm.put(userId,ccst);		
	}
	//ȡ���߳�
	public static ClientConServerThread getClientConServerThread(String userId){
		return hm.get(userId);
	}

}
