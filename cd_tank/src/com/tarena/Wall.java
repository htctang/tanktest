package com.tarena;

import java.util.ArrayList;

import com.tarena.util.ImageUtil;

/**
 * ���ϰ���͵о�������Wall����
 * @author Administrator
 *
 */
public class Wall {
	public int treeWidth = 
			ImageUtil.getImage("tree.gif").getWidth();
	public int treeHeight = 
			ImageUtil.getImage("tree.gif").getHeight();
	public int metalHeight = 
			ImageUtil.getImage("metal.gif").getHeight();
	public int metalWidth = 
			ImageUtil.getImage("metal.gif").getWidth();
	public int kingHeight = 
			ImageUtil.getImage("king.jpg").getHeight();
	public int kingWidth = 
			ImageUtil.getImage("king.jpg").getWidth();
	public int brickHeight = 
			ImageUtil.getImage("brick.gif").getHeight();
	public int brickWidth = 
			ImageUtil.getImage("brick.gif").getWidth();
	//ArrayList ���ϣ��ײ�������飬���ǿ����Զ�����
	//<> ���ͣ����Ƹü��ϵ�Ԫ�ص���������
	ArrayList<Barrier> allBarriers = new ArrayList<Barrier>();
	ArrayList<Tank> allEnemys = new ArrayList<Tank>();
	King king = null;
	public Wall(){
		king = new King(400-kingWidth/2,560-kingHeight,
				ImageUtil.getImage("king.jpg"));
		createBarriers();
	}
	private void createBarriers() {
		for(int i=0;i<4;i++){
			allBarriers.add(new Brick(king.getX()-20-brickWidth,560-brickHeight*(i+1),ImageUtil.getImage("brick.gif")));
			allBarriers.add(new Brick(king.getX()+20+kingWidth/2+brickWidth,560-brickHeight*(i+1),ImageUtil.getImage("brick.gif")));
			allBarriers.add(new Brick(king.getX()+kingWidth/2+20-brickWidth*i,560-brickHeight*4,ImageUtil.getImage("brick.gif")));
		}
		
	}
}
