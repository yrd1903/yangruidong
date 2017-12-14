package com.yrd.five;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Player implements MouseListener{

	//����Ҫ���Ƽ���������Panel
	ChessPanel chessPanel = null;
	//���̶�ά����
	int[][] chessValue;
	//���������������
	int xClicked;
	int yClicked;
	//���̻�׼���꣨�������Ͻ����꣩,������
	int xRef;
	int yRef;
	int distance;	
	//��������λ��,��һλ���X�������ڶ�λ���Y����
	int[] location;
	//������Զ�������������������Ȩ��������
	Computer computer = null;
	//�������
	Judge judge = null;

	
	
	/**
	 * 
	 * ���캯��
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
	 * ������¼�������
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
			
		//һ����������������������Ƴ����������ڴ�������ε�����ټ�������
		//��ֹ���Ի�û�����û���ε����������
		this.chessPanel.removeMouseListener(this);
		xClicked = e.getX();
		yClicked = e.getY();
		System.out.println("x:"+e.getX());
		System.out.println("y:"+e.getY());
		//ͨ����ȡ��������ֵ���ж�����λ��
		location = this.locateChess(xClicked, yClicked);
		//�ı����������ֵ
		if((location[0]>=0 && location[0]<=14 && location[1]>=0 && location[1]<=14))
		{
			//ֻ�е���������㷶Χ�ڲ��ػ�����,���������Ч���
			//��Ҫ�ж��Ƿ��Ѿ�����������
			if(this.chessValue[location[1]][location[0]]!=0){
				//������������λ��ֵ��Ϊ0��˵���˴��Ѿ����������£����򷵻�
				//����������¼��������
				this.chessPanel.addMouseListener(this);
				return;				
			}			
			//ÿһ����Ч�������ÿ����ҵ���Ч���壬���ᴥ����������			
			//������壨�°��壩
			this.chessValue[location[1]][location[0]] = 2;
			//���д���
			this.judge.judgeAndProcess();

			
			//������ʤ�������򷵻أ����Բ�������
			if(judge.result==2){				
				this.chessPanel.addMouseListener(this);
				return;
			}

			
			//������꣬���Խ�������
			//ÿ�ζ�Ҫ�½�һ���̣߳���Ϊ�߳�ִ�����ͻ�����
			Thread computerTurn = new Thread(computer);
			computerTurn.start();
		}
	}
	
	/**
	 * 
	 * ͨ����������������ж�����λ��,������
	 * location[1]:���ӵ�����    location[0]:���ӵ�����
	 * @param x
	 * @param y
	 * @return
	 */
	public int[] locateChess(int x,int y){
		int[] location = new int[2];
		//��������������
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
