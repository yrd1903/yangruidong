package com.qq.server.manager;

import java.util.HashMap;
import com.qq.server.model.ServerConClientThread;

public class ManageServerConClientThread {

	public static HashMap<String,ServerConClientThread> hm = new HashMap<String,ServerConClientThread>();
	//�����߳�
	public static void addServerConClientThread(String userId,ServerConClientThread scct){
		hm.put(userId, scct);
	}
	//ȡ���߳�
	public static ServerConClientThread getServerConClientThread(String userId){
		return hm.get(userId);
	}
	
}
