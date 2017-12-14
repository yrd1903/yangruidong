/**
 * 这是一个管理客户端和服务器保持通讯的线程类
 */
package com.qq.client.manager;
import java.util.*;

import com.qq.client.model.ClientConServerThread;
public class ManageClientConServerThread {

	private static HashMap hm = new HashMap<String, ClientConServerThread>();
	
	//把创建好的线程放入到hm中
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst){
		hm.put(qqId, ccst);
	}
	//通过qqId取得线程
	public static ClientConServerThread getClientConServerThread(String qqId){
		return (ClientConServerThread)hm.get(qqId);
	}

}
