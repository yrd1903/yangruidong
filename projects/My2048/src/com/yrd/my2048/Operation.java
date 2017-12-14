/**
 * 
 * 对BlockPanel面板的操作判断类
 * 
 */
package com.yrd.my2048;
import java.awt.Window;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Operation implements MouseListener {

	//定义x,y坐标
	int xPressed;
	int yPressed;
	int xReleased;
	int yReleased;
	int xDistance;
	int yDistance;
	//定义横纵标志
	int xy;
	//定义方块数字二维数组
	int[][] blockValue;
	//定义方块对象二维数组
	Block[][] block = null;
	//定义方块面板对象
	BlockPanel blockPanel = null;
	
	//构造函数,此类操作的对象是BlockPanel
	public Operation(BlockPanel blockPanel) {
		this.blockValue = blockPanel.getBlockValue();
		this.block = blockPanel.getBlock();
		this.blockPanel = blockPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		xPressed = e.getX();
		yPressed = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		xReleased = e.getX();
		yReleased = e.getY();
		
		xDistance = xReleased-xPressed;
		yDistance = yReleased-yPressed;
		
		//首先判断是纵向移动还是横向移动
		if(Math.abs(xDistance)>Math.abs(yDistance)){
			//横向移动
			xy = 1;
		} else if(Math.abs(xDistance)<Math.abs(yDistance)){
			//纵向移动
			xy = 2;
		} else {
			//无效滑动(45度滑动),不做响应
			xy = 0;
		}
		
		//判断左或者右
		if(xy==1){
			if(xDistance>0){
				//向右滑动
//				System.out.println("向右滑动");
				this.rightProcess();
			} else if (xDistance<0){
				//向左滑动
//				System.out.println("向左滑动");
				this.leftProcess();
			}
		}
		//判断上或者下
		if(xy==2){
			if(yDistance>0){
				//向下滑动
//				System.out.println("向下滑动");
				this.downProcess();
			} else if (yDistance<0){
				//向上滑动
//				System.out.println("向上滑动");
				this.upProcess();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//向左滑动处理函数
	public void leftProcess(){
		//刷新方块数字值数组
		for(int i=0;i<4;i++)
		{
			//依次确定每行各个数应该赋值多少
			for(int j=0;j<4;j++)
			{
				//方块不等于0的情况
				if(blockValue[i][j]!=0)
				{
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				//方块等于0的情况，将上面第一个不等于0的数向下移动	
				} else if (blockValue[i][j]==0)
				{
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//将上面第一个非0的数占据这个数
							blockValue[i][j] = blockValue[i][k];
							blockValue[i][k] = 0;
							break;//找到第一个非0的数就跳出循环
						}
					}
					//按照不等于0继续判断
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();	
		this.blockPanel.refreshBlockPanel(this.blockValue);
		//检查是否结束游戏
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//向右滑动处理函数
	public void rightProcess(){
		//刷新方块数字值数组
		for(int i=0;i<4;i++)
		{
			//依次确定每行各个数应该赋值多少
			for(int j=3;j>=0;j--)
			{
				//方块不等于0的情况
				if(blockValue[i][j]!=0)
				{
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				//方块等于0的情况，将上面第一个不等于0的数向下移动	
				} else if (blockValue[i][j]==0)
				{
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//将上面第一个非0的数占据这个数
							blockValue[i][j] = blockValue[i][k];
							blockValue[i][k] = 0;
							break;
						}
					}
					//按照不等于0继续判断
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}	
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);	
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//向上滑动处理函数
	public void upProcess(){
		//刷新方块数字值数组
		for(int j=0;j<4;j++)
		{
			//依次确定每行各个数应该赋值多少
			for(int i=0;i<4;i++)
			{
				//方块不等于0的情况
				if(blockValue[i][j]!=0)
				{
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				//方块等于0的情况，将上面第一个不等于0的数向下移动	
				} else if (blockValue[i][j]==0)
				{
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//将上面第一个非0的数占据这个数
							blockValue[i][j] = blockValue[k][j];
							blockValue[k][j] = 0;
							break;
						}
					}
					//按照不等于0继续判断
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//向下滑动处理函数
	public void downProcess(){
		//刷新方块数字值数组
		for(int j=0;j<4;j++)
		{
			//依次确定每行各个数应该赋值多少
			for(int i=3;i>=0;i--)
			{
				//方块不等于0的情况
				if(blockValue[i][j]!=0)
				{
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				//方块等于0的情况，将上面第一个不等于0的数向下移动	
				} else if (blockValue[i][j]==0)
				{
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//将上面第一个非0的数占据这个数
							blockValue[i][j] = blockValue[k][j];
							blockValue[k][j] = 0;
							break;
						}
					}
					//接着继续按照不等于0的情况判断
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//如果上面第一个非0的数与这个数相等，则改变这个数，否则不改变，并跳出循环
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);	
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//在剩余空白处随机产生一个方块
	public void generate(){
		//得到空白方块的个数
		int numTem1=0;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(blockValue[i][j]==0)
				{
					numTem1++;
				}
			}
		}

		//如果numTem1=0，说明方块已经填满，不再产生新的方块 
		if(numTem1==0){
			return;
		}
		//在numTem范围内产生一个随机数
		int tem = (int)(Math.random()*numTem1);

		//把产生的随机数赋值到方块数字值数组中
		int numTem2=0;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(blockValue[i][j]==0)
				{
					if(numTem2==tem)
					{
						blockValue[i][j] = 2;
					}
					numTem2++;
				}
			}
		}
	}
	
	//检查是否结束游戏
	public boolean checkOver(int[][] blockValue){
		//扫描每行
		for(int i=0;i<4;i++){
			for(int j=0;j<4-1;j++){
				if(blockValue[i][j]==blockValue[i][j+1]){
					return false;
				}
			}
		}	
		//扫描每列
		for(int j=0;j<4;j++){
			for(int i=0;i<4-1;i++){
				if(blockValue[i][j]==blockValue[i+1][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	//游戏结束处理
	public void overProcess(){
		//点击是：0  点击否：1  点击取消：2
		int type = JOptionPane.showConfirmDialog(null, "游戏已结束，点击'是'重新开始游戏，点击'否'结束游戏！", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
		if(type==0){
			this.blockPanel.reset(blockValue);
		} else if(type==1){
			System.exit(0);
		} 		
	}

}
