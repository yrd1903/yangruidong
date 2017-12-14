package com.qq.server.model;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.*;
import com.qq.server.dao.UserAccountDao;
import com.qq.server.manager.ManageServerConClientThread;
import com.qq.server.util.MyLog;

public class ServerConClientThread extends Thread{

	//该线程的socket
	private Socket socket;
	//该线程与客户端通讯的客户端id
	private String userId;
	//线程标志位
	private boolean flag = true;
	//UserAccountDao
	UserAccountDao userAccountDao;
		
	public ServerConClientThread(Socket socket,String userId){
		this.socket = socket;
		this.userId = userId;
		userAccountDao = new UserAccountDao();
	}
	
	public void stopThread(){
		this.flag = false;
	}
	
	public void run(){
		while(flag){
			try {
				//循环读取从客户端发来的消息
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				//显示
				//System.out.println(message.getSender()+" 对  "+message.getGetter()+" 说："+message.getCon());
				if(message.getMesType().equals(MessageType.MESSAGE_COM_MES)){
					//普通的消息发送包，转发给要发送到的好友
					String getter = message.getGetter();
					//先判断该好友是否在线,只有在线才转发
					if(ManageServerConClientThread.getServerConClientThread(getter)!=null){
						Socket getterSocket = ManageServerConClientThread.getServerConClientThread(getter).getSocket();
						ObjectOutputStream oos = new ObjectOutputStream(getterSocket.getOutputStream());
						oos.writeObject(message);
						//打印日志
						MyLog.printLog("用户"+message.getSender()+" 给  用户"+message.getGetter()+"发送消息，内容："+message.getCon());
					} else {
						//不在线
						System.out.println("该好友不在线！");
						//打印日志
						MyLog.printLog("用户"+message.getSender()+" 给 用户"+message.getGetter()+"发送消息,用户"+message.getGetter()+"不在线");
					}					
				} 							
			} catch (Exception e) {
				e.printStackTrace();
				//发生异常，一般是与客户端连接中断。可能是用户退出，客户端断网，ois.readObject()出错
				System.out.println("发生异常，线程关闭，用户下线");
				MyLog.printLog("发生异常，线程关闭，用户下线");
				//服务器中断与用户的线程，用户下线
				this.userLoginOut();
				//查询目前最新用户列表，并且通知所有与客户端连接的线程返回最新用户列表
				//查询好友列表（默认所有人之间都是好友）
				ArrayList<User> onLineFriends = userAccountDao.getOnLineFriends();
				ArrayList<User> offLineFriends = userAccountDao.getOffLineFriends();
				this.notifyThreadReturnList(userId, onLineFriends, offLineFriends);
				//关闭此线程
				this.stopThread();
			}
		}
	}
	
	//该线程对应的用户下线
	public void userLoginOut(){
		//得到管理用户通讯线程的HashMap
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		//把要下线的用户从HashMap中删除
		hm.remove(userId);
		//修改数据库is_online字段
		userAccountDao.userOffLine(userId);
	}
	
	//用户下线时，通知除了自己外所有在线的，与客户端连接的线程，返回最新的用户列表	
	public void notifyThreadReturnList(String self,ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		HashMap<String, ServerConClientThread> hm = ManageServerConClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();	
		//it.next()执行了，hm中的指针才会向下移动，否则如果it.next()不执行，it.hasNext()永远为真
		while(it.hasNext()){
			String userId = it.next().toString();
			if(self.equals(userId)){
				return;
			}
			ServerConClientThread scct = ManageServerConClientThread.getServerConClientThread(userId);
			//让与客户端连接的线程返回好友列表
			scct.returnFriendListNow(onLineFriends, offLineFriends);
		}
	}
	
	//返回最新用户列表（包括好友是否在线信息）
	public void returnFriendListNow(ArrayList<User> onLineFriends,
			ArrayList<User> offLineFriends){
		
		//做返回的消息包
		Message message = new Message(MessageType.MESSAGE_RET_FRIENDLIST);
		message.setOnLineFriends(onLineFriends);
		message.setOffLineFriends(offLineFriends);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			oos.writeObject(message);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
