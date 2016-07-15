package com.tarena;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * µÐ¾ü
 * @author Administrator
 *
 */
public class Enemy extends Tank{
	public Enemy(int x,int y,int speed,BufferedImage image){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.direction = 1;
	}
	
}
