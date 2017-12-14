/**
 * 
 * ��BlockPanel���Ĳ����ж���
 * 
 */
package com.yrd.my2048;
import java.awt.Window;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Operation implements MouseListener {

	//����x,y����
	int xPressed;
	int yPressed;
	int xReleased;
	int yReleased;
	int xDistance;
	int yDistance;
	//������ݱ�־
	int xy;
	//���巽�����ֶ�ά����
	int[][] blockValue;
	//���巽������ά����
	Block[][] block = null;
	//���巽��������
	BlockPanel blockPanel = null;
	
	//���캯��,��������Ķ�����BlockPanel
	public Operation(BlockPanel blockPanel) {
		this.blockValue = blockPanel.getBlockValue();
		this.block = blockPanel.getBlock();
		this.blockPanel = blockPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		xPressed = e.getX();
		yPressed = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		xReleased = e.getX();
		yReleased = e.getY();
		
		xDistance = xReleased-xPressed;
		yDistance = yReleased-yPressed;
		
		//�����ж��������ƶ����Ǻ����ƶ�
		if(Math.abs(xDistance)>Math.abs(yDistance)){
			//�����ƶ�
			xy = 1;
		} else if(Math.abs(xDistance)<Math.abs(yDistance)){
			//�����ƶ�
			xy = 2;
		} else {
			//��Ч����(45�Ȼ���),������Ӧ
			xy = 0;
		}
		
		//�ж��������
		if(xy==1){
			if(xDistance>0){
				//���һ���
//				System.out.println("���һ���");
				this.rightProcess();
			} else if (xDistance<0){
				//���󻬶�
//				System.out.println("���󻬶�");
				this.leftProcess();
			}
		}
		//�ж��ϻ�����
		if(xy==2){
			if(yDistance>0){
				//���»���
//				System.out.println("���»���");
				this.downProcess();
			} else if (yDistance<0){
				//���ϻ���
//				System.out.println("���ϻ���");
				this.upProcess();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//���󻬶�������
	public void leftProcess(){
		//ˢ�·�������ֵ����
		for(int i=0;i<4;i++)
		{
			//����ȷ��ÿ�и�����Ӧ�ø�ֵ����
			for(int j=0;j<4;j++)
			{
				//���鲻����0�����
				if(blockValue[i][j]!=0)
				{
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				//�������0��������������һ��������0���������ƶ�	
				} else if (blockValue[i][j]==0)
				{
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//�������һ����0����ռ�������
							blockValue[i][j] = blockValue[i][k];
							blockValue[i][k] = 0;
							break;//�ҵ���һ����0����������ѭ��
						}
					}
					//���ղ�����0�����ж�
					for(int k=j+1;k<4;k++)
					{
						if(blockValue[i][k]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();	
		this.blockPanel.refreshBlockPanel(this.blockValue);
		//����Ƿ������Ϸ
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//���һ���������
	public void rightProcess(){
		//ˢ�·�������ֵ����
		for(int i=0;i<4;i++)
		{
			//����ȷ��ÿ�и�����Ӧ�ø�ֵ����
			for(int j=3;j>=0;j--)
			{
				//���鲻����0�����
				if(blockValue[i][j]!=0)
				{
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				//�������0��������������һ��������0���������ƶ�	
				} else if (blockValue[i][j]==0)
				{
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//�������һ����0����ռ�������
							blockValue[i][j] = blockValue[i][k];
							blockValue[i][k] = 0;
							break;
						}
					}
					//���ղ�����0�����ж�
					for(int k=j-1;k>=0;k--)
					{
						if(blockValue[i][k]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[i][k])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[i][k] = 0;
							}
							break;
						}
					}
				}
			}
		}	
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);	
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//���ϻ���������
	public void upProcess(){
		//ˢ�·�������ֵ����
		for(int j=0;j<4;j++)
		{
			//����ȷ��ÿ�и�����Ӧ�ø�ֵ����
			for(int i=0;i<4;i++)
			{
				//���鲻����0�����
				if(blockValue[i][j]!=0)
				{
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				//�������0��������������һ��������0���������ƶ�	
				} else if (blockValue[i][j]==0)
				{
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//�������һ����0����ռ�������
							blockValue[i][j] = blockValue[k][j];
							blockValue[k][j] = 0;
							break;
						}
					}
					//���ղ�����0�����ж�
					for(int k=i+1;k<4;k++)
					{
						if(blockValue[k][j]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//���»���������
	public void downProcess(){
		//ˢ�·�������ֵ����
		for(int j=0;j<4;j++)
		{
			//����ȷ��ÿ�и�����Ӧ�ø�ֵ����
			for(int i=3;i>=0;i--)
			{
				//���鲻����0�����
				if(blockValue[i][j]!=0)
				{
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				//�������0��������������һ��������0���������ƶ�	
				} else if (blockValue[i][j]==0)
				{
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//�������һ����0����ռ�������
							blockValue[i][j] = blockValue[k][j];
							blockValue[k][j] = 0;
							break;
						}
					}
					//���ż������ղ�����0������ж�
					for(int k=i-1;k>=0;k--)
					{
						if(blockValue[k][j]!=0)
						{
							//��������һ����0�������������ȣ���ı�����������򲻸ı䣬������ѭ��
							if(blockValue[i][j]==blockValue[k][j])
							{
								blockValue[i][j] = 2*blockValue[i][j];
								blockValue[k][j] = 0;
							}
							break;
						}
					}
				}
			}
		}		
		this.generate();		
		this.blockPanel.refreshBlockPanel(this.blockValue);	
		if(this.checkOver(blockValue)){
			this.overProcess();
		}
	}
	
	//��ʣ��հ״��������һ������
	public void generate(){
		//�õ��հ׷���ĸ���
		int numTem1=0;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(blockValue[i][j]==0)
				{
					numTem1++;
				}
			}
		}

		//���numTem1=0��˵�������Ѿ����������ٲ����µķ��� 
		if(numTem1==0){
			return;
		}
		//��numTem��Χ�ڲ���һ�������
		int tem = (int)(Math.random()*numTem1);

		//�Ѳ������������ֵ����������ֵ������
		int numTem2=0;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(blockValue[i][j]==0)
				{
					if(numTem2==tem)
					{
						blockValue[i][j] = 2;
					}
					numTem2++;
				}
			}
		}
	}
	
	//����Ƿ������Ϸ
	public boolean checkOver(int[][] blockValue){
		//ɨ��ÿ��
		for(int i=0;i<4;i++){
			for(int j=0;j<4-1;j++){
				if(blockValue[i][j]==blockValue[i][j+1]){
					return false;
				}
			}
		}	
		//ɨ��ÿ��
		for(int j=0;j<4;j++){
			for(int i=0;i<4-1;i++){
				if(blockValue[i][j]==blockValue[i+1][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	//��Ϸ��������
	public void overProcess(){
		//����ǣ�0  �����1  ���ȡ����2
		int type = JOptionPane.showConfirmDialog(null, "��Ϸ�ѽ��������'��'���¿�ʼ��Ϸ�����'��'������Ϸ��", "Game Over!", JOptionPane.YES_NO_CANCEL_OPTION);
		if(type==0){
			this.blockPanel.reset(blockValue);
		} else if(type==1){
			System.exit(0);
		} 		
	}

}
