package tr.org.linux.kamp.agar.view;

import javax.swing.*;
/**
 * @version 1.0
 * @author ali
 *
 */

public class GameFrame extends JFrame{
	public GameFrame(){
		/**
		 * Ekran boyutunu ve framei ayarlanır.
		 */
		setTitle("Agario Clone");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,480);
		setLocationRelativeTo(null);
		//setVisible(true); gamelogic ile cakısıyor
	
		}
	
	
	
}
