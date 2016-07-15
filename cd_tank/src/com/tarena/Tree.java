package com.tarena;

import java.awt.image.BufferedImage;

/**
 * 小树
 * 子弹和坦克都能穿过，但是会被遮挡
 * @author Administrator
 *
 */
public class Tree extends Barrier{
	public Tree(int x,int y,BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = this.image.getWidth();
		this.heigh = this.image.getHeight();
	}
}
