/**
 * 
 * 功能：聊天界面管理类，管理与各个好友聊天的界面对象
 * 
 */
package com.qq.client.manager;

import java.util.HashMap;
import com.qq.client.view.ClientChat;

public class ManageClientChat {

	public static HashMap<String, ClientChat> hm = new HashMap<String, ClientChat>();
	//添加聊天界面
	public static void addClientChat(String friendId,ClientChat chat){
		hm.put(friendId, chat);
	}
	//取得聊天界面
	public static ClientChat getClientChat(String friendId){
		return hm.get(friendId);
	}	
}
