package com.yrd.five;

import javax.swing.JOptionPane;

public class Computer implements Runnable{

	//定义棋盘
	ChessPanel chessPanel = null;
	//定义玩家，用于重新给玩家注册监听
	Player player = null;
	int[][] chessValue = null;	
	//得分表,初始化0，非空位用-1代表
	int[][] scoreTable;
	//定义裁判对象
	Judge judge = null;
	
	
	
	//构造函数
	public Computer(ChessPanel chessPanel,Player player) {
		this.chessPanel = chessPanel;
		this.player = player;
		this.chessValue = chessPanel.chessValue;
		this.scoreTable = new int[15][15];
		this.judge = new Judge(chessPanel);
	}
	
	
	
	@Override
	public void run() {
			
		//延缓
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//电脑下棋（下黑棋）
		this.computerGo(chessValue);
		//裁判处理
		this.judge.judgeAndProcess();
		//继续让player监听，棋权交给玩家
		this.chessPanel.addMouseListener(player);
	}
	
	
	
	/**
	 * 
	 * 电脑下棋
	 * @param chessValue
	 * 
	 */
	public void computerGo(int[][] chessValue){
		
		//计算每个空位置评分得分
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(chessValue[i][j]==0){
					//只有位置没有棋才计算得分
					scoreTable[i][j] = this.calculateDotScore(i, j);
				}				
			}
		}
		
		//找出空位置中得分最大的位置
		//定义得分最大位置的坐标
		int scoreMaxX = 0;
		int scoreMaxY = 0;
		//首先假设得分最大值为0
		int scoreMaxValue = 0;
		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(chessValue[i][j]==0){
					//System.out.println(scoreTable[i][j]);
					if(scoreTable[i][j]>scoreMaxValue){
						scoreMaxValue = scoreTable[i][j];
						scoreMaxX = i;
						scoreMaxY = j;
					}
				}
				
			}
		}
		
		//在得分最大的位置电脑下棋
		chessValue[scoreMaxX][scoreMaxY] = 1;
		
	}
	
	/**
	 * 
	 * 计算包含某一位置的所有五元组的得分的和
	 * @param row
	 * @param col
	 * @return
	 * 
	 */
	public int calculateDotScore(int row,int col){
		
		//定义这个位置分数
		int dotScore = 0;
		
		//扫描15列中的所有五元组
		for(int j=0;j<15;j++)
		{
			for(int i=0;i<11;i++)
			{
				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				for(int v=i;v<i+5;v++){
					if(v==row && j==col){
						isIncluded = true;
						break;
					}
				}
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=i;v<i+5;v++)
					{
						if(chessValue[v][j]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[v][j]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}	
			}
		}
		
		//扫描判断15行中的所有五元组
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<11;j++)
			{
				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				for(int v=j;v<j+5;v++)
				{
					if(i==row && v==col){
						isIncluded = true;
						break;
					}
				}
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=j;v<j+5;v++)
					{
						if(chessValue[i][v]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[i][v]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//扫描右斜下方中的所有五元组
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				for(int v=0;v<5;v++){
					if((j+v)==row && ((j+i)+v)==col){
						isIncluded = true;
						break;					}
				}
				
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[j+v][(j+i)+v]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[j+v][(j+i)+v]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//扫描左斜下方中的所有五元组
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if(((j+i)+v)==row && (j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[(j+i)+v][j+v]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[(j+i)+v][j+v]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}			
		}
		
		//扫描左斜上方中的所有五元组
		for(int i=0;i<11;i++){
			for(int j=0;j<i+1;j++){

				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if((4+i-j-v)==row && (j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[4+i-j-v][j+v]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[4+i-j-v][j+v]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//扫描右斜上方中的所有五元组
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//判断此五元组是否包含这个位置
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if((14-j-v)==row && (i+j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//黑子个数，白子个数
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[14-j-v][i+j+v]==1){
							//如果棋子是黑色
							blackNum++;
						} else if(chessValue[14-j-v][i+j+v]==2){
							//如果棋子是白色
							whiteNum++;
						}
					}
					//根据黑白棋子的个数对这个五元组评分
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//返回这个位置的得分
		return dotScore;		
	}
	
	/**
	 * 
	 * 根据黑白棋的个数计算五元组的分数--评分表
	 * @param blackNum
	 * @param whiteNum
	 * @return
	 * 
	 */
	public int calculateEleScore(int blackNum,int whiteNum){
		//定义得分
		int score = 0;
		//空五元组
		if(blackNum==0 && whiteNum==0){
			score = 7;
		} else if(whiteNum==0){
			//只有黑棋的情况，电脑有利的五元组，在此处下棋是攻击
			switch(blackNum){
			case 1:score = 35;break;
			case 2:score = 800;break;
			case 3:score = 15000;break;
			case 4:score = 800000;break;
			default:score = 0;break;
			}
		} else if(blackNum==0){
			//只有白棋
			//此时是对方有利的五元组，在对方的有利五元组内下棋是防守，也应该加分
			switch(whiteNum){
			case 1:score = 15;break;
			case 2:score = 400;break;
			case 3:score = 1800;break;
			case 4:score = 100000;break;
			default:score = 0;break;
			}
		} else {
			score = 0;
		}
		return score;
	}	
		
}
