package First;

public class TimeMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeMaker timeMaker=new TimeMaker();
		timeMaker.time(1443083288);
			
	}
	
	public String time(int num){
		int secondNum=num;
		int year,month,day,hour,minute,second;
		year=secondNum/(12*30*24*60*60)+1970;
		month=(secondNum/(30*24*60*60))%12+1;
		day=(secondNum/(24*60*60))%30+1;
		
		hour=(secondNum/(60*60))%24;
		minute=(secondNum/(60))%60;
		second=(secondNum%(60))%60;
		
		return ""+year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second;
		
		//System.out.println(year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second);
	}

}
