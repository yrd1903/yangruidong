/**
 * 
 * 方块类
 * 
 */
package com.yrd.my2048;
import java.awt.*;
import javax.swing.*;

public class Block extends JLabel{

	//方块数字的值
	private int value;

	//构造函数
	public Block(int value){
		
		this.setFont(new Font("font", Font.PLAIN, 40));//设定字体
		this.setOpaque(true);//设置Label为不透明
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		//定义方块的数字和颜色
		this.setValue(value);
		this.setColor();
		
	}
	
	//设置方块数字
	private void setValue(int value){
		if(value!=0){
			this.setText(String.valueOf(value));
		} else {
			//如果value为0，则为空方块，不显示任何数字
			this.setText("");
		}
		this.value = value;
	}
	
	//得到方块数字
	public int getValue(){
		return this.value;
	}
	
	//设置方块颜色
	private void setColor(){	
		//根据成员变量value设置不同的颜色
		switch (value){
	    case 0:
	      setBackground(Color.gray);
	      break;
	    case 2:
	      setBackground(new Color(238, 228, 218));
	      break;
	    case 4:
	      setBackground(new Color(238, 224, 198));
	      break;
	    case 8:
	      setBackground(new Color(243, 177, 116));
	      break;
	    case 16:
	      setBackground(new Color(243, 177, 116));
	      break;
	    case 32:
	      setBackground(new Color(248, 149, 90));
	      break;
	    case 64:
	      setBackground(new Color(249, 94, 50));
	      break;
	    case 128:
	      setBackground(new Color(239, 207, 108));
	      break;
	    case 256:
	      setBackground(new Color(239, 207, 99));
	      break;
	    case 512:
	      setBackground(new Color(239, 203, 82));
	      break;
	    case 1024:
	      setBackground(new Color(239, 199, 57));
	      break;
	    case 2048:
	      setBackground(new Color(239, 195, 41));
	      break;
	    case 4096:
	      setBackground(new Color(255, 60, 57));
	      break;
	    }

	}
	
	//刷新方块状态
	public void refreshBlock(int value){
		this.setValue(value);
		this.setColor();
	}
}
