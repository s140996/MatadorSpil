import java.awt.Color;

public class GTerritory extends GOwnable {

	private int rent;
	private int buildPrice;
	private int houseRent;
	private int houseRent2;
	private int houseRent3;
	private int houseRent4;
	private int hotelRent;
	private int pawn;
	private int buildpawn;
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
		this.rent = rent;
		this.buildPrice = buildPrice;
		this.houseRent = houseRent;
		this.houseRent2 = houseRent2;
		this.houseRent3 = houseRent3;
		this.houseRent4 = houseRent4;
		this.hotelRent = hotelRent;
		this.pawn = price / 2;
		this.buildpawn = buildPrice / 2;
	}

	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return this.rent;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		// TODO Auto-generated method stub

		if (super.getOwner() == null) 
		{
			boolean reply = GGUI.boolButton("Vil du købe grunden?", "Køb grunden", "Nej tak");

			if (reply == true) 
			{
				player.acc.deposit(-getPrice());
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.setOwner(player);
				GGUI.setOwner(id, player.toString());
				player.setWorth(super.getPrice());
			}
		}
		else if (super.getOwner() == player)
		{
			GGUI.showMessage("Du ejer denne grund!");

			if (checkFields(gb) == true)
			{
				if (houseCount == 0)
				{
					boolean reply = GGUI.boolButton("Da du ejer alle grundene, kan du købet hus. Vil du det?", "Køb huset", "Nej tak");

					if (reply == true) 
					{
						player.acc.deposit(-buildPrice);
						GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
						this.houseCount++;
						super.getOwner().setHotelCount(1);
						GGUI.setHouses(id, this.houseCount);
						player.setWorth(this.buildPrice);
					}
				}

				boolean run = true;

				while (run == true) {
					if (0 < houseCount && houseCount < 4)
					{
						boolean reply = GGUI.boolButton("Vil du købe endnu et hus på denne grund?", "Køb huset", "Nej tak");

						if (reply == true) 
						{
							player.acc.deposit(-this.buildPrice);
							GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
							this.houseCount++;
							super.getOwner().setHouseCount(1);
							GGUI.setHouses(this.id, this.houseCount);
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

				if (checkHouse(gb) == true && this.hasHotel == false)
				{
					boolean reply = GGUI.boolButton("Du har bygget 4 huse på alle grundene. Vil du købe et hotel på denne?", "Køb hotellet", "Nej tak");

					if (reply == true) 
					{
						player.acc.deposit(-buildPrice);
						GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
						super.getOwner().setHotelCount(1);
						super.getOwner().setHouseCount(-4);
						this.hasHotel = true;
						GGUI.setHotel(this.id, this.hasHotel);
						player.setWorth(this.buildPrice);
					}
				}

			}
		}

		else if (super.isOwned() == true)
		{
			if (super.getOwner().getConvict() == true)
			{
				GGUI.showMessage("Ejeren sidder i fængsel og kan ikke modtage betaling!");
			}
			else 
			{
				if (this.hasHotel == true)
				{
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og vedkommende har et hotel på grunden, du skal derfor betale " + this.hotelRent + " i husleje");
					player.acc.deposit(-this.hotelRent);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(this.hotelRent);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
				else if (houseCount == 4)
				{
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og vedkommende har fire huse på grunden, du skal derfor betale " + this.houseRent4 + " i husleje");
					player.acc.deposit(-this.houseRent4);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(this.houseRent4);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
				else if (houseCount == 3)
				{
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og vedkommende har tre huse på grunden, du skal derfor betale " + this.houseRent3 + " i husleje");
					player.acc.deposit(-this.houseRent3);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(this.houseRent3);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
				else if (houseCount == 2)
				{
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og vedkommende har to huse på grunden, du skal derfor betale " + this.houseRent2 + " i husleje");
					player.acc.deposit(-this.houseRent2);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(this.houseRent2);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
				else if (houseCount == 1)
				{
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og vedkommende har et hus på grunden, du skal derfor betale " + this.houseRent + " i husleje");
					player.acc.deposit(-this.houseRent);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(this.houseRent);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
				else if (houseCount == 0)
				{	
					GGUI.showMessage("Du er landet på et felt der er ejet af en anden spiller og skal derfor betale " + rent);
					player.acc.setBalance(player.acc.getBalance() - rent);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(rent);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				}
			}
		}
	}


	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub

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
				if (territory.getColor().equals(super.getColor()) && territory.getOwner() == super.getOwner() && territory.getHouseCount() == 4)
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

}
