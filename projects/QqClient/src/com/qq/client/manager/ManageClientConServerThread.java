/**
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳���
 */
package com.qq.client.manager;
import java.util.*;

import com.qq.client.model.ClientConServerThread;
public class ManageClientConServerThread {

	private static HashMap hm = new HashMap<String, ClientConServerThread>();
	
	//�Ѵ����õ��̷߳��뵽hm��
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst){
		hm.put(qqId, ccst);
	}
	//ͨ��qqIdȡ���߳�
	public static ClientConServerThread getClientConServerThread(String qqId){
		return (ClientConServerThread)hm.get(qqId);
	}

}
