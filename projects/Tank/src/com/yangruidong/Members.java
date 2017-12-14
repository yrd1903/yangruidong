package com.yangruidong;

import java.util.Vector;

//炸弹类
class Bomb
{
	//定义炸弹坐标
	int x,y;
	//炸弹的生命
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//减少生命值
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else{
			this.isLive=false;
		}
	}
}

//子弹类
class Shot implements Runnable
{
	int x;
	int y;
	//子弹的发射方向
	int direct;
	int speed=1;
	
	//是否还活着
	boolean isLive=true;
	
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	public void run()
	{
		while(true)
		{
			//每次子弹位置变化之间间隔50毫秒
			try{
				Thread.sleep(50);
			}catch(Exception e){
				
			}
			switch(direct)
			{
			case 0:
				//子弹向上
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;				
			}
			
			//System.out.println("子弹坐标x="+x+"y="+y);
			
			//判断该子弹是否碰到边缘
			if(x<=0||x>=400||y<=0||y>=300)
			{
				this.isLive=false;
				break;
			}
						
		}
	}
}
//定义坦克类
class Tank
{
	//表示坦克的横坐标
	int x=0;
	//坦克纵坐标
	int y=0;
	
	//坦克方向,0表示上，1表示右，2表示下，3表示左
	int direct=0;
	
	//坦克颜色
	int color;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	//坦克速度
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}

//敌人的坦克
class EnemyTank extends Tank implements Runnable
{
	boolean isLive=true;
	
	//定义一个向量，可以存放敌人的子弹
	Vector<Shot> ss=new Vector<Shot>();
	//敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹死亡后
	
	public EnemyTank(int x,int y)
	{
		super(x, y);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{			
			switch(this.direct)
			{
			case 0:
				//说明坦克正在向上
				for(int i=0;i<30;i++)
				{
					if(y>0)
					y-=speed;
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//向右
				for(int i=0;i<30;i++)
				{
					if(x<400)
					x+=speed;
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			case 2:
				//向下
				for(int i=0;i<30;i++)
				{
					if(y<300)
					y+=speed;
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			case 3:
				for(int i=0;i<30;i++)
				{
					if(x>0)
					x-=speed;
					try{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				break;
			}
			//让坦克随机产生一个新的方向
			this.direct=(int)(Math.random()*4);
			//判断敌人坦克是否死亡
			if(this.isLive==false)
			{
				//让坦克死亡后退出线程
				break;
			}
			
		}
	}
}

//我的坦克
class Hero extends Tank
{
	//子弹
	Vector<Shot> ss=new Vector<Shot>();
	Shot s=null;
	
	public Hero(int x,int y)
	{
		super(x,y); 
		
	}
	
	//开火
	public void shotEnemy()
	{
				
		switch(this.direct)
		{
		case 0:
			//创建一颗子弹
			s=new Shot(x+10,y,0);
			//把子弹加入到向量
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+30,y+10,1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10,y+30,2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x,y+10,3);
			ss.add(s);
			break;
		}
		Thread t=new Thread(s);
		t.start();
	}
	
	//坦克向上移动
	public void moveUp()
	{
		y-=speed;
	}
	public void moveRight()
	{
		x+=speed;
	}
	public void moveLeft()
	{
		x-=speed;
	}
	public void moveDown()
	{
		y+=speed;
	}
}