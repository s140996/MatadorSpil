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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll, GameBoard gb) {
		
		ChanceCard c = cc.draw();
		
		GGUI.displayChanceCard(c.toString());
		
		switch(c.getType())
		{
		case 1: 
			player.acc.deposit(c.getCash());
			GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
			
			if(c.getPosition() > 0)
			{
				if(c.getPosition() == 1)
				{
					player.setPosition(1);
					GGUI.moveCar(1, player.toString());
				}
				if(c.getPosition() == 3 || c.getPosition() == -3)
				{
					player.changePosition(player.getPosition() + c.getPosition(), GGUI);
					GGUI.moveCar(player.getPosition(), player.toString());
					if(player.getPosition() > 37 && c.getPosition() != -3)
					{
						
					}
				}
				if(c.getPosition() > 3 && c.getPosition() != 11)
				{
					player.setPosition(c.getPosition());
					GGUI.moveCar(player.getPosition(), player.toString());
				}
				if(c.getPosition() == 11)
				{
					if(c.getPosition() > 11)
					{
						player.acc.deposit(-4000);
					}
					player.setPosition(c.getPosition());
					GGUI.moveCar(player.getPosition(), player.toString());
					
					
				}
				if(c.toString() == "Benådes for fængsel")
				{
					if(player.getPrisonCard() == 1)
					{
						player.setPrisonCard(2);
					}
					else
					{
						player.setPrisonCard(1);	
					}
					
					GGUI.displayChanceCard(c.toString());
				}
			}
			break;
		case 2:
			if(player.getWorth() <= 15000)
			{
				player.acc.deposit(40000);
			}
			
			break;
		case 3:
			// ejendomsskat
			int pay = player.getHouseCount() * 500 + player.getHotelCount() * 2300;
			player.acc.deposit(-pay);
			//Mangler at alle andre spillere også skal betale for deres huse og hoteller
			break;
		case 4:
			// nubers of players *
			player.acc.deposit(200 );
			//De andre spillere skal også betale
			// fødselsdag
			break;
		case 5:
			if(c.getPosition() > 36 || c.getPosition() < 6)
			{
				player.setPosition(6);
				
				gb.getField(6 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				gb.getField(6 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				GGUI.moveCar(6, player.toString());
				if(c.getPosition() > 36)
				{
					player.acc.deposit(4000);
				}
			}
			else if(c.getPosition() > 6 && c.getPosition() < 16)
			{
				player.setPosition(16);
				gb.getField(16 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				gb.getField(16 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				GGUI.moveCar(16, player.toString());
			}
			else if(c.getPosition() > 16 && c.getPosition() < 26)
			{
				player.setPosition(26);
				gb.getField(26 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				gb.getField(26 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				GGUI.moveCar(26, player.toString());
			}
			else if(c.getPosition() > 26 && c.getPosition() < 36)
			{
				player.setPosition(36);
				gb.getField(36 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				gb.getField(36 - 1).landOnField(player, GGUI, cc, lastRoll, gb);
				GGUI.moveCar(36, player.toString());
			}
			break;
		case 6:
			int pay2 = player.getHouseCount() * 500 + player.getHotelCount() * 2000;
			player.acc.deposit(-pay2);
			break;
		}
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}
	
}
