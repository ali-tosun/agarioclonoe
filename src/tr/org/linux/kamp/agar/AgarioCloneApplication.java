package tr.org.linux.kamp.agar;

import java.awt.Color;

import tr.org.linux.kamp.agar.logic.GameLogic;
import tr.org.linux.kamp.agar.model.Dificulty;

public class AgarioCloneApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameLogic gameLogic=new GameLogic("Guray",Color.blue,Dificulty.EASY);
		gameLogic.startApplication();
		
	}

}
