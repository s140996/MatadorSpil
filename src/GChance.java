import java.awt.*;
import java.util.*;


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
			player.acc.deposit(c.getCash());
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			// Gå til start
			if(c.getPosition() == 1)
			{
				player.setPosition(1);
				GGUI.moveCar(1, player.toString());
				player.acc.deposit(4000);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			}
			//Ryk tre frem/tilbage
			if(c.getPosition() == 3 || c.getPosition() == -3)
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
			if(c.getPosition() > 3 && c.getPosition() != 11)
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
			if(c.getPosition() == 11)
			{
				gb.getField(31 - 1).landOnField(player, GGUI, cc, cup, gb);
			}
			//Benådes for fængsel
			if(c.toString() == "CHANCEKORT: Du benådes for fængsel")
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
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			}

			break;
		case 3:
			// ejendomsskat
			for(int i = 0; i>40; i++)
			{
				GField field = gb.getField(i);
				if (field.getType() == "Territory")
				{
					GTerritory territory = (GTerritory) field;
					if(territory.isOwned() == true)
					{
						int pay2 = territory.getHouseCount() * 500 + territory.getHotelCount() * 2300;
						territory.getOwner().acc.deposit(pay2);
						GGUI.setGUIBalance(territory.getOwner().acc.getBalance(), territory.getOwner().toString());
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
				else if (fleet.getOwner() != player && fleet.getOwner() != null)
				{
					pay = 2 * fleet.getRent();
					player.acc.deposit(-pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
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
				else if (fleet.getOwner() != player && fleet.getOwner() != null)
				{
					pay = 2 * fleet.getRent();
					player.acc.deposit(-pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
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
				else if (fleet.getOwner() != player && fleet.getOwner() != null)
				{
					pay = 2 * fleet.getRent();
					player.acc.deposit(-pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
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
				else if (fleet.getOwner() != player && fleet.getOwner() != null)
				{
					pay = 2 * fleet.getRent();
					player.acc.deposit(-pay);
					fleet.getOwner().acc.deposit(pay);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(fleet.getOwner().acc.getBalance(), fleet.getOwner().toString());
				}
			}
			break;
		case 6:
			//Oliepriserne er steget
			for(int i = 0; i>40; i++)
			{
				GField field = gb.getField(i);
				if (field.getType() == "Territory")
				{
					GTerritory territory = (GTerritory) field;
					if(territory.isOwned() == true)
					{
						int pay2 = territory.getHouseCount() * 500 + territory.getHotelCount() * 2000;
						territory.getOwner().acc.deposit(pay2);
						GGUI.setGUIBalance(territory.getOwner().acc.getBalance(), territory.getOwner().toString());
					}
				}
			}
			break;
		}
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub

	}

}
