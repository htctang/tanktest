package com.tarena;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.tarena.util.ImageUtil;
/**
 * 坦克类
 * @author Administrator
 *
 */
public class Tank {
	//private default protected public
	//protected:当前包或者不同包的子孙类
	protected int x;//横坐标
	protected int y;//纵坐标
	protected int speed;//速度
	protected BufferedImage image;//图片
	protected int width;//宽
	protected int height;//高
	protected int direction;//方向：0向上 1向下 2向左 3向右
	protected int life;//生命值
	
	/**
	 * 向上移动
	 */
	public void moveUp(){
		this.setY(this.getY()-this.speed);
	}
	/**
	 * 向下移动
	 */
	public void moveDown(){
		this.setY(this.getY()+this.speed);
	}
	/**
	 * 向左移动
	 */
	public void moveLeft(){
		this.setX(this.getX()-this.speed);
	}
	/**
	 * 向右移动
	 */
	public void moveRight(){
		this.setX(this.getX()+this.speed);
	}
	/**
	 * 向上移动事件
	 * @param hero 
	 * @param allEnemys 
	 */
	/*
	 * 1.修改moveUpAction(和其他三个action)的方法参数：添加allEnemys和hero
	 * 2.在调用这四个action的位置，把allEnemys和hero传进来
	 * 3.moveUpAction的实现加上一个条件:touchTank(allEnemys,hero)
	 * 4.修改Tank类的move方法：同样是加上allEnemys和hero这两个参数
	 * */
	public void moveUpAction(ArrayList<Barrier> allBarriers, ArrayList<Tank> allEnemys, Hero hero){
		moveUp();
		this.setImage(ImageUtil.getImage("tankU.png"));
		this.direction = 0;
		if(touchBarrier(allBarriers)||outOfBounds()||touchTank(allEnemys, hero)){
			moveDown();
		}
	}
	/**
	 * 向下移动事件
	 * @param hero 
	 * @param allEnemys 
	 */
	public void moveDownAction(ArrayList<Barrier> allBarriers, ArrayList<Tank> allEnemys, Hero hero){
		moveDown();
		this.setImage(ImageUtil.getImage("tankD.png"));
		this.direction = 1;
		if(touchBarrier(allBarriers)||outOfBounds()||touchTank(allEnemys, hero)){
			moveUp();
		}
	}
	/**
	 * 向左移动事件
	 * @param hero 
	 * @param allEnemys 
	 */
	public void moveLeftAction(ArrayList<Barrier> allBarriers, ArrayList<Tank> allEnemys, Hero hero){
		moveLeft();
		this.setImage(ImageUtil.getImage("tankL.png"));
		this.direction = 2;
		if(touchBarrier(allBarriers)||outOfBounds()||touchTank(allEnemys, hero)){
			moveRight();
		}
	}
	/**
	 * 向右移动事件
	 * @param hero 
	 * @param allEnemys 
	 */
	public void moveRightAction(ArrayList<Barrier> allBarriers, ArrayList<Tank> allEnemys, Hero hero){
		moveRight();
		this.setImage(ImageUtil.getImage("tankR.png"));
		this.direction = 3;
		if(touchBarrier(allBarriers)||outOfBounds()||touchTank(allEnemys, hero)){
			moveLeft();
		}
	}
	public void move(ArrayList<Barrier> allBarriers,ArrayList<Tank> allEnemys,Hero hero){
		if(this.direction==0){
			moveUpAction(allBarriers,allEnemys,hero);
		}else if(this.direction==1){
			moveDownAction(allBarriers,allEnemys,hero);
		}else if(this.direction==2){
			moveLeftAction(allBarriers,allEnemys,hero);
		}else if(this.direction==3){
			moveRightAction(allBarriers,allEnemys,hero);
		}
	}
	/**
	 * 设置随机的方向
	 */
	Random random = new Random();
	public void setRandomDir(){
		int dir = random.nextInt(4);//0~3
		this.direction = dir;
	}
	
	/**
	 * 射击
	 * @return
	 */
	public Bullet shoot(){
		//根据坦克的方向，子弹对应出现的四个坐标
		int[][] dir = {
				{this.x+this.width/2,this.y},
				{this.x+this.width/2,this.y+this.height},
				{this.x,this.y+this.height/2},
				{this.x+this.width,this.y+this.height/2}
				};
		//根据坦克方向，子弹对应的四张图片
		BufferedImage[] img = {
				ImageUtil.getImage("bulletU.gif"),
				ImageUtil.getImage("bulletD.gif"),
				ImageUtil.getImage("bulletL.gif"),
				ImageUtil.getImage("bulletR.gif")
		};
		//从数组取值：数组名[下标]
		Bullet bullet = new Bullet(dir[this.direction][0],
				dir[this.direction][1],
				img[this.direction],3
				);
		//把坦克射击时的方向赋值给子弹
		bullet.setDirection(this.direction);
		return bullet;
	}
	/**
	 * 判断坦克是否碰撞到障碍物
	 * @param allBarriers
	 * @return
	 */
	public boolean touchBarrier(ArrayList<Barrier> allBarriers){
		for(int i=0;i<allBarriers.size();i++){
			Barrier b = allBarriers.get(i);
			//instanceof 判断一个变量是否是指定类的实例
			if(!(b instanceof Tree)){//若果障碍物是小树，则不做碰撞判定
				System.out.println("------");
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.heigh&&this.y>=b.y-this.height){
					return true;
				}
			}
		
		}
		return false;
	}
	public boolean touchTank(ArrayList<Tank> allEnemys,Hero hero){
		for(int i=0;i<allEnemys.size();i++){
			Tank b = allEnemys.get(i);
			if(b!=this){
				
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.height&&this.y>=b.y-this.height){
					return true;
				}
				if(hero!=this){
					if(this.x+this.width>=hero.x&&this.x<=hero.x+hero.width&&
							this.y<=hero.y+hero.height&&this.y>=hero.y-this.height){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 判断坦克是否出界
	 * @return
	 */
	public boolean outOfBounds(){
		if(this.x<0||this.x>780-this.width||
				this.y<0||this.y>565-this.height){
			return true;
		}
		return false;
		
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
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
