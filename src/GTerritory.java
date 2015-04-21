import java.awt.Color;

public class GTerritory extends GOwnable {

	int rent;
	int buildPrice;
	int houseRent;
	int houseRent2;
	int houseRent3;
	int houseRent4;
	int hotelRent;
	int pawn;
	int buildpawn;
	int houseCount;
	boolean hasHotel;

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
		return 0;
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll, GameBoard gb) {
		// TODO Auto-generated method stub

		if (super.getOwner() == null) {

			if (player.acc.getBalance() > getPrice()) {

				boolean reply = GGUI.boolButton("Vil du købe grunden?",
						"Køb grunden", "Nej tak");
				if (reply == true) {

					player.acc.deposit(-getPrice());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.setOwner(player);
					GGUI.setOwner(id, player.toString());

				}

			}
		}

		else if (super.getOwner() == player)

		{
			
		 if (getSimilarCount(gb)) //pladsfylder
			{

			if (player.acc.getBalance() > this.buildPrice) {

				boolean reply = GGUI.boolButton(
						"Du ejer grunden. Vil du købe et hus på den?",
						"Køb huset", "Nej tak");
				if (reply == true) {

					player.acc.deposit(-buildPrice);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setHouses(id, houseCount = 1); 

				}

			}

		}

		
		
		
		if (houseCount == 1) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har et hus på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 2);

			}

		}

		else if (houseCount == 2) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har to huse på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 3);
			}

		}

		else if (houseCount == 3) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har tre huse på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 4);

			}

		}

		else if (houseCount == 4) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har fire huse på den. Vil du købe et hotel på grunden?",
							"Køb et hotel", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHotel(id, hasHotel = true);
			}
		}
		
		}
		
		
		else if (super.getOwner() != null && super.getOwner() != player)
			
		{
			if (houseCount == 0)
			{	
					GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og skal derfor betale " + rent);
					player.acc.setBalance(player.acc.getBalance() - rent);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(rent);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
					
			}
			
			if (houseCount == 1)
			{
				
				GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og vedkommende har et hus p� grunden, du skal derfor betale " + rent + " i husleje");
				player.acc.deposit(-houseRent);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				
			}
			
			if (houseCount == 2)
			{
				
				GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og vedkommende har to huse p� grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent2);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent2);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 3)
			{
				
				GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og vedkommende har tre huse p� grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent3);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent3);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 4)
			{
				
				GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og vedkommende har fire huse p� grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent3);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent3);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 5)
			{
				
				GGUI.showMessage("Du er landet p� et felt der er ejet af en anden spiller og vedkommende har et hotel p� grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-hotelRent);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(hotelRent);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				// test
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

	public boolean getSimilarCount(GameBoard gb)
	{
		int count = 0;
		
		for(int i = 0; i > 40; i++)
			{
			GField field = gb.getField(i);
			if(field.getType() == "Territory")
			{
				GTerritory territory = (GTerritory) field;
				if(territory.getColor().equals(super.getColor()) && territory.getOwner() != null && territory.getOwner().equals(super.getOwner()))
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
			if (super.getColor() == new Color(77, 77, 242) || super.getColor() == new Color(168, 53, 137))
			{
				return true;
			}
		}
		
		return false;
	}
	
}
