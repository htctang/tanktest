package com.tarena;

import java.util.ArrayList;

import com.tarena.util.ImageUtil;

/**
 * 把障碍物和敌军都放在Wall里面
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
	//ArrayList 集合：底层就是数组，但是可以自动扩容
	//<> 泛型：限制该集合的元素的数据类型
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
