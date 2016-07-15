package com.tarena;

import java.awt.image.BufferedImage;

/**
 * 障碍物类  
 * 砖块 小河  小树  金属  都应该继承此类
 * @author Administrator
 *
 */
public class Barrier {
	protected int x;
	protected int y;
	protected BufferedImage image;
	protected int width;
	protected int heigh;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeigh() {
		return heigh;
	}
	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}
	
	
}
