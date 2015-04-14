
public class InPrison {


	public void inPrison(Player player, Cup cup)
	{
		if (player.getPrisonCard() > 0)
		{
			player.setPrisonCard(player.getPrisonCard()-1);
			player.setConvict(false);
		}
		else if (player.acc.getBalance() > 1000)
		{
			prisonChoiceButtons
			if(prisonChoiceButtons = true){
				player.acc.setBalance(player.acc.getBalance()-1000);
				player.setConvict(false);
			}
			else{
				int i = 0;
				while(i < 3 && player.getConvict() == true){
					cup.roll();
					if(cup.getDoubleRoll() > 0){
						player.setConvict(false);
					}
					i++;
				}
			}
		}





	}





}
