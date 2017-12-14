package com.yrd.five;

import javax.swing.JOptionPane;

public class Computer implements Runnable{

	//��������
	ChessPanel chessPanel = null;
	//������ң��������¸����ע�����
	Player player = null;
	int[][] chessValue = null;	
	//�÷ֱ�,��ʼ��0���ǿ�λ��-1����
	int[][] scoreTable;
	//������ж���
	Judge judge = null;
	
	
	
	//���캯��
	public Computer(ChessPanel chessPanel,Player player) {
		this.chessPanel = chessPanel;
		this.player = player;
		this.chessValue = chessPanel.chessValue;
		this.scoreTable = new int[15][15];
		this.judge = new Judge(chessPanel);
	}
	
	
	
	@Override
	public void run() {
			
		//�ӻ�
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�������壨�º��壩
		this.computerGo(chessValue);
		//���д���
		this.judge.judgeAndProcess();
		//������player��������Ȩ�������
		this.chessPanel.addMouseListener(player);
	}
	
	
	
	/**
	 * 
	 * ��������
	 * @param chessValue
	 * 
	 */
	public void computerGo(int[][] chessValue){
		
		//����ÿ����λ�����ֵ÷�
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(chessValue[i][j]==0){
					//ֻ��λ��û����ż���÷�
					scoreTable[i][j] = this.calculateDotScore(i, j);
				}				
			}
		}
		
		//�ҳ���λ���е÷�����λ��
		//����÷����λ�õ�����
		int scoreMaxX = 0;
		int scoreMaxY = 0;
		//���ȼ���÷����ֵΪ0
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
		
		//�ڵ÷�����λ�õ�������
		chessValue[scoreMaxX][scoreMaxY] = 1;
		
	}
	
	/**
	 * 
	 * �������ĳһλ�õ�������Ԫ��ĵ÷ֵĺ�
	 * @param row
	 * @param col
	 * @return
	 * 
	 */
	public int calculateDotScore(int row,int col){
		
		//�������λ�÷���
		int dotScore = 0;
		
		//ɨ��15���е�������Ԫ��
		for(int j=0;j<15;j++)
		{
			for(int i=0;i<11;i++)
			{
				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				for(int v=i;v<i+5;v++){
					if(v==row && j==col){
						isIncluded = true;
						break;
					}
				}
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=i;v<i+5;v++)
					{
						if(chessValue[v][j]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[v][j]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}	
			}
		}
		
		//ɨ���ж�15���е�������Ԫ��
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<11;j++)
			{
				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				for(int v=j;v<j+5;v++)
				{
					if(i==row && v==col){
						isIncluded = true;
						break;
					}
				}
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=j;v<j+5;v++)
					{
						if(chessValue[i][v]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[i][v]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//ɨ����б�·��е�������Ԫ��
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				for(int v=0;v<5;v++){
					if((j+v)==row && ((j+i)+v)==col){
						isIncluded = true;
						break;					}
				}
				
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[j+v][(j+i)+v]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[j+v][(j+i)+v]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//ɨ����б�·��е�������Ԫ��
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if(((j+i)+v)==row && (j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[(j+i)+v][j+v]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[(j+i)+v][j+v]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}			
		}
		
		//ɨ����б�Ϸ��е�������Ԫ��
		for(int i=0;i<11;i++){
			for(int j=0;j<i+1;j++){

				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if((4+i-j-v)==row && (j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[4+i-j-v][j+v]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[4+i-j-v][j+v]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//ɨ����б�Ϸ��е�������Ԫ��
		for(int i=0;i<11;i++){
			for(int j=0;j<11-i;j++){
				
				//�жϴ���Ԫ���Ƿ�������λ��
				boolean isIncluded = false;
				
				for(int v=0;v<5;v++){
					if((14-j-v)==row && (i+j+v)==col){
						isIncluded = true;
						break;	
					}
				}
				if(isIncluded){
					//���Ӹ��������Ӹ���
					int blackNum = 0;
					int whiteNum = 0;
					for(int v=0;v<5;v++)
					{
						if(chessValue[14-j-v][i+j+v]==1){
							//��������Ǻ�ɫ
							blackNum++;
						} else if(chessValue[14-j-v][i+j+v]==2){
							//��������ǰ�ɫ
							whiteNum++;
						}
					}
					//���ݺڰ����ӵĸ����������Ԫ������
					dotScore +=this.calculateEleScore(blackNum, whiteNum);
				}
			}
		}
		
		//�������λ�õĵ÷�
		return dotScore;		
	}
	
	/**
	 * 
	 * ���ݺڰ���ĸ���������Ԫ��ķ���--���ֱ�
	 * @param blackNum
	 * @param whiteNum
	 * @return
	 * 
	 */
	public int calculateEleScore(int blackNum,int whiteNum){
		//����÷�
		int score = 0;
		//����Ԫ��
		if(blackNum==0 && whiteNum==0){
			score = 7;
		} else if(whiteNum==0){
			//ֻ�к���������������������Ԫ�飬�ڴ˴������ǹ���
			switch(blackNum){
			case 1:score = 35;break;
			case 2:score = 800;break;
			case 3:score = 15000;break;
			case 4:score = 800000;break;
			default:score = 0;break;
			}
		} else if(blackNum==0){
			//ֻ�а���
			//��ʱ�ǶԷ���������Ԫ�飬�ڶԷ���������Ԫ���������Ƿ��أ�ҲӦ�üӷ�
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
