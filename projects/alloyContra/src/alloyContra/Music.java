package alloyContra;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

public class Music {
	//��������
	public void mc_back(){
		try {
			   File f=new File(".\\music\\music (13).mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//����ǹ��
	public void mc_attack(){
		try {
			   File f=new File("music\\music (1).mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//���ǵ���
	public void mc_knife(){
		try {
			   File f=new File("music\\knife.wav");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//����ը��������
	public void mc_bomb(){
		try {
			   File f=new File("music\\music (27).mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//�ɻ�ը��������
	public void mc_plane_bomb(){
		try {
			   File f=new File("music\\plane_bomb.wav");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//̹��ը��������
	public void mc_boss_bomb(){
		try {
			   File f=new File("music\\music (8).mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//�ɻ�������
	public void mc_plane(){
		try {
			   File f=new File("music\\planefly.mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//��������������
	public void mc_L_die(){
		try {
			   File f=new File("music\\die.wav");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//��boss��������
	public void mc_frie(){
		try {
			   File f=new File(".\\music\\fire.mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//����������
	public void mc_open(){
		try {
			   File f=new File("music\\open.mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//����������
	public void mc_over(){
		try {
			   File f=new File("music\\music (33).mp3");
			   Player p=Manager.createRealizedPlayer(f.toURI().toURL());
			   p.prefetch();
			   p.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
