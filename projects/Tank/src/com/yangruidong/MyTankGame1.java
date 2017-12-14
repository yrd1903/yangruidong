/**
 *功能：坦克大战2.0
 *作者：杨瑞东
 *日期：2015/4/9
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
	
	//构造函数
	public MyTankGame1()
	{
		mp=new MyPanel();
		
		//启动mp线程
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		//注册监听
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

}

//我的面板定义
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人坦克组(集合知识)
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	//敌人坦克数
	int enSize=3;
	
	//定义一个炸弹向量集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	//定义三张图片，三张图片才能组成一颗炸弹
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel()
	{
		//初始化自己坦克
		hero=new Hero(100,100);
		//初始化敌人的坦克
		for(int i=0;i<enSize;i++)
		{
			//创建敌人的坦克对象
			EnemyTank et=new EnemyTank((i+1)*50,0);
		
			//设置坦克方向向下
			et.setDirect(2);
			
			//启动敌人的坦克
			Thread t=new Thread(et);
			t.start();
			//给敌人坦克添加一颗子弹
			Shot s=new Shot(et.x+10,et.y+30,2);
			//加入给敌人
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			//加入
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
		//初始化图片
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	//重写paint
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//把整个Panel填充背景
		g.fillRect(0, 0, 400, 300);
		
		//画出自己坦克
		this.drawTank(hero.getX(), hero.getY(), g, hero.direct,1);
		
		//从ss中取出每颗子弹，并画出
		for(int i=0;i<this.hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//画出一颗子弹
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1,1,false);
			}
			
			if(myShot.isLive==false)
			{
				//从向量ss中删除掉该子弹
				hero.ss.remove(myShot);
			}
		}
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			//取出炸弹
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
			
			//让b的生命值减小
			b.lifeDown();
			//如果炸弹生命值为0，就把炸弹从bombs向量去掉
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		
		//画出敌人坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(),0);
				//再画出敌人的子弹
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
	
	//写一个函数专门判断是否击中敌人坦克
	public void hitTank(Shot s,EnemyTank et)
	{
		//判断该坦克的方向
		switch(et.direct)
		{
		//如果敌人坦克方向是上或者下
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//创建一颗炸弹，放入Vector
				Bomb b=new Bomb(et.x,et.y);
				//必须放入到Vector,外面才能访问
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//创建一颗炸弹，放入Vector
				Bomb b=new Bomb(et.x,et.y);
				//必须放入到Vector,外面才能访问
				bombs.add(b);
			}
		}
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断是什么类型的坦克
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}
		
		//判断方向
		switch(direct)
		{
			//向上
		case 0:
			//画出我的坦克
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线
			g.drawLine(x+10, y+15, x+10, y);
			break;
			
			//向右
		case 1:
			//画出上面矩形
			g.fill3DRect(x, y, 30,5,false);
			//画出下面矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20,10,false);
			//画出圆形
			g.fillOval(x+10, y+5, 10,10);
			//画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
			
			//向下
		case 2:
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
			
			//向左
		case 3:
			//画出上面矩形
			g.fill3DRect(x, y, 30,5,false);
			//画出下面矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20,10,false);
			//画出圆形
			g.fillOval(x+10, y+5, 10,10);
			//画出线
			g.drawLine(x+15, y+10, x, y+10);
			break;
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//按键按下处理 a表示向左，s表示向下，w表示向上，d表示向右
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//设置我的坦克方向
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
		
		//判断玩家是否按下J
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//开火
			if(this.hero.ss.size()<=4)
			{
				this.hero.shotEnemy();
			}
		}
				
		//必须重新绘制Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每隔100毫秒去重绘子弹
		
		//Mypanel线程为死循环，启动后不断扫描重绘
		while(true)
		{
			//每次间隔100毫秒
			try{
				Thread.sleep(100);
			}catch (Exception e){
				
			}
			
			//判断是否击中
			for(int i=0;i<hero.ss.size();i++)
			{
				//取出子弹
				Shot myShot=hero.ss.get(i);
				if(myShot.isLive)
				{
					//取出每一个敌人坦克，与他判断
					for(int j=0;j<ets.size();j++)
					{
						//取出坦克
						EnemyTank et=ets.get(j);
						if(et.isLive)
						{
							this.hitTank(myShot, et);
						}
					}
				}
			}
			//重绘
			this.repaint();
			
		}
	}
}




