package com.qq.server.manager;

import java.util.*;
import com.qq.server.model.*;

public class ManageServerConClientThread {

	
	public static HashMap hm = new HashMap<String, ServerConClientThread>();
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static void addServerConClienThread(String uid,ServerConClientThread ct){
		hm.put(uid, ct);
	}
	public static ServerConClientThread getServerConClientThread(String uid){
		return (ServerConClientThread)hm.get(uid);
	}
	
	//���ص�ǰ������
	public static String getAllOnLineUserId(){
		//ʹ�õ��������
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()){
			res += it.next().toString()+" ";
		}
		return res;
	}
}
