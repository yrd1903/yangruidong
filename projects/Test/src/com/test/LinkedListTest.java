package com.test;

import java.util.HashMap;
import java.util.LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		LinkedList ll = new LinkedList();
		
		Clerk clerka = new Clerk("张三", 29);
		System.out.println("clerka:"+clerka);
		Clerk clerkb = new Clerk("张三", 29);
		Clerk clerk1 = new Clerk("宋江", 39);
		Clerk clerk2 = new Clerk("林冲", 39);
		HashMap hm = new HashMap();
		String str1 = new String("s001");
		String str2 = new String("s002");
		hm.put(str1, clerk1);
		hm.put(str2, clerk2);

//		if(hm.containsKey(str1)){
//			System.out.println("有");
//		}
		
		System.out.println("jks "+(clerk1.age==clerk2.age));
		
		if(str1==str2){
			System.out.println("地址相等");
		}else{
			System.out.println("地址不等");
		}
		
		System.out.println(hm.size());
		
		Clerk tem = (Clerk)hm.get(str1);
		System.out.println(tem.name);
		
		
		
		String xyzxyzOne = "xyzxyz";
		String xyzOne = "xyz";
		String xyzTwo = "xyz";
		System.out.println("(xyzOne == xyzTwo):"
				+ (xyzOne == xyzTwo));
		
		String xyzxyzTwo = xyzOne + xyzTwo;
		System.out.println("(xyzxyzOne == xyzxyzTwo):"
		+ (xyzxyzOne == xyzxyzTwo));
		
		
		 String str = "123";
	        String str5 = new String("123");
	        System.out.println(str == str5);
	 
	        A a = new A("123");
	        System.out.println(str == a.str);
	        
	        
	        
	        String str6 = "abc";         

	        String str7 = new String("abc").intern();         

	        System.out.println(str6==str7); 
		
	}

}


class A{
    String str;
 
    A(String str){
        this.str = str;
    }
    
}

