

public class GameLauncher {

	private int amountOfPlayers;
	private int playerNo;
	
	private GUIController gui = new GUIController();
	private GameBoard gb = new GameBoard();
	
	public static void main(String[] args) 
	{
		new GameLauncher().newGame();
	}
	
	public void newGame()
	{	
		gui.createGameboard(gb);
	
		amountOfPlayers = gui.amountOfPlayers();
		Player[] playerlist = new Player[amountOfPlayers + 1];
		
		for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
		{ 
			playerlist[playerNo] = new Player(gui.newPlayer(playerNo), 30000, 1, 0, false, true);
			gui.addPlayer(playerlist[playerNo].toString());
		}
		
	}
	
}
