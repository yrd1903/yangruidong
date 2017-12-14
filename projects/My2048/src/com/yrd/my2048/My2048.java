/**
 * 
 * 主界面
 * 
 */
package com.yrd.my2048;
import java.awt.*;
import javax.swing.*;

public class My2048 extends JFrame{

	BlockPanel blockPanel = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		My2048 my2048 = new My2048();
	}
	
	//构造函数
	public My2048(){
		blockPanel = new BlockPanel();
		this.add(blockPanel);

		this.setSize(400,400);
		this.setLocation(400, 200);
		this.setTitle("MY2048");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
