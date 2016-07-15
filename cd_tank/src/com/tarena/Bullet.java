package com.tarena;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Bullet {
	private int x;
	private int y;
	private BufferedImage image;
	private int speed;
	private int width;
	private int height;
	private int direction;
	private boolean fromHero;//是否是英雄机发射的子弹
	
	public Bullet(int x, int y, BufferedImage image, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
		this.speed = speed;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}
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
	 * 根据子弹的方向，调用对应的移动方法
	 */
	public void move(){
		if(this.direction==0){
			moveUp();
		}else if(this.direction==1){
			moveDown();
		}else if(this.direction==2){
			moveLeft();
		}else if(this.direction==3){
			moveRight();
		}
	}
	
	/**
	 * 判断子弹是否与障碍物碰撞
	 * @param allBarriers
	 * @return
	 */
	public void touchBarrier(ArrayList<Barrier> allBarriers,
			ArrayList<Bullet> allBullets){
		for(int i=0;i<allBarriers.size();i++){
			Barrier b = allBarriers.get(i);
			//instanceof 判断一个变量是否是指定类的实例
			//若果障碍物是小树，则不做碰撞判定
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.heigh&&this.y>=b.y-this.height){
					if(b instanceof Brick){
						//删除被子弹碰到的砖块
						allBarriers.remove(b);
						/*
						 * 把当前子弹(就是碰到障碍物的子弹)和该数组的最后一个元素交换，
						 * 然后通过copyOf方法，删除掉最后一个元素
						 */
						allBullets.remove(this);
					}else if(b instanceof Metal){
						allBullets.remove(this);
					}
				}
		}
	}
	/**
	 * 
	 * @param allBullets
	 * @param allEnemys
	 */
	public void touchTank(ArrayList<Bullet> allBullets, ArrayList<Tank> allEnemys,Hero hero) {
		for(int i=0;i<allEnemys.size();i++){
			Tank b = allEnemys.get(i);
			//当子弹是英雄机射出的处理
			if(this.isFromHero()){
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.height&&this.y>=b.y-this.height){
					allEnemys.remove(b);
					allBullets.remove(this);
				}
			}
			//当子弹是敌军发出的处理
			if(this.isFromHero()==false){
				if(this.x+this.width>=hero.x&&this.x<=hero.x+hero.width&&
						this.y<=hero.y+hero.height&&this.y>=hero.y-this.height){
					allBullets.remove(this);
					hero.life--;
				}
			}
			
		}
		
	}
	
	
	public boolean isFromHero() {
		return fromHero;
	}
	public void setFromHero(boolean fromHero) {
		this.fromHero = fromHero;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
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
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	/**
	 * 判断king是否被子弹碰撞
	 * @param king
	 * @return
	 */
	public boolean touchKing(King king,ArrayList<Bullet> allBullets) {
		if(this.x+this.width>=king.x&&this.x<=king.x+king.width&&
				this.y<=king.y+king.height&&this.y>=king.y-this.height){
			allBullets.remove(this);
			return true;
		}
		return false;
	}
	
	
	
	
}
