package Fields;
import java.awt.Color;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class GTerritory extends GOwnable {

	private int rent;
	private int buildPrice;
	private int houseRent;
	private int houseRent2;
	private int houseRent3;
	private int houseRent4;
	private int hotelRent;
	private int houseCount;
	private boolean hasHotel;

	public GTerritory(int id, String name, int price, int rent, int buildPrice,
			int houseRent, int houseRent2, int houseRent3, int houseRent4,
			int hotelRent, Color color) {
		super.setID(id);
		super.setName(name);
		super.setType("Territory");
		super.setPrice(price);
		super.setColor(color);
		super.setPawn(false);
		this.rent = rent;
		this.buildPrice = buildPrice;
		this.houseRent = houseRent;
		this.houseRent2 = houseRent2;
		this.houseRent3 = houseRent3;
		this.houseRent4 = houseRent4;
		this.hotelRent = hotelRent;
	}

	@Override
	public int getRent() {
		
		return this.rent;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
	

		if (super.getPawn() == true && super.getOwner() == player && player.acc.getBalance() > getPrice() / 2)
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
			GGUI.showMessage("Dette felt er pantsat af " + super.getOwner() + ", så du skal ikke betale til ejeren.");
		}
		else
		{
			if (super.getOwner() == null && player.acc.getBalance() > getPrice()) 
			{
				boolean reply = GGUI.boolButton("Vil du købe grunden?", "Køb grunden", "Nej tak");

				if (reply == true) 
				{
					player.acc.withdraw(getPrice());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.setOwner(player);
					GGUI.setOwner(super.getID(), player.toString());
					player.setWorth(super.getPrice());
				}
				else
				{
					GGUI.showMessage("Du valgte ikke at købe grunden.");
				}
				
			}
			else if (super.getOwner() == player)
			{
				GGUI.showMessage("Du ejer denne grund!");

				if (checkFields(gb) == true)
				{
					if (houseCount == 0 && player.acc.getBalance() > buildPrice)
					{
						boolean reply = GGUI.boolButton("Da du ejer alle grundene i samme farve, kan du nu købe huse. Vil du det?", "Køb et hus", "Nej tak");

						if (reply == true) 
						{
							player.acc.withdraw(buildPrice);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							this.houseCount++;
							GGUI.setHouses(super.getID(), this.houseCount);
							player.setWorth(this.buildPrice);
						}
					}

					boolean run = true;

					while (run == true) {
						if (0 < houseCount && houseCount < 4 && player.acc.getBalance() > buildPrice)
						{
							boolean reply = GGUI.boolButton("Vil du købe endnu et hus på denne grund?", "Køb et hus", "Nej tak");

							if (reply == true) 
							{
								player.acc.withdraw(this.buildPrice);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								this.houseCount++;
								GGUI.setHouses(super.getID(), this.houseCount);
								player.setWorth(this.buildPrice);
							}
							else if (reply == false)
							{
								run = false;
							}
						}
						else
						{
							run = false;
						}

					}

					if (checkHouse(gb) == true && this.hasHotel == false && player.acc.getBalance() > buildPrice)
					{
						boolean reply = GGUI.boolButton("Du har bygget 4 huse på alle grundene. Vil du købe et hotel på denne?", "Køb hotellet", "Nej tak");

						if (reply == true) 
						{
							player.acc.withdraw(buildPrice);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							this.houseCount = 0;
							this.hasHotel = true;
							GGUI.setHotel(super.getID(), this.hasHotel);
							player.setWorth(this.buildPrice);
						}
					}

				}
			}

			else if (super.isOwned() == true)
			{
				if (super.getOwner().getConvict() == true)
				{
					GGUI.showMessage(super.getOwner().toString() + " sidder i fængsel og kan ikke modtage betaling!");
				}
				else 
				{
					if (checkFields(gb) == true)
					{
						if (this.hasHotel == true)
						{
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og vedkommende har et hotel på grunden, du skal derfor betale " + this.hotelRent + ",- i husleje");
							
							if(player.acc.getBalance() < this.hotelRent)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(this.hotelRent);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(this.hotelRent);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
						}
						else if (houseCount == 4)
						{
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og vedkommende har fire huse på grunden, du skal derfor betale " + this.houseRent4 + ",- i husleje");
							
							if(player.acc.getBalance() < this.houseRent4)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(this.houseRent4);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(this.houseRent4);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
							
							}
						else if (houseCount == 3)
						{
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og vedkommende har tre huse på grunden, du skal derfor betale " + this.houseRent3 + ",- i husleje");
							
							if(player.acc.getBalance() < this.houseRent3)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(this.houseRent3);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(this.houseRent3);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
						}
						else if (houseCount == 2)
						{
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og vedkommende har to huse på grunden, du skal derfor betale " + this.houseRent2 + ",- i husleje");
							
							if(player.acc.getBalance() < this.houseRent2)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(this.houseRent2);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(this.houseRent2);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
						}
						else if (houseCount == 1)
						{
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og vedkommende har et hus på grunden, du skal derfor betale " + this.houseRent + ",- i husleje");
							
							if(player.acc.getBalance() < this.houseRent)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(this.houseRent);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(this.houseRent);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
						}
						else if (houseCount == 0)
						{	
							GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og skal derfor betale " + rent * 2 + ",-");
							
							if(player.acc.getBalance() < rent * 2)
							{
								getOwner().acc.deposit(player.acc.getBalance());
								GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
								player.acc.setBalance(0);
								GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
								GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
							}
							else
							{
							player.acc.withdraw(rent * 2);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							super.getOwner().acc.deposit(rent * 2);
							GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
							}
						}
					}
					else
					{
						GGUI.showMessage("Du er landet på et felt der er ejet af " + super.getOwner().toString() + " og skal derfor betale " + rent + ",-");
						
						if(player.acc.getBalance() < rent)
						{
							getOwner().acc.deposit(player.acc.getBalance());
							GGUI.showMessage("Du har ikke råd til at betale, og er derfor ude af spillet");
							player.acc.setBalance(0);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							GGUI.setGUIBalance(super.getOwner().acc.getBalance(), super.getOwner().toString());
						}
						else
						{
						player.acc.withdraw(rent);
						GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
						super.getOwner().acc.deposit(rent);
						GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
						}
					}
				}
			}
		}
	}


	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		super.setOwner(null);
		GGUI.removeOwner(fieldnumber);
		this.hasHotel = false;
		this.houseCount = 0;
	}

	public int getBuildPrice() {
		return this.buildPrice;
	}

	public boolean checkFields(GameBoard gb)
	{
		int count = 0;

		for(int i = 0; i < 40; i++)
		{
			GField field = gb.getField(i);
			if (field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;
				if (territory.getColor().equals(super.getColor()) && territory.getOwner() == super.getOwner())
				{
					count++;
				}
			}
		}

		if (count == 3)
		{
			return true;
		}
		else if (count == 2)
		{
			if (super.getColor().equals(new Color(77, 77, 242)) || super.getColor().equals(new Color(168, 53, 137)))
			{
				return true;
			}
		}

		return false;
	}

	public boolean checkHouse(GameBoard gb)
	{
		int count = 0;

		for(int i = 0; i < 40; i++)
		{
			GField field = gb.getField(i);
			if (field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;
				if (territory.getColor().equals(super.getColor()) && territory.getOwner() == super.getOwner() && territory.getHouseCount() == 4 || territory.getColor().equals(super.getColor()) && territory.getOwner() == super.getOwner() && territory.getHotelCount() == 1)
				{
					count++;
				}
			}
		}

		if (count == 3)
		{
			return true;
		}
		else if (count == 2)
		{
			if (super.getColor().equals(new Color(77, 77, 242)) || super.getColor().equals(new Color(168, 53, 137)))
			{
				return true;
			}
		}

		return false;
	}

	public int getHouseCount()
	{
		return this.houseCount;
	}

	public int getHotelCount()
	{
		if (this.hasHotel == true)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public void setHouse(int house)
	{
		this.houseCount = this.houseCount + house;
	}
	
	public void setHotel(int hotel)
	{
		if (hotel == 1)
		{
			this.hasHotel = true;
		}
		else
		{
			this.hasHotel = false;
		}
	}
	
	public void removeHotel()
	{
		this.hasHotel = false;
	}
	
	public boolean getHotel()
	{
		return this.hasHotel;
	}

}
