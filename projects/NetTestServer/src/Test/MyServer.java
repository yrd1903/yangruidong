/*
 * ���ܣ���һ���������ˣ���9999�˿��ϼ���
 * ����ͨ������̨��������͸��ͻ��˵���Ϣ
 */
package Test;
import java.io.IOException;
import java.net.*;
import java.io.*;
public class MyServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer ms=new MyServer();
	}
	public MyServer(){
		try {
			//��9999�˿ڼ���
			System.out.println("��������9999����");
			ServerSocket ss=new ServerSocket(9999);
			
			//�ȴ�����
			Socket s=ss.accept();
			
			//�Ƚ��տͻ��˷��͵�����
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);//���br������ȡ�ŵ����յ�������
			
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);//���pw�������Ҫ���͸��ŵ�������
			
			//���մӿ���̨�������Ϣ
			InputStreamReader isr2=new InputStreamReader(System.in);
			BufferedReader br2=new BufferedReader(isr2);//���br2������ȡҪ�ӿ���̨���������
			
			while(true){
				//�ڿͻ��˷�����Ϣ֮ǰ���������仰��һֱ���ڵȴ���ֱ���յ��ͻ�����Ϣ�ż���ִ���������
				String infoFromClient=br.readLine();
				System.out.println("�ͻ��˷�����"+infoFromClient);
				
				//���մӿ���̨�������Ϣ
				System.out.println("������ϣ���Կͻ���˵�Ļ���");
				String response=br2.readLine();
				//�Ѵӿ���̨���յ���Ϣ�����͸��ͻ���
				pw.println(response);
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
