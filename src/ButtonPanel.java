/*資管三A 103403519 劉昌平*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel extends JPanel{
	private JButton addfish, addturtle;
	private JButton delete, deleteall;
	private JButton chooseone, chooseall;
	public static JLabel label;
	public static String function = "新增魚";
	
	ButtonPanel(){
		setLayout(new BorderLayout());
		buttons buttonpanel = new buttons();
		add(buttonpanel, BorderLayout.NORTH);
		label = new JLabel("目前功能：新增魚" + "     魚數量：0");
		label.setOpaque(true);
		label.setForeground(new Color(0, 178, 238));
		label.setBackground(new Color(54, 54, 54));
		add(label, BorderLayout.CENTER);
	}
	
	public class buttons extends JPanel{
		buttons(){
			GridLayout gridlayout = new GridLayout(2, 3, 0, 0);
			setLayout(gridlayout);
			addfish = new JButton("新增魚");
			addturtle = new JButton("新增烏龜");
			delete = new JButton("移除選取");
			deleteall = new JButton("移除全部");
			chooseone = new JButton("停止/繼續選取");
			chooseall = new JButton("停止/繼續全部");
		
			ButtonHandler buttonhandler = new ButtonHandler();
			addfish.addActionListener(buttonhandler);
			addturtle.addActionListener(buttonhandler);
			delete.addActionListener(buttonhandler);
			deleteall.addActionListener(buttonhandler);
			chooseone.addActionListener(buttonhandler);
			chooseall.addActionListener(buttonhandler);
			add(addfish);
			add(delete);
			add(chooseone);
			add(addturtle);
			add(deleteall);
			add(chooseall);
		}
	}
	
	public class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("新增魚")){
				function = "新增魚";
				label.setText("目前功能：" + function + "     魚數量：" + Interface.tank.fishlist.size());
			}
			else if(e.getActionCommand().equals("新增烏龜"))
				function = "新增烏龜";
			else if(e.getActionCommand().equals("移除選取")){
				function = "移除選取";
				label.setText("目前功能：" + function + "     魚數量：" + Interface.tank.fishlist.size());
			}
			else if(e.getActionCommand().equals("移除全部")){
				function = "移除全部";
				Interface.tank.fishlist.clear();
				Interface.tank.turtlelist.clear();
				Interface.tank.repaint();
				label.setText("目前功能：" + function + "     魚數量：" + Interface.tank.fishlist.size());
			}
			else if(e.getActionCommand().equals("停止/繼續選取"))
				function = "停止/繼續選取";
			else if(e.getActionCommand().equals("停止/繼續全部")){
				function = "停止/繼續全部";
				for(int i=0; i<Interface.tank.fishlist.size(); i++){
					if(Interface.tank.fishlist.get(i).getfishstop() == 1) //魚是停的
						Interface.tank.fishlist.get(i).setfishswim();
					else //魚是游的
						Interface.tank.fishlist.get(i).setfishstop();
				}
				for(int i=0; i<Interface.tank.turtlelist.size(); i++){
					if(Interface.tank.turtlelist.get(i).getturtlestop() == 1)
						Interface.tank.turtlelist.get(i).setturtlewalk();
					else
						Interface.tank.turtlelist.get(i).setturtlestop();
				}
			}
		}
	}
}
