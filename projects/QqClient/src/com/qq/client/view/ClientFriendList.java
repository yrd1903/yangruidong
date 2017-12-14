/**
 * 我的好友列表，也包括陌生人和黑名单
 */
package com.qq.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.qq.client.manager.*;
import com.qq.common.*;

public class ClientFriendList extends JFrame implements ActionListener,MouseListener{
	//第一张卡片
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	//第二张卡片
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	//把整个JFrame设置成CardLayout
	CardLayout c1;
	//登录这id
	String owner;
	
	//调试界面用主函数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientFriendList qqFriendList=new ClientFriendList("1");
	}

	public ClientFriendList(String ownerId){
		
		this.owner = ownerId;
		//处理第一张卡片(显示好友列表)		
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		
		jphy1=new JPanel(new BorderLayout());
		//假定有50个好友
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//给jphy2初始化50个好友
		jbls=new JLabel[50];
		for(int i=0;i<jbls.length;i++){
			jbls[i]=new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		
		//把两个按钮加入到jphy3中
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		//把好友列表的panel放到JScrollPane
		jsp1=new JScrollPane(jphy2);
	
		//对jphy1初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//处理第二张卡片（陌生人）
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		
		jpmsr1=new JPanel(new BorderLayout());
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//给jpmsr2初始化20个陌生人
		JLabel []jbls2=new JLabel[20];
		for(int i=0;i<jbls2.length;i++){
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		
		//把两个按钮加入到jpmsr3中
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		//把陌生人列表的panel放到JScrollPane
		jsp2=new JScrollPane(jpmsr2);
	
		//对jpmsr1初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		c1=new CardLayout();
		this.setLayout(c1);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		//在窗口显示自己的编号
		this.setTitle(owner);
		this.setSize(220,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//更新在线好友情况
	public void updateFriend(Message m){
		String[] onLineFriend = m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++){
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果在第一个卡片点击了陌生人按钮，就显示第二张卡片
		if(e.getSource()==jphy_jb2){
			c1.show(this.getContentPane(),"2");
		}else if(e.getSource()==jpmsr_jb1){
			c1.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//响应用户双击的事件，并得到好友的编号
		//双击时候进入聊天
		if(e.getClickCount()==2)
		{
			//得到好友的编号
			String friendNo=((JLabel)e.getSource()).getText();
			//System.out.println("你希望和  "+friendNo+"  聊天");
			ClientChat chat = new ClientChat(this.owner,friendNo);
			//把聊天界面加入到管理类
			ManageClientChat.addClientChat(this.owner+" "+friendNo, chat);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setForeground(Color.BLACK);
	}
	
	
	
}
