/*資管三A 103403519 劉昌平*/

import java.security.SecureRandom;
import javax.swing.*;
import java.awt.*;

public class Fish implements Runnable{
	private SecureRandom generator = new SecureRandom();
	private final int fishtype = generator.nextInt(3); //0=黃魚 1=藍魚 2=紅魚
	private final int size = generator.nextInt(100) + 50;
	private int xdir, ydir, speed; //0向右 1向左
	private ImageIcon fishimage = null;
	private Point point = new Point();	
	private boolean onwall = false;
	private int fishstop = 0; //0是游 1是停
	
	Fish(Point fishpoint){
		point = fishpoint;	
		random();
		speed = generator.nextInt(2) + 1;
	}
	
	public Point getpoint(){
		return point;
	}
	public ImageIcon getfishimage(){
		return fishimage;
	}
	public void setfishswim(){
		fishstop = 0;
	}
	public void setfishstop(){
		fishstop = 1;
	}
	public int getfishstop(){
		return fishstop;
	}
	public int getfishsize(){
		return size;
	}
	private void random(){
		xdir = generator.nextInt(2); //0向右 1向左
		ydir = generator.nextInt(2); //0向上 1向下
		if(fishtype == 0){
			if(xdir == 0)
				fishimage = new ImageIcon("1.png");
			else if(xdir == 1)
				fishimage = new ImageIcon("2.png");
		}
		else if(fishtype == 1){
			if(xdir == 0)
				fishimage = new ImageIcon("3.png");
			else if(xdir == 1)
				fishimage = new ImageIcon("4.png");
		}
		else if(fishtype == 2){
			if(xdir == 0)
				fishimage = new ImageIcon("5.png");
			else if(xdir == 1)
				fishimage = new ImageIcon("6.png");
		}
	}
	
	private void checkwall(){
		if(point.y < 100){
			ydir = 1;
			onwall = true;
		}
		if((point.y+150) > 800){
			ydir = 0;
			onwall = true;
		}
		if((point.x-100) < 0){
			xdir = 0;
			if(fishtype == 0){
				fishimage = new ImageIcon("1.png");
			}
			else if(fishtype == 1){
				fishimage = new ImageIcon("3.png");
			}
			else if(fishtype == 2){
				fishimage = new ImageIcon("5.png");
			}
			onwall = true;
		}
		if((point.x+100) > 1000){
			xdir = 1;
			if(fishtype == 0){
				fishimage = new ImageIcon("2.png");
			}
			else if(fishtype == 1){
				fishimage = new ImageIcon("4.png");
			}
			else if(fishtype == 2){
				fishimage = new ImageIcon("6.png");
			}
			onwall = true;
		}
		onwall = false;
	}
	
	private void swim(){
		if(xdir == 0){
			point.x += speed;
			if(ydir == 0)
				point.y -= speed;
			else
				point.y += speed;
		}
		else if(xdir == 1){
			point.x -= speed;
			if(ydir == 0)
				point.y -= speed;
			else
		 		point.y += speed;
		}
	}
	
	public void run(){
		while(true){
			try{
				checkwall(); //看方向
				if(onwall == false && fishstop == 0){
					int randomnum = generator.nextInt(50);
					if(randomnum == 30)
						random();
				}
				if(fishstop == 0){ //魚會游		
					swim();
					Thread.sleep(20);
				}
				Interface.tank.repaint();
			}
			catch(InterruptedException e){}
		}
	}
}