/**
 * 
 * ���ߣ�����
 * ���ܣ������������������
 * ���ڣ�2016/1/11
 * 
 */
package com.qq.server.controller;

import java.awt.event.*;
import com.qq.server.model.*;

public class ServerFrameController implements ActionListener{

		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			//���������������
			//�������������߳�
			ServerMain serverMain = new ServerMain();
			serverMain.start();
		} else if(e.getActionCommand().equals("close")){
			//����˹رշ�����
			
		}	
	}

}
