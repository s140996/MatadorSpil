package Fields;
import Die.Cup;
import Game.GUIController;
import Player.Player;


public class InPrison {


	public void inPrison(Player player, Cup cup, GUIController gui)
	{
		gui.showMessage("Det er " + player.toString() + "'s tur!");
		if (player.getPrisonCard() > 0)
		{
			gui.showMessage("Du har brugt din benådning fra fængslet, du er nu fri igen!");
			player.setPrisonCard(player.getPrisonCard()-1);
			player.setConvict(false);
		}
		else
		{
			if(player.acc.getBalance() > 1000)
			{
			boolean reply = gui.boolButton("Betal 1000,- for din frihed eller kast terningerne og prøv lykken?", "Betal", "Kast");
			if(reply == true)
			{	
				player.acc.withdraw(1000);
				gui.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setConvict(false);
			}
			else{
				for(int i = 2; i >= 0 && player.getConvict() == true; i--)
				{
					cup.roll();
					gui.setDice(cup.getDieOne(), cup.getDieTwo());
					if(cup.getDieOne() == cup.getDieTwo())
					{
						player.setConvict(false);
						gui.showMessage("Du slog to ens og er løsladt!");
					}
					else
					{
						gui.showMessage("Du har " + i + " forsøg tilbage!");
					}
				}
			}
			}
			else
			{
				gui.showMessage("Du har ikke råd til at betale dig ud, prøv i stedet lykken med terningerne!");
				for(int i = 2; i >= 0 && player.getConvict() == true; i--)
				{
					cup.roll();
					gui.setDice(cup.getDieOne(), cup.getDieTwo());
					if(cup.getDieOne() == cup.getDieTwo())
					{
						player.setConvict(false);
						gui.showMessage("Du slog to ens og er løsladt!");
					}
					else
					{
						gui.showMessage("Du har " + i + " forsøg tilbage!");
					}
				}
			}
		}
	}
}
