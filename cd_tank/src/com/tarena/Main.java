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
 * JFrame:����
 * JPanel:��ֽ
 * Grahics:����
 * �ѻ�ֽ��ӵ������ϣ�Ȼ����û�ֽ�Ļ滭(paint)���ܣ�
 * �û����ڻ�ֽ�ϻ滭��
 * Ȼ��ѻ�����ʾ����
 */
public class Main extends JPanel{
	public int state;//0���� 1��ͣ 2����
	
	public static final int RUNNING = 0;
	public static final int PAUSE = 1;
	public static final int GAMEOVER = 2;
	
	
	//����Ӣ�۶���
	Hero hero = new Hero(
			300,530,3,ImageUtil.getImage("tankU.png"));
	//new Bullet[0]:����һ������Ϊ0��Bullet����
	ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
	//����wall����
	Wall wall = new FirstWall();
	King king = wall.king;
	//ȡ��wall�����allBarriers���Ե�ֵ   
	//allBarriers���������е��ϰ���
	ArrayList<Barrier> allBarriers = wall.allBarriers;
	//ȡ��wall�����allEnemys���Ե�ֵ
	//allEnemys���������еĵо�
	ArrayList<Tank> allEnemys = wall.allEnemys;
	public static void main(String[] args){
		//�����������
		JFrame frame = new JFrame("̹�˴�ս");
		//���û���ĳߴ�
		frame.setSize(800, 600);
		//���û����λ��
		frame.setLocationRelativeTo(null);
		//���ùر�frame��ͬʱ��������
		frame.setDefaultCloseOperation(3);
		//������ֽ����
		Main panel = new Main();
		//�ѻ�ֽ��ӵ�������
		frame.add(panel);
		//��ʾ����
		frame.setVisible(true);
		panel.action();//��Ϸ��ʼ
		
	}
	
	public void action(){
		
		//��new ���� ��   ���ʵ��   ͬʱ���
		KeyAdapter l = new MyKeyAdapter();
			//ʵ��KeyListener�ĳ��󷽷�
		this.addKeyListener(l);
		//���󽹵�
		this.requestFocus(true);
		
		/*
		 * TimerTask��������߳�
		 * TimerTask�������ע�ᵽTimer����
		 * Timer�������ͨ��schedule(),���Կ���
		 * ���߳�run����ִ�е�Ƶ��
		 */
		TimerTask task = new TimerTask(){
			//����һ���̣߳�����ִ�и��̵߳�run����
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
		//�ӳ�1000��������ע����̣߳�����10�����Ƶ��ִ��
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
	 * ����о��¼�
	 * 1.��bomAtion()�������������ӵ�(allBullets����)��ȡ��ÿһ���ӵ�
	 * 2.����ȡ�����ӵ������touchEnemy(allBullets,allEnemys)����
	 * 3.ʵ��touchEnemy������
	 *   3.1 �������ео�(allEnemys����)
	 *   3.2 �жϵ�ǰ�ӵ��Ƿ��ÿһ���о���ײ
	 *   3.3 �����ײ����ɾ������ײ�о��͵�ǰ�ӵ�
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
	//�о�������¼�
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
	 * �����ӵ����ϰ������ײ�¼�
	 * @param allBarriers
	 */
	protected void touchAction(ArrayList<Barrier> allBarriers) {
		for(int i=0;i<allBullets.size();i++){
			Bullet b = allBullets.get(i);
			b.touchBarrier(allBarriers,allBullets);
		}
		
	}

	/**
	 * �����ӵ��͵о����ƶ�
	 */
	protected void moveAction() {
		//���������ӵ��ƶ�
		for(int i=0;i<allBullets.size();i++){
			Bullet bullet = allBullets.get(i);
			bullet.move();
		}
		//�������еĵо��ƶ�
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
	 * �������еĵо�
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
	 * �����ӵ�
	 * @param g
	 */
	private void drawBullet(Graphics g) {
		//��������ӵ�������
		for(int i=0;i<allBullets.size();i++){
			//ȡ���������ÿһ��Ԫ��
			Bullet bullet = allBullets.get(i);
			//��ÿһ��Ԫ�ض�������
			g.drawImage(
					bullet.getImage(), bullet.getX(),
					bullet.getY(), null);
		}
		
	}

	/**
	 * ����Ӣ�ۻ�
	 * @param g
	 */
	private void drawHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
		
	}
//�ڲ���  	�̳���KeyAdapter 
class MyKeyAdapter extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		//����״̬
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
	//��ͣ״̬
	if(state == PAUSE){
		receivePause(keyCode);
	}
	if(state == GAMEOVER){
		receiveGameOver(keyCode);
	}
		repaint();//�ػ�
	}
}
/**
 * Ӣ�ۻ�����¼�
 */
public void shootAction() {
	Bullet bullet = hero.shoot();
	bullet.setFromHero(true);//��ǰ�ӵ����ΪӢ�ۻ�����
	//Arrays.copyOf(�����Ƶ����飬���Ƶĳ���);
	//������.length:�õ����鵱ǰ�ĳ���
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
