/**
 * 功能：QQ客户端登陆界面
 */
package com.qq.client.view;

import javax.swing.*;

import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.qq.client.manager.*;
import com.qq.client.model.*;

public class ClientLogin extends JFrame implements ActionListener{

	//定义北边需要的组件
	JLabel jlb1;
	//定义中部需要的组件
	//中部有三个JPanel,有一个选项卡窗口管理
	JTabbedPane jtp;
	//jp3  jp4 没有写
	JPanel jp2,jp3,jp4;
	JLabel jp2_jlb1,jp2_jlb2,jp2_jlb3,jp2_jlb4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientLogin qqClientLogin=new ClientLogin();
	}
	
	public ClientLogin()
	{
		//处理北部
		jlb1=new JLabel(new ImageIcon("images/tou.gif"));
		
		//处理中部
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jlb1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jlb2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jlb3=new JLabel("忘记密码",JLabel.CENTER);
		//设置”忘记密码“前景色为蓝色
		jp2_jlb3.setForeground(Color.blue);
		jp2_jlb4=new JLabel("申请密码保护",JLabel.CENTER);
		jp1_jb1=new JButton(new ImageIcon("images/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登陆");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp2.add(jp2_jlb1);
		jp2.add(jp2_jtf);
		jp2.add(jp1_jb1);
		jp2.add(jp2_jlb2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jlb3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jlb4);
		
		//创建选项卡窗口
		jtp=new JTabbedPane();
		jp3=new JPanel();
		jp4=new JPanel();
		jtp.add("QQ号码",jp2);
		jtp.add("手机号码",jp3);
		jtp.add("电子邮件",jp4);
		
		//处理南部
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("images/denglu.gif"));
		//响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("images/xiangdao.gif"));
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jlb1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(e.getSource()==jp1_jb1)
		{
			ClientUser clientUser=new ClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(clientUser.checkUserAndConnect(u)){
								
				try {
					//先创建好友列表
					ClientFriendList clientFriendList = new ClientFriendList(u.getUserId());
					//把clientFriendList加入到管理
					ManageClientFriendList.addClientFriendList(u.getUserId(), clientFriendList);
					
					//发送一个要求返回在线好友的包
					ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					//做一个message包
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//指明得到哪个号码的好友列表
					m.setSender(u.getUserId());
					oos.writeObject(m);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				//关闭登录界面
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"用户名密码错误");
			}
		}
	}

}
