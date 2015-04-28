package Fields;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class Pawn {

	public void pawnGround (Player player, GameBoard gb, GUIController gui) 
	{
		int count = 0;

		for(int i = 0; i < 40; i++)
		{
			GField field = gb.getField(i);

			if (field.getType() == "Brewery" || field.getType() == "Fleet")
			{
				GOwnable ownField = (GOwnable) field;

				if (ownField.getPawn() == false && ownField.getOwner() == player)
				{
					count++;
					boolean reply = gui.boolButton("Vil du pantsætte " + ownField.getName() + " og modtage " + ownField.getPrice() / 2 + "?", "Ja", "Nej");

					if (reply == true)
					{
						player.acc.deposit(ownField.getPrice() / 2);
						gui.setGUIBalance(player.acc.getBalance(), player.toString());
						player.setWorth(-ownField.getPrice());
						ownField.setPawn(true);
					}
				}
			}
			else if (field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;

				if (territory.getPawn() == false && territory.getHouseCount() == 0 && territory.getHotelCount() == 0 && territory.getOwner() == player)
				{
					count++;
					boolean reply = gui.boolButton("Vil du pantsætte " + territory.getName() + " og modtage " + territory.getPrice() / 2 + "?", "Ja", "Nej");

					if (reply == true)
					{
						player.acc.deposit(territory.getPrice() / 2);
						gui.setGUIBalance(player.acc.getBalance(), player.toString());
						player.setWorth(-territory.getPrice());
						territory.setPawn(true);
					}
				}
			}

		}

		if (count == 0)
		{
			gui.showMessage("Du har ingen grunde at pantsætte. Bemærk, hvis der er bygget huse eller hoteller på en grund, så skal de sælges før, at den kan pantsættes.");
		}

	}

	public void sellHotel (Player player, GameBoard gb, GUIController gui)
	{
		int count = 0;

		for(int i = 0; i < 40; i++)
		{
			GField field = gb.getField(i);

			if (field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;

				if (territory.getHotelCount() == 1)
				{
					count++;
					boolean reply = gui.boolButton("Vil du sælge " + territory.getName() + "'s hotel og modtage " + territory.getBuildPrice() / 2 + "?", "Ja", "Nej");

					if (reply == true)
					{
						player.acc.deposit(territory.getBuildPrice() / 2);
						gui.setGUIBalance(player.acc.getBalance(), player.toString());
						player.setWorth(-territory.getBuildPrice());
						player.setHotelCount(-1);
						territory.removeHotel();
						gui.setHotel(territory.getID(), false);
						player.setHouseCount(4);
						territory.setHouse(4);
						gui.setHouses(territory.getID(), territory.getHouseCount());
					}
				}
			}
		}

		if (count == 0)
		{
			gui.showMessage("Du har ingen hoteller at sælge.");
		}
	}

	public void sellHouse (Player player, GameBoard gb, GUIController gui)
	{
		int count = 0;

		for(int i = 0; i < 40; i++)
		{
			GField field = gb.getField(i);

			if (field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;

				if (territory.getHouseCount() > 0)
				{
					count++;

					boolean run = true;

					while (run == true)
					{
						if (0 < territory.getHouseCount() && territory.getHouseCount() <= 4)
						{
							boolean reply = gui.boolButton("Vil du sælge " + territory.getName() + "'s hus og modtage " + territory.getBuildPrice() / 2 + "?", "Ja", "Nej");

							if (reply == true)
							{
								player.acc.deposit(territory.getBuildPrice() / 2);
								gui.setGUIBalance(player.acc.getBalance(), player.toString());
								player.setWorth(-territory.getBuildPrice());
								player.setHouseCount(-1);
								territory.setHouse(player.getHouseCount() - 1);
								gui.setHouses(territory.getID(), territory.getHouseCount());
							}
							else if (reply == false)
							{
								run = false;
							}
						}
						else
						{
							run = false;
						}
					}
				}
			}
		}

		if (count == 0)
		{
			gui.showMessage("Du har ingen huse at sælge.");
		}
	}
}
