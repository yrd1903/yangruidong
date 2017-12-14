package com.qq.server.model;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.qq.common.*;
import com.qq.server.dao.UserAccountDao;
import com.qq.server.manager.ManageServerConClientThread;

public class ServerMain extends Thread{

	public void run(){
		try {
			ServerSocket sc = new ServerSocket(9999);
			UserAccountDao userAccountDao = new UserAccountDao();
			System.out.println("服务器启动，开始在9999端口监听。。。");
			while(true){
				//循环监听是否有客户端登陆
				Socket socket = sc.accept();
				//只要有socket连接，就读取登陆信息
				ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream());
				User user = (User)ois.readObject();
				//验证登陆信息
				//boolean legal = checkUser(user);
				boolean legal = userAccountDao.checkUser(user);
				//给客户端返回信息，并处理连接
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());				
				if(legal){
					//用户合法
					//判断用户是否已经在线
					boolean isOnLine = isOnLine(user);
					if(isOnLine){
						//在线，返回重复登录信息
						Message message = new Message(MessageType.MESSAGE_REPEAT_LOGIN);
						oos.writeObject(message);						
					} else {
						//没有在线，正常登陆
						
						//创建单独线程和该客户端通讯并启动线程
						ServerConClientThread scct = new ServerConClientThread(socket,user.getUserId());
						scct.start();
						//把和客户端通讯的线程加入管理
						ManageServerConClientThread.addServerConClientThread(user.getUserId(), scct);
						//修改数据库is_online字段
						userAccountDao.userOnLine(user.getUserId());
						
						//返回登陆成功信息
						Message message = new Message(MessageType.MESSAGE_LOGIN_SUCCESS);
						//查询成功登陆用户的昵称
						String userName = userAccountDao.getUserName(user);
						message.setUserName(userName);
						//查询好友列表（默认所有人之间都是好友）
						ArrayList<User> onLineFriends = userAccountDao.getOnLineFriends();
						ArrayList<User> offLineFriends = userAccountDao.getOffLineFriends();
						message.setOnLineFriends(onLineFriends);
						message.setOffLineFriends(offLineFriends);						
						oos.writeObject(message);
						
						//服务器通知所有与客户端连接的线程返回目前的用户列表
						//用户下线也应该重新返回好友列表************************
						this.notifyThreadReturnList(user.getUserId(),onLineFriends, offLineFriends);						
					} 					
					
				} else {
					//用户非法
					//返回登陆失败信息，关掉连接
					Message message = new Message(MessageType.MESSAGE_LOGIN_FAIL);
					oos.writeObject(message);
					socket.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//检查用户是否已经在线
	public boolean isOnLine(User user){
		boolean res = false;
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		if(hm.containsKey(user.getUserId())){
			res = true;
		}
		return res;
	}
	
	//用户上线时，通知除了自己外所有在线的，与客户端连接的线程，返回最新的用户列表
	//如果通知自己，客户端刚刚登陆，可能还没有创建ClientFriendList,刷新列表会产生空指针
	public void notifyThreadReturnList(String self,ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();	
		//it.next()执行了，hm中的指针才会向下移动，否则如果it.next()不执行，it.hasNext()永远为真
		while(it.hasNext()){
			String userId = it.next().toString();			
			if(self.equals(userId)){
				continue;
			}
			ServerConClientThread scct = ManageServerConClientThread.getServerConClientThread(userId);
			//让与客户端连接的线程返回好友列表
			scct.returnFriendListNow(onLineFriends, offLineFriends);
		}
	}
	
}
