/**
 * 
 * 五子棋1.0版
 * 功能：人人对战，判断胜负
 * 
 * 
 * 
 */
package com.yrd.five;

import java.awt.*;

import javax.swing.*;

public class FiveInARow extends JFrame{

	ChessPanel chessPanel = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FiveInARow fiveInARow = new FiveInARow();
	}

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
