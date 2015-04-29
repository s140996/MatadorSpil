package Fields;
import java.awt.Color;

import desktop_resources.GUI;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class GTax extends GField {

	private int baseTax;
	private double percentageTax;
	
	public GTax (int id, String name, int baseTax, double percentageTax)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Tax");
		super.setColor(new Color(255, 179, 0));
		this.baseTax = baseTax;
		this.percentageTax = percentageTax;
	}
	
	public int getBasetax()
	{
		return baseTax;
	}
	
	public double getPercentageTax()
	{
		return this.percentageTax;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		
		double pay = 0;
		
		if (this.percentageTax != 0)
		{
			boolean replay = GGUI.boolButton("Hvor meget vil du betale?", this.percentageTax + "% af samlet værdi", "" + this.baseTax  + ",-");
			
			if(replay == true)
			{
				pay = player.getWorth() * this.percentageTax / 100;
				
				player.acc.withdraw((int) pay);
			}
			else if(replay == false)
			{
				if(player.acc.getBalance() < this.baseTax)
				{
					GUI.showMessage("Du har ikke råd til at betale alm. skat, betal derfor 10% af din samlede værdi");
					pay = player.getWorth() * this.percentageTax / 100;
					
					player.acc.withdraw((int) pay);
				}
				else
				{
				pay = this.baseTax;
				player.acc.withdraw((int) pay);
				}
			}
		}
		else
		{
			pay = this.baseTax;
			player.acc.withdraw((int) pay);
		}
		
		GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
		GGUI.showMessage("Det betyder, at du skal betale " + (int) pay  + ",-");
	}
	

}
