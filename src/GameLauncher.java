

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
		
		for (playerNo = 1; playerNo < amountOfPlayers; playerNo++)
		{
			playerlist[playerNo] = new Player(gui.newPlayer(playerNo),);
			gui.addPlayer(playerlist[playerNo].toString());
		}
		
	}

	
	
}
