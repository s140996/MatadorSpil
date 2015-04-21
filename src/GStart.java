import java.awt.Color;


public class GStart extends GField {

	public GStart (int id)
	{
		super.setID(id);
		super.setType("Start");
		super.setColor(Color.black);
		super.setName("Start");
	}
	
	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

}
