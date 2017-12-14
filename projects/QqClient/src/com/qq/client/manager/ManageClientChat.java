/**
 * 这是一个管理用户聊天界面的类
 */
package com.qq.client.manager;

import java.util.*;
import com.qq.client.view.*;
public class ManageClientChat {

	private static HashMap hm = new HashMap<String, ClientChat>();
	
	//加入
	public static void addClientChat(String loginIdAndFriendId,ClientChat qqChat){
		hm.put(loginIdAndFriendId, qqChat);
	}
	//取出
	public static ClientChat getClientChat(String loginIdAndFriendId){
		return (ClientChat)hm.get(loginIdAndFriendId);
	}
}
