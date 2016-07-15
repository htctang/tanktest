package com.tarena;

import java.awt.image.BufferedImage;

/**
 * 小河
 * 坦克穿不过，子弹能穿过
 * @author Administrator
 *
 */
public class River extends Barrier{
	public River(int x,int y,BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = this.image.getWidth();
		this.heigh = this.image.getHeight();
	}
}
