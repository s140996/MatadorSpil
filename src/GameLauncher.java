

public class GameLauncher {

	private int amountOfPlayers;
	private int playerNo;
	
	private GUIController gui = new GUIController();
	private GameBoard gb = new GameBoard();
	private Cup cup = new Cup();
	
	private Player[] playerlist;
	
	private ChanceCardList cc = new ChanceCardList();
	
	public void spil()
	{
		gui.createGameboard(gb);
		cc.randomizer();
		
		newGame();
		
		boolean gameOn = true;
		while (gameOn == true)
		{
		turn();
		}
	}
	
	public void newGame()
	{	
		amountOfPlayers = gui.amountOfPlayers();
		playerlist = new Player[amountOfPlayers + 1];
		
		for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
		{ 
			playerlist[playerNo] = new Player(gui.newPlayer(playerNo), 30000, 1, 0, 0, 0, 0, 0, false, true);
			gui.addPlayer(playerlist[playerNo].toString());
		}
		
	}
	
	public void turn()
	{
		for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
		{
			boolean con = true;
			
			while (con == true)
			{
			switch (gui.turn(playerlist[playerNo]))
			{
			case "Kast terning":
				//Kaster terning
				cup.roll();
				gui.setDice(cup.getDieOne(), cup.getDieTwo());
				
				//Flytter spiller
				playerlist[playerNo].changePosition(cup.getLastRoll());
				gui.moveCar(playerlist[playerNo].getPosition(), playerlist[playerNo].toString());
				
				//Spilleren lander på feltet
				gb.getField(playerlist[playerNo].getPosition() - 1).landOnField(playerlist[playerNo], gui, cc, cup.getLastRoll(), gb);
				
				con = false;
				break;
			case "Sælg huse":
				
				break;
			case "Pantsæt":
				
				break;
			}
			}
		}
	}
	
}
