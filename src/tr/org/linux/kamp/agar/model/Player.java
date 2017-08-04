package tr.org.linux.kamp.agar.model;

import java.awt.Color;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
/**
 * 
 * @author ali
 *@version 1.0
 */




public class Player extends GameObject {
	

	private int speed;
	private String playerName;

	public Player(int x, int y, int radius,int speed, Color color,String playerName) {
		super(x, y, radius, color);
		this.speed=speed;
		this.playerName=playerName;
		// TODO Auto-generated constructor stub
	}
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	/**
	 * 	setRadius.
	 */
	public void setRadius(int radius) {
		// TODO Auto-generated method stub
		super.setRadius(radius);
		if(getRadius()<5){
			setRadius(5);
		}
		else if(getRadius()>250){
			setRadius(250);
		}
		
	}
	
	

	public void draw(Graphics2D g2d){
		super.draw(g2d);
		FontMetrics fontMetrics=g2d.getFontMetrics(g2d.getFont());
		int width=fontMetrics.stringWidth(playerName);
		int nameX=getX()+(getRadius()-width)/2;
		int nameY=getY()-fontMetrics.getHeight();
		g2d.drawString(playerName, nameX, nameY);
		
		
	}
	
	

	

}
