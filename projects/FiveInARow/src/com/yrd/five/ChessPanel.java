package com.yrd.five;

import java.awt.*;

import javax.swing.*;

public class ChessPanel extends JPanel {

	//���屳�������壬����
	ImageIcon bg = null;
	Image background = null;
	ImageIcon blackChess = null;
	ImageIcon whiteChess = null;
	
	//�������Ӷ�ά����(0����û�����ӣ�1������壬2�������)
	int[][] chessValue;
	
	//�����������Ͻǻ�׼λ������,������
	int xRef;
	int yRef;
	int distance;
	
	//����������ƶ���
	Player player = null;

	
	/**
	 * 
	 * ���캯��
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
		//ע�����
		this.addMouseListener(player);
		
	}
	
	/**
	 * 
	 * 
	 * ��ͼ�������������̽���
	 * 
	 */
	public void paint(Graphics g){
		
		super.paint(g);
		//������
		g.drawImage(background,0,0,this.getWidth(),this.getHeight(),this);
		
		//������,������ϸ2������
		for(int i=0;i<15;i++){
			g.fillRect(xRef, yRef+distance*i, 14*distance, 2);
		}
		for(int i=0;i<15;i++){
			g.fillRect(xRef+distance*i, yRef, 2, 14*distance);
		}
		
		//������ڵ�
		g.fillRect(xRef+3*distance-3, yRef+3*distance-3, 8, 8);
		g.fillRect(xRef+11*distance-3, yRef+3*distance-3, 8, 8);
		g.fillRect(xRef+3*distance-3, yRef+11*distance-3, 8, 8);
		g.fillRect(xRef+11*distance-3, yRef+11*distance-3, 8, 8);
		g.fillRect(xRef+7*distance-3, yRef+7*distance-3, 8, 8);
		
		//������
		this.paintChess(g, chessValue);

	}
	
	/**
	 * 
	 * ������	
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
					//������
					//���ӱ߳�30
					blackChess.paintIcon(this, g, (xRef-15+distance*j), (yRef-15+distance*i));
					
				} else if(chessValue[i][j]==2)
				{
					//������
					whiteChess.paintIcon(this, g, (xRef-15+distance*j), (yRef-15+distance*i));
					
				} else if(chessValue[i][j]==0)
				{
					//û�ж���
				}
			}
		}		
	}
	
}
