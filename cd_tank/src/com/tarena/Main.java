package com.tarena;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tarena.util.ImageUtil;

/*
 * JFrame:画板
 * JPanel:画纸
 * Grahics:画笔
 * 把画纸添加到画板上，然后调用画纸的绘画(paint)功能，
 * 用画笔在画纸上绘画。
 * 然后把画板显示出来
 */
public class Main extends JPanel{
	public int state;//0运行 1暂停 2结束
	
	public static final int RUNNING = 0;
	public static final int PAUSE = 1;
	public static final int GAMEOVER = 2;
	
	
	//创建英雄对象
	Hero hero = new Hero(
			300,530,3,ImageUtil.getImage("tankU.png"));
	//new Bullet[0]:创建一个长度为0的Bullet数组
	ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
	//创建wall对象
	Wall wall = new FirstWall();
	King king = wall.king;
	//取出wall对象的allBarriers属性的值   
	//allBarriers包含了所有的障碍物
	ArrayList<Barrier> allBarriers = wall.allBarriers;
	//取出wall对象的allEnemys属性的值
	//allEnemys包含了所有的敌军
	ArrayList<Tank> allEnemys = wall.allEnemys;
	public static void main(String[] args){
		//创建画板对象
		JFrame frame = new JFrame("坦克大战");
		//设置画板的尺寸
		frame.setSize(800, 600);
		//设置画板的位置
		frame.setLocationRelativeTo(null);
		//设置关闭frame的同时结束程序
		frame.setDefaultCloseOperation(3);
		//创建画纸对象
		Main panel = new Main();
		//把画纸添加到画板上
		frame.add(panel);
		//显示画板
		frame.setVisible(true);
		panel.action();//游戏开始
		
	}
	
