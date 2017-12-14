package com.qq.client.manager;

import java.util.HashMap;

import com.qq.client.view.ClientFriendList;

public class ManageClientFriendList {

	public static HashMap<String, ClientFriendList> hm = new HashMap<String, ClientFriendList>();
	//加入主界面
	public static void addClientFriendList(String userId,ClientFriendList friendList){
		hm.put(userId, friendList);
	}
	//得到主界面
	public static ClientFriendList getClientFriendList(String userId){
		return hm.get(userId);
	}
	
}
