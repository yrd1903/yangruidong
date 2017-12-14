package com.yrd.notepad;

import java.awt.*;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;
public class NotePad extends JFrame implements ActionListener{

	//定义需要的组件
	JScrollPane jsp = null;
	JTextArea jta = null;
	//菜单条
	JMenuBar jmb = null;
	//定义JMenu
	JMenu jm1 = null;
	//定义JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	//定义打开文件的路径
	String openSource = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad notePad = new NotePad();
	}
	
	public NotePad(){
		jta = new JTextArea();
		//设置换行
		jta.setLineWrap(true);
		//设置换行在单词边界处，而不是在字符边界
		jta.setWrapStyleWord(true);
		
		jsp = new JScrollPane(jta);		
		jmb = new JMenuBar();
		jm1 = new JMenu("文件");
		//设置助记符
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("打开");
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2 = new JMenuItem("另存为");
		//注册监听
		jmi2.addActionListener(this);
		jmi2.setActionCommand("saveas");
		jmi3 = new JMenuItem("保存");
		//注册监听
		jmi3.addActionListener(this);
		jmi3.setActionCommand("save");
		
		//加入
		this.setJMenuBar(jmb);
		//把jm1放入到jmb中
		jmb.add(jm1);
		//把item放入到menu
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
		//判断是哪个菜单被选中
		if(e.getActionCommand().equals("open")){
			//System.out.println("open");
			JFileChooser jfc1 = new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("请选择文件...");
			//默认方式
			jfc1.showOpenDialog(null);
			//显示
			jfc1.setVisible(true);
			
			//得到用户选择的文件全路径
			openSource = jfc1.getSelectedFile().getAbsolutePath();
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(openSource);
				br = new BufferedReader(fr);
				//从文件中读取信息并显示到jta中
				String s = "";
				String allCon = "";
				while((s=br.readLine())!=null){
					allCon +=s+"\r\n";
				}
				//放置到jta
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
			//准备写入到打开的文件
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
	
	//另存为
	public void savaAs(){
		//出现保存对话框
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setDialogTitle("另存为...");
		//以默认方式显示
		jfc.showOpenDialog(null);
		jfc.setVisible(true);
		
		//得到用户希望把文件保存到的地方,文件全路径
		String filename = jfc.getSelectedFile().getAbsolutePath();
		//准备写入到指定文件
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			//可以做缓存优化
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
