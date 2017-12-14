package Test2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyServer2 extends JFrame implements ActionListener{

	JTextArea jta=null;
	JTextField jtf=null;
	JButton jb=null;
	JScrollPane jsp=null;
	JPanel jp1=null;
	//把信息发给客户端的对象
	PrintWriter pw=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer2 ms2=new MyServer2();
	}
	public MyServer2(){
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jtf=new JTextField(20);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp1=new JPanel();
		
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp,"Center");
		this.add(jp1,"South");
		
		this.setTitle("QQ简易聊天服务器端");
		this.setSize(400,300);
		this.setVisible(true);
		
		try {
			//服务器监听
			ServerSocket ss=new ServerSocket(9988);
			//等待客户端连接
			Socket s=ss.accept();
			
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			
			//读取从客户端发来的信息
			while(true)
			{
				//读取从客户端发来的信息
				String info=br.readLine();
				jta.append("客户端 对 服务器说：  "+info+"\r\n");
				
				
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户按下了发送按钮
		if(e.getSource()==jb)
		{
			//把服务器在jtf写的内容发送给客户端
			String info=jtf.getText();
			jta.append("服务器 对 客户端说：  "+info+"\r\n");
			pw.println(info);
			
			//清空内容
			jtf.setText("");
			
		}
	}

}
