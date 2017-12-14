/**
 * �������������Ľ���
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ��״̬����˰�������һ���߳�
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
	
	//�����½��id��������id
	String ownerId;
	String friendId;
	
	//���Խ�����������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientChat clientChat=new ClientChat("1","3");
	}

	public ClientChat(String ownerId,String friend){
		
		this.ownerId = ownerId;
		this.friendId = friend;
		
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp=new JPanel();
		
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp, "South");
		this.setTitle(this.ownerId+" ���ں�  "+this.friendId+" ����");
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());
		this.setSize(300,200);
		this.setVisible(true);		
	}

	//��ʾ��Ϣ
	public void showMessage(Message m){
		String info = m.getSender()+" ��  "+m.getGetter()+"˵��"+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			//����û�����˷��Ͱ�ť
			Message m = new Message();
			m.setMesType(MessageType.message_com_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			
			/****���淢����Ϣ�����̹߳����߳��յ���Ϣ���ý������****/
			//���͸�������
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
