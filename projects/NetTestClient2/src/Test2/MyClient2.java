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
		jb=new JButton("����");
		jb.addActionListener(this);
		jp1=new JPanel();
		
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp,"Center");
		this.add(jp1,"South");
		
		this.setTitle("QQ�������� �ͻ���");
		this.setSize(400,300);
		this.setVisible(true);
		
		try {
			Socket s=new Socket("127.0.0.1",9988);
			//��InputStream��InputStreamReaderת����Ҳ�����ֽ������ַ���ת��
			//Socketֻ���ֽ�����Ҫͨ�������ת��Ϊ��Ҫ����
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			
			pw=new PrintWriter(s.getOutputStream(),true);
			
			while(true)
			{
				//��ͣ�Ķ��ӷ�������������Ϣ
				String info=br.readLine();
				jta.append("������ �� �ͻ���˵��  "+info+"\r\n");
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
			//�ѿͻ��˷��͵���Ϣ��ʾ��jta
			jta.append("�ͻ��� �� ������˵��  "+info+"\r\n");
			pw.println(info);
			//���·��Ͱ�ť������������ߵ�����
			jtf.setText("");
		}
	}

}
