package com.yrd.five;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Player implements MouseListener{

	//定义要控制监听的棋盘Panel
	ChessPanel chessPanel = null;
	//棋盘二维数组
	int[][] chessValue;
	//定义鼠标点击的坐标
	int xClicked;
	int yClicked;
	//棋盘基准坐标（网格左上角坐标）,网格宽度
	int xRef;
	int yRef;
	int distance;	
	//落子行列位置,第一位存放X列数，第二位存放Y行数
	int[] location;
	//定义电脑对象，用于玩家下完棋把棋权交给电脑
	Computer computer = null;
	//定义裁判
	Judge judge = null;

	
	
	/**
	 * 
	 * 构造函数
	 * @param chessPanel
	 * 
	 */
	public Player(ChessPanel chessPanel){
		this.chessPanel = chessPanel;
		this.xRef = chessPanel.xRef;
		this.yRef = chessPanel.yRef;
		this.distance = chessPanel.distance;
		this.chessValue = chessPanel.chessValue;
		this.computer = new Computer(chessPanel, this);
		this.judge = new Judge(chessPanel);
	}
	
	
	/**
	 * 
	 * 鼠标点击事件处理函数
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
			
		//一旦进来鼠标点击处理函数，先移除鼠标监听，在处理完这次点击后再继续监听
		//防止电脑还没下棋用户多次点击错误下子
		this.chessPanel.removeMouseListener(this);
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
				//如果鼠标点击的棋位置值不为0，说明此处已经有棋子落下，程序返回
				//必须加上重新监听的语句
				this.chessPanel.addMouseListener(this);
				return;				
			}			
			//每一次有效点击，即每次玩家的有效下棋，都会触发电脑下棋			
			//玩家下棋（下白棋）
			this.chessValue[location[1]][location[0]] = 2;
			//裁判处理
			this.judge.judgeAndProcess();

			
			//如果玩家胜利，程序返回，电脑不再下棋
			if(judge.result==2){				
				this.chessPanel.addMouseListener(this);
				return;
			}

			
			//玩家下完，电脑接着下棋
			//每次都要新建一个线程，因为线程执行完后就会死亡
			Thread computerTurn = new Thread(computer);
			computerTurn.start();
		}
	}
	
	/**
	 * 
	 * 通过鼠标点击的坐标来判断落子位置,返回数
	 * location[1]:落子的行数    location[0]:落子的列数
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] locateChess(int x,int y){
		int[] location = new int[2];
		//计算落子行列数
		int xNum = (x-xRef+distance/2)/distance;
		int yNum = (y-yRef+distance/2)/distance;
		location[0] = xNum;
		location[1] = yNum;
		return location;
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
