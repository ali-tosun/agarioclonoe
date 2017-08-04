package tr.org.linux.kamp.agar.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import tr.org.linux.kamp.agar.model.GameObject;
/**
 * @version 1.0
 * @author ali
 *
 */
public class GamePanel extends JPanel{
	
	private ArrayList<GameObject> gameObjects;
	
	
	public GamePanel(ArrayList<GameObject> gameObjects) {
		this.gameObjects=gameObjects;
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/**
	 * kenisine gelen tip gameObject tipinde ise direk olarak ekrana çizer.
	 */
	protected synchronized void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		
		for (GameObject gameObject : gameObjects) {
			gameObject.draw(g2d);
		}
		
		
	}

}
