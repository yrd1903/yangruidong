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
			//链接服务器端
			Socket s=new Socket("127.0.0.1",9999);
			//pw用来发送数据
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			//br用来接收从控制台输入的数据
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			//br2用来接收数据
			InputStreamReader isr2=new InputStreamReader(s.getInputStream());
			BufferedReader br2=new BufferedReader(isr2);
			
			while(true)
			{
				System.out.println("请输入你想对服务器说的话：");
				//客户端先从控制台接收
				String info=br.readLine();
				//然后发送给服务器
				pw.println(info);
				
				//接收从服务器发来的话
				//这句话在服务器发来消息之前会一直等待，直到收到服务器发来的消息才继续执行下面代码
				String res=br2.readLine();
				//System.out.println("99");
				System.out.println("服务器说"+res);
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
