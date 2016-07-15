package com.tarena;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.tarena.util.ImageUtil;
/**
 * ̹����
 * @author Administrator
 *
 */
public class Tank {
	//private default protected public
	//protected:��ǰ�����߲�ͬ����������
	protected int x;//������
	protected int y;//������
	protected int speed;//�ٶ�
	protected BufferedImage image;//ͼƬ
	protected int width;//��
	protected int height;//��
	protected int direction;//����0���� 1���� 2���� 3����
	protected int life;//����ֵ
	
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
	 * �����ƶ��¼�
	 * @param hero 
	 * @param allEnemys 
	 */
	/*
	 * 1.�޸�moveUpAction(����������action)�ķ������������allEnemys��hero
	 * 2.�ڵ������ĸ�action��λ�ã���allEnemys��hero������
	 * 3.moveUpAction��ʵ�ּ���һ������:touchTank(allEnemys,hero)
	 * 4.�޸�Tank���move������ͬ���Ǽ���allEnemys��hero����������
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
	 * �����ƶ��¼�
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
	 * �����ƶ��¼�
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
	 * �����ƶ��¼�
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
	 * ��������ķ���
	 */
	Random random = new Random();
	public void setRandomDir(){
		int dir = random.nextInt(4);//0~3
		this.direction = dir;
	}
	
	/**
	 * ���
	 * @return
	 */
	public Bullet shoot(){
		//����̹�˵ķ����ӵ���Ӧ���ֵ��ĸ�����
		int[][] dir = {
				{this.x+this.width/2,this.y},
				{this.x+this.width/2,this.y+this.height},
				{this.x,this.y+this.height/2},
				{this.x+this.width,this.y+this.height/2}
				};
		//����̹�˷����ӵ���Ӧ������ͼƬ
		BufferedImage[] img = {
				ImageUtil.getImage("bulletU.gif"),
				ImageUtil.getImage("bulletD.gif"),
				ImageUtil.getImage("bulletL.gif"),
				ImageUtil.getImage("bulletR.gif")
		};
		//������ȡֵ��������[�±�]
		Bullet bullet = new Bullet(dir[this.direction][0],
				dir[this.direction][1],
				img[this.direction],3
				);
		//��̹�����ʱ�ķ���ֵ���ӵ�
		bullet.setDirection(this.direction);
		return bullet;
	}
	/**
	 * �ж�̹���Ƿ���ײ���ϰ���
	 * @param allBarriers
	 * @return
	 */
	public boolean touchBarrier(ArrayList<Barrier> allBarriers){
		for(int i=0;i<allBarriers.size();i++){
			Barrier b = allBarriers.get(i);
			//instanceof �ж�һ�������Ƿ���ָ�����ʵ��
			if(!(b instanceof Tree)){//�����ϰ�����С����������ײ�ж�
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
	 * �ж�̹���Ƿ����
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
