

public class GameLauncher {

	private int amountOfPlayers;
	private int playerNo;

	private GUIController gui = new GUIController();
	private GameBoard gb = new GameBoard();
	private Cup cup = new Cup();
	private InPrison prison = new InPrison();

	private Player[] playerlist;

	private ChanceCardList cc = new ChanceCardList();

	public void spil()
	{
		gui.createGameboard(gb);
		cc.randomizer();
		
		switch(gui.startMenu())
		{
		case "Nyt spil":
			newGame();
			break;
		case "Load spil":
			
			break;
		}
		
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

			//Checker om spilleren er i fængslet
			if (playerlist[playerNo].getConvict() == true)
			{
				prison.inPrison(playerlist[playerNo], cup, gui);
			}
			else
			{
				while (con == true)
				{
					switch (gui.turn(playerlist[playerNo]))
					{
					case "Kast terning":
						//Kaster terning
						cup.roll();
						gui.setDice(cup.getDieOne(), cup.getDieTwo());

						//Flytter spiller
						playerlist[playerNo].changePosition(cup.getLastRoll(), gui);
						gui.moveCar(playerlist[playerNo].getPosition(), playerlist[playerNo].toString());

						//Spilleren lander på feltet
						gb.getField(playerlist[playerNo].getPosition() - 1).landOnField(playerlist[playerNo], gui, cc, cup.getLastRoll(), gb);
						
						if (cup.getDieOne() == cup.getDieTwo())
						{
							//Stadig samme spillers tur
						}
						else
						{
						con = false;
						}
						break;
					case "Sælg huse":

						break;
					case "Pantsæt":

						break;
					case "Gem spil":
						
						break;
					}
				}
			}
		}
	}

}
