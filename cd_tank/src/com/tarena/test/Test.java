package com.tarena.test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class Test {
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void test(){
		
	}
	//
	public abstract void test2();
	public static void main(String[] args) {
		KeyAdapter l = new MyKeyAdapter();
	}
}

class MyKeyAdapter extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyPressed(e);
	}
	
}
 	
