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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc) {
		
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
					player.changePosition(player.getPosition() + c.getPosition());
					GGUI.moveCar(player.getPosition(), player.toString());
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
			
			break;
		case 4:
			
			break;
	
		}
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}
	
}
