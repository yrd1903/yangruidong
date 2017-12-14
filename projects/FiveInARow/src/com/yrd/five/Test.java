package com.yrd.five;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		int num = test.calculateEleScore(3, 0);
		System.out.println(num);
	}
	
	//根据黑白棋的个数计算五元组的分数--评分表
		public int calculateEleScore(int blackNum,int whiteNum){
			//定义得分
			int score = 0;
			//空五元组
			if(blackNum==0 && whiteNum==0){
				score = 7;
			} else if(whiteNum==0){
				//只有黑棋的情况
				switch(blackNum){
				case 1:score = 35;break;
				case 2:score = 800;break;
				case 3:score = 15000;break;
				case 4:score = 800000;break;
				default:score = 0;break;
				}
			} else if(blackNum==0){
				//只有白棋
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
