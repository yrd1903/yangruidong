package com.test;

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dog dog = new Dog();
		dog.start();
		dog.show();
		
	}

}

class Dog extends Thread{
	public void run(){
		int i=0;
		while(i<20){
			System.out.println("线程运行"+i);
			i++;
		}
	}
	
	public void show(){
		System.out.println("show method run........");
	}
}
