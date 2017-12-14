/**
 * �ҵĺ����б�Ҳ����İ���˺ͺ�����
 */
package com.qq.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.qq.client.manager.*;
import com.qq.common.*;

public class ClientFriendList extends JFrame implements ActionListener,MouseListener{
	//��һ�ſ�Ƭ
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	//�ڶ��ſ�Ƭ
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	//������JFrame���ó�CardLayout
	CardLayout c1;
	//��¼��id
	String owner;
	
	//���Խ�����������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientFriendList qqFriendList=new ClientFriendList("1");
	}

	public ClientFriendList(String ownerId){
		
		this.owner = ownerId;
		//�����һ�ſ�Ƭ(��ʾ�����б�)		
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("������");
		
		jphy1=new JPanel(new BorderLayout());
		//�ٶ���50������
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//��jphy2��ʼ��50������
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
		
		//��������ť���뵽jphy3��
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		//�Ѻ����б��panel�ŵ�JScrollPane
		jsp1=new JScrollPane(jphy2);
	
		//��jphy1��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//����ڶ��ſ�Ƭ��İ���ˣ�
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
		
		jpmsr1=new JPanel(new BorderLayout());
		//�ٶ���20��İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//��jpmsr2��ʼ��20��İ����
		JLabel []jbls2=new JLabel[20];
		for(int i=0;i<jbls2.length;i++){
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		
		//��������ť���뵽jpmsr3��
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		//��İ�����б��panel�ŵ�JScrollPane
		jsp2=new JScrollPane(jpmsr2);
	
		//��jpmsr1��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		c1=new CardLayout();
		this.setLayout(c1);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		//�ڴ�����ʾ�Լ��ı��
		this.setTitle(owner);
		this.setSize(220,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//�������ߺ������
	public void updateFriend(Message m){
		String[] onLineFriend = m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++){
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����ڵ�һ����Ƭ�����İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if(e.getSource()==jphy_jb2){
			c1.show(this.getContentPane(),"2");
		}else if(e.getSource()==jpmsr_jb1){
			c1.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫�����¼������õ����ѵı��
		//˫��ʱ���������
		if(e.getClickCount()==2)
		{
			//�õ����ѵı��
			String friendNo=((JLabel)e.getSource()).getText();
			//System.out.println("��ϣ����  "+friendNo+"  ����");
			ClientChat chat = new ClientChat(this.owner,friendNo);
			//�����������뵽������
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
