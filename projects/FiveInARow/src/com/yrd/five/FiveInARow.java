
/**
 * 
 * 
 * 
 * ���ܣ��˻���ս������
 * ���������ö�ÿһ����λ�������ֵ��㷨��ֻ���ǵ�ǰһ��
 * ���ڣ�2015/12/28
 * 
 * 
 */

package com.yrd.five;
import javax.swing.*;

public class FiveInARow extends JFrame{

	ChessPanel chessPanel = null;
	/**
	 * 
	 * ���������
	 * @param args
	 */
	public static void main(String[] args) {		
		new FiveInARow();		
	}

	/**
	 * 
	 * ���캯��
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
