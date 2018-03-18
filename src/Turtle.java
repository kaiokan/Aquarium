/*資管三A 103403519 劉昌平*/

import java.security.SecureRandom;
import javax.swing.*;
import java.awt.*;

public class Turtle implements Runnable{
	private SecureRandom generator = new SecureRandom();
	private int xdir, speed; //0向右 1向左
	private ImageIcon turtleimage = null;
	private Point point = new Point();	
	private boolean onwall = false;
	private int turtlestop = 0;
	
	Turtle(Point turtlepoint){
		point = turtlepoint;	
		random();
	}
	
	public Point getpoint(){
		return point;
	}
	public ImageIcon getturtleimage(){
		return turtleimage;
	}
	public int getturtlestop(){
		return turtlestop;
	}
	public void setturtlestop(){
		turtlestop = 1;
	}
	public void setturtlewalk(){
		turtlestop = 0;
	}
	
	private void random(){
		xdir = generator.nextInt(2); //0向右 1向左
		speed = generator.nextInt(2) + 1;
		if(xdir == 0)
			turtleimage = new ImageIcon("w.png");
		else if(xdir == 1)
			turtleimage = new ImageIcon("w2.png");
		
	}
	
	private void checkwall(){
		if((point.x-100) < 0){
			xdir = 0;
			turtleimage = new ImageIcon("w.png");
			onwall = true;
		}
		if((point.x+100) > 1000){
			xdir = 1;
			turtleimage = new ImageIcon("w2.png");
			onwall = true;
		}
		onwall = false;
	}
	
	private void walk(){
		if(xdir == 0)
			point.x += speed;
		else if(xdir == 1)
			point.x -= speed;
	}
	
	public void run(){
		while((point.y+280) < 1000){
			try{
				System.out.println(point.y);
				point.y = point.y + 10;
				Thread.sleep(20);
				Interface.tank.repaint();
			}
			catch(InterruptedException e){}
		}
		while(true){
			try{
				checkwall(); //看方向
				if(onwall == false && turtlestop == 0){
					int randomnum = generator.nextInt(50);
					if(randomnum == 30)
						random();
				}
				if(turtlestop == 0){ //烏龜會走		
					walk();
					Thread.sleep(20);
				}
				Interface.tank.repaint();
			}
			catch(InterruptedException e){}
		}
	}
}