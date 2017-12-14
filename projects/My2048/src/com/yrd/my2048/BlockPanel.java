/**
 * 
 * ����Panel
 * 
 */
package com.yrd.my2048;
import java.awt.*;
import javax.swing.*;

public class BlockPanel extends JPanel{
	
	//����4*4����ķ����������
	Block[][] block = null;
	

	//����4*4����ķ�������ֵ��ά����
	int[][] blockValue;
	
	

	//���캯��
	public BlockPanel(){
		
		this.setLayout(new GridLayout(4,4,5,5));
		block = new Block[4][4];
		blockValue = new int[4][4];
		Operation operation = new Operation(this);
		
		
		this.addMouseListener(operation);
		
		//����������������������ɳ�ʼ����������
		int tem1 = (int)(Math.random()*16);
		int tem2 = this.noRepeat(tem1);
		int tem = 0;

		//ѭ��������������е����з������ֻ��������ʾ�������Է����������鸳ֵ
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				//��ʼ���������飬����Ϊ2
				if(tem==tem1 || tem==tem2){
					block[i][j] = new Block(2);
					blockValue[i][j] = 2;
				} else {
					block[i][j] = new Block(0);
					blockValue[i][j] = 0;
				}
				
				this.add(block[i][j]);
				tem++;
			}
		}	
	}
	
	//ˢ�·��������������
	public void refreshBlockPanel(int[][] blockValue){
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				block[i][j].refreshBlock(blockValue[i][j]);
			}
		}
	}
	
	//����һ������֪�����ظ��������
	public int noRepeat(int value){
		int tem=0;
		while((tem=(int)(Math.random()*16))==value){
			
		}
		return tem;
	}
	
	//��λ�����¿�ʼ��Ϸ
	public void reset(int[][] blockValue){
		
		//����������������������ɳ�ʼ����������
		int tem1 = (int)(Math.random()*16);
		int tem2 = this.noRepeat(tem1);
		int tem = 0;

		//ѭ��������������е����з������ֻ��������ʾ�������Է����������鸳ֵ
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				//��ʼ���������飬����Ϊ2
				if(tem==tem1 || tem==tem2){
					blockValue[i][j] = 2;
				} else {
					blockValue[i][j] = 0;
				}
				tem++;
			}
		}	
		//ˢ��
		this.refreshBlockPanel(blockValue);
	}
	
	
	public Block[][] getBlock() {
		return block;
	}
	
	public int[][] getBlockValue() {
		return blockValue;
	}
}
