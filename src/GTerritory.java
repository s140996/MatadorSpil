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
	
	
	public GTerritory (int id, String name, int price, int rent, int buildPrice, int houseRent, int houseRent2, int houseRent3, int houseRent4, int hotelRent, Color color)
	{
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
	public void landOnField(Player player, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
