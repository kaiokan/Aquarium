import java.awt.*;
import javax.swing.*;

public class Interface extends JFrame{
	public static ButtonPanel buttonpanel;
	public static Tank tank;
	public Interface(){
		super("水族箱");
		buttonpanel = new ButtonPanel();
		add(buttonpanel, BorderLayout.NORTH);
		tank = new Tank();
		add(tank, BorderLayout.CENTER);
	}
	
	public static void main( String[] args ){ 
		  Interface run = new Interface();
	      run.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      run.setSize( 1000, 800 ); 
	      run.setVisible( true ); 
	 } 
}
