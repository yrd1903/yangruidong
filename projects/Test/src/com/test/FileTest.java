package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args){
		
		FileReader fr = null;
		BufferedReader br = null;
		String path = FileTest.class.getResource("").getPath();
		System.out.println(path.substring(1)+"../../");
		try {
			//..返回上一层目录
			fr = new FileReader("D:/lib/qac/../../NOTE.txt");
			br = new BufferedReader(fr);
			String s = "";
			String allCon = "";
			while((s=br.readLine())!=null){
				allCon +=s+"\r\n";
			}
			System.out.println(allCon);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
