import java.awt.*;
import java.util.*;


public class GChance extends GField {

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
			if(c.toString() == "Du benådes for fængsel")
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
			int pay = player.getHouseCount() * 500 + player.getHotelCount() * 2300;
			player.acc.deposit(-pay);
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			//Mangler at alle andre spillere også skal betale for deres huse og hoteller
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
				gb.getField(6 - 1).landOnField(player, GGUI, cc, cup, gb);

				GField field = gb.getField(6 - 1);
				GFleet fleet = (GFleet) field;

				if (fleet.getOwner() != player)
				{
					gb.getField(6 - 1).landOnField(player, GGUI, cc, cup, gb);
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
				gb.getField(16 - 1).landOnField(player, GGUI, cc, cup, gb);

				GField field = gb.getField(16 - 1);
				GFleet fleet = (GFleet) field;

				if (fleet.getOwner() != player)
				{
					gb.getField(16 - 1).landOnField(player, GGUI, cc, cup, gb);
				}

			}
			else if(player.getPosition() > 16 && player.getPosition() < 26)
			{
				player.setPosition(26);
				GGUI.moveCar(26, player.toString());
				gb.getField(26 - 1).landOnField(player, GGUI, cc, cup, gb);

				GField field = gb.getField(26 - 1);
				GFleet fleet = (GFleet) field;

				if (fleet.getOwner() != player)
				{
					gb.getField(26 - 1).landOnField(player, GGUI, cc, cup, gb);
				}

			}
			else if(player.getPosition() > 26 && player.getPosition() < 36)
			{
				player.setPosition(36);
				GGUI.moveCar(36, player.toString());
				gb.getField(36 - 1).landOnField(player, GGUI, cc, cup, gb);

				GField field = gb.getField(36 - 1);
				GFleet fleet = (GFleet) field;

				if (fleet.getOwner() != player)
				{
					gb.getField(36 - 1).landOnField(player, GGUI, cc, cup, gb);
				}

			}
			break;
		case 6:
			//Oliepriserne er steget
			int pay2 = player.getHouseCount() * 500 + player.getHotelCount() * 2000;
			player.acc.deposit(-pay2);
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			break;
		}
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub

	}

}
