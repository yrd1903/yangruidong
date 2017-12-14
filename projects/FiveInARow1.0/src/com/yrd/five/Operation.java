package com.yrd.five;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Operation implements MouseListener{

	//����Ҫ���Ƽ���������Panel
	ChessPanel chessPanel = null;
	//���������������
	int xClicked;
	int yClicked;
	//���̻�׼���꣨�������Ͻ����꣩,������
	int xRef;
	int yRef;
	int distance;
	//���̶�ά����
	int[][] chessValue;
	//��������λ��,��һλ���X�������ڶ�λ���Y����
	int[] location;
	//���岽��
	int stepNum;
	//��Ϸʤ��
	int result;

	//���캯��
	public Operation(ChessPanel chessPanel){
		this.chessPanel = chessPanel;
		this.xRef = chessPanel.xRef;
		this.yRef = chessPanel.yRef;
		this.distance = chessPanel.distance;
		this.chessValue = chessPanel.chessValue;
		stepNum = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
				//��������������λ��ֵ��Ϊ0��˵���˴��Ѿ����������£����򷵻�
				return;
			}
			if(this.stepNum%2==0){
				//��������
				this.chessValue[location[1]][location[0]] = 1;
			} else if (this.stepNum%2!=0){
				//�������
				this.chessValue[location[1]][location[0]] = 2;
			}
			//�ػ�����
			chessPanel.repaint();
			//���岽������1
			this.stepNum++;
			System.out.println("step:"+stepNum);
			
			//�ж�ʤ��
			result = this.checkResult(chessValue);
			//������
			this.processResult(result);
		}
	}
	
	//ͨ����������������ж�����λ��,��������
	public int[] locateChess(int x,int y){
		int[] location = new int[2];
		//��������������
		int xNum = (x-xRef+distance/2)/distance;
		int yNum = (y-yRef+distance/2)/distance;
//		System.out.println(xNum);
//		System.out.println(yNum);
		location[0] = xNum;
		location[1] = yNum;
		return location;
	}
	
	//�ж��Ƿ����ʤ��  ����0��û�зֳ�ʤ��      ����1������ʤ      ����2������ʤ
	public int checkResult(int[][] chessValue){
		//�����жϽ������,��ʼû�зֳ�ʤ�� 0
		int result = 0;
		//ɨ���ж�15��
		for(int j=0;j<15;j++)
		{
			for(int i=0;i<11;i++)
			{
				//��ÿһ�е�ǰ11�����ӽ����жϣ��Ƿ�������������������
				int sameNum = 0;
				for(int v=i;v<i+4;v++)//�Ƚ��Ĵ�
				{
					if(chessValue[v][j]==chessValue[v+1][j]){
						//���������ͬһ��ɫ
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//ɨ���ж�15��
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<11;j++)
			{
				//��ÿһ�е�ǰ11�����ӽ����жϣ��Ƿ�������������������
				int sameNum = 0;
				for(int v=j;v<j+4;v++)//�Ƚ��Ĵ�
				{
					if(chessValue[i][v]==chessValue[i][v+1]){
						//���������ͬһ��ɫ
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//ɨ���ж���б�·�
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//�Ƚ��Ĵ�
					if(chessValue[j+v][(j+i)+v]==chessValue[j+v+1][(j+i)+v+1]){
						//System.out.println((j+v)+"--"+(j+i+v));
						//���������ͬһ��ɫ
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//ɨ���ж���б�·�
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//�Ƚ��Ĵ�
					if(chessValue[(j+i)+v][j+v]==chessValue[(j+i)+v+1][j+v+1]){
						//���������ͬһ��ɫ
						System.out.println((j+i+v)+"--"+(j+v));
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//ɨ���ж���б�Ϸ�
		for(int i=0;i<11;i++){
			for(int j=0;j<i+1;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//�Ƚ��Ĵ�
					if(chessValue[4+i-j-v][j+v]==chessValue[4+i-j-v-1][j+v+1]){
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//ɨ����б�Ϸ�
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				int sameNum = 0;
				for(int v=0;v<4;v++){//�Ƚ��Ĵ�
					if(chessValue[14-j-v][i+j+v]==chessValue[14-j-v-1][i+j+v+1]){
						sameNum++;
					}
				}
				if(sameNum==4){
					//����ĴαȽ϶�����ȣ����������������
					//ȡ��������ɫ
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
		
		//���ǰ����ж϶�û�з��أ���û�зֳ�ʤ��������0
		return 0;
	}
	
	//������Ϸ
	public void reset(int[][] chessValue){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				chessValue[i][j] = 0;
			}
		}
		this.chessPanel.repaint();
	}
	
	//���ݽ��������Ӧ����
	public void processResult(int res){
		
		if(res==1){
			//0:��    1:��    2:ȡ��
			int type = JOptionPane.showConfirmDialog(null, "����ʤ�������'��'���¿�ʼ��Ϸ�����'��'������Ϸ��", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
			//�����û����������ת
			if(type==0){
				this.reset(chessValue);
			} else if(type==1){
				System.exit(0);
			} else {
				System.exit(0);
			}
		}
		if(res==2){
			int type = JOptionPane.showConfirmDialog(null, "����ʤ�������'��'���¿�ʼ��Ϸ�����'��'������Ϸ��", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
			//�����û����������ת
			if(type==0){
				this.reset(chessValue);
			} else if(type==1){
				System.exit(0);
			} else {
				System.exit(0);
			}
		}	
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
