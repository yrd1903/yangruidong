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
	//����Ϣ�����ͻ��˵Ķ���
	PrintWriter pw=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer2 ms2=new MyServer2();
	}
	public MyServer2(){
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jtf=new JTextField(20);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp1=new JPanel();
		
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp,"Center");
		this.add(jp1,"South");
		
		this.setTitle("QQ���������������");
		this.setSize(400,300);
		this.setVisible(true);
		
		try {
			//����������
			ServerSocket ss=new ServerSocket(9988);
			//�ȴ��ͻ�������
			Socket s=ss.accept();
			
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			
			//��ȡ�ӿͻ��˷�������Ϣ
			while(true)
			{
				//��ȡ�ӿͻ��˷�������Ϣ
				String info=br.readLine();
				jta.append("�ͻ��� �� ������˵��  "+info+"\r\n");
				
				
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����û������˷��Ͱ�ť
		if(e.getSource()==jb)
		{
			//�ѷ�������jtfд�����ݷ��͸��ͻ���
			String info=jtf.getText();
			jta.append("������ �� �ͻ���˵��  "+info+"\r\n");
			pw.println(info);
			
			//�������
			jtf.setText("");
			
		}
	}

}
