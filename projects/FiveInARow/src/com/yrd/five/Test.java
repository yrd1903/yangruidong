package com.yrd.five;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		int num = test.calculateEleScore(3, 0);
		System.out.println(num);
	}
	
	//���ݺڰ���ĸ���������Ԫ��ķ���--���ֱ�
		public int calculateEleScore(int blackNum,int whiteNum){
			//����÷�
			int score = 0;
			//����Ԫ��
			if(blackNum==0 && whiteNum==0){
				score = 7;
			} else if(whiteNum==0){
				//ֻ�к�������
				switch(blackNum){
				case 1:score = 35;break;
				case 2:score = 800;break;
				case 3:score = 15000;break;
				case 4:score = 800000;break;
				default:score = 0;break;
				}
			} else if(blackNum==0){
				//ֻ�а���
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
