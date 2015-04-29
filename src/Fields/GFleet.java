package Fields;
import java.awt.Color;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class GFleet extends GOwnable {

	private int rent;

	public GFleet (int id, String name, int price, int rent)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Fleet");
		super.setPrice(price);
		super.setColor(new Color(185, 236, 252));
		super.setPawn(false);
		this.rent = rent;
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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {

		if (super.getPawn() == true && super.getOwner() == player)
		{
			boolean reply = GGUI.boolButton("Vil du købe din pantsatte grund tilbage?", "Ja", "Nej");
			if (reply == true)
			{
				player.acc.withdraw(getPrice() / 2);
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
			if (super.isOwned() == false)
			{
				boolean reply = GGUI.boolButton("Vil du købe Færgen?", "Ja", "Nej");

				if(reply == true)
				{
					super.setOwner(player);
					GGUI.setOwner(super.getID(), player.toString());
					player.acc.withdraw(super.getPrice());
					player.setWorth(super.getPrice());
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
						GGUI.showMessage(getOwner() + " sidder i fængslel og kan ikke modtage betaling!");
					}

					else 
					{
						GGUI.showMessage("Du er landet på en Færge der er ejet af " + getOwner() + ", betal billetten på " + getRent() + ",-");
						
						if(player.acc.getBalance() < getRent())
						{
							getOwner().acc.deposit(player.acc.getBalance());
							GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
							player.acc.setBalance(0);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
						}
						
						else if(player.acc.getBalance() > getRent())
						{
							player.acc.withdraw(getRent());
							super.getOwner().acc.deposit(getRent());
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
						}
						
					}
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
