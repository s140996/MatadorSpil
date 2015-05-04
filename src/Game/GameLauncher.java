package Game;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.*;
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

		turn();	
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
		if (lastManStanding() == false)
		{
			for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
			{	
				if (playerlist[playerNo].getAlive() == true)
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

								//Spilleren lander på feltet
								gb.getField(playerlist[playerNo].getPosition() - 1).landOnField(playerlist[playerNo], gui, cc, cup, gb);

								//Tjekker om spiller har tabt
								if (playerlist[playerNo].acc.getBalance() == 0)
								{
									gui.playerLost(playerlist[playerNo].toString());

									playerlist[playerNo].setAlive(false);

									for(int i = 0; i < 40; i++)
									{
										GField field = gb.getField(i);
										if (field.getType() == "Territory" || field.getType() == "Fleet" || field.getType() == "Brewery")
										{
											GOwnable ownField = (GOwnable) field;
											if (ownField.getOwner() == playerlist[playerNo])
											{
												ownField.removeOwner(playerlist[playerNo], ownField.getID(), gui);
											}
										}
									}
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
								if (cup.getDoubleRoll() > 0)
								{
									gui.showMessage("Du skal spille din tur færdig før du kan gemme spillet.");
								}
								else
								{
									db.save(playerlist, this.amountOfPlayers, this.playerNo, gb);
								}
								
								break;
							}
						}
					}
				}
			}
		}
		else if (lastManStanding() == true)
		{
			gui.showMessage("Tillykke!!!!! Du har vundet spillet.");
		}
	}

	public void setAmountOfPlayers(int no)
	{
		this.amountOfPlayers = no;
	}

	public boolean lastManStanding()
	{
		int playerLeft = 0;

		for (playerNo = 1; playerNo < amountOfPlayers + 1; playerNo++)
		{
			if (playerlist[playerNo].getAlive() == true)
			{
				playerLeft++;
			}
		}

		if (playerLeft == 1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
