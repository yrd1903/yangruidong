package Test2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class MyClient2 extends JFrame implements ActionListener{

	JTextArea jta=null;
	JTextField jtf=null;
	JButton jb=null;
	JScrollPane jsp=null;
	JPanel jp1=null;
	PrintWriter pw=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient2 ms2=new MyClient2();
	}
	public MyClient2(){
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
		
		this.setTitle("QQ简易聊天 客户端");
		this.setSize(400,300);
		this.setVisible(true);
		
		try {
			Socket s=new Socket("127.0.0.1",9988);
			//从InputStream到InputStreamReader转换，也就是字节流到字符流转换
			//Socket只有字节流，要通过别的流转换为需要的流
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			
			while(true)
			{
				//不停的读从服务器发来的消息
				String info=br.readLine();
				jta.append("服务器 对 客户端说：  "+info+"\r\n");
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb)
		{
			String info=jtf.getText();
			//把客户端发送的信息显示到jta
			jta.append("客户端 对 服务器说：  "+info+"\r\n");
			pw.println(info);
			//按下发送按钮后清空输入框里边的内容
			jtf.setText("");
		}
	}

}
