
public class InPrison {


	public void inPrison(Player player, Cup cup, GUIController gui)
	{
		if (player.getPrisonCard() > 0)
		{
			gui.showMessage("Du har brugt din benådning fra fænslet, du er nu fri igen!");
			player.setPrisonCard(player.getPrisonCard()-1);
			player.setConvict(false);
		}
		else
		{
			boolean reply = gui.boolButton("Betal eller Kast terninger?", "Betal", "Kast");
			if(reply == true){
				
				player.acc.setBalance(player.acc.getBalance()-1000);
				gui.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setConvict(false);
			}
			else{
				int i = 0;
				while(i < 3 && player.getConvict() == true){
					cup.roll();
					gui.setDice(cup.getDieOne(), cup.getDieTwo());
					if(cup.getDoubleRoll() > 0)
					{
						player.setConvict(false);
					}
					i++;
				}
			}
		}





	}





}
