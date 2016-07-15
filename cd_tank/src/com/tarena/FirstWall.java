package com.tarena;

import com.tarena.util.ImageUtil;

public class FirstWall extends Wall{
	
	public FirstWall(){
		//创建砖块
		for(int i=0;i<20;i++){
			allBarriers.add(new Brick(400+i*brickWidth, 200, ImageUtil.getImage("brick.gif")));
		}
		//创建金属
		for(int i=0;i<20;i++){
			allBarriers.add(new Metal(200+i*metalWidth,400,ImageUtil.getImage("metal.gif")));
		}
		
		//创建小树
				for(int i=0;i<20;i++){
					allBarriers.add(new Tree(200+i*treeWidth,300,ImageUtil.getImage("tree.gif")));
				}
		//创建小河
		allBarriers.add(new River(100, 80, ImageUtil.getImage("river.jpg")));
		//创建敌军
		for(int i=0;i<4;i++){
			allEnemys.add(new Enemy(10+i*150,100,2,ImageUtil.getImage("tankD.png")));
		}
		
	}
}
