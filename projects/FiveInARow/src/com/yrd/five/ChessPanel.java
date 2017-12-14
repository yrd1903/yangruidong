package com.yrd.five;

import java.awt.*;

import javax.swing.*;

public class ChessPanel extends JPanel {

	//定义背景，黑棋，白棋
	ImageIcon bg = null;
	Image background = null;
	ImageIcon blackChess = null;
	ImageIcon whiteChess = null;
	
	//定义棋子二维数组(0代表没有棋子，1代表黑棋，2代表白棋)
	int[][] chessValue;
	
	//定义棋盘左上角基准位置坐标,网格宽度
	int xRef;
	int yRef;
	int distance;
	
	//定义监听控制对象
	Player player = null;

	
	/**
	 * 
	 * 构造函数
	 * 
	 */
	public ChessPanel(){
		
		chessValue = new int[15][15];
		xRef = 75;
		yRef = 70;
		distance = 36;
		
		bg = new ImageIcon(ChessPanel.class.getResource("/bg.jpg"));
		background = bg.getImage();
		blackChess = new ImageIcon(ChessPanel.class.getResource("/blackchess.GIF"));
		whiteChess = new ImageIcon(ChessPanel.class.getResource("/whitechess.GIF"));
		
		player = new Player(this);
		//注册监听
		this.addMouseListener(player);
		
	}
	
	/**
	 * 
	 * 
	 * 画图函数，绘制棋盘界面
	 * 
	 */
	public void paint(Graphics g){
		
		super.paint(g);
		//画背景
		g.drawImage(background,0,0,this.getWidth(),this.getHeight(),this);
		
		//画网格,线条粗细2个像素
		for(int i=0;i<15;i++){
			g.fillRect(xRef, yRef+distance*i, 14*distance, 2);
		}
		for(int i=0;i<15;i++){
			g.fillRect(xRef+distance*i, yRef, 2, 14*distance);
		}
		
		//画五个黑点
		g.fillRect(xRef+3*distance-3, yRef+3*distance-3, 8, 8);
		g.fillRect(xRef+11*distance-3, yRef+3*distance-3, 8, 8);
		g.fillRect(xRef+3*distance-3, yRef+11*distance-3, 8, 8);
		g.fillRect(xRef+11*distance-3, yRef+11*distance-3, 8, 8);
		g.fillRect(xRef+7*distance-3, yRef+7*distance-3, 8, 8);
		
		//画棋子
		this.paintChess(g, chessValue);

	}
	
	/**
	 * 
	 * 画棋子	
	 * @param g
	 * @param chessValue
	 * 
	 */
	public void paintChess(Graphics g,int[][] chessValue){
			
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(chessValue[i][j]==1)
				{
					//画黑棋
					//棋子边长30
					blackChess.paintIcon(this, g, (xRef-15+distance*j), (yRef-15+distance*i));
					
				} else if(chessValue[i][j]==2)
				{
					//画白棋
					whiteChess.paintIcon(this, g, (xRef-15+distance*j), (yRef-15+distance*i));
					
				} else if(chessValue[i][j]==0)
				{
					//没有动作
				}
			}
		}		
	}
	
}
