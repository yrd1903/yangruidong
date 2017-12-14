package com.qq.client.controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//控制器包含发送消息的线程，发送消息线程包含界面
public class ClientFriendListController implements MouseListener{

	Color friendList = new Color(255,222,173);
	Color background = new Color(238,238,238);
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource();
		jLabel.setBackground(friendList);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource();
		jLabel.setBackground(background);
	}

}
