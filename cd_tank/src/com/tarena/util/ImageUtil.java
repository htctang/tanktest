package com.tarena.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * �쳣���ڱ�������п��ܻ��������������
 * 
 * try{
 * ���ܳ����쳣�Ĵ���
 * }catch(���ܳ��ֵ��쳣�Ķ���){
 * ����쳣����֮��Ĵ���ʽ
 * }
 * 
 * �γ̰��ţ�1~4 9:00   5 10:00
 * try{
 * �ҽ���9�����
 * }catch(�ٵ�){
 * 
 * }
 */
public class ImageUtil {
	public static BufferedImage getImage(String name){
		BufferedImage image = null;
		try{
		image = ImageIO.read(
				ImageUtil.class.getResource("/"+name));
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
}
