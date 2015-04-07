public class Player {
	
	private String name;
	private int worth;
	private int position;
	
	public Account acc = new Account(30000);
	
	public Player(String name, int worth, int position)
	{
		this.name = name;
		this.worth = worth;
		this.position = position;
	}
	
	public void setPosition(int newPosition)
	{
		this.position = newPosition;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public void changePosition(int diceRoll)
	{
		this.position = this.position + diceRoll;
		
		if (this.position > 40)
		{
			this.position = this.position - 40;
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getWorth()
	{
		return this.worth;
	}
	
	public void setWorth(int worth)
	{
		this.worth = worth;
	}
	
}


