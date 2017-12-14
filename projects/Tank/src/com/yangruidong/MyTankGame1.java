/**
 *���ܣ�̹�˴�ս2.0
 *���ߣ�����
 *���ڣ�2015/4/9
 */

package com.yangruidong;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.io.*;
public class MyTankGame1 extends JFrame 
{

	MyPanel mp=null;
	public static void main(String[] args) 
	{
	
		MyTankGame1 myTankGame1=new MyTankGame1();
		
		
	}
	
	//���캯��
	public MyTankGame1()
	{
		mp=new MyPanel();
		
		//����mp�߳�
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		//ע�����
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

}

//�ҵ���嶨��
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//�������̹����(����֪ʶ)
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	//����̹����
	int enSize=3;
	
	//����һ��ը����������
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	//��������ͼƬ������ͼƬ�������һ��ը��
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//���캯��
	public MyPanel()
	{
		//��ʼ���Լ�̹��
		hero=new Hero(100,100);
		//��ʼ�����˵�̹��
		for(int i=0;i<enSize;i++)
		{
			//�������˵�̹�˶���
			EnemyTank et=new EnemyTank((i+1)*50,0);
		
			//����̹�˷�������
			et.setDirect(2);
			
			//�������˵�̹��
			Thread t=new Thread(et);
			t.start();
			//������̹�����һ���ӵ�
			Shot s=new Shot(et.x+10,et.y+30,2);
			//���������
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			//����
			ets.add(et);
		}
		try {
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ʼ��ͼƬ
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	//��дpaint
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//������Panel��䱳��
		g.fillRect(0, 0, 400, 300);
		
		//�����Լ�̹��
		this.drawTank(hero.getX(), hero.getY(), g, hero.direct,1);
		
		//��ss��ȡ��ÿ���ӵ���������
		for(int i=0;i<this.hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//����һ���ӵ�
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1,1,false);
			}
			
			if(myShot.isLive==false)
			{
				//������ss��ɾ�������ӵ�
				hero.ss.remove(myShot);
			}
		}
		
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			//ȡ��ը��
			Bomb b=bombs.get(i);
			
			if(b.life>6)
			{
				System.out.println("image1");
				g.drawImage(image1, b.x, b.y, 30,30,this);
				
			}else if(b.life>3)
			{
				System.out.println("image2");
				g.drawImage(image2, b.x, b.y, 30,30,this);
			}else
			{
				System.out.println("image3");
				g.drawImage(image3, b.x, b.y, 30,30,this);
			}
			
			//��b������ֵ��С
			b.lifeDown();
			//���ը������ֵΪ0���Ͱ�ը����bombs����ȥ��
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		
		//��������̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(),0);
				//�ٻ������˵��ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive)
					{
						try {
							g.draw3DRect(enemyShot.x, enemyShot.y, 1,1,false);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	//дһ������ר���ж��Ƿ���е���̹��
	public void hitTank(Shot s,EnemyTank et)
	{
		//�жϸ�̹�˵ķ���
		switch(et.direct)
		{
		//�������̹�˷������ϻ�����
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				//����һ��ը��������Vector
				Bomb b=new Bomb(et.x,et.y);
				//������뵽Vector,������ܷ���
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				//����һ��ը��������Vector
				Bomb b=new Bomb(et.x,et.y);
				//������뵽Vector,������ܷ���
				bombs.add(b);
			}
		}
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//�ж���ʲô���͵�̹��
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}
		
		//�жϷ���
		switch(direct)
		{
			//����
		case 0:
			//�����ҵ�̹��
			//������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//������
			g.drawLine(x+10, y+15, x+10, y);
			break;
			
			//����
		case 1:
			//�����������
			g.fill3DRect(x, y, 30,5,false);
			//�����������
			g.fill3DRect(x, y+15, 30, 5, false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20,10,false);
			//����Բ��
			g.fillOval(x+10, y+5, 10,10);
			//������
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
			
			//����
		case 2:
			//������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//������
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
			
			//����
		case 3:
			//�����������
			g.fill3DRect(x, y, 30,5,false);
			//�����������
			g.fill3DRect(x, y+15, 30, 5, false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20,10,false);
			//����Բ��
			g.fillOval(x+10, y+5, 10,10);
			//������
			g.drawLine(x+15, y+10, x, y+10);
			break;
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//�������´��� a��ʾ����s��ʾ���£�w��ʾ���ϣ�d��ʾ����
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//�����ҵ�̹�˷���
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}else if(e.getKeyCode()==KeyEvent.VK_W)
		{
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D)
		{			
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			this.hero.setDirect(2);
			this.hero.moveDown();
		}
		
		//�ж�����Ƿ���J
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//����
			if(this.hero.ss.size()<=4)
			{
				this.hero.shotEnemy();
			}
		}
				
		//�������»���Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//ÿ��100����ȥ�ػ��ӵ�
		
		//Mypanel�߳�Ϊ��ѭ���������󲻶�ɨ���ػ�
		while(true)
		{
			//ÿ�μ��100����
			try{
				Thread.sleep(100);
			}catch (Exception e){
				
			}
			
			//�ж��Ƿ����
			for(int i=0;i<hero.ss.size();i++)
			{
				//ȡ���ӵ�
				Shot myShot=hero.ss.get(i);
				if(myShot.isLive)
				{
					//ȡ��ÿһ������̹�ˣ������ж�
					for(int j=0;j<ets.size();j++)
					{
						//ȡ��̹��
						EnemyTank et=ets.get(j);
						if(et.isLive)
						{
							this.hitTank(myShot, et);
						}
					}
				}
			}
			//�ػ�
			this.repaint();
			
		}
	}
}




