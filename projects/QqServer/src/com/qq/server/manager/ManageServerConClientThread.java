package com.qq.server.manager;

import java.util.*;
import com.qq.server.model.*;

public class ManageServerConClientThread {

	
	public static HashMap hm = new HashMap<String, ServerConClientThread>();
	//向hm中添加一个客户端通讯线程
	public static void addServerConClienThread(String uid,ServerConClientThread ct){
		hm.put(uid, ct);
	}
	public static ServerConClientThread getServerConClientThread(String uid){
		return (ServerConClientThread)hm.get(uid);
	}
	
	//返回当前在线人
	public static String getAllOnLineUserId(){
		//使用迭代器完成
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()){
			res += it.next().toString()+" ";
		}
		return res;
	}
}
