/**
 * 
 * ������
 * 
 */
package com.yrd.my2048;
import java.awt.*;
import javax.swing.*;

public class Block extends JLabel{

	//�������ֵ�ֵ
	private int value;

	//���캯��
	public Block(int value){
		
		this.setFont(new Font("font", Font.PLAIN, 40));//�趨����
		this.setOpaque(true);//����LabelΪ��͸��
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		//���巽������ֺ���ɫ
		this.setValue(value);
		this.setColor();
		
	}
	
	//���÷�������
	private void setValue(int value){
		if(value!=0){
			this.setText(String.valueOf(value));
		} else {
			//���valueΪ0����Ϊ�շ��飬����ʾ�κ�����
			this.setText("");
		}
		this.value = value;
	}
	
	//�õ���������
	public int getValue(){
		return this.value;
	}
	
	//���÷�����ɫ
	private void setColor(){	
		//���ݳ�Ա����value���ò�ͬ����ɫ
		switch (value){
	    case 0:
	      setBackground(Color.gray);
	      break;
	    case 2:
	      setBackground(new Color(238, 228, 218));
	      break;
	    case 4:
	      setBackground(new Color(238, 224, 198));
	      break;
	    case 8:
	      setBackground(new Color(243, 177, 116));
	      break;
	    case 16:
	      setBackground(new Color(243, 177, 116));
	      break;
	    case 32:
	      setBackground(new Color(248, 149, 90));
	      break;
	    case 64:
	      setBackground(new Color(249, 94, 50));
	      break;
	    case 128:
	      setBackground(new Color(239, 207, 108));
	      break;
	    case 256:
	      setBackground(new Color(239, 207, 99));
	      break;
	    case 512:
	      setBackground(new Color(239, 203, 82));
	      break;
	    case 1024:
	      setBackground(new Color(239, 199, 57));
	      break;
	    case 2048:
	      setBackground(new Color(239, 195, 41));
	      break;
	    case 4096:
	      setBackground(new Color(255, 60, 57));
	      break;
	    }

	}
	
	//ˢ�·���״̬
	public void refreshBlock(int value){
		this.setValue(value);
		this.setColor();
	}
}
