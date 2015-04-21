
public class GGoPrison extends GField{

	public GGoPrison(int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("GoPrison");
	}
	
	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) 
	{
		GGUI.showMessage("Desværre, du skal i fængsel!!");
		player.setPosition(11);
		player.setConvict(true);
		GGUI.moveCar(11, player.toString());
		cup.resetDoubleRoll();
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

	
	
}
