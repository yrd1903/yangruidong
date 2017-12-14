/**
 * ���ܣ�QQ�ͻ��˵�½����
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

	//���山����Ҫ�����
	JLabel jlb1;
	//�����в���Ҫ�����
	//�в�������JPanel,��һ��ѡ����ڹ���
	JTabbedPane jtp;
	//jp3  jp4 û��д
	JPanel jp2,jp3,jp4;
	JLabel jp2_jlb1,jp2_jlb2,jp2_jlb3,jp2_jlb4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�����ϲ���Ҫ�����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientLogin qqClientLogin=new ClientLogin();
	}
	
	public ClientLogin()
	{
		//������
		jlb1=new JLabel(new ImageIcon("images/tou.gif"));
		
		//�����в�
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jlb1=new JLabel("QQ����",JLabel.CENTER);
		jp2_jlb2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jlb3=new JLabel("��������",JLabel.CENTER);
		//���á��������롰ǰ��ɫΪ��ɫ
		jp2_jlb3.setForeground(Color.blue);
		jp2_jlb4=new JLabel("�������뱣��",JLabel.CENTER);
		jp1_jb1=new JButton(new ImageIcon("images/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����½");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�ѿؼ�����˳����뵽jp2
		jp2.add(jp2_jlb1);
		jp2.add(jp2_jtf);
		jp2.add(jp1_jb1);
		jp2.add(jp2_jlb2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jlb3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jlb4);
		
		//����ѡ�����
		jtp=new JTabbedPane();
		jp3=new JPanel();
		jp4=new JPanel();
		jtp.add("QQ����",jp2);
		jtp.add("�ֻ�����",jp3);
		jtp.add("�����ʼ�",jp4);
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("images/denglu.gif"));
		//��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("images/xiangdao.gif"));
		//��������ť���뵽jp1
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
		//����û������¼
		if(e.getSource()==jp1_jb1)
		{
			ClientUser clientUser=new ClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(clientUser.checkUserAndConnect(u)){
								
				try {
					//�ȴ��������б�
					ClientFriendList clientFriendList = new ClientFriendList(u.getUserId());
					//��clientFriendList���뵽����
					ManageClientFriendList.addClientFriendList(u.getUserId(), clientFriendList);
					
					//����һ��Ҫ�󷵻����ߺ��ѵİ�
					ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					//��һ��message��
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ���õ��ĸ�����ĺ����б�
					m.setSender(u.getUserId());
					oos.writeObject(m);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				//�رյ�¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"�û����������");
			}
		}
	}

}
