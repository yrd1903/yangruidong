/**
 * 这是与好友聊天的界面
 * 因为客户端要处于读取的状态，因此把它做成一个线程
 */
package com.qq.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.qq.client.manager.*;
import com.qq.common.*;

public class ClientChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	
	//定义登陆者id和聊天者id
	String ownerId;
	String friendId;
	
	//调试界面用主函数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientChat clientChat=new ClientChat("1","3");
	}

	public ClientChat(String ownerId,String friend){
		
		this.ownerId = ownerId;
		this.friendId = friend;
		
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp=new JPanel();
		
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp, "South");
		this.setTitle(this.ownerId+" 正在和  "+this.friendId+" 聊天");
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());
		this.setSize(300,200);
		this.setVisible(true);		
	}

	//显示消息
	public void showMessage(Message m){
		String info = m.getSender()+" 对  "+m.getGetter()+"说："+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			//如果用户点击了发送按钮
			Message m = new Message();
			m.setMesType(MessageType.message_com_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			
			/****界面发送消息调用线程管理，线程收到消息调用界面管理****/
			//发送给服务器
			try {
				ObjectOutputStream oos = new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(this.ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
	}
}
