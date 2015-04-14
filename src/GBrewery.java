
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
	public void landOnField(Player player, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return this.rent;
	}
	
}
