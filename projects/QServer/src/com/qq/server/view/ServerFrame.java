/**
 * 
 * 作者：杨瑞东
 * 功能：服务器主界面
 * 日期：2016/1/11
 * 
 */
package com.qq.server.view;

import javax.swing.*;

import java.awt.event.*;

import com.qq.server.model.*;
import com.qq.server.util.MyLog;

public class ServerFrame extends JFrame implements ActionListener{

	JPanel jp;
	JButton jb1,jb2;
	public static void main(String[] args) {		
		ServerFrame serverFrame = new ServerFrame();
	}
	
	public ServerFrame(){
		jp = new JPanel();
		jb1 = new JButton("启动服务器");
		jb1.addActionListener(this);
		jb1.setActionCommand("start");
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jb2.setActionCommand("close");
		
		jp.add(jb1);
		jp.add(jb2);
		this.add(jp);
		
		this.setTitle("QQ服务器");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocation(400,200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			//点击了启动服务器
			//创建服务器主线程
			ServerMain serverMain = new ServerMain();
			serverMain.start();
		} else if(e.getActionCommand().equals("close")){
			//点击了关闭服务器
			MyLog.printLog("服务器关闭");
			System.exit(0);
		}	
	}
}
