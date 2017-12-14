package alloyContra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public  class Hostage{
	private Background b=Background.background;
	public static int oder_x=700,oder_y=400,oder_move_x=0,oder_type=10,gift_type=2,ci_oder=0,gift_y=0,oder_position_x=0,oder_position_y=0;
	public static boolean is_rescued=false;
	private Toolkit kit=Toolkit.getDefaultToolkit();
	private Image[] oder=new Image[42];{	     //老头的图片
		for(int i=0;i<42;i++){
			oder[i]=kit.getImage("img\\oder"+i+".png");
		}
	}
	private Image[] gift=new Image[5];{            //奖品图片
		for(int i=0;i<5;i++){
			gift[i]=kit.getImage("img\\gift"+i+".png");
		}
	}
	//得到奖品
	public void prize(Graphics g){
		g.drawImage(gift[gift_type], oder_x-373+b.x, oder_y+gift_y+5,b);
		g.drawImage(gift[gift_type], oder_x-373+b.x-800, oder_y+gift_y+5,b);
	}
	//老头被绑架了
	public void kidnap(Graphics g){
		//检查是否近距离
		if((Leadone.leadone_position_x>=oder_x+b.x-50&&Leadone.leadone_position_x<=oder_x+b.x+50)&&
				(Leadone.leadone_position_y>=oder_y-50&&Leadone.leadone_position_y<=oder_y+50)){
			Leadone.is_knife=true;
		}
		g.drawImage(oder[oder_type],oder_x+b.x,oder_y,b);
		if(oder_type<13){
			if(ci_oder<100){
				ci_oder++;
			}else{
				oder_type++;
				ci_oder=0;
			}
		}else{
			oder_type=10;
		}
	}
	//老头获救
	public void rescued(Graphics g){
		g.drawImage(oder[oder_type],oder_x-oder_move_x+b.x,oder_y,b);
		if(oder_type>=27&&oder_type<=28){
			b.have_gift=true;
			gift_y+=1;
		}
		if(oder_type<38){
			if(ci_oder<40){
				ci_oder++;
			}else{
				oder_type++;
				ci_oder=0;
			}
		}else{
			oder_type=10;
			oder_x+=800;
			oder_y+=3;
			oder_move_x=0;
			gift_y=0;
			Background.spark_x=0;
			is_rescued=false;
			Keys.is_space=false;
			Keys.is_zadan=false;
		}
	}
}
