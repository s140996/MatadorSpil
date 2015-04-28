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
					boolean reply = gui.boolButton("Vil du pantsætte " + ownField.getName() + " og modtag " + ownField.getPrice() / 2 + "?", "Ja", "Nej");
					
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
					boolean reply = gui.boolButton("Vil du pantsætte " + territory.getName() + " og modtag " + territory.getPrice() / 2 + "?", "Ja", "Nej");
					
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
	
	public void pawnBuilding (Player player, GameBoard gb, GUIController gui)
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
					
				}
					
				if (territory.getHotelCount() > 0)
				{
					
					
				}
			}
		}
		
		if (count == 0)
		{
			gui.showMessage("Du har ingen grunde at pantsætte. Bemærk, hvis der er bygget huse eller hoteller på en grund, så skal de sælges før, at den kan pantsættes.");
		}
	}
	
}
