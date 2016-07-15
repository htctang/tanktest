package com.tarena;

import java.awt.image.BufferedImage;

/**
 * 砖块类
 * 子弹能消灭
 * 坦克不能通过
 * @author Administrator
 *
 */
public class Brick extends Barrier{
	public Brick(int x,int y,BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = this.image.getWidth();
		this.heigh = this.image.getHeight();
	}
}
