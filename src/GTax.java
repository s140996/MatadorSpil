import java.awt.Color;

public class GTax extends GField {

	private int baseTax;
	private double percentageTax;
//	private String owner;
	
	public GTax (int id, String name, int baseTax, double percentageTax)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Tax");
		super.setColor(new Color(255, 179, 0));
		this.baseTax = baseTax;
		this.percentageTax = percentageTax;
	}
	
	public int getBasetax()
	{
		return baseTax;
	}
	
	public double getPercentageTax()
	{
		return this.percentageTax;
	}
	
	//public int getPercentageTax(int playerWorth) {
		
	
		
	

	@Override
	public void landOnField(Player player, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	//	if (owner != null)
	//	{
			
	//		owner = null;
			
	//	}
	}
	
	

}
