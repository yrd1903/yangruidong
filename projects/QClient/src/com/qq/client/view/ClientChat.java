package com.qq.client.view;

import java.awt.*;
import javax.swing.*;
import com.qq.client.manager.*;
import com.qq.common.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ClientChat extends JFrame implements MouseListener,ActionListener,KeyListener,WindowListener{

	ImageIcon audio,video,file,chatRight,close,send,menu;
	Color border;
	Font content;
	String userId,friendId,userName,friendName;
	JLabel jla1,jla2,jla3,jla4,jla5;
	JTextArea jta1,jta2;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	JButton jb1,jb2;
	JScrollPane jsp1,jsp2;
	
	public static void main(String[] args) {
		
		//new ClientChat("1001","1002");
	}
	
	public ClientChat(String userId,String friendId,String userName,String friendName){
		this.userId = userId;
		this.friendId = friendId;
		this.userName = userName;
		this.friendName = friendName;
		//所需图片
		audio = new ImageIcon("images/audio.png");
		video = new ImageIcon("images/video.png");
		file = new ImageIcon("images/file.png");
		chatRight = new ImageIcon("images/chat_right.png");
		close = new ImageIcon("images/close.png");
		send = new ImageIcon("images/send.png");
		menu = new ImageIcon("images/menu.png");
		//聊天字体
		content = new Font("微软雅黑",Font.BOLD,14);
		//聊天窗口头部背景颜色
		border = new Color(238,238,238);
		//北部功能
		jp5 = new JPanel();		
		jla1 = new JLabel(audio);
		jla2 = new JLabel(video);
		jla3 = new JLabel(file);
		jla1.addMouseListener(this);
		jla2.addMouseListener(this);
		jla3.addMouseListener(this);
		
		//左下内容
		jta1 = new JTextArea();	
		jta1.setEditable(false);
		jta1.setLineWrap(true);
		jta1.setWrapStyleWord(true);
		jta1.setFont(content);
		jsp1 = new JScrollPane(jta1);
		jla4 = new JLabel(menu,JLabel.LEFT);
		jta2 = new JTextArea(5,20);
		jta2.setLineWrap(true);
		jta2.setWrapStyleWord(true);
		jta2.setFont(content);
		jta2.addKeyListener(this);
		jsp2 = new JScrollPane(jta2);
		jp6 = new JPanel();
		jb1 = new JButton(close);
		jb2 = new JButton(send);
		jb1.addActionListener(this);
		jb1.setActionCommand("close");
		jb2.addActionListener(this);
		jb2.setActionCommand("send");
		//右下图片
		jla5 = new JLabel(chatRight);
		
		jp1 = new JPanel(new BorderLayout());
		jp2 = new JPanel(new BorderLayout());
		jp3 = new JPanel(new BorderLayout());
		jp4 = new JPanel(new BorderLayout());
		
		//添加组件
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp5.add(jla1);
		jp5.add(jla2);
		jp5.add(jla3);
		
		jp6.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp6.add(jb1);
		jp6.add(jb2);
		
		jp4.add(jla4,BorderLayout.NORTH);
		jp4.add(jsp2,BorderLayout.CENTER);
		jp4.add(jp6,BorderLayout.SOUTH);
		
		jp3.add(jsp1,BorderLayout.CENTER);
		jp3.add(jp4,BorderLayout.SOUTH);
		
		jp2.add(jp3,BorderLayout.CENTER);
		jp2.add(jla5,BorderLayout.EAST);
		
		jp1.add(jp5,BorderLayout.NORTH);
		jp1.add(jp2,BorderLayout.CENTER);
		
		this.add(jp1);
		this.addWindowListener(this);
		
		//不设置DefaultCloseOperation，否则会关闭 所有窗口
		this.setTitle(userName+" 和  "+friendName+" 聊天中");
		this.setSize(600,550);
		this.setLocation(300, 100);
		this.setResizable(false);
		this.setVisible(true);	
		
	}
	
	//收到消息时显示消息
	public void showMessage(Message message){
		String con = message.getCon();
		String sender = message.getSender();
		//String time = message.getSendTime();
		//String info = "用户"+sender+" "+time+"\r\n"+con+"\r\n";
		String info = "["+friendName+"]:  "+con+"\r\n";
		this.jta1.append(info);
	}

	//发送消息函数
	public void sendMessage(){
		//取得要发送的内容
		String con = this.jta2.getText();
		//判断内容是否为空
		if("".equals(con)){
			return;
		}
		//清除发送框
		this.jta2.setText("");
		//把自己的消息写到消息区
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowTime = dateFormat.format(new Date());
		//this.jta1.append("用户"+userId+" "+nowTime+"\r\n"+con+"\r\n");
		this.jta1.append("["+userName+"]:  "+con+"\r\n");
		//创建发送对象
		Message message = new Message(MessageType.MESSAGE_COM_MES);
		message.setSender(this.userId);
		message.setGetter(this.friendId);
		message.setSenderName(userName);
		message.setGetterName(friendName);
		message.setCon(con);		
		message.setSendTime(nowTime);
		//从线程管理类中取出线程，进而取出socket，发送数据到服务器
		Socket userSocket = ManageClientConServerThread.getClientConServerThread(userId).getSocket();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(userSocket.getOutputStream());
			oos.writeObject(message);			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	//动作监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("close")){
			//点击了关闭按钮
			this.dispose();			
		} else if(e.getActionCommand().equals("send")){
			//点击了发送按钮			
			this.sendMessage();
		}
		
	}
	
	//鼠标监听
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
		jLabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource();
		jLabel.setBorder(BorderFactory.createLineBorder(border));
	}

	//键盘监听
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ENTER) && (e.isControlDown())){
			//按下了ctrl+anter
			this.sendMessage();
		}			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	//windows监听
	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		//关闭窗口
		//从ManageClientChat的hm中把此窗口对象删除
		HashMap<String, ClientChat> hm = ManageClientChat.hm;
		hm.remove(friendId);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	


}
