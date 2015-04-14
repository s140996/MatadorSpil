import desktop_resources.GUI;


public class GameLauncher {

	public static void main(String[] args) {
		
	GUIController GUIJ = new GUIController();
	GameBoard gb = new GameBoard();
	
	GUIJ.createGameboard(gb);
	
	GUIJ.setDice(1, 2);

	
	}

}
