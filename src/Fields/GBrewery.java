package Fields;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class GBrewery extends GOwnable {

	private int rent;

	public GBrewery (int id, String name, int price, int rent)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Brewery");
		super.setPrice(price);
		super.setPawn(false);
		this.rent = rent;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		if (super.getPawn() == true && super.getOwner() == player)
		{
			boolean reply = GGUI.boolButton("Vil du købe din pantsatte grund tilbage?", "Ja", "Nej");
			if (reply == true)
			{
				player.acc.deposit(-getPrice() / 2);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				player.setWorth(super.getPrice());
				super.setPawn(false);
				GGUI.showMessage("Du har tilbagekøbt din pantsatte grund for " + super.getPrice() / 2 + ",-");
			}
		}
		else if (super.getPawn() == true)
		{
			GGUI.showMessage("Dette felt er pantsat af " + player.toString() + ", så du skal ikke betale til ejeren.");
		}
		else
		{
			if(super.isOwned() == false)
			{
				boolean reply = GGUI.boolButton("Vil du købe bryggeriet?", "Ja", "Nej");

				if(reply == true)
				{
					super.setOwner(player);
					GGUI.setOwner(super.getID(), player.toString());
					player.acc.deposit(-super.getPrice());
					player.setWorth(super.getPrice());
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
						GGUI.showMessage(getOwner() + " sidder i fængsel og kan ikke modtage betaling!");
					}

					else 
					{
						int multi = super.getOwner().getBrewerysOwned();

						int pay = multi * getRent() * cup.getLastRoll();

						GGUI.showMessage("Velkommen til bryggeriet, betal: " + pay + ",- til " + getOwner() + " for drikkevarerne!");

						player.acc.deposit(-pay);
						super.getOwner().acc.deposit(pay);

						GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
						GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
					}
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
