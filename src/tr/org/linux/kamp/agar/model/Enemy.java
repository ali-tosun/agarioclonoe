package tr.org.linux.kamp.agar.model;

import java.awt.Color;
/**
 * @version 1.0
 * @author ali
 *
 */

public class Enemy extends GameObject {
	private int speed;
	
	public Enemy(int x, int y, int radius,int speed, Color color) {
		super(x, y, radius, color);
		this.speed=speed;
		// TODO Auto-generated constructor stub
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
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

}
