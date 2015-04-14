import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;


public class GameLauncher {

	public static void main(String[] args) {
		
	GUIController GUIJ = new GUIController();
	GameBoard gb = new GameBoard();
	
	GUIJ.createGameboard(gb);
	
	}

}
