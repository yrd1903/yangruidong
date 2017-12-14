package com.yrd.five;

import javax.swing.JOptionPane;

public class Judge {

	//定义棋盘对象
	ChessPanel chessPanel = null;
	//定义棋盘二维数组
	int[][] chessValue;
	//定义胜负结果
	int result;
	
	
	/**
	 * 
	 * 构造函数
	 * @param chessPanel
	 * 
	 */
	public Judge(ChessPanel chessPanel){
		//Judge需要知道棋盘的情况		
		this.chessPanel = chessPanel;
		this.chessValue = chessPanel.chessValue;
	}

	/**
	 * 
	 * 判断胜负并且处理结果
	 * 
	 */
	public void judgeAndProcess(){
		//重绘棋盘
		chessPanel.repaint();
		//判断胜负
		result = this.checkResult(chessValue);
		//处理结果
		this.processResult(result);		
	}
	
	/**
	 * 
	 * 根据结果进行相应处理
	 * @param res
	 * 
	 */
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
	
	/**
	 * 
	 * 重置游戏
	 * @param chessValue
	 * 
	 */
	public void reset(int[][] chessValue){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				chessValue[i][j] = 0;
			}
		}
		this.chessPanel.repaint();
	}
		
	
	/**
	 * 
	 * 判断是否决出胜负  返回0：没有分出胜负      返回1：黑棋胜      返回2：白棋胜
	 * @param chessValue
	 * @return
	 * 
	 */
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
						//System.out.println((j+i+v)+"--"+(j+v));
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

	

	
}
