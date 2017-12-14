/**
 * 
 * ���ߣ�����
 * ���ܣ��ͻ��˵�½����
 * ���ڣ�2016/1/11
 * �޸�����¼��ʽ��֤���������ߺ��ѷ�����Ϣ�������쳣��
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
		//��Ҫ������
		login = new Font(Font.DIALOG,Font.BOLD,18);
		lable = new Font(Font.DIALOG,Font.BOLD,14);
		//����ͼƬ
		title = new ImageIcon("images/login_title.png");
		jla1 = new JLabel(title);
		//�в���¼����
		jp = new JPanel(new GridLayout(4,3));		
		jla2 = new JLabel("�˺�",JLabel.CENTER);
		jla2.setFont(lable);
		jtf = new JTextField();
		jla3 = new JLabel("ע���˺�",JLabel.CENTER);
		jla3.setFont(lable);
		jla3.setForeground(Color.BLUE);
		jla4 = new JLabel("����",JLabel.CENTER);
		jla4.setFont(lable);
		jpf = new JPasswordField();
		jpf.addKeyListener(this);
		jla5 = new JLabel("�һ�����",JLabel.CENTER);
		jla5.setFont(lable);
		jla5.setForeground(Color.BLUE);
		jla6_1 = new JLabel();
		jp_1 = new JPanel(new GridLayout(1,2));
		jcb1 = new JCheckBox("��ס����");
		jcb2 = new JCheckBox("�Զ���¼");
		jla6_2 = new JLabel();
		jla6_3 = new JLabel();
		jb = new JButton("��¼");
		jb.addActionListener(this);
		jb.setActionCommand("login");
		Font login = new Font(Font.DIALOG,Font.BOLD,18);
		jb.setFont(login);
		jla6_4 = new JLabel();
		
		//������
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
		//JFrame��JComponent��JLabelĬ���ǲ����Ի�ý���
		//����Ĭ������£�swing�ǰѽ������������ڿ��Ի�ý���ĵ�һ�����
		//��ˣ�Ҫ����JFrame��ý���
		//�����Ҳ����keylistener
		this.setFocusable(true);
		this.setSize(430,320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(400, 200);
		this.setVisible(true);
	}
	
	
	
	
	//��������
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("login")){
			//�û�����˵�¼��ť
			this.sendLoginInfo();
		}
	}

	//���̼���
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Enter����KeyCode��10
		if(e.getKeyCode()==10){
			this.sendLoginInfo();
		}
	}
	
	public void sendLoginInfo(){
		//���û���¼��Ϣ���͵�������
		String userId = this.jtf.getText().trim();
		String passWd = new String(this.jpf.getPassword());
		//�˺������ʽ��֤
		if("".equals(userId)){
			JOptionPane.showMessageDialog(this,"�˺Ų���Ϊ�գ�");
			return;
		}
		if(this.isNumeric(userId)==false){
			JOptionPane.showMessageDialog(this,"�˺Ÿ�ʽ����");
			return;
		}
		if("".equals(passWd)){
			JOptionPane.showMessageDialog(this,"���벻��Ϊ�գ�");
			return;
		}
		User user = new User(userId,passWd);
		ClientLoginOpe loginOpe = new ClientLoginOpe();
		//�����û���¼���ص���Ϣ
		Message message = loginOpe.userLoginIn(user);
		if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){
			this.dispose();
			//ȡ���û��ǳ�
			String userName = message.getUserName();
			//ȡ�����ߺ��ѺͲ����ߺ���
			ArrayList<User> onLineFriends = message.getOnLineFriends();
			ArrayList<User> offLineFriends = message.getOffLineFriends();
			//����������
			ClientFriendList friendList = new ClientFriendList(userId,userName,onLineFriends,offLineFriends);
			//����ClientFriendList����
			ManageClientFriendList.addClientFriendList(userId, friendList);
		} else if(message.getMesType().equals(MessageType.MESSAGE_REPEAT_LOGIN)){
			JOptionPane.showMessageDialog(this,"�û��Ѿ����ߣ�");
		} else {
			//����MessageType.MESSAGE_LOGIN_FAIL
			JOptionPane.showMessageDialog(this,"�û����������");
		}
	}
	
	//��֤�ַ����Ƿ�Ϊ���֣�Ҫ��str��������Ϊ��
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
