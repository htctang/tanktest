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
	private boolean fromHero;//�Ƿ���Ӣ�ۻ�������ӵ�
	
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
	 * �����ƶ�
	 */
	public void moveUp(){
		this.setY(this.getY()-this.speed);
	}
	/**
	 * �����ƶ�
	 */
	public void moveDown(){
		this.setY(this.getY()+this.speed);
	}
	/**
	 * �����ƶ�
	 */
	public void moveLeft(){
		this.setX(this.getX()-this.speed);
	}
	/**
	 * �����ƶ�
	 */
	public void moveRight(){
		this.setX(this.getX()+this.speed);
	}
	/**
	 * �����ӵ��ķ��򣬵��ö�Ӧ���ƶ�����
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
	 * �ж��ӵ��Ƿ����ϰ�����ײ
	 * @param allBarriers
	 * @return
	 */
	public void touchBarrier(ArrayList<Barrier> allBarriers,
			ArrayList<Bullet> allBullets){
		for(int i=0;i<allBarriers.size();i++){
			Barrier b = allBarriers.get(i);
			//instanceof �ж�һ�������Ƿ���ָ�����ʵ��
			//�����ϰ�����С����������ײ�ж�
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.heigh&&this.y>=b.y-this.height){
					if(b instanceof Brick){
						//ɾ�����ӵ�������ש��
						allBarriers.remove(b);
						/*
						 * �ѵ�ǰ�ӵ�(���������ϰ�����ӵ�)�͸���������һ��Ԫ�ؽ�����
						 * Ȼ��ͨ��copyOf������ɾ�������һ��Ԫ��
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
			//���ӵ���Ӣ�ۻ�����Ĵ���
			if(this.isFromHero()){
				if(this.x+this.width>=b.x&&this.x<=b.x+b.width&&
						this.y<=b.y+b.height&&this.y>=b.y-this.height){
					allEnemys.remove(b);
					allBullets.remove(this);
				}
			}
			//���ӵ��ǵо������Ĵ���
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
	 * �ж�king�Ƿ��ӵ���ײ
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
