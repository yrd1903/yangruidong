/*
 * 功能：是一个服务器端，在9999端口上监听
 * 可以通过控制台，输入回送给客户端的信息
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
			//在9999端口监听
			System.out.println("服务器在9999监听");
			ServerSocket ss=new ServerSocket(9999);
			
			//等待链接
			Socket s=ss.accept();
			
			//先接收客户端发送的数据
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);//这个br用来读取信道中收到的数据
			
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);//这个pw用来输出要发送给信道的数据
			
			//接收从控制台输入的信息
			InputStreamReader isr2=new InputStreamReader(System.in);
			BufferedReader br2=new BufferedReader(isr2);//这个br2用来读取要从控制台输入的数据
			
			while(true){
				//在客户端发来消息之前，下面的这句话会一直处于等待，直到收到客户端消息才继续执行下面代码
				String infoFromClient=br.readLine();
				System.out.println("客户端发来："+infoFromClient);
				
				//接收从控制台输入的信息
				System.out.println("输入你希望对客户端说的话：");
				String response=br2.readLine();
				//把从控制台接收的信息，回送给客户端
				pw.println(response);
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
