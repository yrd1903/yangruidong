/**
 * 
 * 作者：杨瑞东
 * 功能：服务器主界面控制器
 * 日期：2016/1/11
 * 
 */
package com.qq.server.controller;

import java.awt.event.*;
import com.qq.server.model.*;

public class ServerFrameController implements ActionListener{

		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			//点击了启动服务器
			//创建服务器主线程
			ServerMain serverMain = new ServerMain();
			serverMain.start();
		} else if(e.getActionCommand().equals("close")){
			//点击了关闭服务器
			
		}	
	}

}