	public void action(){
		
		//把new 对象 和   类的实现   同时完成
		KeyAdapter l = new MyKeyAdapter();
			//实现KeyListener的抽象方法
		this.addKeyListener(l);
		//请求焦点
		this.requestFocus(true);
		
		/*
		 * TimerTask本身就是线程
		 * TimerTask对象可以注册到Timer对象，
		 * Timer对象可以通过schedule(),可以控制
		 * 该线程run方法执行的频率
		 */
		TimerTask task = new TimerTask(){
			//运行一个线程，就是执行该线程的run方法
			public void run(){
				if(state==RUNNING){
					moveAction();
					touchAction(allBarriers);
					enemyShootAction();
					dirAction();
					bomAtion();
					checkGameOver();
					repaint();
				}
			}
		};
		Timer timer = new Timer();
		//延迟1000毫秒运行注册的线程，并以10毫秒的频率执行
		timer.schedule(task,1000,10);
	}
	protected void checkGameOver() {
		if(hero.life<=0||kingOver()){
			state = GAMEOVER;
		}
	}
	private boolean kingOver() {
		for(int i=0;i<allBullets.size();i++){
			Bullet b = allBullets.get(i);
			if(b.touchKing(king,allBullets)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 消灭敌军事件
	 * 1.在bomAtion()方法遍历所有子弹(allBullets集合)，取出每一颗子弹
	 * 2.调用取出的子弹对象的touchEnemy(allBullets,allEnemys)方法
	 * 3.实现touchEnemy方法：
	 *   3.1 遍历所有敌军(allEnemys集合)
	 *   3.2 判断当前子弹是否和每一个敌军碰撞
	 *   3.3 如果碰撞，则删除被碰撞敌军和当前子弹
	 */
	protected void bomAtion() {
		for(int i=0;i<allBullets.size();i++){
			Bullet bullet = allBullets.get(i);
			bullet.touchTank(allBullets,allEnemys,hero);
		}
		
	}

	int dirIndex = 0;
	protected void dirAction() {
		dirIndex++;
		if(dirIndex%50==0){
			for(int i=0;i<allEnemys.size();i++){
				Tank enemy = allEnemys.get(i);
				enemy.setRandomDir();
			}
		}
		
	}

	int index = 0;
	//敌军的射击事件
	protected void enemyShootAction() {
		index++;
		if(index%30==0){
			for(int i=0;i<allEnemys.size();i++){
				Tank enemy = allEnemys.get(i);
				Bullet bullet = enemy.shoot();
				allBullets.add(bullet);
			}
		}
		
	}

	/**
	 * 处理子弹与障碍物的碰撞事件
	 * @param allBarriers
	 */
	protected void touchAction(ArrayList<Barrier> allBarriers) {
		for(int i=0;i<allBullets.size();i++){
			Bullet b = allBullets.get(i);
			b.touchBarrier(allBarriers,allBullets);
		}
		
	}

	/**
	 * 控制子弹和敌军的移动
	 */
	protected void moveAction() {
		//控制所有子弹移动
		for(int i=0;i<allBullets.size();i++){
			Bullet bullet = allBullets.get(i);
			bullet.move();
		}
		//控制所有的敌军移动
		for(int i=0;i<allEnemys.size();i++){
			Tank enemy = allEnemys.get(i);
			enemy.move(allBarriers,allEnemys,hero);
		}
	}

	public void paint(Graphics g) {
		g.drawImage(ImageUtil.getImage("jay.jpg"), 0, 0, null);
		drawHero(g);
		drawBullet(g);
		drawKing(g);
		drawBarrier(g);
		drawEnemy(g);
	}
	/*
	 * 绘制所有的敌军
	 */
	private void drawEnemy(Graphics g) {
		for(int i=0;i<allEnemys.size();i++){
			Tank enemy = allEnemys.get(i);
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
		}
		
	}

	private void drawBarrier(Graphics g) {
		for(int i=0;i<allBarriers.size();i++){
			Barrier b = allBarriers.get(i);
			g.drawImage(b.getImage(), b.getX(), b.getY(), null);
		}
		
	}

	private void drawKing(Graphics g) {
		
		g.drawImage(
				king.getImage(), king.getX(), king.getY(), null);
		
	}

	/**
	 * 绘制子弹
	 * @param g
	 */
	private void drawBullet(Graphics g) {
		//遍历存放子弹的数组
		for(int i=0;i<allBullets.size();i++){
			//取出该数组的每一个元素
			Bullet bullet = allBullets.get(i);
			//把每一个元素都画出来
			g.drawImage(
					bullet.getImage(), bullet.getX(),
					bullet.getY(), null);
		}
		
	}

	/**
	 * 绘制英雄机
	 * @param g
	 */
	private void drawHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
		
	}
//内部类  	继承了KeyAdapter 
class MyKeyAdapter extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		//运行状态
	if(state==RUNNING){
		if(keyCode==KeyEvent.VK_UP){
			hero.moveUpAction(allBarriers,allEnemys,hero);
		}else if(keyCode==KeyEvent.VK_LEFT){
			hero.moveLeftAction(allBarriers,allEnemys,hero);
		}else if(keyCode==KeyEvent.VK_RIGHT){
			hero.moveRightAction(allBarriers,allEnemys,hero);
		}else if(keyCode==KeyEvent.VK_DOWN){
			hero.moveDownAction(allBarriers,allEnemys,hero);
		}else if(keyCode==KeyEvent.VK_SPACE){
			shootAction();
		}else if(keyCode==KeyEvent.VK_P){
			state = PAUSE;
		}
	}
	//暂停状态
	if(state == PAUSE){
		receivePause(keyCode);
	}
	if(state == GAMEOVER){
		receiveGameOver(keyCode);
	}
		repaint();//重画
	}
}
/**
 * 英雄机射击事件
 */
public void shootAction() {
	Bullet bullet = hero.shoot();
	bullet.setFromHero(true);//当前子弹标记为英雄机发出
	//Arrays.copyOf(被复制的数组，复制的长度);
	//数组名.length:得到数组当前的长度
//	allBullets=Arrays.copyOf(allBullets, allBullets.length+1);
//	allBullets[allBullets.length-1]=bullet;
	allBullets.add(bullet);
}

public void receiveGameOver(int keyCode) {
	if(keyCode==KeyEvent.VK_R){
		wall = new FirstWall();
		allBarriers = wall.allBarriers;
		allEnemys = wall.allEnemys;
		allBullets.removeAll(allBullets);
		hero = new Hero(
				300,530,3,ImageUtil.getImage("tankU.png"));
		state = RUNNING;
	}
	
}

public void receivePause(int keyCode) {
	if(keyCode==KeyEvent.VK_S){
		System.out.println("00000000000000");
		state = RUNNING;
	}else if(keyCode==KeyEvent.VK_Q){
		System.exit(0);
	}
}	
}
