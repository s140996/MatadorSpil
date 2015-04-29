package Game;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.InPrison;
import Fields.Pawn;
import Player.Player;

public class GameLauncher {

	private int amountOfPlayers;
	private int playerNo;

	private GUIController gui = new GUIController();
	private GameBoard gb = new GameBoard();
	private Cup cup = new Cup();
	private InPrison prison = new InPrison();
	private Pawn pawn = new Pawn();

	private Player[] playerlist;

	private ChanceCardList cc = new ChanceCardList();
	private DBController db = new DBController();
	
	public void game()
	{
		gui.createGameboard(gb);
		cc.randomizer();

		switch(gui.startMenu())
		{
		case "Nyt spil":
			newGame();
			break;
		case "Load spil":
			db.load();
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
			playerlist[playerNo] = new Player(gui.newPlayer(playerNo), 0, 1, 0, 0, 0, false, true);
			gui.addPlayer(playerlist[playerNo].toString());
		}

	}

	public void turn()
	{
		for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
		{	
			boolean con = true;

			cup.resetDoubleRoll();

			while (con == true) //Den pågældende spillers tur
			{
				//Checker om spilleren er i fængslet
				if (playerlist[playerNo].getConvict() == true)
				{
					prison.inPrison(playerlist[playerNo], cup, gui);

					if (playerlist[playerNo].getConvict() == true)
					{
						con = false;
					}
				}

				if (playerlist[playerNo].getConvict() == false)
				{
					switch (gui.turn(playerlist[playerNo]))
					{
					case "Kast terninger":
						//Kaster terning
						cup.roll();
						gui.setDice(cup.getDieOne(), cup.getDieTwo());

						//Flytter spiller
						playerlist[playerNo].changePosition(cup.getLastRoll(), gui);
						gui.moveCar(playerlist[playerNo].getPosition(), playerlist[playerNo].toString());
						
						//Sætter lastBalance
						playerlist[playerNo].acc.setLastBalance(playerlist[playerNo].acc.getBalance());

						//Spilleren lander på feltet
						gb.getField(playerlist[playerNo].getPosition() - 1).landOnField(playerlist[playerNo], gui, cc, cup, gb);
						
						//Tjekker om spiller har tabt
						if (playerlist[playerNo].acc.getBalance() == 0)
						{
							
						}
						
						if (cup.getDieOne() == cup.getDieTwo() && playerlist[playerNo].getConvict() == false)
						{
							if (cup.getDoubleRoll() == 3)
							{
								//Går i fængsel
								gui.showMessage("Du har slået tre gange to ens!");
								gb.getField(31 - 1).landOnField(playerlist[playerNo], gui, cc, cup, gb);
								con = false;
							}
							else 
							{
								//Stadig samme spillers tur
								gui.showMessage("Du har slået to ens, og derfor fået en ekstra tur!");
							}
						}
						else
						{
							con = false;
						}
						
						break;
					case "Sælg hotel":
						pawn.sellHotel(playerlist[playerNo], gb, gui);
						break;
					case "Sælg huse":
						pawn.sellHouse(playerlist[playerNo], gb, gui);
						break;
					case "Pantsæt":
						pawn.pawnGround(playerlist[playerNo], gb, gui);
						break;
					case "Gem spil":
						db.save(playerlist, this.amountOfPlayers);
						break;
					}
				}
			}
		}
	}
	
	public void setAmountOfPlayers(int no)
	{
		this.amountOfPlayers = no;
	}

}
