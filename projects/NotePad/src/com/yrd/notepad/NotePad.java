package com.yrd.notepad;

import java.awt.*;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;
public class NotePad extends JFrame implements ActionListener{

	//������Ҫ�����
	JScrollPane jsp = null;
	JTextArea jta = null;
	//�˵���
	JMenuBar jmb = null;
	//����JMenu
	JMenu jm1 = null;
	//����JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	//������ļ���·��
	String openSource = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad notePad = new NotePad();
	}
	
	public NotePad(){
		jta = new JTextArea();
		//���û���
		jta.setLineWrap(true);
		//���û����ڵ��ʱ߽紦�����������ַ��߽�
		jta.setWrapStyleWord(true);
		
		jsp = new JScrollPane(jta);		
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�");
		//�������Ƿ�
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("��");
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2 = new JMenuItem("���Ϊ");
		//ע�����
		jmi2.addActionListener(this);
		jmi2.setActionCommand("saveas");
		jmi3 = new JMenuItem("����");
		//ע�����
		jmi3.addActionListener(this);
		jmi3.setActionCommand("save");
		
		//����
		this.setJMenuBar(jmb);
		//��jm1���뵽jmb��
		jmb.add(jm1);
		//��item���뵽menu
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		
		this.add(jsp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,450);
		this.setLocation(400,200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж����ĸ��˵���ѡ��
		if(e.getActionCommand().equals("open")){
			//System.out.println("open");
			JFileChooser jfc1 = new JFileChooser();
			//��������
			jfc1.setDialogTitle("��ѡ���ļ�...");
			//Ĭ�Ϸ�ʽ
			jfc1.showOpenDialog(null);
			//��ʾ
			jfc1.setVisible(true);
			
			//�õ��û�ѡ����ļ�ȫ·��
			openSource = jfc1.getSelectedFile().getAbsolutePath();
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(openSource);
				br = new BufferedReader(fr);
				//���ļ��ж�ȡ��Ϣ����ʾ��jta��
				String s = "";
				String allCon = "";
				while((s=br.readLine())!=null){
					allCon +=s+"\r\n";
				}
				//���õ�jta
				jta.setText(allCon);
				//System.out.println(allCon);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if(e.getActionCommand().equals("saveas")){
			
			savaAs();
			
		} else if(e.getActionCommand().equals("save")){
			if(openSource==null){
				savaAs();
				return;
			}
			//׼��д�뵽�򿪵��ļ�
			FileWriter fr = null;
			BufferedWriter bw = null;
			try {
				fr = new FileWriter(openSource);
				bw = new BufferedWriter(fr);
				bw.write(this.jta.getText());
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally{
				try {
					bw.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		}
	}
	
	//���Ϊ
	public void savaAs(){
		//���ֱ���Ի���
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setDialogTitle("���Ϊ...");
		//��Ĭ�Ϸ�ʽ��ʾ
		jfc.showOpenDialog(null);
		jfc.setVisible(true);
		
		//�õ��û�ϣ�����ļ����浽�ĵط�,�ļ�ȫ·��
		String filename = jfc.getSelectedFile().getAbsolutePath();
		//׼��д�뵽ָ���ļ�
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			//�����������Ż�
			bw.write(this.jta.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
