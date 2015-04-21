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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll) {
		// TODO Auto-generated method stub

		if (super.getOwner() == null) {

			if (player.acc.getBalance() > getPrice()) {

				boolean reply = GGUI.boolButton("Vil du købe grunden?",
						"Køb grunden", "Nej tak");
				if (reply == true) {

					player.acc.setBalance(player.acc.getBalance() - getPrice());
					super.setOwner(player);

				}

			}
		}

		else if (super.getOwner() == player)

		{

			if (player.acc.getBalance() > houseRent) {

				boolean reply = GGUI.boolButton(
						"Du ejer grunden. Vil du købe et hus på den?",
						"Køb huset", "Nej tak");
				if (reply == true) {

					player.acc.setBalance(player.acc.getBalance() - houseRent);
					GGUI.setHouses(id, houseCount = 1); 

				}

			}

		}

		else if (houseCount == 1) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har et hus på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.setBalance(player.acc.getBalance() - houseRent2);
				GGUI.setHouses(id, houseCount = 2);

			}

		}

		else if (houseCount == 2) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har to huse på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.setBalance(player.acc.getBalance() - houseRent3);
				GGUI.setHouses(id, houseCount = 3);
			}

		}

		else if (houseCount == 3) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har tre huse på den. Vil du købe et hus mere på grunden?",
							"Køb et hus mere", "Nej tak");
			if (reply == true) {

				player.acc.setBalance(player.acc.getBalance() - houseRent4);
				GGUI.setHouses(id, houseCount = 4);

			}

		}

		else if (houseCount == 4) {

			boolean reply = GGUI
					.boolButton(
							"Du ejer grunden og du har fire huse på den. Vil du købe et hotel på grunden?",
							"Køb et hotel", "Nej tak");
			if (reply == true) {

				player.acc.setBalance(player.acc.getBalance() - hotelRent);
				GGUI.setHouses(id, houseCount = 3);
				GGUI.setHotel(id, hasHotel = true);
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
