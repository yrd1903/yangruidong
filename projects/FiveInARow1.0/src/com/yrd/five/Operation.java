package com.yrd.five;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Operation implements MouseListener{

	//定义要控制监听的棋盘Panel
	ChessPanel chessPanel = null;
	//定义鼠标点击的坐标
	int xClicked;
	int yClicked;
	//棋盘基准坐标（网格左上角坐标）,网格宽度
	int xRef;
	int yRef;
	int distance;
	//棋盘二维数组
	int[][] chessValue;
	//落子行列位置,第一位存放X列数，第二位存放Y行数
	int[] location;
	//下棋步数
	int stepNum;
	//游戏胜负
	int result;

	//构造函数
	public Operation(ChessPanel chessPanel){
		this.chessPanel = chessPanel;
		this.xRef = chessPanel.xRef;
		this.yRef = chessPanel.yRef;
		this.distance = chessPanel.distance;
		this.chessValue = chessPanel.chessValue;
		stepNum = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		xClicked = e.getX();
		yClicked = e.getY();
		System.out.println("x:"+e.getX());
		System.out.println("y:"+e.getY());
		//通过获取到的坐标值来判断落子位置
		location = this.locateChess(xClicked, yClicked);
		//改变棋盘数组的值
		if((location[0]>=0 && location[0]<=14 && location[1]>=0 && location[1]<=14))
		{
			//只有点击在网格格点范围内才重绘棋盘,否则就是无效点击
			//需要判断是否已经有棋子落下
			if(this.chessValue[location[1]][location[0]]!=0){
				//如果鼠标点击的棋子位置值不为0，说明此处已经有棋子落下，程序返回
				return;
			}
			if(this.stepNum%2==0){
				//黑棋先走
				this.chessValue[location[1]][location[0]] = 1;
			} else if (this.stepNum%2!=0){
				//白棋后走
				this.chessValue[location[1]][location[0]] = 2;
			}
			//重绘棋盘
			chessPanel.repaint();
			//下棋步数增加1
			this.stepNum++;
			System.out.println("step:"+stepNum);
			
			//判断胜负
			result = this.checkResult(chessValue);
			//处理结果
			this.processResult(result);
		}
	}
	
	//通过鼠标点击的坐标来判断落子位置,返回数组
	public int[] locateChess(int x,int y){
		int[] location = new int[2];
		//计算落子行列数
		int xNum = (x-xRef+distance/2)/distance;
		int yNum = (y-yRef+distance/2)/distance;
//		System.out.println(xNum);
//		System.out.println(yNum);
		location[0] = xNum;
		location[1] = yNum;
		return location;
	}
	
	//判断是否决出胜负  返回0：没有分出胜负      返回1：黑棋胜      返回2：白棋胜
	public int checkResult(int[][] chessValue){
		//定义判断结果变量,初始没有分出胜负 0
		int result = 0;
		//扫描判断15列
		for(int j=0;j<15;j++)
		{
			for(int i=0;i<11;i++)
			{
				//对每一列的前11个棋子进行判断，是否后面有连续的五个棋子
				int sameNum = 0;
				for(int v=i;v<i+4;v++)//比较四次
				{
					if(chessValue[v][j]==chessValue[v+1][j]){
						//如果棋子是同一颜色
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[i][j]==1){
						result = 1;
						return result;
					} else if (chessValue[i][j]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//扫描判断15行
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<11;j++)
			{
				//对每一行的前11个棋子进行判断，是否后面有连续的五个棋子
				int sameNum = 0;
				for(int v=j;v<j+4;v++)//比较四次
				{
					if(chessValue[i][v]==chessValue[i][v+1]){
						//如果棋子是同一颜色
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[i][j]==1){
						result = 1;
						return result;
					} else if (chessValue[i][j]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//扫描判断右斜下方
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//比较四次
					if(chessValue[j+v][(j+i)+v]==chessValue[j+v+1][(j+i)+v+1]){
						//System.out.println((j+v)+"--"+(j+i+v));
						//如果棋子是同一颜色
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[j][j+i]==1){
						result = 1;
						return result;
					} else if (chessValue[j][j+i]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//扫描判断左斜下方
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//比较四次
					if(chessValue[(j+i)+v][j+v]==chessValue[(j+i)+v+1][j+v+1]){
						//如果棋子是同一颜色
						System.out.println((j+i+v)+"--"+(j+v));
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[j+i][j]==1){
						result = 1;
						return result;
					} else if (chessValue[j+i][j]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//扫描判断左斜上方
		for(int i=0;i<11;i++){
			for(int j=0;j<i+1;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//比较四次
					if(chessValue[4+i-j-v][j+v]==chessValue[4+i-j-v-1][j+v+1]){
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[4+i-j][j]==1){
						result = 1;
						return result;
					} else if (chessValue[4+i-j][j]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//扫描右斜上方
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//比较四次
					if(chessValue[14-j-v][i+j+v]==chessValue[14-j-v-1][i+j+v+1]){
						sameNum++;
					}
				}
				if(sameNum==4){
					//如果四次比较都是相等，则有五个棋子连续
					//取出棋子颜色
					if(chessValue[14-j][i+j]==1){
						result = 1;
						return result;
					} else if (chessValue[14-j][i+j]==2){
						result = 2;
						return result;
					}
				}
			}
		}
		
		//如果前面的判断都没有返回，即没有分出胜负，返回0
		return 0;
	}
	
	//重置游戏
	public void reset(int[][] chessValue){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				chessValue[i][j] = 0;
			}
		}
		this.chessPanel.repaint();
	}
	
	//根据结果进行相应处理
	public void processResult(int res){
		
		if(res==1){
			//0:是    1:否    2:取消
			int type = JOptionPane.showConfirmDialog(null, "黑棋胜利！点击'是'重新开始游戏，点击'否'结束游戏！", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
			//根据用户点击进行跳转
			if(type==0){
				this.reset(chessValue);
			} else if(type==1){
				System.exit(0);
			} else {
				System.exit(0);
			}
		}
		if(res==2){
			int type = JOptionPane.showConfirmDialog(null, "白棋胜利！点击'是'重新开始游戏，点击'否'结束游戏！", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
			//根据用户点击进行跳转
			if(type==0){
				this.reset(chessValue);
			} else if(type==1){
				System.exit(0);
			} else {
				System.exit(0);
			}
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
