public class GPrison extends GField {
	
	public GPrison (int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Prison");
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
