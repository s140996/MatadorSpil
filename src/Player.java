public class Player {
	
	private String name;
	private int worth;
	private int position;
	private int prisonCard;
	
	public Account acc = new Account(30000);
	
	public Player(String name, int worth, int position, int prisonCard)
	{
		this.name = name;
		this.worth = worth;
		this.position = position;
		this.prisonCard = prisonCard;
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
	
	public void setPrisonCard()
	{
		
	}
	
	


}


