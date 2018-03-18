/*資管三A 103403519 劉昌平*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

public class Tank extends JPanel{
	public static ArrayList <Fish> fishlist = new ArrayList<Fish>();
	public static ArrayList <Turtle> turtlelist = new ArrayList<Turtle>();
	ExecutorService executorService = Executors.newCachedThreadPool();
	
	Tank(){
		addMouseListener(new MouseHandler());
	}
	
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(ButtonPanel.function.equals("新增魚")){
				Fish fish = new Fish(e.getPoint());
				fishlist.add(fish);
				executorService.execute(fish);
				ButtonPanel.label.setText("目前功能：" + ButtonPanel.function + "     魚數量：" + fishlist.size());
			}
			else if(ButtonPanel.function.equals("新增烏龜")){
				Turtle turtle = new Turtle(e.getPoint());
				turtlelist.add(turtle);
				executorService.execute(turtle);
			}
			else if(ButtonPanel.function.equals("移除選取")){
				for(int i=0; i<fishlist.size(); i++){
					if(comparepoint(e.getPoint(), fishlist.get(i).getpoint()) == 1){
						fishlist.remove(i);
						ButtonPanel.label.setText("目前功能：" + ButtonPanel.function + "     魚數量：" + fishlist.size());
						break;
					}
				}
				for(int i=0; i<turtlelist.size(); i++){
					if(comparepoint(e.getPoint(), turtlelist.get(i).getpoint()) == 1){
						turtlelist.remove(i);
						break;
					}
				}
			}
			else if(ButtonPanel.function.equals("停止/繼續選取")){
				for(int i=0; i<fishlist.size(); i++){
					if(comparepoint(e.getPoint(), fishlist.get(i).getpoint()) == 1){
						if(fishlist.get(i).getfishstop() == 0)
							fishlist.get(i).setfishstop();
						else
							fishlist.get(i).setfishswim();
						break;
					}
				}
				for(int i=0; i<turtlelist.size(); i++){
					if(comparepoint(e.getPoint(), turtlelist.get(i).getpoint()) == 1){
						if(turtlelist.get(i).getturtlestop() == 0)
							turtlelist.get(i).setturtlestop();
						else
							turtlelist.get(i).setturtlewalk();
						break;
					}
				}
			}
		}
	}
	
	private int comparepoint(Point p1, Point p2){
		if(p1.x > (p2.x-100) && p1.x < (p2.x+100) && p1.y > (p2.y-100) && p1.y < (p2.y+100))
			return 1;
		return 0;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g = g.create();
		this.setBackground(new Color(135, 206, 255));
		for(Fish fishes:fishlist)
			g.drawImage(fishes.getfishimage().getImage(), fishes.getpoint().x -100, fishes.getpoint().y - 100, fishes.getfishsize(), fishes.getfishsize(), null);
		for(Turtle turtles:turtlelist)
			g.drawImage(turtles.getturtleimage().getImage(), turtles.getpoint().x -100, turtles.getpoint().y - 100, null);
	}
}
