package alloyContra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Obstruction {
	private Background b=Background.background;
	public static int barrier_x=750,barrier_y=500,barrier_type=0,barrier_bang_type=0,ci_barrier=0;
	public static boolean is_bang=false,is_knife_obstruction=false;
	private Toolkit kit=Toolkit.getDefaultToolkit();
	 //ÕÏ°­ÎïµÄÍ¼Æ¬
	private Image[] barrier=new Image[4];{	    
		for(int i=0;i<4;i++){
			barrier[i]=kit.getImage("img\\barrier"+i+".png");
		}
	}
	//ÕÏ°­Îï±¬Õ¨µÄÍ¼Æ¬
	private Image[][] barrier_bang=new Image[4][6];{
		for(int i=0;i<4;i++){
			for(int j=0;j<6;j++){
				barrier_bang[i][j]=kit.getImage("img\\barrier_bang"+i+j+".png");
			}
		}
	}
	//ÕÏ°­Îï
	public void explosion(Graphics g){
		//¼ì²éÊÇ·ñ½ü¾àÀë
		if((Leadone.leadone_position_x>=barrier_x+b.x-50&&Leadone.leadone_position_x<=barrier_x+b.x+50)&&
				(Leadone.leadone_position_y>=barrier_y-50&&Leadone.leadone_position_y<=barrier_y+50)){
			Leadone.is_knife=true;
		}
		g.drawImage(barrier[barrier_type],barrier_x+b.x,barrier_y,b);
	}
	//ÕÏ°­Îï±¬Õ¨
	public void  explosion_bang(Graphics g){
		g.drawImage(barrier_bang[barrier_type][barrier_bang_type], barrier_x+b.x, barrier_y, b);
		if(barrier_bang_type<5){
			if(ci_barrier<50){
				ci_barrier++;
			}else{
				barrier_bang_type++;
				ci_barrier=0;
			}
		}else{
			barrier_bang_type=0;
			barrier_x+=600;
			barrier_y-=3;
			if(barrier_type<3){		
				barrier_type++;
			}else{
				barrier_type=0;
			}
			is_bang=false;
			Keys.is_space=false;
			Keys.is_zadan=false;
			Background.spark_x=0;
		}
	}
}
