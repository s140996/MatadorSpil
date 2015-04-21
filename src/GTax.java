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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		// TODO Auto-generated method stub
		double pay = 0;
		
		if (this.percentageTax != 0)
		{
			boolean replay = GGUI.boolButton("Hvor meget vil du betale?", this.percentageTax + "% af samlet værdi", "" + this.baseTax);
			
			if(replay == true)
			{
				pay = player.getWorth() * this.percentageTax / 100;
				
				player.acc.deposit(-(int) pay);
			}
			else if(replay == false)
			{
				pay = this.baseTax;
				player.acc.deposit(-(int) pay);
			}
		}
		else
		{
			pay = this.baseTax;
			player.acc.deposit(-(int) pay);
		}
		
		GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
		GGUI.showMessage("Det betyder, at du skal betale " + (int) pay);
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		//Her sker der intet, da den ikke kan ejes.
	}
	
	

}
