package com.tarena.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * 异常：在编码过程中可能会遇到的意外情况
 * 
 * try{
 * 可能出现异常的代码
 * }catch(可能出现的异常的对象){
 * 如果异常发生之后的处理方式
 * }
 * 
 * 课程安排：1~4 9:00   5 10:00
 * try{
 * 我今天9点半起床
 * }catch(迟到){
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
