/**
 * 
 * ���ܣ������������࣬�����������������Ľ������
 * 
 */
package com.qq.client.manager;

import java.util.HashMap;
import com.qq.client.view.ClientChat;

public class ManageClientChat {

	public static HashMap<String, ClientChat> hm = new HashMap<String, ClientChat>();
	//����������
	public static void addClientChat(String friendId,ClientChat chat){
		hm.put(friendId, chat);
	}
	//ȡ���������
	public static ClientChat getClientChat(String friendId){
		return hm.get(friendId);
	}	
}
