package com.qq.client.view;

import javax.swing.*;

import com.qq.client.manager.ManageClientChat;
import com.qq.client.model.*;
import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientFriendList extends JFrame implements MouseListener,WindowListener{

	ImageIcon photo;
	ImageIcon blackPhoto;
	Font titleFont;
	Font friendFont;
	Color background;
	Color friendList;
	String userId;
	String userName;
	JLabel jla;
	JPanel jp;
	JScrollPane jsp;
	JLabel[] jlarr1,jlarr2;
	JLabel[] jlarr;
	
	//�����б�
	ArrayList<User> onLineFriends;
	ArrayList<User> offLineFriends;
	
	public static void main(String[] args) {

		//new ClientFriendList("1001","����");
	}
	
	public ClientFriendList(String userId,String userName,
			ArrayList<User> onLineFriends,ArrayList<User> offLineFriends){
		this.userId = userId;
		this.userName = userName;
		this.onLineFriends = onLineFriends;
		this.offLineFriends = offLineFriends;
		//������Ҫ��Ԫ��
		photo = new ImageIcon("images/friend.png");
		blackPhoto = new ImageIcon("images/friend_black.png");
		titleFont = new Font(Font.DIALOG,Font.BOLD,16);
		friendFont = new Font(Font.DIALOG,Font.BOLD,12);
		background = new Color(238,238,238);
		friendList = new Color(255,222,173);
		//�������
		jla = new JLabel(userName+"("+userId+")");
		jla.setFont(titleFont);
		//�ϲ����
		GridLayout gl = new GridLayout(50,1,5,5);
		jp = new JPanel(gl);
		//��ʼ��50������
//		jlarr = new JLabel[50];
//		for(int i=0;i<50;i++){
//			jlarr[i] = new JLabel("�û�"+(1001+i),photo,JLabel.LEFT);
//			jlarr[i].setOpaque(true);
//			jlarr[i].setFont(friendFont);
//			jlarr[i].setBackground(background);
//			jlarr[i].addMouseListener(this);
//			jp.add(jlarr[i]);
//		}
		
		
		//��ʼ������
		
//		int onLineSize = onLineFriends.size();
//		if(onLineSize>0){
//			jlarr1 = new JLabel[onLineSize];
//			for(int i=0;i<onLineSize;i++){
//				String friendName = onLineFriends.get(i).getUserName();
//				String friendId = onLineFriends.get(i).getUserId();
//				jlarr1[i] = new JLabel(friendName+"("+friendId+")",photo,JLabel.LEFT);
//				jlarr1[i].setOpaque(true);
//				jlarr1[i].setFont(friendFont);
//				jlarr1[i].setBackground(background);
//				jlarr1[i].addMouseListener(this);
//				jp.add(jlarr1[i]);			
//			}			
//		}		
//		int offLineSize = offLineFriends.size();
//		if(offLineSize>0){
//			jlarr2 = new JLabel[offLineSize];
//			for(int i=0;i<offLineSize;i++){
//				String friendName = offLineFriends.get(i).getUserName();
//				String friendId = offLineFriends.get(i).getUserId();
//				jlarr2[i] = new JLabel(friendName+"("+friendId+")",blackPhoto,JLabel.LEFT);
//				jlarr2[i].setOpaque(true);
//				jlarr2[i].setFont(friendFont);
//				jlarr2[i].setBackground(background);
//				jlarr2[i].addMouseListener(this);
//				jp.add(jlarr2[i]);			
//			}			
//		}
		
		
		int sizeOnLine = onLineFriends.size();
		int sizeOffLine = offLineFriends.size();		
		int sizeTotal = sizeOffLine+sizeOnLine;
		jlarr = new JLabel[sizeTotal];
		
		for(int i=0;i<sizeOnLine;i++){
			String friendName = onLineFriends.get(i).getUserName();
			String friendId = onLineFriends.get(i).getUserId();
			jlarr[i] = new JLabel(friendName+"("+friendId+")",photo,JLabel.LEFT);
			jlarr[i].setOpaque(true);
			jlarr[i].setFont(friendFont);
			jlarr[i].setBackground(background);
			jlarr[i].addMouseListener(this);
			jp.add(jlarr[i]);			
		}
		int index = 0;
		for(int i=sizeOnLine;i<sizeTotal;i++){
			String friendName = offLineFriends.get(index).getUserName();
			String friendId = offLineFriends.get(index).getUserId();
			index++;
			jlarr[i] = new JLabel(friendName+"("+friendId+")",blackPhoto,JLabel.LEFT);
			jlarr[i].setOpaque(true);
			jlarr[i].setFont(friendFont);
			jlarr[i].setBackground(background);
			jlarr[i].addMouseListener(this);
			jp.add(jlarr[i]);					
		}
		
		
		jsp = new JScrollPane(jp);
		//���ù����ٶ�
		JScrollBar jsb = jsp.getVerticalScrollBar();
		jsb.setUnitIncrement(40);
		
		this.add(jla,BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		this.addWindowListener(this);
		
		this.setTitle("QQ����");
		this.setSize(280,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(900, 80);
		this.setResizable(false);
		this.setVisible(true);		
	}
	
	//�����쳣������������ж�ʱ���˳�QQ
	public void showServerException(){
		JOptionPane.showMessageDialog(this,"�����쳣��QQ���رգ�");
		//�ر�QQ
		System.exit(0);
	}
	
	//���º����б�
	public void updateFriendList(ArrayList<User> onLineFriends,ArrayList<User> offLineFriends){

		int sizeOnLine = onLineFriends.size();
		int sizeOffLine = offLineFriends.size();		
		int sizeTotal = sizeOffLine+sizeOnLine;
		
		for(int i=0;i<sizeOnLine;i++){
			String friendName = onLineFriends.get(i).getUserName();
			String friendId = onLineFriends.get(i).getUserId();
			jlarr[i].setText(friendName+"("+friendId+")");	
			jlarr[i].setIcon(photo);
		}
		int index = 0;
		for(int i=sizeOnLine;i<sizeTotal;i++){
			String friendName = offLineFriends.get(index).getUserName();
			String friendId = offLineFriends.get(index).getUserId();
			index++;
			jlarr[i].setText(friendName+"("+friendId+")");	
			jlarr[i].setIcon(blackPhoto);
		}
	}
	
	
	//����¼�����
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			//˫����ĳ������
			JLabel jLabel = (JLabel)e.getSource();
			//String friendId = jLabel.getText().substring(2,6);
			//ȡ�����ѵ�id���ں����ǳƺ����һ�������а���			
			String jLabelText = jLabel.getText();
			int idBeginIndex = jLabelText.lastIndexOf("(");
			String friendId = jLabelText.substring(idBeginIndex+1, jLabelText.length()-1);
			//�ж��Ƿ��Ѿ��д˺��ѵ����촰�ڴ�
			HashMap<String, ClientChat> hm = ManageClientChat.hm;
			if(hm.containsKey(friendId)){
				//�Ѿ��д˴��ڴ�
				return;
			}
			ClientChat chat = new ClientChat(userId,friendId);
			ManageClientChat.addClientChat(friendId, chat);
		}
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

	
	//window���ڼ���
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//�رտͻ��ˣ���Ҫ�����������������Ϣ
//		ClientLoginOpe loginOpe = new ClientLoginOpe();
//		loginOpe.userLoginOut(userId);
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
