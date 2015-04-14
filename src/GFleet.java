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
