import java.awt.Color;

public class GFleet extends GOwnable {

	private int rent;

	public GFleet (int id, String name, int price, int rent)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Fleet");
		super.setPrice(price);
		this.rent = rent;
		super.setColor(new Color(185, 236, 252));
	}

	@Override
	public int getRent() {

		if (super.getOwner().getFleetsOwned() == 1)
		{
			rent = 500;
		}

		else if (super.getOwner().getFleetsOwned() == 2)
		{
			rent = 1000;
		}

		if (super.getOwner().getFleetsOwned() == 3)
		{
			rent = 2000;
		}

		if (super.getOwner().getFleetsOwned() == 4)
		{
			rent = 4000;
		}

		return rent;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll, GameBoard gb) {

		if (super.isOwned() == false)
		{
			boolean reply = GGUI.boolButton("Vil du købe Færgen?", "Ja", "Nej");

			if(reply == true)
			{
				super.setOwner(player);
				GGUI.setOwner(super.getID(), player.toString());
				player.acc.deposit(-super.getPrice());
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setFleetsOwned(player.getFleetsOwned()+1);
			}
			else if(reply == false)
			{
				GGUI.showMessage("Du valgte ikke at købe færgen");
			}
		}

		else if(super.isOwned() == true)
		{
			if(super.getOwner() == player)
			{
				GGUI.showMessage("Du ejer den selv!");
			}

			else
			{
				if (super.getOwner().getConvict() == true)
				{

				}

				else 
				{
					player.acc.deposit(-getRent());
					super.getOwner().acc.deposit(getRent());

					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
				}
			}	
		}

	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {

		super.setOwner(null);
		player.setFleetsOwned(player.getFleetsOwned()-1);
		GGUI.removeOwner(fieldnumber);

	}

}
