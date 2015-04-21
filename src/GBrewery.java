
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
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, GameBoard gb) {
		
		if(super.isOwned() == false)
		{
			boolean reply = GGUI.boolButton("Vil du købe grunden?", "Ja", "Nej");
			
				if(reply == true)
				{
					super.setOwner(player);
					GGUI.setOwner(super.getID(), player.toString());
					player.acc.deposit(-super.getPrice());
				}
			}
	
		if(super.isOwned() == true)
		{
			if(super.getOwner() == player)
			{
				GGUI.showMessage("Du ejer den selv!");
			}
			
			else
			{
						
					
					
				}
				
				
			}
		
		
		}
	
	}
		

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRent() {
		
		
		return this.rent;
	}
	
}
