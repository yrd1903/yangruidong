/**
 * ����һ�������û�����������
 */
package com.qq.client.manager;

import java.util.*;
import com.qq.client.view.*;
public class ManageClientChat {

	private static HashMap hm = new HashMap<String, ClientChat>();
	
	//����
	public static void addClientChat(String loginIdAndFriendId,ClientChat qqChat){
		hm.put(loginIdAndFriendId, qqChat);
	}
	//ȡ��
	public static ClientChat getClientChat(String loginIdAndFriendId){
		return (ClientChat)hm.get(loginIdAndFriendId);
	}
}
