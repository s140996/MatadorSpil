
public class GBrewery extends GOwnable {

	private int rent;

	public GBrewery (int id, String name, int price, int rent)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Brewery");
		super.setPrice(price);
		this.rent = rent;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {

		if(super.isOwned() == false)
		{
			boolean reply = GGUI.boolButton("Vil du købe bryggeriet?", "Ja", "Nej");

			if(reply == true)
			{
				super.setOwner(player);
				GGUI.setOwner(super.getID(), player.toString());
				player.acc.deposit(-super.getPrice());
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setBrewerysOwned(player.getBrewerysOwned()+1);
			}

			else if(reply == false)
			{
				GGUI.showMessage("Du valgte ikke at købe bryggeriet");
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
					GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
				}

				else 
				{
					GGUI.showMessage("Velkommen til bryggeriet, betal ejeren for drikkevarerne!");
					int multi = super.getOwner().getBrewerysOwned();

					int pay = multi * getRent() * cup.getLastRoll();

					player.acc.deposit(-pay);
					super.getOwner().acc.deposit(pay);

					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
				}
			}

		}

	}



	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {

		super.setOwner(null);
		player.setBrewerysOwned(player.getBrewerysOwned()-1);
		GGUI.removeOwner(fieldnumber);

	}

	@Override
	public int getRent() {
		return this.rent;
	}

}
