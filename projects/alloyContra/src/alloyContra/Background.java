package alloyContra;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
@SuppressWarnings("unused")
//游戏主类
public class Background extends JPanel{
	private static final long serialVersionUID = 1L;
	public static Background background;
	public static Music music;
	public static int x=0,ci=0,ci_jump=0,ci_bomb=0,ci_zadan=0,spark_x=0,kit_ci=0,enemy_spark_x=0;
	public static boolean is_open=true,have_gift=false,is_die=false,is_found=false,is_spark=false,is_enemy_spark=false;
	Image back,explain,gaobai;
	Thread t;
	
	public static String str[]={"开心地离开","感觉不顺气"};//提示框
	public static ImageIcon img=new ImageIcon(".\\img\\gameover.png");
	public static ImageIcon h=new ImageIcon(".\\img\\h0.gif");
	Enemy enemy;               //敌人
	Enemy_boss_lv boss_lv;      //boss绿
	Enemy1 enemy1;
	
	Leadone leadone=new Leadone();
	MyThread myThread=new MyThread();
	Truck truck=new Truck();
	Bullet bullet=new Bullet();
	Bomb bomb=new Bomb();
	Hostage hostage=new Hostage();
	Obstruction obstruction=new Obstruction();
	Boss boss=new Boss();
	Ldie ldie=new Ldie();
	Edie edie=new Edie();
	
	public Background(){
		music=new Music();
		t=new AloneThread();
		t.start();
		
		back=Toolkit.getDefaultToolkit().getImage(".\\img\\map.jpg");        //背景图片
		explain=Toolkit.getDefaultToolkit().getImage(".\\img\\explain.png");       //操作说明
		gaobai=Toolkit.getDefaultToolkit().getImage(".\\img\\gaobai.png");       //告白
				
		boss_lv=new Enemy_boss_lv();      //实例boss绿
		enemy=new Enemy();             //实例敌人
		enemy1=new Enemy1(); 
		new Un_breath();//倒计时
		new Enemy_Thread();          //实例敌人线程
		new Enemy_Thread_1();
		new Enemy_Thread_2();
		new Enemy_Thread_3();	
	}
	private Toolkit kit=Toolkit.getDefaultToolkit();
	private Image[] open=new Image[6];{	     //开场的图片
		for(int i=0;i<6;i++){
			open[i]=kit.getImage("img\\open"+i+".png");
		}
	}
	private Image[] spark=new Image[3];{  //射击产生的火花
		for(int i=0;i<3;i++){
			spark[i]=kit.getImage("img\\spark"+i+".png");
		}
	}
	
