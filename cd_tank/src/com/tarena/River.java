package com.tarena;

import java.awt.image.BufferedImage;

/**
 * С��
 * ̹�˴��������ӵ��ܴ���
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
