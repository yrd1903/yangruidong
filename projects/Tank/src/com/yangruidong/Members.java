package com.yangruidong;

import java.util.Vector;

//ը����
class Bomb
{
	//����ը������
	int x,y;
	//ը��������
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//��������ֵ
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

//�ӵ���
class Shot implements Runnable
{
	int x;
	int y;
	//�ӵ��ķ��䷽��
	int direct;
	int speed=1;
	
	//�Ƿ񻹻���
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
			//ÿ���ӵ�λ�ñ仯֮����50����
			try{
				Thread.sleep(50);
			}catch(Exception e){
				
			}
			switch(direct)
			{
			case 0:
				//�ӵ�����
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
			
			//System.out.println("�ӵ�����x="+x+"y="+y);
			
			//�жϸ��ӵ��Ƿ�������Ե
			if(x<=0||x>=400||y<=0||y>=300)
			{
				this.isLive=false;
				break;
			}
						
		}
	}
}
//����̹����
class Tank
{
	//��ʾ̹�˵ĺ�����
	int x=0;
	//̹��������
	int y=0;
	
	//̹�˷���,0��ʾ�ϣ�1��ʾ�ң�2��ʾ�£�3��ʾ��
	int direct=0;
	
	//̹����ɫ
	int color;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	//̹���ٶ�
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

//���˵�̹��
class EnemyTank extends Tank implements Runnable
{
	boolean isLive=true;
	
	//����һ�����������Դ�ŵ��˵��ӵ�
	Vector<Shot> ss=new Vector<Shot>();
	//��������ӵ���Ӧ���ڸոմ���̹�˺͵��˵�̹���ӵ�������
	
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
				//˵��̹����������
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
				//����
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
				//����
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
			//��̹���������һ���µķ���
			this.direct=(int)(Math.random()*4);
			//�жϵ���̹���Ƿ�����
			if(this.isLive==false)
			{
				//��̹���������˳��߳�
				break;
			}
			
		}
	}
}

//�ҵ�̹��
class Hero extends Tank
{
	//�ӵ�
	Vector<Shot> ss=new Vector<Shot>();
	Shot s=null;
	
	public Hero(int x,int y)
	{
		super(x,y); 
		
	}
	
	//����
	public void shotEnemy()
	{
				
		switch(this.direct)
		{
		case 0:
			//����һ���ӵ�
			s=new Shot(x+10,y,0);
			//���ӵ����뵽����
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
	
	//̹�������ƶ�
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