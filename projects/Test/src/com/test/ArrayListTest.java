package com.test;

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList al = new ArrayList();
		//System.out.println("al��С��"+al.size());
		//��ArrayList��������ݣ�������Object
		Clerk clerk1 = new Clerk("����", 20);
		Clerk clerk2 = new Clerk("����", 20);
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk1);
		System.out.println("al��С��"+al.size());
		
		//��η�������
		//Clerk tem = (Clerk)al.get(0);
		//System.out.println("��һ���˵������ǣ�"+tem.name);
		
		//ѭ����������
		for(int i=0;i<al.size();i++){
			Clerk tem = (Clerk)al.get(i);
			System.out.println("���֣�"+tem.name);
		}
		
		//ɾ��
		al.remove(1);
		System.out.println("*****ɾ����*****");
		for(int i=0;i<al.size();i++){
			Clerk tem = (Clerk)al.get(i);
			System.out.println("���֣�"+tem.name);
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
