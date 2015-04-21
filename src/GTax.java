import java.awt.Color;

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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll) {
		// TODO Auto-generated method stub
		if (this.percentageTax != 0)
		{
			boolean replay = GGUI.boolButton("Hvor meget vil du betale?", this.percentageTax + "% af samlet værdi", "" + this.baseTax);
			
			if(replay == true)
			{
				double pay = player.getWorth() * this.percentageTax / 100;
				
				player.acc.deposit(-(int) pay);
			}
			else if(replay == false)
			{
				player.acc.deposit(-this.baseTax);
			}
		}
		else
		{
			player.acc.deposit(-this.baseTax);
		}
		
		GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		//Her sker der intet, da den ikke kan ejes.
	}
	
	

}
