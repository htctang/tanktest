package com.tarena;

import java.awt.image.BufferedImage;

public class King {
	 int x;
	 int y;
	 BufferedImage image;
	 int width;
	 int height;
	public King(int x, int y, BufferedImage image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}
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
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
