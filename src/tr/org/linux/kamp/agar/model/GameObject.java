package tr.org.linux.kamp.agar.model;

import java.awt.*;
/**
 * 
 * @author ali
 * @version 1.0
 * 
 */

public abstract class GameObject {
	

	
	private int x; // posizyon bilgileri.
	private int y; // posizyon bilgileri.
	private int radius;
	private Color color;
	public GameObject(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	/**
	 * 
	 * @param g2d , Draw on the screen.
		
	 */
	public void draw(Graphics2D g2d){
		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getRadius(), getRadius());
	}
	
	
	/**
	 * 	
		
	In the GameLogic class, it is used for collision checks.
	 * @return
	 */
	public Rectangle getRectangle(){
		Rectangle rct=new Rectangle(getX(),getY(),getRadius(),getRadius());
		return rct;
	}
	
	
	/**
	 * Getter and Setter
	 * @return
	 */
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	

}
