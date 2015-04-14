public class Player {
	
	private String name;
	private int worth;
	private int position;
	private int prisonCard;
	private boolean convict;
	private boolean alive;
	
	public Account acc = new Account(30000);
	
	public Player(String name, int worth, int position, int prisonCard, boolean convict, boolean alive)
	{
		this.name = name;
		this.worth = worth;
		this.position = position;
		this.prisonCard = prisonCard;
		this.convict = false;
		this.alive = true;
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
			this.acc.deposit(4000);
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public int getWorth()
	{
		return this.worth;
	}
	
	public void setWorth(int worth)
	{
		this.worth = worth;
	}
	
	public int getPrisonCard()
	{
		return prisonCard;
	}
	
	public void setPrisonCard(int prisonCard)
	{
		this.prisonCard = prisonCard;
	}
	
	public void setConvict(boolean convict)
	{
		this.convict = convict;
	}
	
	public boolean getConvict()
	{
		return convict;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	
	
}


