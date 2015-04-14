

public class GameLauncher {

	private GUIController GUIJ = new GUIController();
	private GameBoard gb = new GameBoard();
	
	public static void main(String[] args) {
		
	new GameLauncher().spil();
	
	}
	
	public void spil()
	{
		GUIJ.createGameboard(gb);
		
		GUIJ.setDice(1, 2);
	}

}
