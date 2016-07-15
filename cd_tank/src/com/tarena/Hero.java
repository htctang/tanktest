package com.tarena;

import java.awt.image.BufferedImage;

public class Hero extends Tank{
	
	public Hero(int x,int y,int speed,BufferedImage image){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.direction = 0;
		this.life = 10;
	}
}
