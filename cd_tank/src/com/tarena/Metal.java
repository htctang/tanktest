package com.tarena;

import java.awt.image.BufferedImage;

/**
 * ����
 * �ӵ���������̹��Ҳ������
 * @author Administrator
 *
 */
public class Metal extends Barrier{
	public Metal(int x,int y,BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = this.image.getWidth();
		this.heigh = this.image.getHeight();
	}
}
