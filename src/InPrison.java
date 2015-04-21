
public class InPrison {


	public void inPrison(Player player, Cup cup, GUIController gui)
	{
		gui.showMessage("Det er " + player.toString() + "'s tur!");
		if (player.getPrisonCard() > 0)
		{
			gui.showMessage("Du har brugt din benådning fra fænslet, du er nu fri igen!");
			player.setPrisonCard(player.getPrisonCard()-1);
			player.setConvict(false);
		}
		else
		{
			boolean reply = gui.boolButton("Betal eller Kast terninger?", "Betal", "Kast");
			if(reply == true)
			{	
				player.acc.setBalance(player.acc.getBalance()-1000);
				gui.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setConvict(false);
			}
			else{
				for(int i = 0; i < 3 && player.getConvict() == true; i++)
				{
					cup.roll();
					gui.setDice(cup.getDieOne(), cup.getDieTwo());
					if(cup.getDieOne() == cup.getDieTwo())
					{
						player.setConvict(false);
					}
					else
					{
						gui.showMessage("Du slog ikke to ens. Prøv igen!");
					}
				}
			}
		}
	}
}
