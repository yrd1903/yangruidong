/**
 * 
 * 功能：管理客户端与服务器连接线程的类
 * 
 */
package com.qq.client.manager;

import java.util.HashMap;
import com.qq.client.model.ClientConServerThread;

public class ManageClientConServerThread {

	public static HashMap<String,ClientConServerThread> hm = new HashMap<String,ClientConServerThread>();
	//添加线程
	public static void addClientConServerThread(String userId,ClientConServerThread ccst){
		hm.put(userId,ccst);		
	}
	//取出线程
	public static ClientConServerThread getClientConServerThread(String userId){
		return hm.get(userId);
	}

}
