package com.qq.server.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLog {

	public static void main(String[] args) {
		//测试
		//printLog("用户1001上线");
	}

	public static void printLog(String str){
		String pathTem = MyLog.class.getResource("").getPath();
		String path = pathTem.substring(1);

		SimpleDateFormat titleFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title = titleFormat.format(new Date());
		String time = timeFormat.format(new Date());
		
		File logFile = new File(path+"../../../../../log/"+title+".log");
		FileWriter fw = null;
		try {
			if(!logFile.exists()){
				logFile.createNewFile();
			}			
			String logOld = getFileContent(logFile);
			String logNew = "Time:["+time+"]"+" Message:"+str;
			fw = new FileWriter(logFile);
			//logOld和logNew之间不用加回车，因为取出来的logOld最后本来就有一个回车
			fw.write(logOld+logNew);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getFileContent(File file){
		FileReader fr = null;
		BufferedReader br = null;
		String allCon = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String s = "";
			while((s=br.readLine())!=null){
				allCon +=s+"\r\n";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return allCon; 
	}
	
}
