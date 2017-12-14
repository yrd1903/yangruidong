
/**
 * 
 * 
 * 
 * 功能：人机对战五子棋
 * 描述：采用对每一个空位进行评分的算法，只考虑当前一步
 * 日期：2015/12/28
 * 
 * 
 */

package com.yrd.five;
import javax.swing.*;

public class FiveInARow extends JFrame{

	ChessPanel chessPanel = null;
	/**
	 * 
	 * 主函数入口
	 * @param args
	 */
	public static void main(String[] args) {		
		new FiveInARow();		
	}

	/**
	 * 
	 * 构造函数
	 * 
	 */
	public FiveInARow(){

		chessPanel = new ChessPanel();
		this.add(chessPanel);

		this.setTitle("Five-In-A-Row");
		this.setSize(650, 650);
		this.setLocation(350, 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
}
