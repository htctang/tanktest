package com.tarena;

import java.awt.image.BufferedImage;

/**
 * С��
 * �ӵ���̹�˶��ܴ��������ǻᱻ�ڵ�
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
