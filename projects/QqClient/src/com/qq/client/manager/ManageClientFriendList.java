/**
 *������ѣ���������İ���˽��� ��
 */
package com.qq.client.manager;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;

public class ManageClientFriendList {              

	private static HashMap hm = new HashMap<String, ClientFriendList>();
	
	public static void addClientFriendList(String qqId,ClientFriendList clientFriendList){
		hm.put(qqId, clientFriendList);
	}
	public static ClientFriendList getClientFriendList(String qqId){
		return (ClientFriendList)hm.get(qqId);
	}
}
