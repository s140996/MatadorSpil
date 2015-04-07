
public class ChanceCard {

	private String text;
	private int cash;
	private int position;
	
	public ChanceCard (String text, int cash, int position)
	{
		this.text = text;
		this.cash = cash;
		this.position = position;
	}
	
	public int getCash()
	{
		return this.cash;
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
