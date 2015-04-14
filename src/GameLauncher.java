

public class GameLauncher {

	private GUIController gui = new GUIController();
	private GameBoard gb = new GameBoard();
	
	public static void main(String[] args) 
	{
		new GameLauncher().spil();
	}
	
	public void spil()
	{
		gui.createGameboard(gb);
		
		gui.setDice(1, 2);
	}

	
	
}
