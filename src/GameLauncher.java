import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;


public class GameLauncher {

	public static void main(String[] args) {
		
	/*	 Car car = new Car.Builder()
         .typeRacecar()
         .primaryColor(Color.BLUE)
         .secondaryColor(Color.RED)
         .patternDiagonalDualColor()
         .build();
		
		GUI.addPlayer("Peter", 1000);
		GUI.addPlayer("HEJ", 1000);
		GUI.addPlayer("ter", 1000);
		GUI.addPlayer("eter", 1000);
		GUI.addPlayer("Pete", 1000);
		GUI.addPlayer("diller", 1000, car);
		
	*/
		
		
		GUIController.addPlayer("Hans");
		GUI.setDice(5, 4);

	}

}
