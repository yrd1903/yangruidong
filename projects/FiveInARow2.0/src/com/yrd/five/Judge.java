package com.yrd.five;

import javax.swing.JOptionPane;

public class Judge {

	//�������̶���
	ChessPanel chessPanel = null;
	//�������̶�ά����
	int[][] chessValue;
	//����ʤ�����
	int result;
	
	
	/**
	 * 
	 * ���캯��
	 * @param chessPanel
	 * 
	 */
	public Judge(ChessPanel chessPanel){
		//Judge��Ҫ֪�����̵����		
		this.chessPanel = chessPanel;
		this.chessValue = chessPanel.chessValue;
	}

	/**
	 * 
	 * �ж�ʤ�����Ҵ�����
	 * 
	 */
	public void judgeAndProcess(){
		//�ػ�����
		chessPanel.repaint();
		//�ж�ʤ��
		result = this.checkResult(chessValue);
		//������
		this.processResult(result);		
	}
	
	/**
	 * 
	 * ���ݽ��������Ӧ����
	 * @param res
	 * 
	 */
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
	
	/**
	 * 
	 * ������Ϸ
	 * @param chessValue
	 * 
	 */
	public void reset(int[][] chessValue){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				chessValue[i][j] = 0;
			}
		}
		this.chessPanel.repaint();
	}
		
	
	/**
	 * 
	 * �ж��Ƿ����ʤ��  ����0��û�зֳ�ʤ��      ����1������ʤ      ����2������ʤ
	 * @param chessValue
	 * @return
	 * 
	 */
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
						//System.out.println((j+i+v)+"--"+(j+v));
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

	

	
}
