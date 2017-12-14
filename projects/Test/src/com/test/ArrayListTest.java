package com.test;

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList al = new ArrayList();
		//System.out.println("al大小："+al.size());
		//向ArrayList中添加数据，类型是Object
		Clerk clerk1 = new Clerk("芈月", 20);
		Clerk clerk2 = new Clerk("秦王", 20);
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk1);
		System.out.println("al大小："+al.size());
		
		//如何访问数据
		//Clerk tem = (Clerk)al.get(0);
		//System.out.println("第一个人的名字是："+tem.name);
		
		//循环遍历访问
		for(int i=0;i<al.size();i++){
			Clerk tem = (Clerk)al.get(i);
			System.out.println("名字："+tem.name);
		}
		
		//删除
		al.remove(1);
		System.out.println("*****删除后*****");
		for(int i=0;i<al.size();i++){
			Clerk tem = (Clerk)al.get(i);
			System.out.println("名字："+tem.name);
		}
		
	}

}

class Clerk{
	
	String name;
	int age;
	
	Clerk(String name,int age){
		this.name = name;
		this.age = age;
	}
}
