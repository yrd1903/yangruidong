/**
 * 
 * 作者：杨瑞东
 * 功能：客户端登陆界面
 * 日期：2016/1/11
 * 修复：登录格式验证；给不在线好友发送消息服务器异常；
 * 
 */
package com.qq.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.qq.common.*;
import com.qq.client.manager.ManageClientFriendList;
import com.qq.client.model.*;

public class ClientLogin extends JFrame implements ActionListener,KeyListener{

	ImageIcon title;
	Font login,lable;
	JPanel jp,jp_1;
	JLabel jla1,jla2,jla3,jla4,jla5,jla6_1,jla6_2,jla6_3,jla6_4;
	JTextField jtf;
	JPasswordField jpf;
	JCheckBox jcb1,jcb2;
	JButton jb;
	
	public static void main(String[] args) {		
		new ClientLogin();
	}
	
	public ClientLogin(){
		//需要的字体
		login = new Font(Font.DIALOG,Font.BOLD,18);
		lable = new Font(Font.DIALOG,Font.BOLD,14);
		//北部图片
		title = new ImageIcon("images/login_title.png");
		jla1 = new JLabel(title);
		//中部登录部分
		jp = new JPanel(new GridLayout(4,3));		
		jla2 = new JLabel("账号",JLabel.CENTER);
		jla2.setFont(lable);
		jtf = new JTextField();
		jla3 = new JLabel("注册账号",JLabel.CENTER);
		jla3.setFont(lable);
		jla3.setForeground(Color.BLUE);
		jla4 = new JLabel("密码",JLabel.CENTER);
		jla4.setFont(lable);
		jpf = new JPasswordField();
		jpf.addKeyListener(this);
		jla5 = new JLabel("找回密码",JLabel.CENTER);
		jla5.setFont(lable);
		jla5.setForeground(Color.BLUE);
		jla6_1 = new JLabel();
		jp_1 = new JPanel(new GridLayout(1,2));
		jcb1 = new JCheckBox("记住密码");
		jcb2 = new JCheckBox("自动登录");
		jla6_2 = new JLabel();
		jla6_3 = new JLabel();
		jb = new JButton("登录");
		jb.addActionListener(this);
		jb.setActionCommand("login");
		Font login = new Font(Font.DIALOG,Font.BOLD,18);
		jb.setFont(login);
		jla6_4 = new JLabel();
		
		//组件添加
		jp_1.add(jcb1);
		jp_1.add(jcb2);
		
		jp.add(jla2);
		jp.add(jtf);
		jp.add(jla3);
		jp.add(jla4);
		jp.add(jpf);
		jp.add(jla5);
		jp.add(jla6_1);
		jp.add(jp_1);
		jp.add(jla6_2);
		jp.add(jla6_3);
		jp.add(jb);
		jp.add(jla6_4);
		
		this.add(jla1,BorderLayout.NORTH);
		this.add(jp,BorderLayout.CENTER);
		
		this.addKeyListener(this);
		//JFrame、JComponent、JLabel默认是不可以获得焦点
		//而且默认情况下，swing是把焦点分配给容器内可以获得焦点的第一个组件
		//因此，要设置JFrame获得焦点
		//密码框也加入keylistener
		this.setFocusable(true);
		this.setSize(430,320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(400, 200);
		this.setVisible(true);
	}
	
	
	
	
	//监听部分
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("login")){
			//用户点击了登录按钮
			this.sendLoginInfo();
		}
	}

	//键盘监听
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Enter键的KeyCode是10
		if(e.getKeyCode()==10){
			this.sendLoginInfo();
		}
	}
	
	public void sendLoginInfo(){
		//把用户登录信息发送到服务器
		String userId = this.jtf.getText().trim();
		String passWd = new String(this.jpf.getPassword());
		//账号密码格式验证
		if("".equals(userId)){
			JOptionPane.showMessageDialog(this,"账号不能为空！");
			return;
		}
		if(this.isNumeric(userId)==false){
			JOptionPane.showMessageDialog(this,"账号格式错误！");
			return;
		}
		if("".equals(passWd)){
			JOptionPane.showMessageDialog(this,"密码不能为空！");
			return;
		}
		User user = new User(userId,passWd);
		ClientLoginOpe loginOpe = new ClientLoginOpe();
		//接收用户登录返回的信息
		Message message = loginOpe.userLoginIn(user);
		if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
			this.dispose();
			//取出用户昵称
			String userName = message.getUserName();
			//取出在线好友和不在线好友
			ArrayList<User> onLineFriends = message.getOnLineFriends();
			ArrayList<User> offLineFriends = message.getOffLineFriends();
			//创建主界面
			ClientFriendList friendList = new ClientFriendList(userId,userName,onLineFriends,offLineFriends);
			//加入ClientFriendList管理
			ManageClientFriendList.addClientFriendList(userId, friendList);
		} else if(message.getMesType().equals(MessageType.MESSAGE_REPEAT_LOGIN)){
			JOptionPane.showMessageDialog(this,"用户已经在线！");
		} else {
			//返回MessageType.MESSAGE_LOGIN_FAIL
			JOptionPane.showMessageDialog(this,"用户名密码错误！");
		}
	}
	
	//验证字符串是否为数字，要求str参数不能为空
	public boolean isNumeric(String str){
		for(int i=0;i<str.length();i++){
			char tem = str.charAt(i);
			if(!Character.isDigit(tem)){
				return false;
			}
		}
		return true;
	}

}
