/**
 * 
 * 界面Panel
 * 
 */
package com.yrd.my2048;
import java.awt.*;
import javax.swing.*;

public class BlockPanel extends JPanel{
	
	//定义4*4矩阵的方块对象数组
	Block[][] block = null;
	

	//定义4*4矩阵的方块数字值二维数组
	int[][] blockValue;
	
	

	//构造函数
	public BlockPanel(){
		
		this.setLayout(new GridLayout(4,4,5,5));
		block = new Block[4][4];
		blockValue = new int[4][4];
		Operation operation = new Operation(this);
		
		
		this.addMouseListener(operation);
		
		//产生两个随机数，用于生成初始的两个方块
		int tem1 = (int)(Math.random()*16);
		int tem2 = this.noRepeat(tem1);
		int tem = 0;

		//循环遍历产生面板中的所有方块对象（只有两个显示），并对方块数字数组赋值
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				//初始化两个方块，数字为2
				if(tem==tem1 || tem==tem2){
					block[i][j] = new Block(2);
					blockValue[i][j] = 2;
				} else {
					block[i][j] = new Block(0);
					blockValue[i][j] = 0;
				}
				
				this.add(block[i][j]);
				tem++;
			}
		}	
	}
	
	//刷新方块对象，重新排列
	public void refreshBlockPanel(int[][] blockValue){
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				block[i][j].refreshBlock(blockValue[i][j]);
			}
		}
	}
	
	//产生一个与已知数不重复的随机数
	public int noRepeat(int value){
		int tem=0;
		while((tem=(int)(Math.random()*16))==value){
			
		}
		return tem;
	}
	
	//复位，重新开始游戏
	public void reset(int[][] blockValue){
		
		//产生两个随机数，用于生成初始的两个方块
		int tem1 = (int)(Math.random()*16);
		int tem2 = this.noRepeat(tem1);
		int tem = 0;

		//循环遍历产生面板中的所有方块对象（只有两个显示），并对方块数字数组赋值
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				//初始化两个方块，数字为2
				if(tem==tem1 || tem==tem2){
					blockValue[i][j] = 2;
				} else {
					blockValue[i][j] = 0;
				}
				tem++;
			}
		}	
		//刷新
		this.refreshBlockPanel(blockValue);
	}
	
	
	public Block[][] getBlock() {
		return block;
	}
	
	public int[][] getBlockValue() {
		return blockValue;
	}
}
