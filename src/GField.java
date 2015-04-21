import java.awt.Color;

public abstract class GField {
	
	private String name;
	public int id;
	private String type;
	private Color color;
	
	public abstract void landOnField(Player player, GUIController GGUI, ChanceCardList cc, int lastRoll);
	public abstract void removeOwner(Player player, int fieldnumber, GUIController GGUI);
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	

}
