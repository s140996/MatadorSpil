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

				boolean reply = GGUI.boolButton("Vil du k√∏be grunden?",
						"K√∏b grunden", "Nej tak");
				if (reply == true) {

					player.acc.deposit(-getPrice());
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.setOwner(player);
				

				}

			}
		}

		else if (super.getOwner() == player)

		{

			if (player.acc.getBalance() > houseRent) {

				boolean reply = GGUI.boolButton(
						"Du ejer grunden. Vil du k√∏be et hus p√• den?",
						"K√∏b huset", "Nej tak");
				if (reply == true) {

					player.acc.deposit(-buildPrice);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					GGUI.setHouses(id, houseCount = 1); 

				}

			}

		}

		else if (houseCount == 1) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har et hus p√• den. Vil du k√∏be et hus mere p√• grunden?",
							"K√∏b et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 2);

			}

		}

		else if (houseCount == 2) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har to huse p√• den. Vil du k√∏be et hus mere p√• grunden?",
							"K√∏b et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 3);
			}

		}

		else if (houseCount == 3) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har tre huse p√• den. Vil du k√∏be et hus mere p√• grunden?",
							"K√∏b et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHouses(id, houseCount = 4);

			}

		}

		else if (houseCount == 4) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har fire huse p√• den. Vil du k√∏be et hotel p√• grunden?",
							"K√∏b et hotel", "Nej tak");
			if (reply == true) {

				player.acc.deposit(-buildPrice);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				GGUI.setHotel(id, hasHotel = true);
			}
		}
		
		else if (super.getOwner() != null && super.getOwner() != player)
			
		{
			if (houseCount == 0)
			{	
					GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og skal derfor betale" + rent);
					player.acc.setBalance(player.acc.getBalance() - rent);
					GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
					super.getOwner().acc.deposit(rent);
					GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
					
			}
			
			if (houseCount == 1)
			{
				
				GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og vedkommende har et hus pÂ grunden, du skal derfor betale " + rent + " i husleje");
				player.acc.deposit(-houseRent);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
				
			}
			
			if (houseCount == 2)
			{
				
				GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og vedkommende har to huse pÂ grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent2);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent2);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 3)
			{
				
				GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og vedkommende har tre huse pÂ grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent3);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent3);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 4)
			{
				
				GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og vedkommende har fire huse pÂ grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-houseRent3);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(houseRent3);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
			}
			
			if (houseCount == 5)
			{
				
				GGUI.showMessage("Du er landet pÂ et felt der er ejet af en anden spiller og vedkommende har et hotel pÂ grunden, du skal derfor betale " + houseRent + " i husleje");
				player.acc.deposit(-hotelRent);
				GGUI.setGUIBalance(player.acc.getBalance(), player.toString());
				super.getOwner().acc.deposit(hotelRent);
				GGUI.setGUIBalance(getOwner().acc.getBalance(), getOwner().toString());
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

}