	public void paint(Graphics g){    
		//双缓冲
		Image offScreenImage = this.createImage(WIDTH, HEIGHT);
		Graphics gImage = offScreenImage.getGraphics();
		gImage.setColor(gImage.getColor());
		gImage.fillRect(0, 0, WIDTH, HEIGHT); 
		super.paint(gImage);
		
		if(is_open){
			if(ci<50){
				g.drawImage(open[0],0,0,1000,600,this);
				if(ci>1)	g.drawImage(open[1],120, 100, this);	
				if(ci>5)	g.drawImage(open[2],320,100,this);
				if(ci>10)	g.drawImage(open[3],520, 100, this);	
				if(ci>15)	g.drawImage(open[4],720, 100, this);
			}else if(ci<100){
				g.drawImage(explain, 0, 0, 1000,600,this);
			}else{
				g.drawImage(gaobai,0,0,1000,600,this);
			}
			if(ci>160){
				ci=0;
				is_open=false;
			}			
			try {
				Thread.sleep(200);
				ci++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}else{
			g.drawImage(back, x, 0, 5000,650,this);
			if(Truck.is_plane){
				truck.fly_plane(g); 
			}else if(Boss.is_attack_plane){
				boss.attack_plane(g);
			}
			ldie.L_is_die();                    //主角是否中枪判断
			edie.E_is_die();                  //敌人是否中枪判断
			ldie.un_bread(g);//倒计时
			
			enemy.paintEnemy(g);     //画出敌人
			enemy.enemy_1(g);        //画出敌人二
			boss_lv.paint_boss(g);    //画出boss
			enemy1.paint_enemy_all(g);   //画出敌人新增
			if(is_found){                           //发现主角开枪                     
				bullet.enemy_bullet(g);	     //画出敌人子弹
				enemy1.paint_bullet(g);          //画出敌人子弹新增
			}
			//击中产生火花
			if(is_spark){
				Bullet.x=500;
				is_spark=false;
				/*
				if(spark_x<20){
					Bullet.x=500;
					if(Leadone.type==0){
						g.drawImage(spark[Leadone.type], Bullet.bullet_position_x+spark_x, Bullet.bullet_position_y, this);
					}else{
						g.drawImage(spark[Leadone.type], Bullet.bullet_position_x-spark_x, Bullet.bullet_position_y, this);
					}		
					spark_x++;
				}else{
					spark_x=0;
					is_spark=false;
				}
				*/
			}
			//敌人击中产生火花
			/*
			if(is_enemy_spark){
				if(enemy_spark_x<20){
					if(Leadone.type==0){
				//		g.drawImage(spark[Leadone.type], Leadone.leadone_position_x+spark_x, Leadone.leadone_position_y+5, this);
					}else{
				//		g.drawImage(spark[Leadone.type], Leadone.leadone_position_x-spark_x, Leadone.leadone_position_y+5, this);
					}		
					enemy_spark_x++;
				}else{
					enemy_spark_x=0;
					is_enemy_spark=false;
				}
			}
			*/
			
			//得到礼物
			if(have_gift&&(Leadone.leadone_position_x>=Hostage.oder_x-373+x-30-800&&Leadone.leadone_position_x<=Hostage.oder_x-373+x+30-800)&&
					(Leadone.leadone_position_y>=Hostage.oder_y-30&&Leadone.leadone_position_y<=Hostage.oder_y+30) ){
				have_gift=false;
				Leadone.body_type=1;
				Leadone.attack_type=1;
				Leadone.jump_body_type=1;
				Bullet.bullet_type=1;
				if(Hostage.gift_type<3){
					Hostage.gift_type++;
				}else{
					Hostage.gift_type=0;
				}
			}
			//人质击中判断
			if((Keys.is_space&&Hostage.oder_x+x>=Bullet.bullet_position_x-10&&Hostage.oder_x+x<=Bullet.bullet_position_x+10&&Bullet.bullet_position_y>=Hostage.oder_y-5&&Bullet.bullet_position_y<=Hostage.oder_y+15) ||
			   (Keys.is_zadan&&Hostage.oder_x+x>=Bomb.bang_zadan_x-30&&Hostage.oder_x+x<=Bomb.bang_zadan_x+30&&Bomb.bang_zadan_y>=Hostage.oder_y-10&&Bomb.bang_zadan_y<=Hostage.oder_y+40) ||
			  (Leadone.is_knife&&Keys.space&&(Leadone.leadone_position_x>=Hostage.oder_x+x-50&&Leadone.leadone_position_x<=Hostage.oder_x+x+50)&&
				(Leadone.leadone_position_y>=Hostage.oder_y-50&&Leadone.leadone_position_y<=Hostage.oder_y+50))){
				Hostage.is_rescued=true;
				if(spark_x<20){
					Bullet.x=500;
					if(Leadone.type==0){
						g.drawImage(spark[Leadone.type], Hostage.oder_x+x+spark_x, Hostage.oder_y, this);
					}else{
						g.drawImage(spark[Leadone.type], Hostage.oder_x+x-spark_x, Hostage.oder_y, this);
					}		
					spark_x+=1;
				}
			}
			if(Hostage.is_rescued){
				if((Hostage.oder_type>=19&&Hostage.oder_type<=23)||(Hostage.oder_type>=31&&Hostage.oder_type<=37)){
					Hostage.oder_move_x+=2;	
				}
				hostage.rescued(g);
			}else{
				hostage.kidnap(g);
			}
			if(have_gift){
				hostage.prize(g);
			}
			//障碍物击中判断
			if((Keys.is_space&&Obstruction.barrier_x+x>=Bullet.bullet_position_x-10&&Obstruction.barrier_x+x<=Bullet.bullet_position_x+10&&Bullet.bullet_position_y>=Obstruction.barrier_y-25&&Bullet.bullet_position_y<=Obstruction.barrier_y+25) ||
					   (Keys.is_zadan&&Obstruction.barrier_x+x>=Bomb.bang_zadan_x-15&&Obstruction.barrier_x+x<=Bomb.bang_zadan_x+15&&Bomb.bang_zadan_y>=Obstruction.barrier_y-40&&Bomb.bang_zadan_y<=Obstruction.barrier_y+40) ||
					   (Leadone.is_knife&&Keys.space&&(Leadone.leadone_position_x>=Obstruction.barrier_x+x-50&&Leadone.leadone_position_x<=Obstruction.barrier_x+x+50)&&
						(Leadone.leadone_position_y>=Obstruction.barrier_y-50&&Leadone.leadone_position_y<=Obstruction.barrier_y+50))){
				Obstruction.is_bang=true;
				if(spark_x<20){
					Bullet.x=500;
					if(Leadone.type==0){
						g.drawImage(spark[Leadone.type], Obstruction.barrier_x+x+spark_x, Obstruction.barrier_y, this);
					}else{
						g.drawImage(spark[Leadone.type], Obstruction.barrier_x+x-spark_x, Obstruction.barrier_y, this);
					}	
					spark_x+=1;
				}
			}
			if(Obstruction.is_bang){
				obstruction.explosion_bang(g);
			}else{
				obstruction.explosion(g);
			}	
		}
		//坦克击中判断		
		if(kit_ci<40){
			boss.attack_boss(g);
			//坦克击中判断				
			if((Keys.is_space&& Boss.attack_boss_x+x>=Bullet.bullet_position_x-10&& Boss.attack_boss_x+x<=Bullet.bullet_position_x+20&&Bullet.bullet_position_y>=Boss.attack_boss_y+150-30&&Bullet.bullet_position_y<=Boss.attack_boss_y+150+10) ||
					   (Keys.is_zadan&&Boss.attack_boss_x+x >=Bomb.bang_zadan_x-50&&Boss.attack_boss_x+x <=Bomb.bang_zadan_x+50&&Bomb.bang_zadan_y>=Boss.attack_boss_y+150-50&&Bomb.bang_zadan_y<=Boss.attack_boss_y+150+50) ){
				Boss.is_hit=true;
				if(Bullet.bullet_type==0){
					 kit_ci++;	
				}else if(Bullet.bullet_type==1){
					kit_ci+=2;
				}
				if(spark_x<20){
					Bullet.x=500;
					if(Leadone.type==0){
						g.drawImage(spark[Leadone.type], Bullet.bullet_position_x+spark_x, Bullet.bullet_position_y, this);
					}else{
						g.drawImage(spark[Leadone.type], Bullet.bullet_position_x-spark_x, Bullet.bullet_position_y, this);
					}		
					spark_x+=1;
				}
			}
			if(Boss.is_hit){
				boss.hitTank(g);
			}
		}else{
			if(Boss.D_x<180){
				boss.destroy(g);
				Boss.D_x++;
			}else{
				boss.towel(g);
			}	
		}
		//大boss出现判断
		if(Boss.boss_number>=4){
			Boss.is_attack_plane=false;
			kit_ci=100;
			if(Boss.is_here){
				boss.M_here(g);               //将大boss送到
			}else{
				if(Boss.kit_monster<200){
					boss.M_attack(g);            //大boss的攻击
					if(Boss.monster_type>=12&&Boss.monster_type<=18){
						boss.spray(g);             //大boss喷火
					}
					//大boss击中判断
					if((Keys.is_space&& 750>=Bullet.bullet_position_x-10&& 750<=Bullet.bullet_position_x+30&&Bullet.bullet_position_y>=400-10&&Bullet.bullet_position_y<=400+50) ||
							   (Keys.is_zadan&&750 >=Bomb.bang_zadan_x-50&&750<=Bomb.bang_zadan_x+50&&Bomb.bang_zadan_y>=400-20&&Bomb.bang_zadan_y<=400+50) ){
						Boss.is_hit=true;
						if(Bullet.bullet_type==0){
							 Boss.kit_monster++;	
						}else if(Bullet.bullet_type==1){
							Boss.kit_monster+=2;
						}
						if(spark_x<20){
							Bullet.x=500;
							if(Leadone.type==0){
								g.drawImage(spark[Leadone.type], Bullet.bullet_position_x+spark_x, Bullet.bullet_position_y, this);
							}else{
								g.drawImage(spark[Leadone.type], Bullet.bullet_position_x-spark_x, Bullet.bullet_position_y, this);
							}		
							spark_x+=1;
						}
					}
					if(Boss.is_hit){
						boss.hitTank(g);
					}
				}else{
					boss.die_monster(g);
				}
			}
		}
		
		//扔炸弹判断
		if(Bomb.bang_zadan_x>0&&Bomb.bang_zadan_y>0){   
			bomb.bang_zadan(g);
			if(Bomb.type_bang<5){
				if(ci_bomb<50){
					ci_bomb++;
				}else{
					ci_bomb=0;
					Bomb.type_bang++;
					//炸弹声音
					if(Bomb.type_bang==1){
						music.mc_bomb();
					}
				}
			}else{
				Bomb.type_bang=0;
				Bomb.bang_zadan_x=0;
				Bomb.bang_zadan_y=0;
			}
		}
		if(Keys.zhadan){
			bomb.throw_zadan(g);
			if(Bomb.type_zadan<5){
				if(ci_zadan<10){
					ci_zadan++;
				}else{
					ci_zadan=0;
					Bomb.type_zadan++;
				}
			}else{
				Bomb.type_zadan=0;
			}
			if(Leadone.type==1){
				if(Bomb.move_zadan_x<100){
					Bomb.move_zadan_x+=2;
					Bomb.move_zadan_y-=1;
				}else if(Bomb.move_zadan_x>=100&&Bomb.move_zadan_x<200){
					Bomb.move_zadan_x+=2;
					Bomb.move_zadan_y+=1;				
				}else{
					Keys.zhadan=false;
					Bomb.bang_zadan_x=Bomb.zadan_x+Bomb.move_zadan_x;
					Bomb.bang_zadan_y=Bomb.zadan_y+Bomb.move_zadan_y;
					Bomb.move_zadan_x=0;
					Bomb.move_zadan_y=0;
				}		
			}else{
				if(Bomb.move_zadan_x>-100){
					Bomb.move_zadan_x-=2;
					Bomb.move_zadan_y-=1;
				}else if(Bomb.move_zadan_x<=-100&&Bomb.move_zadan_x>-200){
					Bomb.move_zadan_x-=2;
					Bomb.move_zadan_y+=1;				
				}else{
					Keys.zhadan=false;
					Bomb.bang_zadan_x=Bomb.zadan_x+Bomb.move_zadan_x;
					Bomb.bang_zadan_y=Bomb.zadan_y+Bomb.move_zadan_y;
					Bomb.move_zadan_x=0;
					Bomb.move_zadan_y=0;
				}
			}
		}
		
		//主角死了
		if(is_die){
			Keys.stand=false;  
			//Keys.move=false;//我的改动-胜
			ldie.dieLeadone(g);       
		}
		
		if(Keys.move){                          //主角移动
			leadone.move(g);		
		}else if(Keys.space){               //主角开枪
			leadone.attack(g);
			if(Leadone.is_knife){
				if(Leadone.type_knife<5){
					if(ci<30){
						ci++;	
					}else{ 
						//刀声
						if(Leadone.type_knife==0){
							music.mc_knife();
						}
						Leadone.type_knife++;
						ci=0;
					}
				}else{
					Leadone.type_knife=0;
					Keys.space=false;
					Leadone.is_knife=false;		
				}
			}else{
				bullet.fly(g);
				if(Leadone.type_bullet<5){
					if(ci<50){
						ci++;
					}else{
						//开枪声音
						if(Leadone.type_bullet==0){
							music.mc_attack();
						}
						ci=0;
						Leadone.type_bullet++;
					}
				}else{
					Leadone.type_bullet=0;
				}
				if(Bullet.x<500){
					Bullet.x+=8;
				}else{	
					Bullet.x=0;
					Leadone.type_bullet=0;
					Keys.space=false;
				}	
			}
		}else if(Keys.stand){                      //主角站着
			leadone.stand(g);
			if(Leadone.type_stand<2){
				if(ci>50){
					Leadone.type_stand++;
					ci=0;
				}else ci++;				
			}else{
				Leadone.type_stand=0;
			}
		}else if(Keys.jump){		                   //主角跳跃
			leadone.jump(g);
			if(Leadone.type_jump<7 && Leadone.move_y>-100){
				if(ci_jump>20){
					Leadone.type_jump++;
					ci_jump=0;
				}else{
					ci_jump++;
					Leadone.move_y-=1;
				}
			}else{
				Leadone.type_jump=0;
				Keys.stand=true;
				Keys.jump=false;
				Leadone.move_y=0;
			}
		}        
	}	
	
	class AloneThread extends Thread{
	    public void run(){
	    	while(true){
	    		try{
	    		}catch(Exception e){
	    		}
	    		repaint();
	    	}
	    }	
	}	
	public void win(){
		if(!Boss.boss_lifes){
			int i=JOptionPane.showOptionDialog(this,h, 
					null, JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,img,str,str[0]);	
			if(i==0||i==1){
				System.exit(0);
			}
			Boss.boss_lifes=false;
		}
	}
	public static void main(String[] args){
		background=new Background();
		JFrame frame=new JFrame();
		frame.add(background);
		frame.addKeyListener(new Keys());
		frame.setSize(1000,640);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//背景音乐
		music.mc_open();
	}
}

