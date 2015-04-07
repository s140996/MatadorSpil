
public class ChanceCard {

	private String text;
	private int money;
	private int position;
	
	public ChanceCard (String text, int money, int position)
	{
		this.text = text;
		this.money = money;
		this.position = position;
	}
	
	public int getMoney()
	{
		return this.money;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public String toString()
	{
		return this.text;
	}
	
}
