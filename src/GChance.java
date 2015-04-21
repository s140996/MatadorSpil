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

			if(c.getPosition() == 1)
			{
				player.setPosition(1);
				GGUI.moveCar(1, player.toString());
				player.acc.deposit(4000);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			}
			if(c.getPosition() == 3 || c.getPosition() == -3)
			{
				player.changePosition(player.getPosition() + c.getPosition(), GGUI);
				GGUI.moveCar(player.getPosition(), player.toString());
				gb.getField(player.getPosition() - 1).landOnField(player, GGUI, cc, cup, gb);
			}
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
			if(c.getPosition() == 11)
			{
				gb.getField(player.getPosition() - 1).landOnField(player, GGUI, cc, cup, gb);
			}
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
			player.acc.deposit(1000);
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			break;
		case 5:
			if(c.getPosition() > 36 || c.getPosition() < 6)
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
				
				if(c.getPosition() > 36)
				{
					player.acc.deposit(4000);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				}
			}
			else if(c.getPosition() > 6 && c.getPosition() < 16)
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
			else if(c.getPosition() > 16 && c.getPosition() < 26)
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
			else if(c.getPosition() > 26 && c.getPosition() < 36)
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
