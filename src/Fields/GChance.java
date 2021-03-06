package Fields;
import java.awt.*;

import ChanceCard.ChanceCard;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class GChance extends GField {

	private int pay;

	public GChance (int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Chance");
		super.setColor(new Color(252, 227, 185));
	}


	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {

		ChanceCard c = cc.draw();

		GGUI.showMessage("Træk et chancekort og prøv lykken!");

		GGUI.showMessage(c.toString());

		switch(c.getType())
		{
		case 1: 
			if(c.getPosition() == 0)
			{
				if(c.getCash() < 0)
				{
					player.acc.withdraw(- c.getCash());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				}
				else
				{
					player.acc.deposit(c.getCash());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				}
			}
			// Gå til start
			else if(c.getPosition() == 1)
			{
				player.goToStart();
				GGUI.moveCar(1, player.toString());
			}
			//Ryk tre frem/tilbage
			else if(c.getPosition() == 3 || c.getPosition() == -3)
			{
				if(player.getPosition() < 4 && c.getPosition() == -3)
				{
					player.setPosition(40);
					GGUI.moveCar(40, player.toString());
					gb.getField(player.getPosition() - 1).landOnField(player, GGUI, cc, cup, gb);
				}
				else
				{
					player.changePosition(c.getPosition(), GGUI);
					GGUI.moveCar(player.getPosition(), player.toString());
					gb.getField(player.getPosition() - 1).landOnField(player, GGUI, cc, cup, gb);
				}


			}
			//Ryk frem til bestemt felt
			else if(c.getPosition() > 3 && c.getPosition() != 11)
			{
				if(c.getPosition() < player.getPosition())
				{
					player.acc.deposit(4000);	
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());	
				}
				player.setPosition(c.getPosition());
				GGUI.moveCar(player.getPosition(), player.toString());
				gb.getField(player.getPosition() - 1).landOnField(player, GGUI, cc, cup, gb);

			}
			//Ryk i fængsel
			else if(c.getPosition() == 11)
			{
				gb.getField(31 - 1).landOnField(player, GGUI, cc, cup, gb);
			}
			//Benådes for fængsel
			else if(c.toString() == "CHANCEKORT: Du benådes for fængsel")
			{
				if(player.getPrisonCard() == 1)
				{
					player.setPrisonCard(2);
				}
				else
				{
					player.setPrisonCard(1);	
				}
			}

			break;
		case 2:
			//Matadorlegatet
			if(player.getWorth() <= 15000)
			{
				player.acc.deposit(40000);
				GGUI.showMessage("Da din samlede formue er under 15000,- modtager du 40000,-");
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			}
			else
			{
				GGUI.showMessage("Da din samlede formue er over 15000,- modtager du ikke 40000,-");
			}

			break;
		case 3:
			// ejendomsskat
			for(int i = 0; i < 40; i++)
			{
				GField field = gb.getField(i);
				if (field.getType() == "Territory")
				{
					GTerritory territory = (GTerritory) field;
					if(territory.isOwned() == true && territory.getPawn() == false)
					{
						int pay2 = territory.getHouseCount() * 800 + territory.getHotelCount() * 2300;
						territory.getOwner().acc.withdraw(pay2);
						GGUI.setGUIBalance(territory.getOwner().acc.getBalance(), territory.getOwner().toString());
						if(pay2 > 0)
						{
							GGUI.showMessage(territory.getOwner().toString() + " skal betale " + pay2 + ",- i ejendomsskat for bebyggelsen på " + territory.getName());
						}
					}
				}
			}
			break;
		case 4:
			//Konfirmation
			player.acc.deposit(1000);
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			break;
		case 5:
			//Ryk frem til nærmeste redderi
			if(player.getPosition() > 36 || player.getPosition() < 6)
			{
				player.setPosition(6);
				GGUI.moveCar(6, player.toString());

				GField field = gb.getField(6 - 1);
				GFleet fleet = (GFleet) field;

				if(fleet.getOwner() == player || fleet.getOwner() == null)
				{
					gb.getField(6 - 1).landOnField(player, GGUI, cc, cup, gb);
				}
				else if (fleet.getOwner() != player && fleet.getOwner() != null && fleet.getOwner().getConvict() == false)
				{
					pay = 2 * fleet.getRent();
					player.acc.withdraw(pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.showMessage(player.toString() + " skal betale " + pay + ",- til " + fleet.getOwner().toString());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
				}
				else
				{
					GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
				}

				if(player.getPosition() > 36)
				{
					player.acc.deposit(4000);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				}
			}
			else if(player.getPosition() > 6 && player.getPosition() < 16)
			{
				player.setPosition(16);
				GGUI.moveCar(16, player.toString());

				GField field = gb.getField(16 - 1);
				GFleet fleet = (GFleet) field;
				if(fleet.getOwner() == player || fleet.getOwner() == null)
				{
					gb.getField(16 - 1).landOnField(player, GGUI, cc, cup, gb);
				}
				else if (fleet.getOwner() != player && fleet.getOwner() != null && fleet.getOwner().getConvict() == false)
				{
					pay = 2 * fleet.getRent();
					player.acc.withdraw(pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.showMessage(player.toString() + " skal betale " + pay + ",- til " + fleet.getOwner().toString());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
				}
				else
				{
					GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
				}

			}
			else if(player.getPosition() > 16 && player.getPosition() < 26)
			{
				player.setPosition(26);
				GGUI.moveCar(26, player.toString());

				GField field = gb.getField(26 - 1);
				GFleet fleet = (GFleet) field;

				if(fleet.getOwner() == player || fleet.getOwner() == null)
				{
					gb.getField(26 - 1).landOnField(player, GGUI, cc, cup, gb);
				}
				else if (fleet.getOwner() != player && fleet.getOwner() != null && fleet.getOwner().getConvict() == false)
				{
					pay = 2 * fleet.getRent();
					player.acc.withdraw(pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.showMessage(player.toString() + " skal betale " + pay + ",- til " + fleet.getOwner().toString());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
				}
				else
				{
					GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
				}

			}
			else if(player.getPosition() > 26 && player.getPosition() < 36)
			{
				player.setPosition(36);
				GGUI.moveCar(36, player.toString());

				GField field = gb.getField(36 - 1);
				GFleet fleet = (GFleet) field;

				if(fleet.getOwner() == player || fleet.getOwner() == null)
				{
					gb.getField(36 - 1).landOnField(player, GGUI, cc, cup, gb);
				}
				else if (fleet.getOwner() != player && fleet.getOwner() != null && fleet.getOwner().getConvict() == false)
				{
					pay = 2 * fleet.getRent();
					player.acc.withdraw(pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.showMessage(player.toString() + " skal betale " + pay + ",- til " + fleet.getOwner().toString());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
				}
				else
				{
					GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
				}
			}
			break;
		case 6:
			//Oliepriserne er steget
			for(int i = 0; i < 40; i++)
			{
				GField field = gb.getField(i);
				if (field.getType() == "Territory")
				{
					GTerritory territory = (GTerritory) field;
					if(territory.isOwned() == true && territory.getPawn() == false)
					{
						int pay2 = territory.getHouseCount() * 500 + territory.getHotelCount() * 2000;
						territory.getOwner().acc.withdraw(pay2);
						GGUI.setGUIBalance(territory.getOwner().acc.getBalance(), territory.getOwner().toString());
						if(pay > 0)
						{
							GGUI.showMessage(territory.getOwner().toString() + " skal betale " + pay2 + ",- for bebyggelsen på " + territory.getName());
						}
					}
				}
			}
			break;
		}
	}

}
