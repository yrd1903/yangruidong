package Test;
import java.net.*;
import java.io.*;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient mc=new MyClient();
	}
	
	public MyClient(){
		try {
			//���ӷ�������
			Socket s=new Socket("127.0.0.1",9999);
			//pw������������
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			//br�������մӿ���̨���������
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			//br2������������
			InputStreamReader isr2=new InputStreamReader(s.getInputStream());
			BufferedReader br2=new BufferedReader(isr2);
			
			while(true)
			{
				System.out.println("����������Է�����˵�Ļ���");
				//�ͻ����ȴӿ���̨����
				String info=br.readLine();
				//Ȼ���͸�������
				pw.println(info);
				
				//���մӷ����������Ļ�
				//��仰�ڷ�����������Ϣ֮ǰ��һֱ�ȴ���ֱ���յ���������������Ϣ�ż���ִ���������
				String res=br2.readLine();
				//System.out.println("99");
				System.out.println("������˵"+res);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
